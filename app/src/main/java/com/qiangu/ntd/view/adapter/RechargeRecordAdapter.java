package com.qiangu.ntd.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qiangu.ntd.R;

/**
 * Created by Administrator on 2016/12/15.
 */

public class RechargeRecordAdapter
        extends BaseQuickAdapter<String, BaseViewHolder> {

    public RechargeRecordAdapter() {
        super(R.layout.item_recharge_record);
    }


    @Override
    protected void convert(BaseViewHolder helper,String s) {
      // GlideUtils.display(helper.getView(R.id.iv), commentImage.fullUrl);
      //  helper.setImageResource(R.id.iv,R.drawable.bg);
    }
}
