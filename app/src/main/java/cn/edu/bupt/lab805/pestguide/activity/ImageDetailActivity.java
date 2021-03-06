package cn.edu.bupt.lab805.pestguide.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;


public class ImageDetailActivity extends Activity {

    @BindView(R.id.image_detail)
    PhotoView photoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);

        // 启用图片缩放功能
        photoView.enable();

        String path = getIntent().getStringExtra("imgPath");
        String clazz = getIntent().getStringExtra("Class");

        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.upload_picture_default);
        if (clazz.equalsIgnoreCase("UploadFragment")) {
            Glide.with(this)
                    .setDefaultRequestOptions(options)
                    .load("file:///" + path)
                    .into(photoView);
        } else {
            Glide.with(photoView.getContext())
                    .setDefaultRequestOptions(options)
                    .load(path)
                    .into(photoView);
        }

        photoView.setOnClickListener(v -> finish());
    }
}
