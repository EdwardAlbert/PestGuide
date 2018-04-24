package cn.edu.bupt.lab805.pestguide.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.application.MyApplication;
import cn.edu.bupt.lab805.pestguide.bean.Result;
import cn.edu.bupt.lab805.pestguide.bean.Type;
import cn.edu.bupt.lab805.pestguide.entity.Grain;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.util.Api;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DepotEditActivity extends AppCompatActivity {

    private static final String TAG = "DepotEditActivity";

    @BindView(R.id.edit_et_grainname)
    EditText grainname_et;
    @BindView(R.id.edit_sp_clxs)
    Spinner clxs_sp;
    @BindView(R.id.edit_et_innum)
    EditText innum_et;
    @BindView(R.id.edit_et_source)
    EditText source_et;
    @BindView(R.id.edit_tv_indate)
    TextView indate_tv;
    @BindView(R.id.edit_tv_harvestdate)
    TextView harvestdate_tv;
    @BindView(R.id.edit_btn_calenderindate)
    ImageButton indate_btn;
    @BindView(R.id.edit_btn_calenderharvestdate)
    ImageButton harvest_btn;
    @BindView(R.id.edit_et_reserve)
    EditText reserve_et;
    @BindView(R.id.edit_btn_submit)
    Button submit_btn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ProgressDialog dialog;

    private String[] aItems;
    private int inyear;
    private int inmonth;
    private int inday;
    private int harvestyear;
    private int harvestmonth;
    private int harvestday;
    private String lcbm;

    private Grain grain;
    private Logininfo logininfo;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depot_edit);
        ButterKnife.bind(this);
        initDatas();
        initViews();
    }

    private void initDatas() {
        lcbm = getIntent().getStringExtra("lcbm");
        dbHelper = DBHelper.getInstance();
        grain = dbHelper.queryGrainByLCBM(lcbm);
        logininfo = dbHelper.queryLogininfo();
    }

    private void initViews() {
        toolbar.setTitle(getString(R.string.depot_desc));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aItems = getResources().getStringArray(R.array.clxs);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.layout_sppiner, aItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 绑定 Adapter到控件
        clxs_sp.setAdapter(adapter);

        setData();
        initCalendar();
    }

    private void initCalendar() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Date date = new Date(); // 获取当前日期Date对象
        calendar.setTime(date);// //为Calendar对象设置时间为当前日期

        inyear = calendar.get(Calendar.YEAR); // 获取Calendar对象中的年
        inmonth = calendar.get(Calendar.MONTH);// 获取Calendar对象中的月
        inday = calendar.get(Calendar.DAY_OF_MONTH);// 获取这个月的第几天

        harvestyear = calendar.get(Calendar.YEAR); // 获取Calendar对象中的年
        harvestmonth = calendar.get(Calendar.MONTH);// 获取Calendar对象中的月
        harvestday = calendar.get(Calendar.DAY_OF_MONTH);// 获取这个月的第几天

