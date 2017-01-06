package com.soul.androidcompilptions.customview.customImageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.soul.androidcompilptions.R;

import java.util.ArrayList;
import java.util.List;

public class CustomImageActivity extends AppCompatActivity {

    private PieView mPieView;

    private List<PieData> mPieDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_image);
        initView();
        initData();



    }

    private void initData() {
        mPieDatas = new ArrayList<>();
        mPieDatas.add(new PieData("fdas", 20));
        mPieDatas.add(new PieData("fdas", 20));
        mPieDatas.add(new PieData("fdas", 20));
        mPieDatas.add(new PieData("fdas", 20));
        mPieDatas.add(new PieData("fdas", 20));
        mPieView.setData(mPieDatas);
    }

    private void initView() {

    }


}
