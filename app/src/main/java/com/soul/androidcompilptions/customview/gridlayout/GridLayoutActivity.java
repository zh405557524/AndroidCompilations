package com.soul.androidcompilptions.customview.gridlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.soul.androidcompilptions.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GridLayoutActivity extends AppCompatActivity {

    private LinearLayoutManager mLinearLayoutManager;
    private Context mContext;
    private List<AppInfo> mAllApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);

        mContext = GridLayoutActivity.this;
        RecyclerView rvApplist = (RecyclerView) findViewById(R.id.rv_applist);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);

        rvApplist.setLayoutManager(gridLayoutManager);
        mAllApps = AppUtils.getAllApps(mContext);

        Collections.sort(mAllApps);

        final Map<String, List<AppInfo>> stringListMap = AppUtils.classifyAppinfos(mAllApps);

        Set<String> strings = stringListMap.keySet();
        ArrayList<String> list = new ArrayList<>();
        list.add("☆");
        for (String s : strings) {
            list.add(s);
        }

        String[] strings1 = new String[list.size()];
        String[] strings2 = list.toArray(strings1);


        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
//                AppInfo appInfo = mAllApps.get(position);
//                String s = PinyinUtil.getPinyin(appInfo.getAppName()).toLowerCase().charAt(0) + "";
//                List<AppInfo> appInfoList = stringListMap.get(s);
//                if (appInfoList == null) {
//
//                    return 4;
//                }
//                AppInfo appInfo1 = appInfoList.get(appInfoList.size() - 1);
//                if (appInfo1.getPackName().equals(appInfo.getPackName())) {
//                    int i = appInfoList.size() % 4;
//                    switch (i) {
//                        case 0:
//                            return 1;
//                        case 1:
//                            return 4;
//                        case 2:
//                            return 3;
//                        case 3:
//                            return 2;
//                    }
//                    return 1;
//                } else {
//                    return 1;
//                }
                return 2;
            }
        });


        AppsListAdapter appsListAdapter = new AppsListAdapter(mContext, mAllApps);
        rvApplist.setAdapter(appsListAdapter);
    }
}
