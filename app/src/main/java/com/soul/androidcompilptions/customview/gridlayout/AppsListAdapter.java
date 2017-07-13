package com.soul.androidcompilptions.customview.gridlayout;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soul.androidcompilptions.R;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * * @author soul
 *
 * @项目名:FluxMotonitoring
 * @包名: com.soul.fluxmotonitoring.ui.adapter
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2016/11/3 10:26
 */

public class AppsListAdapter extends RecyclerView.Adapter<AppsListAdapter.AppsListHolder> {
    private final LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<AppInfo> mData;
    private final Map<String, List<AppInfo>> mStringListMap;
    private final String[] mSetKey;
    private final List<AppInfo> mAppInfoList1;


    public AppsListAdapter(Context context, List<AppInfo> data) {
        mContext = context;
        mData = data;
        mStringListMap = AppUtils.classifyAppinfos(mData);
        Set<String> setKey = mStringListMap.keySet();
        String[] strings = new String[setKey.size()];
        mSetKey = setKey.toArray(strings);
        mAppInfoList1 = mStringListMap.get("☆");
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public AppsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new AppsListHolder(mLayoutInflater.inflate(R.layout.item_app,parent,false));
    }

    @Override
    public void onBindViewHolder(AppsListHolder holder, int position) {
        AppInfo appInfo = mData.get(position);
        holder.tvApptv.setText(appInfo.getAppName());
        holder.ivAppicon.setImageDrawable(appInfo.getIcon());

    }


    @Override
    public int getItemCount() {
        if (mStringListMap != null) {
            return mData.size();
        }
        return 0;
    }


    public static class AppsListHolder extends RecyclerView.ViewHolder {
        public ImageView ivAppicon;
        public TextView tvApptv;

        public AppsListHolder(View itemView) {
            super(itemView);
            ivAppicon = (ImageView) itemView.findViewById(R.id.iv_appicon);
            tvApptv = (TextView) itemView.findViewById(R.id.tv_apptv);
        }

    }


    public static class AppitemHolder extends RecyclerView.ViewHolder {
        public TextView tvLetter;
        public RecyclerView rvItem;

        public AppitemHolder(View itemView) {
            super(itemView);

            tvLetter = (TextView) itemView.findViewById(R.id.tv_letter);
            rvItem = (RecyclerView) itemView.findViewById(R.id.rv_item);
        }
    }


    public class AppListGridLayoutManager extends GridLayoutManager {
        public AppListGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }
    }


    public class AppItemAdapter extends RecyclerView.Adapter<AppsListHolder> {
        private List<AppInfo> mData;

        public AppItemAdapter(List<AppInfo> data) {
            mData = data;
        }

        @Override
        public AppsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new AppsListHolder(View.inflate(mContext, R.layout.item_app, null));
        }

        @Override
        public void onBindViewHolder(AppsListHolder holder, int position) {
            AppInfo appInfo = mData.get(position);
            holder.tvApptv.setText(appInfo.getAppName());
            holder.ivAppicon.setImageDrawable(appInfo.getIcon());
        }

        @Override
        public int getItemCount() {
            if (mData != null) {
                return mData.size();
            }
            return 0;
        }
    }


}
