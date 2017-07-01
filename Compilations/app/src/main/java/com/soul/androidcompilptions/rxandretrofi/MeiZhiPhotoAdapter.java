package com.soul.androidcompilptions.rxandretrofi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.entity.MeiZhi;
import com.soul.androidcompilptions.rxandretrofi.ui.picture.PictureActivity;
import com.soul.library.base.BaseAdapter;
import com.soul.library.widget.RatioImageView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import static com.soul.androidcompilptions.rxandretrofi.config.ExtraConstant.EXTRA_POSITION;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/16 16:27
 */

public class MeiZhiPhotoAdapter extends BaseAdapter<MeiZhi> {
    private final Listener listener;

    public interface Listener {
        void onImageClick(MeiZhi meiZhi, RatioImageView imageView);

        void onGanKClick(MeiZhi meiZhi);
    }

    public MeiZhiPhotoAdapter(Context context, int layoutId, List<MeiZhi> dataS, Listener listener) {
        super(context, layoutId, dataS);
        this.listener = listener;
    }


    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        super.onViewHolderCreated(holder, itemView);
        RatioImageView meiZhiView = holder.getView(R.id.iv_meiZhi);
        meiZhiView.setOriginalSize(50, 50);
    }

    @Override
    protected void convert(ViewHolder holder, MeiZhi meiZhi, int position) {
        RatioImageView imageView = holder.getView(R.id.iv_meiZhi);
        Glide.with(mContext)
                .load(meiZhi.url)
                .centerCrop()
                .into(imageView);
        holder.setText(R.id.tv_des, meiZhi.desc);
        imageView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, PictureActivity.class);
            intent.putExtra(EXTRA_POSITION, position);
            ((AppCompatActivity) mContext).startActivityForResult(intent, 10);
        });

        holder.getConvertView().setOnClickListener(view -> listener.onGanKClick(meiZhi));
    }

    public void setData(List<MeiZhi> data) {
        if (mDatas != null) {
            mDatas.clear();
            mDatas.addAll(data);
        } else {
            mDatas = data;
        }
    }

    public void addData(List<MeiZhi> data) {
        if (mDatas != null) {
            mDatas.addAll(data);
        } else {
            mDatas = data;
        }
    }

}
