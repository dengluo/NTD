package com.qiangu.ntd.ui.financial;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.base.BaseFragment;
import com.qiangu.ntd.ui.MainActivity;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/24.
 */

public class FinancialFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.tvCountDown) TextView mTvCountDown;
    private Disposable mDisposable;


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


    @Override public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        countDown();
    }


    private void countDown() {
        mDisposable = Flowable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS)
                              .observeOn(AndroidSchedulers.mainThread())
                              .doOnNext(aLong -> {
                                  //      Log.d(TAG, "倒计时");
                                  mTvCountDown.setText(
                                          (5 - aLong) + " s倒计时自动跳回... ");
                              })
                              .doOnComplete(() -> {
                                  //  Log.d(TAG, "倒计时完毕");
                                  BaseActivity baseActivity
                                          = (BaseActivity) mContext;
                                  if (baseActivity instanceof MainActivity) {
                                      ((MainActivity) mContext).mBtnHome.performClick();
                                  }
                                  else {
                                      getActivity().finish();
                                  }
                              })
                              .subscribe();
    }


    @Override public void onDestroyView() {
        super.onDestroyView();

        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
    @Override protected void initView(View view) {
        countDown();
    }
}
