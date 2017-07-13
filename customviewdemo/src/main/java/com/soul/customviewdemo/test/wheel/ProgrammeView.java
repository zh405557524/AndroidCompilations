package com.soul.customviewdemo.test.wheel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.soul.customviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.customviewdemo.test.wheel
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/6/15 10:24
 */

public class ProgrammeView extends FrameLayout {
    private List<String> mData = new ArrayList<>();


    private WheelView mWheelView;
    private ProgrammeWheelAdapter mNumericWheelAdapter;

    public ProgrammeView(@NonNull Context context) {
        this(context, null);
    }

    public ProgrammeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgrammeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(getContext(), R.layout.item_programme, null);
        addView(view);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mWheelView = (WheelView) findViewById(R.id.recyclerView);
        initData();
    }

    private void initData() {
        mData.add("03:39    开讲啦-2017-25");
        mData.add("04:57    新闻联播");
        mData.add("05:27    人与自然");
        mData.add("06:00    朝闻天下");
        mData.add("08:35    生活早参考-特别节目");
        mData.add("03:39    开讲啦-2017-25");
        mData.add("04:57    新闻联播");
        mData.add("05:27    人与自然");
        mData.add("06:00    朝闻天下");
        mData.add("08:35    生活早参考-特别节目");
        mData.add("03:39    开讲啦-2017-25");
        mData.add("04:57    新闻联播");
        mData.add("05:27    人与自然");
        mData.add("06:00    朝闻天下");
        mData.add("08:35    生活早参考-特别节目");
        mData.add("03:39    开讲啦-2017-25");
        mData.add("04:29    今日说法");
        mData.add("04:57    新闻联播");
        mData.add("05:27    人与自然");
        mData.add("06:00    朝闻天下");
        mData.add("08:35    生活早参考-特别节目");
        mData.add("03:39    开讲啦-2017-25");
        mData.add("04:57    新闻联播");
        mData.add("05:27    人与自然");
        mData.add("06:00    朝闻天下");
        mData.add("08:35    生活早参考-特别节目");
        ProgrammeWheelAdapter programmeWheelAdapter = new ProgrammeWheelAdapter(mData);
        mWheelView.setAdapter(programmeWheelAdapter);
        mWheelView.setVisibleItems(5);
        mWheelView.TEXT_SIZE = 40;
        mWheelView.setCurrentItem(10);
        //        mWheelView.setCurrentItem(mData.indexOf("04:29    今日说法``"));

        //        mWheelView.setLayoutManager(new LinearLayoutManager(getContext()));
        //        mWheelView.setAdapter(new CommonAdapter<String>(getContext(), R.layout.item_text, mData) {
        //            @Override
        //            protected void convert(ViewHolder viewHolder, String o, int i) {
        //                if (o.equals("04:29    今日说法")) {
        //                    o = o + "(正在播放)";
        //                    viewHolder.setTextColor(R.id.tv_textView, ContextCompat.getColor(getContext(), R.color.colorPrimary));
        //                } else {
        //                    viewHolder.setTextColor(R.id.tv_textView, ContextCompat.getColor(getContext(), R.color.checkBack));
        //
        //                }
        //                viewHolder.setText(R.id.tv_textView, o);
        //            }
        //        });

    }

}
