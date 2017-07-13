package com.soul.androidcompilptions.customview.popupwindow;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.soul.androidcompilptions.R;

public class PopupWindowDemo extends AppCompatActivity {

    private static final String TAG = "PopupWindowDemo";
    private TextView mTvClickMe;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_demo);

        mTvClickMe = (TextView) findViewById(R.id.tv_clikMe);
        setView();

        mTvClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick:mTvClickMe 被点击");
                mPopupWindow.showAsDropDown(mTvClickMe);
            }
        });

    }

    private void setView() {
        View view = View.inflate(PopupWindowDemo.this, R.layout.item, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: 被点击了");
            }
        });

        mPopupWindow = new PopupWindow(view,-2,-2);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
