package com.soul.androidcompilptions.customview.canvas_study;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.soul.androidcompilptions.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 学习 Canvas
 */
public class CanvasStudyActivity extends AppCompatActivity {
    @BindView(R.id.tv_text)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_study);
        ButterKnife.bind(this);
        mTextView.setText("fdsafdsafdsa");
    }
}
