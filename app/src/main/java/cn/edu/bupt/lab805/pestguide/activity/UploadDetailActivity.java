package cn.edu.bupt.lab805.pestguide.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.application.MyApplication;
import cn.edu.bupt.lab805.pestguide.bean.Page;
import cn.edu.bupt.lab805.pestguide.bean.Result;
import cn.edu.bupt.lab805.pestguide.bean.UploadData;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.util.Api;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;
import cn.edu.bupt.lab805.pestguide.util.StringUtils;
import cn.edu.bupt.lab805.pestguide.util.UrlContainer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UploadDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_upload_detail)
    RecyclerView rvUploadDetail;
    @BindView(R.id.img_photo_upload_detail)
    PhotoView imgPhoto;
    @BindView(R.id.tv_time_upload_detail)
    TextView tvTime;
    @BindView(R.id.tv_location_upload_detail)
    TextView tvLocation;
    @BindView(R.id.tv_lonti_upload_detail)
    TextView tvLonti;
    @BindView(R.id.tv_lati_upload_detail)
    TextView tvLati;
    @BindView(R.id.tv_temp_upload_detail)
    TextView tvTemp;
    @BindView(R.id.tv_humi_upload_detail)
    TextView tvHumi;

    private UploadData data;
    private DBHelper dbHelper;
    private Logininfo logininfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_detail);
        ButterKnife.bind(this);
        initViews();
        initData();
    }

    private void initData() {
        dbHelper = DBHelper.getInstance();
        logininfo = dbHelper.queryLogininfo();

        long id = getIntent().getLongExtra("id", 0L);
        if (id != 0L) {
            getUploadData(id);
        }
    }

    private void initViews() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgPhoto.setOnClickListener(view -> {
            if (data.getRealdataPics() != null && data.getRealdataPics().size() > 0
                    && !TextUtils.isEmpty(data.getRealdataPics().get(0).getPic())) {
                Intent intent = new Intent(UploadDetailActivity.this, ImageDetailActivity.class);
                intent.putExtra("imgPath", getString(R.string.base_url) + data.getRealdataPics().get(0).getPic());
                intent.putExtra("Class", getClass().getSimpleName());
                startActivity(intent);
            }
        });
    }

    /**
     * 获取上传记录
     *
     * @param id
     */
    private void getUploadData(long id) {
        Api api = MyApplication.getInstance().getApi();
        api.getHistoryDataById(logininfo.getUsername(), logininfo.getPassword(), "1", "1", id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<Page<UploadData>>>() {
                    @Override
                    public void accept(Result<Page<UploadData>> result) throws Exception {
                        if (result != null && result.getT() != null
                                && result.getT().getRows() != null
                                && result.getT().getRows().size() > 0) {
                            data = result.getT().getRows().get(0);
                            updateView();
                        }
                    }
                });
    }

    private void updateView() {
        try {
            tvTime.setText(StringUtils.whichString(data.getCollecttime()));
            tvLocation.setText(StringUtils.whichString(String.valueOf(data.getX())));
            tvLati.setText(data.getLatitude() == null ? "" : String.format(getString(R.string.loc_unit_format), data.getLatitude()));
            tvLonti.setText(data.getLongtitude() == null ? "" : String.format(getString(R.string.loc_unit_format), data.getLongtitude()));
            tvTemp.setText(data.getTemperature() == null ? "" : String.format(getString(R.string.temper_unit_format), data.getTemperature()));
            tvHumi.setText(data.getHumidity() == null ? "" : String.format(getString(R.string.humi_unit_format), data.getHumidity()));
            if (data.getRealdataPics() != null && data.getRealdataPics().size() > 0
                    && !TextUtils.isEmpty(data.getRealdataPics().get(0).getPic())) {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.mipmap.default_select_image);
                Glide.with(this).setDefaultRequestOptions(requestOptions)
                        .load(getString(R.string.base_url) + data.getRealdataPics().get(0).getPic())
                        .into(imgPhoto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
