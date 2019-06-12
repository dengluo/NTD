package com.qiangu.ntd.ui.financial;

import android.os.Bundle;
import android.view.View;
import butterknife.Unbinder;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseFragment;

/**
 * Created by Administrator on 2016/11/24.
 */

public class FinancialFragment extends BaseFragment{

    Unbinder unbinder;
    public static FinancialFragment newInstance() {
        FinancialFragment fragment = new FinancialFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override protected int getContentViewID() {
        return R.layout.fragment_financial;
    }


    @Override protected View getLoadingTargetView() {
       // return ButterKnife.findById(getActivity(), android.R.id.content);
        return null;
    }


    @Override protected void initView(View view) {

    }


}
