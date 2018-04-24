package cn.edu.bupt.lab805.pestguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.edu.bupt.lab805.pestguide.application.MyApplication;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.bean.Result;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.util.Api;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;
import cn.edu.bupt.lab805.pestguide.widget.ClearEditText;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    ClearEditText etUserName;
    @BindView(R.id.et_password)
    ClearEditText etPassWord;
    @BindView(R.id.btn_login)
    Button btnLogin;
    Disposable disposable;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        dbHelper = DBHelper.getInstance();
    }

    @OnClick(R.id.btn_login)
    void onLoginBtnClick() {
        if (TextUtils.isEmpty(etUserName.getText())) {
            etUserName.setError(getString(R.string.request_for_username));
            etUserName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etPassWord.getText())) {
            etPassWord.setError(getString(R.string.request_for_password));
            etPassWord.requestFocus();
            return;
        }
        if (disposable != null) return;
        login(etUserName.getText().toString(), etPassWord.getText().toString());
    }

    private void login(final String username, final String password) {
        Api api = MyApplication.getInstance().getApi();
        try {
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
                                    //写入登录信息
                                    Logininfo logininfo = new Logininfo(null, username, password, null, null);
                                    dbHelper.insertLogininfo(logininfo);
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                    break;
                                case error:
                                    Snackbar.make(btnLogin, result.getContent(), Snackbar.LENGTH_SHORT).show();
                                    break;
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Snackbar.make(btnLogin, getString(R.string.tip_net_error), Snackbar.LENGTH_SHORT).show();
                            disposable = null;
                        }

                        @Override
                        public void onComplete() {
                            disposable.dispose();
                            disposable = null;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        if (disposable != null)
            disposable.dispose();
        super.onDestroy();
    }
}
