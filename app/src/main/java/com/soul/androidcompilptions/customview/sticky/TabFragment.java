package com.soul.androidcompilptions.customview.sticky;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soul.androidcompilptions.R;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.customview
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2016/11/7 16:47
 */

public class TabFragment extends Fragment {

    private static final java.lang.String TILTE = "title";
    private String mTitle = "Defaut Value";
    private RecyclerView mRecyclerView;
    private List<String> mDatas = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTitle = getArguments().getString(TILTE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_stickynavlayout_innerscrollview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDatas.add("\u25f7");
        for (int i = 0; i < 100; i++) {
            char[] chars = new char[6];
            chars[0] = '\\';
            chars[1] = 'u';
            chars[2] = '2';
            chars[3] = '3';
            chars[4] = 'F';
            String s = i + "";
            char[] chars1 = s.toCharArray();
            chars[5] = chars1[0];

            String s1 = String.valueOf(chars);
            mDatas.add(s1);
        }
        mRecyclerView.setAdapter(new CommonAdapter<String>(getActivity(), R.layout.item, mDatas) {
            @Override
            public void convert(ViewHolder holder, String o) {
                holder.setText(R.id.id_info, o);
            }
        });
        return view;
    }

    public static TabFragment newInstance(String title) {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TILTE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

}
