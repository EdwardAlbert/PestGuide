package cn.edu.bupt.lab805.pestguide.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.util.DBcopyUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Observable.empty()
                .observeOn(Schedulers.io())
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
                .subscribe();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 2000);
    }
}
