package cn.edu.bupt.lab805.pestguide.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.util.ActivityManager;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.btn_logout_setting)
    Button btnLogout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHelper = DBHelper.getInstance();

        btnLogout.setOnClickListener(view -> {
            dbHelper.deleteLogininfo();
            dbHelper.deleteAllDepot();
            dbHelper.deleteAllUser();
            new Thread(() -> startActivity(new Intent(SettingActivity.this, LoginActivity.class))).start();
            ActivityManager.getInstance().exit();
            finish();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
