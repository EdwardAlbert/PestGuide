package cn.edu.bupt.lab805.pestguide.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import cn.edu.bupt.lab805.pestguide.R;

public class MeFragment extends Fragment {

    @BindView(R.id.me_tv_name)
    TextView tvName;
    @BindView(R.id.me_tv_factory)
    TextView tvFactory;
    @BindView(R.id.me_tv_role)
    TextView tvRole;

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
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

}
