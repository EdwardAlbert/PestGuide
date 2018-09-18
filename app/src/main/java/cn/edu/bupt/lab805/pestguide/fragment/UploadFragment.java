package cn.edu.bupt.lab805.pestguide.fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONObject;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.activity.DepotSelectorActivity;
import cn.edu.bupt.lab805.pestguide.activity.ImageDetailActivity;
import cn.edu.bupt.lab805.pestguide.activity.InnerPositionActivity;
import cn.edu.bupt.lab805.pestguide.activity.PestSelectorActivity;
import cn.edu.bupt.lab805.pestguide.adapter.UploadRVAdapter;
import cn.edu.bupt.lab805.pestguide.application.MyApplication;
import cn.edu.bupt.lab805.pestguide.bean.Data;
import cn.edu.bupt.lab805.pestguide.bean.RealInsects;
import cn.edu.bupt.lab805.pestguide.bean.RealTimeData;
import cn.edu.bupt.lab805.pestguide.bean.Result;
import cn.edu.bupt.lab805.pestguide.bean.Type;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.entity.User;
import cn.edu.bupt.lab805.pestguide.service.LocationService;
import cn.edu.bupt.lab805.pestguide.util.Api;
import cn.edu.bupt.lab805.pestguide.util.DBHelper;
import cn.edu.bupt.lab805.pestguide.util.JsonUtils;
import cn.edu.bupt.lab805.pestguide.widget.MyItemAnimator;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static android.app.Activity.RESULT_OK;

