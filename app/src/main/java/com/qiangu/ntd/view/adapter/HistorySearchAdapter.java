package com.qiangu.ntd.view.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.heaven7.android.dragflowlayout.DragAdapter;
import com.qiangu.ntd.R;

/**
 * Created by Administrator on 2016/12/15.
 */

public class HistorySearchAdapter extends DragAdapter<String> {
    @Override public int getItemLayoutId() {
        return R.layout.item_history_search;
    }


    @Override
    public void onBindData(View itemView, int dragState, String data) {
        itemView.setTag(data);
        TextView tvContent = (TextView) itemView.findViewById(R.id.tvContent);
        tvContent.setText(data);
    }


    @NonNull @Override public String getData(View itemView) {
        return (String) itemView.getTag();
    }
}