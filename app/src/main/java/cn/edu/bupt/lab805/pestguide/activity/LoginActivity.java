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
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.bean.LoginBean;
import cn.edu.bupt.lab805.pestguide.util.Api;
import cn.edu.bupt.lab805.pestguide.util.UrlContainer;
import cn.edu.bupt.lab805.pestguide.widget.ClearEditText;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    ClearEditText etUserName;
    @BindView(R.id.et_password)
    ClearEditText etPassWord;
    @BindView(R.id.btn_login)
    Button btnLogin;
    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
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
        if (disposable != null && !disposable.isDisposed()) return;
        login(etUserName.getText().toString(), etPassWord.getText().toString());
    }

    private void login(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlContainer.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        api.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        switch (loginBean.getType()) {
                            case success:
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                break;
                            case error:
                                Snackbar.make(btnLogin, loginBean.getContent(), Snackbar.LENGTH_SHORT).show();
                                break;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Snackbar.make(btnLogin, getString(R.string.tip_net_error), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
