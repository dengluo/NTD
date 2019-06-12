package com.qiangu.ntd.ui.home;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseFragment;
import com.qiangu.ntd.base.utils.ActivityUtils;
import com.qiangu.ntd.ui.MainActivity;
import com.qiangu.ntd.ui.MessageActivity;
import com.qiangu.ntd.ui.SearchActivity;
import com.qiangu.ntd.ui.WebActivity;
import com.qiangu.ntd.ui.exchange.ExchangeActivity;
import com.qiangu.ntd.ui.financial.FinancialActivity;
import com.qiangu.ntd.ui.home.recharge.RechargeActivity;
import com.qiangu.ntd.ui.home.transfer.TransferActivity;
import com.qiangu.ntd.view.adapter.HomeBannerImageHolderView;
import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */

public class HomeFragment extends BaseFragment {
    List<String> mStrings = new ArrayList<>();
    @BindView(R.id.convenientBanner) ConvenientBanner mConvenientBanner;
    @BindView(R.id.tvBanner) TextBannerView mTvBanner;
    @BindView(R.id.tvTips) TextView mTvTips;
    @BindView(R.id.btnCheckBalances) Button mBtnCheckBalances;
    @BindView(R.id.llBalance) LinearLayout mLlBalance;
    @BindView(R.id.rlBalance) RelativeLayout mRlBalance;

    private List<String> mList;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override protected int getContentViewID() {
        return R.layout.fragment_home;
    }


    @Override protected View getLoadingTargetView() {
        // return ButterKnife.findById(getActivity(), android.R.id.content);
        return null;
    }


    @Override protected void initView(View view) {
        mConvenientBanner.setOnItemClickListener(position -> {

        });
        mList = new ArrayList<>();
        mList.add("学好Java、Android、C#、");
        mList.add("走遍天下都不怕！！！！！");
        mList.add("不是我吹，就怕你做不到，哈哈");
        for (int i = 0; i < 5; i++) {
            mStrings.add("http://pic1.nipic.com/2008-12-05/200812505313579_2" +
                    ".jpg");
        }
        mTvBanner.setDatas(mList);
        mConvenientBanner.setPages(
                (CBViewHolderCreator<HomeBannerImageHolderView>) () -> new HomeBannerImageHolderView(),
                mStrings)
                         //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                         .setPageIndicator(
                                 new int[] { R.drawable.ic_page_indicator,
                                         R.drawable.ic_page_indicator_focused })
                         //设置指示器的方向
                         .setPageIndicatorAlign(
                                 ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        mTvBanner.setItemOnClickListener(new ITextBannerItemClickListener() {
            @Override public void onItemClick(String data, int position) {
                Toast.makeText(mContext, String.valueOf(position) + ">>" + data,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick({ R.id.tvRecharge, R.id.tvTransfer, R.id.tvExchange,
                     R.id.tvFinancial, R.id.ibtNavigation, R.id.ibtMessage,
                     R.id.tvSearch, R.id.btnCheckBalances,
                     R.id.ibtCustomerService })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvRecharge:
                ActivityUtils.launchActivity(mContext, RechargeActivity.class);
                break;
            case R.id.tvTransfer:
                ActivityUtils.launchActivity(mContext, TransferActivity.class);
                break;
            case R.id.tvExchange:
                ActivityUtils.launchActivity(mContext, ExchangeActivity.class);
                break;
            case R.id.tvFinancial:
                ActivityUtils.launchActivity(mContext, FinancialActivity.class);
                break;
            case R.id.ibtNavigation:
                ((MainActivity) mContext).openLeftLayout();
                break;
            case R.id.ibtMessage:
                ActivityUtils.launchActivity(mContext, MessageActivity.class);
                break;
            case R.id.tvSearch:
                ActivityUtils.launchActivity(mContext, SearchActivity.class);
                break;
            case R.id.btnCheckBalances:
                mBtnCheckBalances.setVisibility(View.GONE);
                mLlBalance.setVisibility(View.VISIBLE);
                mTvTips.setVisibility(View.GONE);
                LinearLayout.LayoutParams params
                        = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, mContext.getResources()
                                             .getDimensionPixelSize(
                                                     R.dimen.dp_50), 0, 0);
                mRlBalance.setLayoutParams(params);
                break;

            case R.id.ibtCustomerService:
                ActivityUtils.launchActivity(mContext, WebActivity.class,
                        WebActivity.buildBundle("https://www.baidu.com/"));
                break;
        }
    }


    @Override public void onResume() {
        super.onResume();
        /**调用startViewAnimator()重新开始文字轮播*/
        mTvBanner.startViewAnimator();
    }


    @Override public void onStop() {
        super.onStop();
        /**调用stopViewAnimator()暂停文字轮播，避免文字重影*/
        mTvBanner.stopViewAnimator();
    }
}
