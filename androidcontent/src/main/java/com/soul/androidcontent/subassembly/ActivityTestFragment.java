package com.soul.androidcontent.subassembly;

import android.content.Intent;
import android.view.View;

import com.soul.androidcontent.subassembly.activity.TestActivity;
import com.soul.lib.test.ButtonTextFragment;

/**
 * Description: Activity测试类
 * Author: zhuMing
 * CreateDate: 2020/6/27 22:41
 * ProjectName: libs
 * UpdateUser:
 * UpdateDate: 2020/6/27 22:41
 * UpdateRemark:
 */
public class ActivityTestFragment extends ButtonTextFragment implements View.OnClickListener {
    @Override
    public String getClassName() {

        return "Activity";
    }

    @Override
    protected void initEvent() {
        addTextName("启动Activity", this);
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        switch (tag) {
            case "启动Activity":
                UIUtils.startActivity(getContext(), TestActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK);
                break;
        }
    }
}
