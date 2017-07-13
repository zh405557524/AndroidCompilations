package com.soul.androidcompilptions.customview.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.soul.androidcompilptions.R;

public class WidgetDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_demo);
        Mywidget mywidget = new Mywidget();

    }
}
