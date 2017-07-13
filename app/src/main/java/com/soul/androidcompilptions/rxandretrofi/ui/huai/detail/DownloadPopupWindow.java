package com.soul.androidcompilptions.rxandretrofi.ui.huai.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.bean.AudioListBean;

public class DownloadPopupWindow extends PopupWindow {
    private ImageView ivDown;
    private TextView tvTitle;
    private ImageView ivCancel;
    private RecyclerView rcAudioList;
    private Context mContext;
    private View mMenuView;

    public DownloadPopupWindow(Activity context, OnClickListener itemsOnClick) {
        super(context);
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.download_dialog, null);
        ivDown = (ImageView) mMenuView.findViewById(R.id.iv_down);
        tvTitle = (TextView) mMenuView.findViewById(R.id.tv_title);
        ivCancel = (ImageView) mMenuView.findViewById(R.id.iv_cancel);
        rcAudioList = (RecyclerView) mMenuView.findViewById(R.id.rc_audio_list);
        rcAudioList.setLayoutManager(new LinearLayoutManager(mContext));


        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    dismiss();
                }
                return true;
            }
        });
        ivDown.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, DownloadActivity.class);
            mContext.startActivity(intent);
        });
    }

    public void setData(AudioListBean audioListBean, DetailPresenter detailPresenter) {
        AudioListBean.DataBean.HeadBean head = audioListBean.getData().getHead();
        tvTitle.setText(head.getTitle());
        rcAudioList.setAdapter(new AudioListAdapter(mContext, audioListBean.getData().getList(),detailPresenter,head));
    }

}
