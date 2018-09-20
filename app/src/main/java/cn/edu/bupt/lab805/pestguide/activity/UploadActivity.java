package cn.edu.bupt.lab805.pestguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.adapter.HistoryUploadAdapter;
import cn.edu.bupt.lab805.pestguide.application.MyApplication;
import cn.edu.bupt.lab805.pestguide.bean.UploadData;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.util.Api;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;
import cn.edu.bupt.lab805.pestguide.util.UploadDiffCallback;
import cn.edu.bupt.lab805.pestguide.widget.MyItemAnimator;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UploadActivity extends AppCompatActivity {

    private static final String TAG = "UploadActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rv_upload)
    RecyclerView rvUpload;

    private HistoryUploadAdapter adapter;
    private List<UploadData> datas;
    private List<UploadData> newDatas;
    private DiffUtil.DiffResult uploadDiffResult;
    private int page;
    private int totalPages;

    private DBHelper dbHelper;
    private Logininfo logininfo;

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

        // 设置刷新监听器
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> refreshHistoryData("", ""));

        // 设置加载更多监听器
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> loadMoreHistoryData("", ""));

        rvUpload.setLayoutManager(new LinearLayoutManager(this));
        rvUpload.setItemAnimator(new MyItemAnimator());
        adapter = new HistoryUploadAdapter(this, datas);
        rvUpload.setAdapter(adapter);
        adapter.setOnItemClickListener((view, pos) -> {
            Log.i(TAG, "onItemClick: " + pos);
            Intent intent = new Intent(UploadActivity.this, UploadDetailActivity.class);
            intent.putExtra("id", datas.get(pos).getId());
            startActivity(intent);
        });

        // 首次进入页面刷新数据
        smartRefreshLayout.autoRefresh();
        updateUploadList();
    }

    /**
     * 刷新纪录
     */
    private void refreshHistoryData(String lcbm, String source) {
        Api api = MyApplication.getInstance().getApi();
        api.getHistoryData(logininfo.getUsername(), logininfo.getPassword(), "1", "10",
                "collecttime", "desc", lcbm, source)
                .subscribeOn(Schedulers.io())
                .doOnNext(result -> {
                    if (result != null & result.getT() != null && result.getT().getRows() != null) {
                        newDatas = result.getT().getRows();
                        updateUploadList();
                        smartRefreshLayout.finishRefresh(true);
                        page = 1;
                        totalPages = result.getT().getTotalPages();
                    } else {
                        smartRefreshLayout.finishRefresh(false);
                    }
                })
                .subscribe();
    }

    /**
     * 加载更多
     */
    private synchronized void loadMoreHistoryData(String lcbm, String source) {
        if (page >= totalPages) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
            return;
        }
        page++;
        Api api = MyApplication.getInstance().getApi();
        api.getHistoryData(logininfo.getUsername(), logininfo.getPassword(), String.valueOf(page), "10",
                "collecttime", "desc", lcbm, source)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(result -> {
                    if (result != null && result.getT() != null && result.getT().getRows() != null) {
                        datas.addAll(result.getT().getRows());
                        adapter.setDatas(datas);
                        adapter.notifyItemInserted(datas.size() - result.getT().getRows().size());
                        smartRefreshLayout.finishLoadMore(500, true, false);
                    } else {
                        smartRefreshLayout.finishRefresh(false);
                    }
                })
                .subscribe();
    }

    private void updateUploadList() {
        Observable.empty()
                .subscribeOn(Schedulers.computation())
                .doOnComplete(() -> uploadDiffResult = DiffUtil.calculateDiff(new UploadDiffCallback(datas, newDatas), true))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                    uploadDiffResult.dispatchUpdatesTo(adapter);
                    datas = newDatas;
                    adapter.setDatas(datas);
                    rvUpload.scrollToPosition(0);
                })
                .subscribe();
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
