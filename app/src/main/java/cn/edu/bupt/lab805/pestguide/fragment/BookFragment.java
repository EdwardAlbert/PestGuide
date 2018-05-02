package cn.edu.bupt.lab805.pestguide.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.activity.PestdetailActivity;
import cn.edu.bupt.lab805.pestguide.adapter.PestBookAdapter;
import cn.edu.bupt.lab805.pestguide.entity.Pest;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;

public class BookFragment extends Fragment {
    private static final String TAG = "BookFragment";

    private DBHelper dbHelper;
    private List<Pest> datas;
    private PestBookAdapter adapter;
    private Unbinder unbinder;

    @BindView(R.id.rv_pest)
    RecyclerView rv;

    public BookFragment() {
    }

    public static BookFragment newInstance() {
        BookFragment fragment = new BookFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        unbinder = ButterKnife.bind(this, view);
        dbHelper = DBHelper.getInstance();
        datas = dbHelper.queryPestList();
        adapter = new PestBookAdapter(getActivity(), datas);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new PestBookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Intent intent = new Intent(getActivity(), PestdetailActivity.class);
                intent.putExtra("id", datas.get(pos).getId());
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
