package com.qiangu.ntd.ui.exchange;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jaeger.library.StatusBarUtil;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.view.adapter.ExchangeRecordAdapter;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRecordActivity extends BaseActivity   implements

        OnLoadMoreListener {
    @BindView(R.id.ibtBack) ImageButton mIbtBack;
    @BindView(R.id.llDetails) LinearLayout mLlDetails;
    @BindView(R.id.tvBalance) TextView mTvBalance;
    @BindView(R.id.swipe_target) RecyclerView mRv;
    @BindView(R.id.llBalance) LinearLayout mLlBalance;
    @BindView(R.id.swipeToLoadLayout) SwipeToLoadLayout mSwipeToLoadLayout;
    private ExchangeRecordAdapter mExchangeRecordAdapter;
    private List<String> mList;
    private int pageNumber = 1;
    private int pageSize = 10;
    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_exchange_record;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override public void onLoadMore() {
        pageNumber++;
        getData();
    }



    @Override protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageViewInFragment(
                ExchangeRecordActivity.this, 0,
                null);
        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mList.add("http://pic1.nipic.com/2008-12-05/200812505313579_2" +
                    ".jpg");
        }
        mExchangeRecordAdapter = new ExchangeRecordAdapter();
        mExchangeRecordAdapter.openLoadAnimation();
        mRv.setAdapter(mExchangeRecordAdapter);
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
        mRv.setLayoutManager(new LinearLayoutManager(ExchangeRecordActivity
                .this));
        mRv.setFocusable(false);
        mRv.setNestedScrollingEnabled(false);
        mExchangeRecordAdapter.setNewData(mList);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        //mSwipeToLoadLayout.setRefreshing(true);
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
    public void getData() {
        //DataManager.getMyOrderList(pageNumber, pageSize, mStatus)
        //           .compose(this.bindToLifecycle())
        //           .subscribe(new CObserver<Response<ListData<Order>>>() {
        //               @Override public void onPrepare() {
        //
        //               }
        //
        //
        //               @Override public void onError(ErrorThrowable throwable) {
        //                   if (mSwipeToLoadLayout == null) return;
        //                   if (mSwipeToLoadLayout.isLoadingMore()) {
        //                       mSwipeToLoadLayout.setLoadingMore(false);
        //                   }
        //                   if (mSwipeToLoadLayout.isRefreshing()) {
        //                       mSwipeToLoadLayout.setRefreshing(false);
        //                   }
        //                   if (pageNumber > 1 &&
        //                           throwable.code == ReturnCode.CODE_EMPTY) {
        //                       pageNumber--;
        //                   }
        //                   else {
        //                       mMyOrderAdapter.getData().clear();
        //                       mMyOrderAdapter.notifyDataSetChanged();
        //                       mMyOrderAdapter.setEmptyView(mEmptyViewGroud);
        //                       mSwipeToLoadLayout.setLoadMoreEnabled(false);
        //                   }
        //                   Log.d("result", throwable.msg + "");
        //               }
        //
        //
        //               @Override
        //               public void onSuccess(Response<ListData<Order>> listData) {
        //                   if (mSwipeToLoadLayout == null) {
        //                       return;
        //                   }
        //                   if (mSwipeToLoadLayout.isLoadingMore()) {
        //                       mSwipeToLoadLayout.setLoadingMore(false);
        //                   }
        //                   if (mSwipeToLoadLayout.isRefreshing()) {
        //                       mSwipeToLoadLayout.setRefreshing(false);
        //
        //                   }
        //                   List<Order> list = listData.data.list;
        //                   if (pageNumber == 1) {
        //                       mMyOrderAdapter.setNewData(list);
        //                   }
        //                   else {
        //                       mMyOrderAdapter.addData(list);
        //                   }
        //                   //Log.d("result",
        //                   //        mStatus + "啊" + listData.data.total + "啊" +
        //                   //                mMyOrderAdapter.getData().size());
        //                   if (listData.data.total ==
        //                           mMyOrderAdapter.getData().size()) {
        //                       mSwipeToLoadLayout.setLoadMoreEnabled(false);
        //                   }
        //                   else {
        //                       mSwipeToLoadLayout.setLoadMoreEnabled(true);
        //                   }
        //               }
        //           });
    }
}
