package cn.edu.bupt.lab805.pestguide.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.activity.PestdetailActivity;
import cn.edu.bupt.lab805.pestguide.adapter.PestBookAdapter;
import cn.edu.bupt.lab805.pestguide.entity.Pest;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;
import cn.edu.bupt.lab805.pestguide.util.PestDiffCallBack;
import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class BookFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "BookFragment";

    private DBHelper dbHelper;
    private List<Pest> datas;
    private List<Pest> newDatas;
    private PestBookAdapter adapter;
    private Unbinder unbinder;
    private DiffUtil.DiffResult pestDiffResult;

    @BindView(R.id.rv_pest)
    RecyclerView rv;
    @BindView(R.id.radio_group)
    RadioRealButtonGroup radioGroup;
    @BindView(R.id.book_search_value)
    EditText etSearch;
    @BindView(R.id.book_search_key)
    ImageView searchButton;

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
        initDatas();
        initViews();
        return view;
    }

    private void initDatas() {
        dbHelper = DBHelper.getInstance();
        datas = dbHelper.queryPestList();
    }

    private void initViews() {
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

        radioGroup.setOnPositionChangedListener(new RadioRealButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(RadioRealButton button, int currentPosition, int lastPosition) {
                if (lastPosition == currentPosition) {
                    return;
                }
                newDatas = dbHelper.queryPestByClassId(currentPosition);
                updatePestList();
            }
        });
        searchButton.setOnClickListener(this);
    }

    private void updatePestList() {
        Observable.empty()
                .observeOn(Schedulers.computation())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        pestDiffResult = DiffUtil.calculateDiff(new PestDiffCallBack(datas, newDatas), true);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        pestDiffResult.dispatchUpdatesTo(adapter);
                        datas = newDatas;
                        adapter.setDatas(datas);
                        rv.scrollToPosition(0);
                    }
                })
                .subscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book_search_key:
                String searchStr = etSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(searchStr)) {
                    newDatas = dbHelper.queryPestBySearch(searchStr);
                    updatePestList();
                }
                break;
        }
    }
}
