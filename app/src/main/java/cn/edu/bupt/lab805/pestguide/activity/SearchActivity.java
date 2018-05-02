package cn.edu.bupt.lab805.pestguide.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.adapter.PestBookAdapter;
import cn.edu.bupt.lab805.pestguide.entity.Pest;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SearchActivity";

    @BindView(R.id.search_btn_s1)
    Button btn_s1;
    @BindView(R.id.search_btn_s2)
    Button btn_s2;
    @BindView(R.id.search_btn_s3)
    Button btn_s3;
    @BindView(R.id.search_btn_s4)
    Button btn_s4;
    @BindView(R.id.search_search_key)
    ImageView searchKey;
    @BindView(R.id.search_search_value)
    EditText searchValue;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_lv_pest)
    RecyclerView rv;

    private DBHelper dbHelper;
    private List<Pest> pest;
    private PestBookAdapter adapter;
    private int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initDatas();
        initViews();
    }

    private void initDatas() {
        Intent intent = getIntent();
        index = intent.getIntExtra("index", -1);
        dbHelper = DBHelper.getInstance();
        pest = dbHelper.queryPestList();
    }

    private void initViews() {
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_s1.setOnClickListener(this);
        btn_s2.setOnClickListener(this);
        btn_s3.setOnClickListener(this);
        btn_s4.setOnClickListener(this);
        searchKey.setOnClickListener(this);

        adapter = new PestBookAdapter(this, pest);
        adapter.setOnItemClickListener(new PestBookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Intent data = new Intent();
                data.putExtra("name", pest.get(pos).getName());
                data.putExtra("index", index);
                setResult(RESULT_OK, data);
                finish();
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_search_key:
                String str = searchValue.getText().toString();
                if (TextUtils.isEmpty(str))
                    pest = dbHelper.queryPestList();
                else
                    pest = dbHelper.queryPestBySearch(str);
                adapter.setDatas(pest);
                break;
            case R.id.search_btn_s1:
                pest = dbHelper.queryPestBySearch(btn_s1.getText().toString());
                adapter.setDatas(pest);
                break;
            case R.id.search_btn_s2:
                pest = dbHelper.queryPestBySearch(btn_s2.getText().toString());
                adapter.setDatas(pest);

                break;
            case R.id.search_btn_s3:
                pest = dbHelper.queryPestBySearch(btn_s3.getText().toString());
                adapter.setDatas(pest);
                break;
            case R.id.search_btn_s4:
                Intent intent = new Intent();
                intent.putExtra("name", "其他");
                intent.putExtra("index", index);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
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
