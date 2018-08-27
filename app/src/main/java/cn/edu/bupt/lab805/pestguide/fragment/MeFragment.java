package cn.edu.bupt.lab805.pestguide.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.activity.FactoryActivity;
import cn.edu.bupt.lab805.pestguide.activity.SettingActivity;
import cn.edu.bupt.lab805.pestguide.activity.UploadActivity;
import cn.edu.bupt.lab805.pestguide.entity.Factory;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.entity.User;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;

public class MeFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.me_tv_name)
    TextView tvName;
    @BindView(R.id.me_tv_factory)
    TextView tvFactory;
    @BindView(R.id.me_tv_role)
    TextView tvRole;

    private DBHelper dbHelper;
    private Factory factory;
    private User user;

    private Unbinder unbinder;

    public MeFragment() {
    }

    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        unbinder = ButterKnife.bind(this, view);
        initdatas();
        initViews();
        view.findViewById(R.id.me_factory).setOnClickListener(this);
        view.findViewById(R.id.me_mecheck).setOnClickListener(this);
        view.findViewById(R.id.me_set).setOnClickListener(this);
        view.findViewById(R.id.me_inform).setOnClickListener(this);
        view.findViewById(R.id.me_recognition).setOnClickListener(this);
        return view;
    }

    private void initdatas() {
        dbHelper = DBHelper.getInstance();
        Logininfo logininfo = dbHelper.queryLogininfo();
        user = dbHelper.queryUserByUsername(logininfo.getUsername());
        factory = dbHelper.queryFactory();
    }

    private void initViews() {
        if (factory != null)
            tvFactory.setText(factory.getLkmc());
        if (user != null) {
            tvName.setText(user.getRealname());
            tvRole.setText(user.getTitle());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.me_factory:
                startActivity(new Intent(getActivity(), FactoryActivity.class));
                break;
            case R.id.me_set:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.me_mecheck:
                startActivity(new Intent(getActivity(), UploadActivity.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