public class UploadFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "UploadFragment";

    private static final int REQUEST_IMAGE = 1;
    private static final int REQUEST_TYPE = 2;
    private static final int REQUEST_DEPOT = 3;
    private static final int REQUEST_POSITION = 4;
    private static final int PERMISSION_CAMERA = 5;
    private static final int PERMISSION_LOC = 6;

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
    @BindView(R.id.upload_btn_location)
    ImageView locationButton;
    @BindView(R.id.upload_tv_longtitude)
    TextView longitudeText;
    @BindView(R.id.upload_tv_latitude)
    TextView latitudeText;
    @BindView(R.id.upload_btn_envir)
    ImageButton envirButton;
    @BindView(R.id.upload_btn_submit)
    Button submitButton;

    private ProgressDialog progressDialog;

    private Unbinder unbinder;
    //定位相关
    private LocationService locationService;
    private boolean isLocationBtnClick = false;
    private boolean isEnvirBtnClick = false;
    private DecimalFormat df = new DecimalFormat("###.00");

    private String lcbm; //保存粮仓编码
    private String pos; //保存仓内位置
    private String photoPath; //保存照片路径
    private ArrayList<RealInsects> realInsectsList = new ArrayList<>();//害虫种类和数量
    private UploadRVAdapter adapter;//害虫列表adapter
    private DBHelper dbHelper;
    private Logininfo logininfo;

    private String cityID = null;

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

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(getString(R.string.uploading));

        //粮仓按钮
        depotButton.setOnClickListener(this);
        lcmcText.setOnClickListener(this);
        //仓内位置
        posImage.setOnClickListener(this);
        posText.setOnClickListener(this);
        //选择照片
        pictureButton.setOnClickListener(this);
        uploadImage.setOnClickListener(this);
        //选择害虫种类和数量
        pestButton.setOnClickListener(this);
        //提交按钮
        submitButton.setOnClickListener(this);
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
                checkPhotoPermissions();
                break;
            case R.id.upload_im_image:
                if(photoPath!=null){
                    Intent intent = new Intent(getActivity(), ImageDetailActivity.class);
                    intent.putExtra("imgPath",photoPath);
                    intent.putExtra("Class",getClass().getSimpleName());
                    startActivity(intent);
                }
                break;
            case R.id.upload_btn_pest:
                Intent intent = new Intent(getActivity(), PestSelectorActivity.class);
                intent.putParcelableArrayListExtra("RealInsectsList", realInsectsList);
                startActivityForResult(intent, REQUEST_TYPE);
                break;
            case R.id.upload_btn_submit:
                checkSubmit();
                break;
        }
    }

    // 定位相关
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        // 获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService = MyApplication.getInstance().locationService;
        // 注册监听
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService
                .getDefaultLocationClientOption());

        locationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Log.d(TAG, "onClick: 开始定位");
                isLocationBtnClick = true;
                isEnvirBtnClick = false;
                checkLocPermissions();
            }
        });
        envirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLocationBtnClick = false;
                isEnvirBtnClick = true;
                checkLocPermissions();
            }
        });
    }

    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                if (isLocationBtnClick) {
//                    Log.d(TAG, "onReceiveLocation: " + location.getDistrict());
                    latitudeText.setText(df.format(location.getLatitude()));
                    longitudeText.setText(df.format(location.getLongitude()));
                    isLocationBtnClick = false;
                } else if (isEnvirBtnClick) {
                    cityID = dbHelper.queryCityID(location.getCity(), location.getDistrict());
                    isEnvirBtnClick = false;
                    getWheather(cityID);
                }
            }
            locationService.stop();
        }

    };

    /**
     * 检查上传
     */
    private void checkSubmit() {
        if (TextUtils.isEmpty(photoPath)) {
            Snackbar.make(submitButton, getString(R.string.request_for_photo), Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(lcbm)) {
            Snackbar.make(submitButton, getString(R.string.request_for_lcbm), Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pos)) {
            Snackbar.make(submitButton, getString(R.string.request_for_pos), Snackbar.LENGTH_SHORT).show();
            return;
        }

        //封装数据
        Data data = new Data();
        data.setTemperature(Float.parseFloat(temEdit.getText().toString()));
        data.setHumidity(Float.parseFloat(humEdit.getText().toString()));
        data.setX(Integer.parseInt(pos));
        data.setLongtitude(Double.parseDouble(longitudeText.getText().toString()));
        data.setLatitude(Double.parseDouble(latitudeText.getText().toString()));
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        data.setCollecttime(sdf.format(new Date()));
        data.setSource("App");
        data.setTrapsource(spTrapSource.getSelectedItem().toString());
        data.setRealInsects(realInsectsList);

        RealTimeData realTimeData = new RealTimeData(logininfo.getUsername(), lcbm, data);
//        Log.d(TAG, "checkSubmit: " + JsonUtils.toJson(realTimeData));
        progressDialog.show();
        uploadData(realTimeData);

    }

    /**
     * 检查拍照权限
     */
    private void checkPhotoPermissions() {
        boolean CAMERA = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
        boolean READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
        boolean WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
        if (CAMERA || READ_EXTERNAL_STORAGE || WRITE_EXTERNAL_STORAGE) {
            requestPermissions(new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CAMERA);

        } else {
            getPhoto();
        }
    }

    /**
     * 检查定位权限
     */
    private void checkLocPermissions() {
        int permission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                | ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                | ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_WIFI_STATE)
                | ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_NETWORK_STATE)
                | ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CHANGE_WIFI_STATE)
                | ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE);
        if (permission != 0) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.READ_PHONE_STATE}, PERMISSION_LOC);

        } else {
            gotoLoc();
        }
    }

    /**
     * 定位
     */
    private void gotoLoc() {
        locationService.start();
    }

    /**
     * 获取照片
     */
    private void getPhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)
                .selectionMode(PictureConfig.SINGLE) //选择模式 单选
                .previewImage(true) //是否可以预览图片
                .enableCrop(true)
                .hideBottomControls(false)
                .showCropFrame(true)
                .showCropGrid(true)
                .previewEggs(true)
                .rotateEnabled(true)
                .scaleEnabled(true)
                .freeStyleCropEnabled(true)
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
                    pos = data.getIntExtra("position", 0) + "";
                    posText.setText(pos);
                    break;
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    photoPath = selectList.get(0).getCutPath();
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
        switch (requestCode) {
            case PERMISSION_LOC:
                if (grantResults.length == 6 &&
                        (grantResults[0] | grantResults[1] | grantResults[2] | grantResults[3] | grantResults[4] | grantResults[5]) == 0) {
                    gotoLoc();
                }
                break;
            case PERMISSION_CAMERA:
                if (grantResults.length == 3 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    getPhoto();
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 获取温度和湿度
     *
     * @param cityID
     */
    private void getWheather(String cityID) {
        if (TextUtils.isEmpty(cityID)) return;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.weatherapi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        api.getWheather(cityID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        if (body != null) {
                            String res = body.string();
                            if (!TextUtils.isEmpty(res)) {
                                JSONObject object = new JSONObject(res);
                                JSONObject obj = object.getJSONObject("weatherinfo");
                                String temp = obj.getString("temp");
                                String sd = obj.getString("SD");
                                temEdit.setText(temp);
                                humEdit.setText(sd.substring(0, sd.length() - 1));
                            }
                        }
                    }
                });
    }

    /**
     * 上传数据
     *
     * @param realTimeData
     */
    private void uploadData(RealTimeData realTimeData) {
        Api api = MyApplication.getInstance().getApi();
        api.uploadData(realTimeData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (result != null && result.getType() == Type.success) {
                            uploadPic(logininfo.getUsername(), result.getId(), photoPath);
                        } else {
                            Log.d(TAG, "onNext: data");
                            progressDialog.dismiss();
                            Snackbar.make(submitButton, getString(R.string.submit_failed), Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: data");
                        progressDialog.dismiss();
                        Snackbar.make(submitButton, getString(R.string.submit_failed), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 上传图片
     *
     * @param username
     * @param id
     * @param photoPath
     */
    private void uploadPic(String username, Long id, String photoPath) {
        Log.d(TAG, "uploadPic: " + username + " " + id);
        Api api = MyApplication.getInstance().getApi();
        api.uploadPic(getPartMap(photoPath, username, id.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (result != null && result.getType() == Type.success) {
                            clearData();
                            Snackbar.make(submitButton, getString(R.string.submit_success), Snackbar.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, "onNext: pic " + result.toString());
                            Snackbar.make(submitButton, getString(R.string.submit_failed), Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "onError: pic");
                        progressDialog.dismiss();
                        Snackbar.make(submitButton, getString(R.string.submit_failed), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        progressDialog.dismiss();
                    }
                });
    }

    /**
     * 封装数据和图片
     *
     * @param photoPath
     * @param name
     * @param cid
     * @return
     */
    private Map<String, RequestBody> getPartMap(String photoPath, String name, String cid) {
        Map<String, RequestBody> map = new HashMap<>();
        //封装文本数据
        MediaType textType = MediaType.parse("text/plain");
        RequestBody username = RequestBody.create(textType, name);
        RequestBody id = RequestBody.create(textType, cid);
        map.put("username", username);
        map.put("id", id);
        //封装图片数据
        File photo = new File(photoPath);
        RequestBody file = RequestBody.create(MediaType.parse("image/jpg"), photo);
        map.put("files\"; filename=\"" + photo.getName(), file);
        return map;
    }

    /**
     * 清除已上传的数据
     */
    public void clearData(){
        pos = null;
        posText.setText("");
        lcbm = null;
        lcmcText.setText("");
        photoPath = null;
        uploadImage.setImageResource(R.mipmap.upload_picture_default);
    }


}
