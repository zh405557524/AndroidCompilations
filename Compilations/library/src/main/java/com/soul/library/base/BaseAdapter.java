package com.soul.library.base;

import android.content.Context;

import com.soul.library.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.List;

/**
 * 描述：
 * 作者：祝明
 * 创建时间：2017/4/6 13:39
 */

public abstract class BaseAdapter<T> extends CommonAdapter<T> {

    private final HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private final LoadMoreWrapper mLoadMoreWrapper;

    public BaseAdapter(Context context, int layoutId, List data) {
        super(context, layoutId, data);
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(this);
        mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
    }

    public LoadMoreWrapper getLoadMoreAdapter(){

        return mLoadMoreWrapper;
    }

    public void setOnLoadMoreListener(LoadMoreWrapper.OnLoadMoreListener loadMoreListener) {
        mLoadMoreWrapper.setOnLoadMoreListener(loadMoreListener);
    }

    public void notifyDataChanged(){
        mLoadMoreWrapper.notifyDataSetChanged();
        notifyDataSetChanged();
    }
}
