package com.soul.androidcompilptions;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.soul.androidcompilptions.customview.customImageview.CustomImageActivity;
import com.soul.androidcompilptions.customview.dragfooterview.DragFooterActivity;
import com.soul.androidcompilptions.customview.gridlayout.GridLayoutActivity;
import com.soul.androidcompilptions.customview.lineargradient.LinearGradientActivity;
import com.soul.androidcompilptions.customview.popupwindow.PopupWindowDemo;
import com.soul.androidcompilptions.customview.sticky.StickyNavActivity;
import com.soul.androidcompilptions.customview.widget.WidgetDemoActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {
    private final String CATERGORY = "com.soul.androidcompilptions.category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String path = intent.getStringExtra(CATERGORY);

        if (path == null) {
            path = "";
        }

        setListAdapter(new SimpleAdapter(this, getData(path),
                android.R.layout.simple_list_item_1, new String[]{"title"},
                new int[]{android.R.id.text1}));
        getListView().setTextFilterEnabled(true);
    }

    protected List<Map<String, Object>> getData(String prefix) {
        List<Map<String, Object>> myData = new ArrayList<>();
        putActivityName(myData, new Intent(MainActivity.this, StickyNavActivity.class), "嵌套滑动");
        putActivityName(myData, new Intent(MainActivity.this, GridLayoutActivity.class), "GridLayout");
        putActivityName(myData, new Intent(MainActivity.this, CustomImageActivity.class), "自定义View 图片");
        putActivityName(myData, new Intent(MainActivity.this, PopupWindowDemo.class), " popopWindow Demo");
        putActivityName(myData, new Intent(MainActivity.this, LinearGradientActivity.class), " 线性渐变");
        putActivityName(myData, new Intent(MainActivity.this, DragFooterActivity.class), "带拖拽效果的DragContainer");
        putActivityName(myData, new Intent(MainActivity.this, WidgetDemoActivity.class), "widget的用法");
        return myData;
    }

    private void putActivityName(List<Map<String, Object>> myData, Intent intent, String nickName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", nickName);
        map.put("intent", intent);
        myData.add(map);


    }


    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);
        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
}
