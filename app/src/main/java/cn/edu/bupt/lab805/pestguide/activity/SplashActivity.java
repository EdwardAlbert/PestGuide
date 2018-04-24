package cn.edu.bupt.lab805.pestguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.application.MyApplication;
import cn.edu.bupt.lab805.pestguide.bean.Result;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.util.Api;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;
import cn.edu.bupt.lab805.pestguide.util.DBcopyUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    @BindView(R.id.splash)
    ImageView splash;

    private DBHelper dbHelper;
    private Logininfo logininfo;

    private String username;
    private String password;

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Observable.empty()
                .subscribeOn(Schedulers.io())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        try {
                            new DBcopyUtils(SplashActivity.this).createDataBase();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        checkUser();
                    }
                })
                .subscribe();
//        checkUser();
    }

    /**
     * 获取数据库内的登录信息
     */
    private void checkUser() {
        dbHelper = DBHelper.getInstance();
        logininfo = dbHelper.queryLogininfo();
        if (logininfo != null) {
            username = logininfo.getUsername();
            password = logininfo.getPassword();
            login(username, password);
        } else {
            enterLogin();
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    private void login(final String username, final String password) {
        Api api = MyApplication.getInstance().getApi();
        api.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Result result) {
                        switch (result.getType()) {
                            case success:
                                enterHome();
                                break;
                            case error:
                                enterLogin();
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        enterLogin();
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }

    /**
     * 进入登录界面
     */
    private void enterLogin() {
        Log.i(TAG, "enterLogin");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);

    }

    /**
     * 进入主界面
     */
    private void enterHome() {
        Log.i(TAG, "enterHome");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
