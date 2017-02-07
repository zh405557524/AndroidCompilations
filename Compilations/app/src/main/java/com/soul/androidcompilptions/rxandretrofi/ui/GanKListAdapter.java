package com.soul.androidcompilptions.rxandretrofi.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soul.androidcompilptions.R;
import com.soul.androidcompilptions.rxandretrofi.entity.GanK;
import com.soul.library.utils.StringStyles;
import com.soul.library.widget.AnimRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi.ui
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/20 16:34
 */

public class GanKListAdapter extends AnimRecyclerViewAdapter<GanKListAdapter.ViewHolder> {

    private List<GanK> ganKList;

    public GanKListAdapter(List<GanK> ganKList) {
        this.ganKList = ganKList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gank, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GanK gank = ganKList.get(position);
        if (position == 0) {
            showCategory(holder);
        }
        else {
            boolean theCategoryOfLastEqualsToThis = ganKList.get(
                    position - 1).type.equals(ganKList.get(position).type);
            if (!theCategoryOfLastEqualsToThis) {
                showCategory(holder);
            }
            else {
                hideCategory(holder);
            }
        }
        holder.category.setText(gank.type);
        SpannableStringBuilder builder = new SpannableStringBuilder(gank.desc).append(
                StringStyles.format(holder.gank.getContext(), " (via. " +
                        gank.who +
                        ")", R.style.ViaTextAppearance));
        CharSequence gankText = builder.subSequence(0, builder.length());

        holder.gank.setText(gankText);
        showItemAnim(holder.gank, position);

    }

    @Override
    public int getItemCount() {
        if (ganKList != null) {
            return ganKList.size();
        }

        return 0;
    }

    public void setData(List<GanK> data) {
        ganKList = data;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category)
        TextView category;
        @BindView(R.id.title)
        TextView gank;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.gank_layout)
        void onGank(View v) {
            GanK gank = ganKList.get(getLayoutPosition());
            Intent intent = WebActivity.newIntent(v.getContext(), gank.url, gank.desc);
            v.getContext().startActivity(intent);
        }
    }


    /**
     * view.isShown() is a kidding...
     */
    private boolean isVisibleOf(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    private void showCategory(ViewHolder holder) {
        if (!isVisibleOf(holder.category))
            holder.category.setVisibility(View.VISIBLE);
    }


    private void hideCategory(ViewHolder holder) {
        if (isVisibleOf(holder.category))
            holder.category.setVisibility(View.GONE);
    }


}
