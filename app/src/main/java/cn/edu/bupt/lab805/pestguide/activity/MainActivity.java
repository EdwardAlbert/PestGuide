package cn.edu.bupt.lab805.pestguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.bean.TabEntity;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.fragment.BookFragment;
import cn.edu.bupt.lab805.pestguide.fragment.DepotFrament;
import cn.edu.bupt.lab805.pestguide.fragment.MeFragment;
import cn.edu.bupt.lab805.pestguide.fragment.UploadFragment;
import cn.edu.bupt.lab805.pestguide.util.ActivityManager;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;
import cn.edu.bupt.lab805.pestguide.util.DataUtil;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

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

    private DBHelper dbHelper;
    private DataUtil dataUtil;
    private Logininfo logininfo;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        initdatas();
        initViews();
    }

    private void initViews() {
        toolbar.setTitle(getString(R.string.app_name));
        mTitles = getResources().getStringArray(R.array.fragment_title);
        fragments.add(UploadFragment.newInstance());
        fragments.add(DepotFrament.newInstance());
        fragments.add(BookFragment.newInstance());
        fragments.add(MeFragment.newInstance());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout.setTabData(mTabEntities, this, R.id.fl_change, fragments);
    }

    /**
     * 初始化数据
     */
    private void initdatas() {
        dbHelper = DBHelper.getInstance();
        logininfo = dbHelper.queryLogininfo();
        dataUtil = DataUtil.getInstance();
        dataUtil.syncFactory(logininfo.getUsername(), logininfo.getPassword());
        dataUtil.syncUser(logininfo.getUsername(), logininfo.getPassword());
        dataUtil.syncDepot(logininfo.getUsername(), logininfo.getPassword());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
