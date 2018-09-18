package cn.edu.bupt.lab805.pestguide.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.entity.Pest;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;

public class PestdetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PestdetailActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.detail_icon)
    ImageView img_icon;
    @BindView(R.id.detail_name)
    TextView tv_name;
    @BindView(R.id.detail_category)
    TextView tv_category;
    @BindView(R.id.detail_family)
    TextView tv_family;
    @BindView(R.id.detail_feature)
    TextView tv_feature;
    @BindView(R.id.detail_habit)
    TextView tv_habit;
    @BindView(R.id.detail_damage)
    TextView tv_damage;
    @BindView(R.id.detail_distribution)
    TextView tv_distribution;
    @BindView(R.id.detail_prevention)
    TextView tv_prevention;
    @BindView(R.id.detail_importance)
    TextView tv_importance;

    private DBHelper dbHelper;
    private Pest pest;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pestdetail);
        ButterKnife.bind(this);
        initDatas();
        initViews();
    }

    private void initViews() {
        toolbar.setTitle(getString(R.string.toolbar_pestdetail));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_name.setText(pest.getName());
        tv_category.setText(pest.getCategory());
        tv_family.setText(pest.getFamily());
        tv_feature.setText(pest.getFeature());
        tv_habit.setText(pest.getHabit());
        tv_damage.setText(pest.getDamage());
        tv_distribution.setText(pest.getDistribution());
        tv_prevention.setText(pest.getPrevention());
        if ("5".equals(pest.getImportance())) {
            tv_importance.setVisibility(View.VISIBLE);
            tv_importance.setText(getString(R.string.importan));
        }
        path = "file:///android_asset/" + pest.getPictureurl();
//        File file = new File(path);
//        if (file.exists())
//            Log.d(TAG, "initViews: 图片路径 " + file.getAbsolutePath());
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.default_select_image);
        Glide.with(this).load(path)
                .apply(options).into(img_icon);
        img_icon.setOnClickListener(this);
    }

    private void initDatas() {
        Intent intent = getIntent();
        Long id = intent.getLongExtra("id", 1L);
        dbHelper = DBHelper.getInstance();
        pest = dbHelper.queryPestById(id);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_icon:
                Intent intent = new Intent(this,ImageDetailActivity.class);
                intent.putExtra("imgPath",path);
                intent.putExtra("Class",getClass().getSimpleName());
                startActivity(intent);
                break;
        }
    }
}
