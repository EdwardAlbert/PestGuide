package cn.edu.bupt.lab805.pestguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.adapter.DepotAdapter;
import cn.edu.bupt.lab805.pestguide.entity.Depot;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;

public class DepotSelectorActivity extends AppCompatActivity {

    @BindView(R.id.rv_depot)
    RecyclerView rv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private DBHelper dbHelper;
    private List<Depot> depots;
    private DepotAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depot_selector);
        ButterKnife.bind(this);
        initDatas();
        initViews();
    }

    private void initDatas(){
        dbHelper = DBHelper.getInstance();
        depots = dbHelper.queryDepotList();
    }

    private void initViews(){
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new DepotAdapter(this,depots);
        adapter.setOnItemClickListener((view, pos) -> {
            Depot p = depots.get(pos);
            Intent intent = new Intent();
            intent.putExtra("lcbm", p.getLcbm());
            intent.putExtra("lcmc", p.getBinname());
            setResult(RESULT_OK, intent);
            finish();
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
