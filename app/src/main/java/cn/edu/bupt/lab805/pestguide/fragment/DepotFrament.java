package cn.edu.bupt.lab805.pestguide.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.activity.DepotActivity;
import cn.edu.bupt.lab805.pestguide.adapter.DepotAdapter;
import cn.edu.bupt.lab805.pestguide.entity.Depot;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;


public class DepotFrament extends Fragment {

    private static final String TAG = "DepotFrament";

    @BindView(R.id.depot_list)
    SwipeMenuRecyclerView recyclerView;

    private DBHelper dbHelper;
    private List<Depot> datas;
    private DepotAdapter adapter;


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
        ButterKnife.bind(this, view);
        initDatas();
        initViews();
        return view;
    }

    private void initDatas() {
        dbHelper = DBHelper.getInstance();
        datas = dbHelper.queryDepotList();
    }

    private void initViews() {
        adapter = new DepotAdapter(getActivity(), datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DepotAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Intent intent = new Intent(getActivity(), DepotActivity.class);
                intent.putExtra("lcbm",datas.get(pos).getLcbm());
                startActivity(intent);
            }
        });
    }

}