//        indate_tv.setText(inyear + "-" + (inmonth + 1) + "-" + inday); // 显示当前的年月日
//        harvestdate_tv.setText(harvestyear + "-" + (harvestmonth + 1) + "-" + harvestday); // 显示当前的年月日
        // 添加单击事件--设置日期
        indate_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(
                        DepotEditActivity.this, InDatelistener, inyear,
                        inmonth, inday);
                dpd.show();// 显示DatePickerDialog组件
            }
        });

        harvest_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DatePickerDialog dpd = new DatePickerDialog(
                        DepotEditActivity.this, HarvestDatelistener,
                        harvestyear, harvestmonth, harvestday);
                dpd.show();// 显示DatePickerDialog组件
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog = new ProgressDialog(DepotEditActivity.this);
                dialog.setMessage(getString(R.string.uploading));
                dialog.show();
                uploadGrain(logininfo.getUsername(), logininfo.getPassword(), lcbm,
                        grainname_et.getText().toString(), source_et.getText().toString(),
                        clxs_sp.getSelectedItem().toString(), Integer.parseInt(innum_et.getText().toString()),
                        indate_tv.getText().toString(), harvestdate_tv.getText().toString(),
                        Integer.parseInt(reserve_et.getText().toString()));
            }
        });
    }

    //设置默认粮情信息
    private void setData() {
        if (grain != null) {
            grainname_et.setText(grain.getGrainname());
            source_et.setText(grain.getSource());
            indate_tv.setText(grain.getIndate());
            harvestdate_tv.setText(grain.getHarvestdate());

            clxs_sp.setSelection(getSpSelectItem(grain.getClxs(), aItems));

            if (grain.getInnum() != null) {
                innum_et.setText(grain.getInnum().toString());
            }
            if (grain.getReserveperiod() != null) {
                reserve_et.setText(grain.getReserveperiod().toString());
            }
        }
    }

    private int getSpSelectItem(String string, String[] aItems) {
        int item = 0;
        for (int i = 0; i < aItems.length; i++) {
            if (string.equalsIgnoreCase(aItems[i])) {
                item = i;
                break;
            }
        }
        return item;
    }

    private DatePickerDialog.OnDateSetListener InDatelistener = new DatePickerDialog.OnDateSetListener() {
        /**
         * params：view：该事件关联的组件 params：myyear：当前选择的年 params：monthOfYear：当前选择的月
         * params：dayOfMonth：当前选择的日
         */

        // 当DatePickerDialog关闭时，更新日期显示
        private void updateDate() {
            // 在TextView上显示日期
            String month = (inmonth + 1 < 10) ? "0" + (inmonth + 1) : "" + (inmonth + 1);
            String day = inday < 10 ? "0" + inday : "" + inday;
            indate_tv.setText(inyear + "-" + month + "-" + day);

        }

        @Override
        public void onDateSet(DatePicker view, int myyear, int monthOfYear,
                              int dayOfMonth) {
            inyear = myyear;
            inmonth = monthOfYear;
            inday = dayOfMonth;

            // 更新日期
            updateDate();
        }
    };

    private DatePickerDialog.OnDateSetListener HarvestDatelistener = new DatePickerDialog.OnDateSetListener() {
        /**
         * params：view：该事件关联的组件 params：myyear：当前选择的年 params：monthOfYear：当前选择的月
         * params：dayOfMonth：当前选择的日
         */

        // 当DatePickerDialog关闭时，更新日期显示
        private void updateDate() {
            // 在TextView上显示日期
            String month = (harvestmonth + 1 < 10) ? "0" + (harvestmonth + 1) : "" + (harvestmonth + 1);
            String day = harvestday < 10 ? "0" + harvestday : "" + harvestday;
            harvestdate_tv.setText(harvestyear + "-" + month + "-" + day);

        }

        @Override
        public void onDateSet(DatePicker view, int myyear, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub

            harvestyear = myyear;
            harvestmonth = monthOfYear;
            harvestday = dayOfMonth;

            // 更新日期
            updateDate();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 上传粮情
     *
     * @param username
     * @param password
     * @param lcbm
     * @param grainname
     * @param source
     * @param clxs
     * @param innum
     * @param indate
     * @param harvestdate
     * @param reserveperiod
     */
    private void uploadGrain(String username, String password,
                             String lcbm, String grainname,
                             String source, String clxs,
                             Integer innum, String indate,
                             String harvestdate, Integer reserveperiod) {
        Api api = MyApplication.getInstance().getApi();
        try {
            api.uploadGrain(username, password, lcbm, grainname, source, clxs, innum, indate, harvestdate, reserveperiod)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Result>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Result result) {
                            if (result.getType() == Type.success) {
                                dialog.dismiss();
                                Toast.makeText(DepotEditActivity.this, getString(R.string.upload_success), Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                finish();
                            } else {
                                dialog.dismiss();
                                Snackbar.make(toolbar, getString(R.string.upload_fail), Snackbar.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            dialog.dismiss();
                            e.printStackTrace();
                            Snackbar.make(toolbar, getString(R.string.upload_fail), Snackbar.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                            dialog.dismiss();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
