package com.soul.androidcompilptions.text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.soul.androidcompilptions.R;

public class HFSDKActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hfsdk);

        findViewById(R.id.bt_music).setOnClickListener(this);
        mText = (TextView) findViewById(R.id.tv_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_music:
//                MusicManager.getInstance().getMusicTagList(new RequestCallBack<List<SongClassificationBean>>() {
//                    @Override
//                    public void onSucceed(List<SongClassificationBean> songClassificationBeans) {
//                        LogUtils.i("songClassificationBeans：" + songClassificationBeans.toString());
//                        mText.setText(songClassificationBeans.toString());
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//                        e.printStackTrace();
//                    }
//                });
                break;
        }
    }
}
