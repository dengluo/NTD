package com.qiangu.ntd.ui.home.recharge;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jaeger.library.StatusBarUtil;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.view.adapter.RechargeRecordAdapter;
import java.util.ArrayList;
import java.util.List;

public class RechargeRecordActivity extends BaseActivity {
    @BindView(R.id.ibtBack) ImageButton mIbtBack;
    @BindView(R.id.llDetails) LinearLayout mLlDetails;
    @BindView(R.id.tvBalance) TextView mTvBalance;
    @BindView(R.id.rv) RecyclerView mRv;
    @BindView(R.id.llBalance) LinearLayout mLlBalance;
    private RechargeRecordAdapter mRechargeRecordAdapter;
    private List<String> mList;
    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_recharge_record;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageViewInFragment(RechargeRecordActivity.this, 0,
                null);
        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mList.add("http://pic1.nipic.com/2008-12-05/200812505313579_2" +
                    ".jpg");
        }
      mRechargeRecordAdapter = new RechargeRecordAdapter();
        mRechargeRecordAdapter.openLoadAnimation();
        mRv.setAdapter(  mRechargeRecordAdapter);
        //mEmptyViewGroud = getLayoutInflater().inflate(
        //        R.layout.layout_empty_view, (ViewGroup) mRv.getParent(), false);
        //TextView tvEmptyContent = (TextView) mEmptyViewGroud.findViewById(
        //        R.id.tvEmptyContent);
        //tvEmptyContent.setText("暂无通知");
        //Drawable drawable = getResources().getDrawable(
        //        R.drawable.bg_empty_collection);
        //drawable.setBounds(0, 0, drawable.getMinimumWidth(),
        //        drawable.getMinimumHeight());
        //tvEmptyContent.setCompoundDrawables(null, drawable, null, null);
        mRv.setLayoutManager(new LinearLayoutManager(RechargeRecordActivity
                .this));
        mRechargeRecordAdapter.setNewData(mList);
        mRv.addOnItemTouchListener(new  OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
    }


    @Override protected void initToolbar() {

    }


    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }


    @OnClick({ R.id.ibtBack }) public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtBack:
                finish();
                break;
        }
    }

}
