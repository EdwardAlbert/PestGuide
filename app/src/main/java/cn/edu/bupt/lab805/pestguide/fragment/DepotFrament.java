package cn.edu.bupt.lab805.pestguide.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.activity.DepotActivity;
import cn.edu.bupt.lab805.pestguide.adapter.DepotAdapter;
import cn.edu.bupt.lab805.pestguide.entity.Depot;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;
import cn.edu.bupt.lab805.pestguide.util.DataUtil;
import cn.edu.bupt.lab805.pestguide.widget.MyItemAnimator;


public class DepotFrament extends Fragment {

    private static final String TAG = "DepotFrament";

    @BindView(R.id.depot_list)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private DBHelper dbHelper;
    private List<Depot> datas;
    private DepotAdapter adapter;
    private Unbinder unbinder;
    private Logininfo logininfo;


    public DepotFrament() {
        // Required empty public constructor
    }

    public static DepotFrament newInstance() {
        return new DepotFrament();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_depot, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDatas();
        initViews();
        return view;
    }

    private void initDatas() {
        dbHelper = DBHelper.getInstance();
        datas = dbHelper.queryDepotList();
        logininfo = dbHelper.queryLogininfo();
    }

    private void initViews() {
        adapter = new DepotAdapter(getActivity(), datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new MyItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, pos) -> {
            Intent intent = new Intent(getActivity(), DepotActivity.class);
            intent.putExtra("lcbm", datas.get(pos).getLcbm());
            startActivity(intent);
        });
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> updateList());

    }

    private void updateList() {
        DataUtil.getInstance().syncDepot(logininfo.getUsername(), logininfo.getPassword());
        new Handler().postDelayed(() -> {
            datas = dbHelper.queryDepotList();
            adapter.setDatas(datas);
            adapter.notifyDataSetChanged();
            smartRefreshLayout.finishRefresh();
        }, 2000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
