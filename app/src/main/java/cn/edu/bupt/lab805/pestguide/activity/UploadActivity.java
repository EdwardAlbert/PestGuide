package cn.edu.bupt.lab805.pestguide.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.entity.Depot;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;

public class UploadActivity extends AppCompatActivity {

    @BindView(R.id.srlayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_amount_upload)
    TextView tvUploadAmount;
    @BindView(R.id.tv_amount_depot)
    TextView tvDepotAmount;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private DBHelper dbHelper;
    private Logininfo logininfo;
    private List<Depot> depots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
        initDatas();
        initViews();
    }

    private void initDatas() {
        dbHelper = DBHelper.getInstance();
        logininfo = dbHelper.queryLogininfo();
    }

    private void initViews() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
