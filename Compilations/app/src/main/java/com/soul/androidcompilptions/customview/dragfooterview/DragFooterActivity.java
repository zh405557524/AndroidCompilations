package com.soul.androidcompilptions.customview.dragfooterview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soul.androidcompilptions.R;

import java.util.ArrayList;
import java.util.List;

public class DragFooterActivity extends AppCompatActivity {

    private Context mContext;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_footer);
        mContext = DragFooterActivity.this;
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add(i + "-->");
        }

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setAdapter(new DragFooterAdapter());
    }


    private class DragFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DragFooterHolder(View.inflate(mContext, R.layout.item, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            DragFooterHolder holder1 = (DragFooterHolder) holder;
            String s = mData.get(position);
            holder1.mInfo.setText(s);
        }

        @Override
        public int getItemCount() {
            if (mData != null) {
                return mData.size();
            }
            return 0;
        }
    }


    private class DragFooterHolder extends RecyclerView.ViewHolder {

        public TextView mInfo;

        public DragFooterHolder(View itemView) {
            super(itemView);
            mInfo = (TextView) itemView.findViewById(R.id.id_info);

        }
    }


}
