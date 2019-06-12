package com.qiangu.ntd.view.adapter;

import android.util.Log;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.utils.StringUtils;

/**
 * Created by Administrator on 2016/12/15.
 */

public class KeywordAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private String keyword;


    public String getKeyword() {
        return keyword;
    }


    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public KeywordAdapter() {
        super(R.layout.item_keyword);
    }


    @Override protected void convert(BaseViewHolder helper, String s) {
        // GlideUtils.display(helper.getView(R.id.iv), commentImage.fullUrl);
        Log.d("result",getKeyword()+"数据");
        helper.setText(R.id.tvKeyword, StringUtils.matcherSearchTitle(
                mContext.getResources().getColor(R.color.textContentColor3), s,
                getKeyword()));
    }
}
