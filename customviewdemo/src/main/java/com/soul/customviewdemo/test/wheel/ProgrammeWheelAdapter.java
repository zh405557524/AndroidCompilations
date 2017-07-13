package com.soul.customviewdemo.test.wheel;

import java.util.ArrayList;
import java.util.List;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.customviewdemo.test.wheel
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/6/15 14:29
 */

public class ProgrammeWheelAdapter implements WheelAdapter {

    private List<String> mData = new ArrayList<>();

    public ProgrammeWheelAdapter(List<String> data) {
        mData = data;
    }

    @Override
    public int getItemsCount() {
        return mData.size();
    }

    @Override
    public String getItem(int index) {
        return mData.get(index);
    }

    @Override
    public int getMaximumLength() {

        int max = -1;
        for (String str : mData) {
            if (str.length() > max) {
                max = str.length();
            }
        }
        return max;
    }
}
