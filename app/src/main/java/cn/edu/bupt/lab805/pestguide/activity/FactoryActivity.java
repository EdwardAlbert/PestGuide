package cn.edu.bupt.lab805.pestguide.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.entity.Factory;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;

public class FactoryActivity extends AppCompatActivity {
    private static final String TAG = "FactoryActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.factory_lkmc)
    TextView lkmc;
    @BindView(R.id.factory_lkbm)
    TextView lkbm;
    @BindView(R.id.factory_lkdz)
    TextView lkdz;
    @BindView(R.id.factory_totalbin)
    TextView totalbin;
    @BindView(R.id.factory_post)
    TextView post;
    @BindView(R.id.factory_contact)
    TextView contact;
    @BindView(R.id.factory_phone)
    TextView phone;

    private Factory factory;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
        ButterKnife.bind(this);
        initDatas();
        initViews();
    }

    private void initDatas() {
        dbHelper = DBHelper.getInstance();
        factory = dbHelper.queryFactory();
    }

    private void initViews() {
        toolbar.setTitle(getString(R.string.toolbar_pestdetail));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lkmc.setText(factory.getLkmc());
        lkbm.setText(factory.getLkbm());
        lkdz.setText(factory.getLkdz());
        totalbin.setText(factory.getTotalbin().toString());
        post.setText(factory.getPostcode());
        contact.setText(factory.getContact());
        phone.setText(factory.getPhone());
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
