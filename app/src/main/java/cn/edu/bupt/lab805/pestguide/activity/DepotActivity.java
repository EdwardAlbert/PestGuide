package cn.edu.bupt.lab805.pestguide.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.application.MyApplication;
import cn.edu.bupt.lab805.pestguide.bean.Result;
import cn.edu.bupt.lab805.pestguide.entity.Depot;
import cn.edu.bupt.lab805.pestguide.entity.Grain;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.util.Api;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;
import cn.edu.bupt.lab805.pestguide.util.DataUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DepotActivity extends AppCompatActivity {

    private static final String TAG = "DepotActivity";
    private static final int REQUEST_DEPOT_EDIT = 1;

    @BindView(R.id.depot_caution)
    RelativeLayout caution;
    @BindView(R.id.depot_tv_lcmc)
    TextView lcmc_tv;
    @BindView(R.id.depot_tv_capacity)
    TextView capacity_tv;
    @BindView(R.id.depot_tv_depottype)
    TextView depottype_tv;
    @BindView(R.id.depot_tv_grainname)
    TextView grainname_tv;
    @BindView(R.id.depot_tv_clxs)
    TextView clxs_tv;
    @BindView(R.id.depot_tv_innum)
    TextView innum_tv;
    @BindView(R.id.depot_tv_source)
    TextView source_tv;
    @BindView(R.id.depot_tv_indate)
    TextView indate_tv;
    @BindView(R.id.depot_tv_harvestdate)
    TextView harvest_tv;
    @BindView(R.id.depot_tv_reserveperiod)
    TextView reserve_tv;
    @BindView(R.id.depot_bn_actionadd)
    ImageButton imgBtnAction;
    @BindView(R.id.img_delete_grain_depot)
    ImageView imgDeleteGrain;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private DBHelper dbHelper;
    private DataUtil dataUtil;
    private Grain grain;
    private Depot mDepot;
    private Logininfo logininfo;
    private String lcbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depot);
        ButterKnife.bind(this);
        initDatas();
        initViews();
    }

    private void initDatas() {
        lcbm = getIntent().getStringExtra("lcbm");
        dataUtil = DataUtil.getInstance();
        dbHelper = DBHelper.getInstance();
        mDepot = dbHelper.queryDepotByLCBM(lcbm);
        logininfo = dbHelper.queryLogininfo();
        grain = dbHelper.queryGrainByLCBM(lcbm);
        syncGrain(logininfo.getUsername(), logininfo.getPassword(), lcbm);
    }

    private void initViews() {
        toolbar.setTitle(getString(R.string.depot_desc));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lcmc_tv.setText(mDepot.getBinname());
        depottype_tv.setText(mDepot.getTypebin());
        capacity_tv.setText(mDepot.getCapacity().toString() + getString(R.string.ton));
        setData();
    }

    //设置粮情信息
    private void setData() {
        if (grain != null) {
            grainname_tv.setText(grain.getGrainname());
            clxs_tv.setText(grain.getClxs());
            source_tv.setText(grain.getSource());
            indate_tv.setText(grain.getIndate());
            harvest_tv.setText(grain.getHarvestdate());
            if (grain.getInnum() != null) {
                innum_tv.setText(grain.getInnum().toString());
            } else {
                innum_tv.setText("");
            }
            if (grain.getReserveperiod() != null) {
                reserve_tv.setText(grain.getReserveperiod().toString());
            } else {
                reserve_tv.setText("");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.depot_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_edit:
                Intent intent = new Intent(DepotActivity.this, DepotEditActivity.class);
                intent.putExtra("lcbm", lcbm);
                startActivityForResult(intent,REQUEST_DEPOT_EDIT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_DEPOT_EDIT:
                if (resultCode == RESULT_OK)
                    syncGrain(logininfo.getUsername(), logininfo.getPassword(), lcbm);
                break;
        }
    }

    /**
     * 获取粮情信息
     *
     * @param username
     * @param password
     * @param lcbm
     */
    private void syncGrain(String username, String password, final String lcbm) {
        Api api = MyApplication.getInstance().getApi();
        try {
            api.getGrain(username, password, lcbm)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Result<Grain>>() {
                        @Override
                        public void accept(Result<Grain> result) throws Exception {
                            if (result != null && result.getT() != null) {
                                grain = result.getT();
                                dbHelper.deleteGrainByLCBM(lcbm);
                                grain.setLcbm(lcbm);
                                dbHelper.insertGrain(grain);
                                setData();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
