package cn.edu.bupt.lab805.pestguide.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.entity.TabEntity;
import cn.edu.bupt.lab805.pestguide.fragment.SimpleCardFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_change)
    FrameLayout frameLayout;
    @BindView(R.id.common_tablayout)
    CommonTabLayout mTabLayout;

    private String[] mTitles;
    private int[] mIconUnselectIds = {R.mipmap.tab_upload, R.mipmap.tab_depot,
            R.mipmap.tab_book, R.mipmap.tab_me};
    private int[] mIconSelectIds = {R.mipmap.tab_upload_select, R.mipmap.tab_depot_select,
            R.mipmap.tab_book_select, R.mipmap.tab_me_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mTitles = getResources().getStringArray(R.array.fragment_title);
        for (String title : mTitles) {
            fragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
        }
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout.setTabData(mTabEntities, this, R.id.fl_change, fragments);
    }
}
