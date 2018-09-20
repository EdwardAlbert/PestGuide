package cn.edu.bupt.lab805.pestguide.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.adapter.UserListAdapter;
import cn.edu.bupt.lab805.pestguide.application.MyApplication;
import cn.edu.bupt.lab805.pestguide.bean.Result;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.entity.User;
import cn.edu.bupt.lab805.pestguide.util.Api;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;
import cn.edu.bupt.lab805.pestguide.widget.MyItemAnimator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.user_list)
    RecyclerView rvUser;

    private DBHelper dbHelper;
    private Logininfo logininfo;
    private List<User> datas;
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        initDatas();
        initViews();
    }

    private void initViews() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new UserListAdapter(this, datas);
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setItemAnimator(new MyItemAnimator());
        rvUser.setAdapter(adapter);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            updateUserList();
        });
    }

    private void initDatas() {
        dbHelper = DBHelper.getInstance();
        logininfo = dbHelper.queryLogininfo();
        datas = dbHelper.queryUserList();

    }

    /**
     * 更新用户列表
     */
    private void updateUserList() {
        Api api = MyApplication.getInstance().getApi();
        api.getUser(logininfo.getUsername(), logininfo.getPassword())
                .subscribeOn(Schedulers.io())
                .doOnNext(result -> {
                    if (result != null && result.getT() != null) {
                        dbHelper.deleteAllUser();
                        dbHelper.insertListUsers(result.getT());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result != null && result.getT() != null) {
                        datas = result.getT();
                        adapter.setDatas(datas);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
