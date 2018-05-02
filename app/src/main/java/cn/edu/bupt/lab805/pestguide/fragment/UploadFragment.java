package cn.edu.bupt.lab805.pestguide.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.activity.DepotSelectorActivity;
import cn.edu.bupt.lab805.pestguide.activity.InnerPositionActivity;
import cn.edu.bupt.lab805.pestguide.activity.PestSelectorActivity;
import cn.edu.bupt.lab805.pestguide.adapter.UploadRVAdapter;
import cn.edu.bupt.lab805.pestguide.bean.RealInsects;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;
import cn.edu.bupt.lab805.pestguide.widget.MyItemAnimator;

import static android.app.Activity.RESULT_OK;

public class UploadFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "UploadFragment";

    private static final int REQUEST_IMAGE = 10;
    public static final int REQUEST_TYPE = 30;
    public static final int REQUEST_DEPOT = 40;
    public static final int REQUEST_POSITION = 50;
    private static final int REQUEST_PERMISSION = 60;

    @BindView(R.id.upload_btn_depot)
    ImageButton depotButton;
    @BindView(R.id.upload_tv_lcmc)
    TextView lcmcText;
    @BindView(R.id.upload_tv_position)
    TextView posText;  //仓内位置
    @BindView(R.id.img_position_upload)
    ImageView posImage; //仓内位置
    @BindView(R.id.upload_btn_picture)
    ImageButton pictureButton;
    @BindView(R.id.upload_im_image)
    ImageView uploadImage;
    @BindView(R.id.sp_pest_source_upload)
    Spinner spTrapSource;
    @BindView(R.id.tv_tips_upload)
    TextView tvTips; //虫种和数量提示
    @BindView(R.id.upload_btn_pest)
    ImageButton pestButton;
    @BindView(R.id.rv_upload)
    RecyclerView rv;
    @BindView(R.id.tv_tem_upload)
    EditText temEdit;
    @BindView(R.id.tv_hum_upload)
    EditText humEdit;
    ImageButton envirButton;
    Button submitButton;

    private Unbinder unbinder;

    private String lcbm; //保存粮仓编码
    private String photoPath; //保存照片路径
    private ArrayList<RealInsects> realInsectsList = new ArrayList<>();//害虫种类和数量
    private UploadRVAdapter adapter;//害虫列表adapter
    private DBHelper dbHelper;
    private Logininfo logininfo;

    public UploadFragment() {
        // Required empty public constructor
    }

    public static UploadFragment newInstance() {
        return new UploadFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDatas();
        initViews();
        return view;

    }

    private void initDatas() {
        dbHelper = DBHelper.getInstance();
        logininfo = dbHelper.queryLogininfo();
    }

    private void initViews() {
        //粮仓按钮
        depotButton.setOnClickListener(this);
        lcmcText.setOnClickListener(this);
        //仓内位置
        posImage.setOnClickListener(this);
        posText.setOnClickListener(this);
        //选择照片
        pictureButton.setOnClickListener(this);
        //选择害虫种类和数量
        pestButton.setOnClickListener(this);
        adapter = new UploadRVAdapter(getActivity(), realInsectsList);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(new MyItemAnimator());
        rv.setAdapter(adapter);
        updateTips();
        //当切换fragment时editText内容不保存
        temEdit.setSaveEnabled(false);
        humEdit.setSaveEnabled(false);
    }

    //更新tips显示与否
    private void updateTips() {
        if (adapter.getPestListSize() == 0) {
            tvTips.setVisibility(View.VISIBLE);
        } else {
            tvTips.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload_tv_lcmc:
            case R.id.upload_btn_depot:
                startActivityForResult(new Intent(getActivity(), DepotSelectorActivity.class), REQUEST_DEPOT);
                break;
            case R.id.upload_tv_position:
            case R.id.img_position_upload:
                startActivityForResult(new Intent(getActivity(), InnerPositionActivity.class),
                        REQUEST_POSITION);
                break;
            case R.id.upload_btn_picture:
                checkPermissions();
                break;
            case R.id.upload_btn_pest:
                Intent intent = new Intent(getActivity(), PestSelectorActivity.class);
                intent.putParcelableArrayListExtra("RealInsectsList", realInsectsList);
                startActivityForResult(intent, REQUEST_TYPE);
                break;
        }
    }

    /**
     * 检查权限
     */
    private void checkPermissions() {
        boolean CAMERA = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
        boolean READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
        boolean WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
        if (CAMERA || READ_EXTERNAL_STORAGE || WRITE_EXTERNAL_STORAGE) {
            requestPermissions(new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);

        } else {
            getPhoto();
        }
    }

    /**
     * 获取照片
     */
    private void getPhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1) //最大选择照片数量
                .selectionMode(PictureConfig.SINGLE) //选择模式 单选
                .previewImage(true) //是否可以预览图片
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_DEPOT:
                    lcbm = data.getStringExtra("lcbm");
                    lcmcText.setText(data.getStringExtra("lcmc"));
                    break;
                case REQUEST_POSITION:
                    posText.setText(data.getIntExtra("position", 0) + "");
                    break;
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    photoPath = selectList.get(0).getPath();
                    Glide.with(getActivity()).load(photoPath).into(uploadImage);
                    break;
                case REQUEST_TYPE:
                    if (data.hasExtra("RealInsectsList")) {
                        realInsectsList = data.getParcelableArrayListExtra("RealInsectsList");
                        adapter.setDatas(realInsectsList);
                        updateTips();
                    }
                    break;
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length == 3 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                getPhoto();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
