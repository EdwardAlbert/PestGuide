package cn.edu.bupt.lab805.pestguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.adapter.PestSelectorAdapter;
import cn.edu.bupt.lab805.pestguide.bean.RealInsects;
import cn.edu.bupt.lab805.pestguide.fragment.DeletePestDialogFragment;
import cn.edu.bupt.lab805.pestguide.widget.MyItemAnimator;

public class PestSelectorActivity extends AppCompatActivity implements DeletePestDialogFragment.DeletePestListener {

    private static final String TAG = "PestSelector";
    private static final int REQUEST_TYPE = 30;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_pest_selector)
    RecyclerView recyclerView;
    @BindView(R.id.btn_add_pest_selector)
    Button btnAdd;
    private ArrayList<RealInsects> RealInsectsList = new ArrayList<>();
    private PestSelectorAdapter adapter;
    private DeletePestDialogFragment dialogFragment;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pest_selector);
        ButterKnife.bind(this);
        initDatas();
        initViews();
    }

    private void initDatas(){
        intent = getIntent();
        if (intent.hasExtra("RealInsectsList")) {
            RealInsectsList = intent.getParcelableArrayListExtra("RealInsectsList");
        }
    }

    private void initViews() {
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new MyItemAnimator());
        adapter = new PestSelectorAdapter(this, RealInsectsList);
        initEvent();

        recyclerView.setAdapter(adapter);
        btnAdd = (Button) findViewById(R.id.btn_add_pest_selector);
        btnAdd.setOnClickListener(v -> adapter.addData());

    }

    private void initEvent() {
        adapter.setOnItemClickLitener(new PestSelectorAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(PestSelectorActivity.this, SearchActivity.class);
                intent.putExtra("index", position);
                startActivityForResult(intent, REQUEST_TYPE);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                if (dialogFragment == null)
                    dialogFragment = new DeletePestDialogFragment();
                dialogFragment.setIndex(position);
                dialogFragment.show(getFragmentManager().beginTransaction(), "deletePestDialogFragment");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_TYPE:
                if (resultCode == RESULT_OK) {
                    adapter.editName(data.getIntExtra("index", -1), data.getStringExtra("name"));
                }
                break;
            default:
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

    @Override
    public void onPestDelete(int pos) {
        adapter.removeData(pos);
    }

    @Override
    public void onBackPressed() {
        checkDatas(RealInsectsList);
        super.onBackPressed();
    }

    private void checkDatas(ArrayList<RealInsects> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getKind() == null) {
                list.remove(i);
            } else if (list.get(i).getNum() == null) {
                list.get(i).setNum(0);
            }
        }
        intent.putParcelableArrayListExtra("RealIdnsectsList",list);
        setResult(RESULT_OK, intent);
    }
}
