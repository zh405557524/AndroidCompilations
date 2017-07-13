package com.soul.library.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.soul.library.R;
import com.soul.library.utils.picassoprogress.AlxPicassoUtils;
import com.soul.library.widget.photoview.PhotoView;

public class PhotoActivity extends AppCompatActivity {

    public static final String URL = "url";
    public static final String TRANSIT_PIC = "picture";
    private PhotoView mImageView;
    private String mUrl;
    private ProgressWheel mProgressWheel;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        StatusBarUtil.setTranslucent(this, 25);
        initView();
        initData();
        initEvent();

    }

    private void initView() {
        mImageView = (PhotoView) findViewById(R.id.iv_photo);
        mProgressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);
        mTv = (TextView) findViewById(R.id.tv1);
    }

    private void initData() {
        // 启用图片缩放功能
        mImageView.enable();
        Intent intent = getIntent();
        if (intent != null) {
            mUrl = intent.getStringExtra(URL);
            //            Picasso.with(this).load(mUrl).into(mImageView);
            AlxPicassoUtils.displayImageProgress(mUrl, mImageView, mProgressWheel, mTv);
        }
    }

    private void initEvent() {
        mImageView.setOnClickListener(view -> finish());
        //显示模糊占位图而且带进度条的下载过程

    }


}
