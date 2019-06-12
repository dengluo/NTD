package com.qiangu.ntd.ui;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.view.adapter.MessageAdapter;
import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity {

    @BindView(R.id.rv) RecyclerView mRv;

    private MessageAdapter mMessageAdapter;
    private List<String> mList;

    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_message;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {
        mList = new ArrayList<>();
        mList.add("学好Java、Android、C#、");
        mList.add("走遍天下都不怕！！！！！");
        mList.add("不是我吹，就怕你做不到，哈哈");
        mMessageAdapter = new MessageAdapter();
        mMessageAdapter.openLoadAnimation();
        mRv.setAdapter(mMessageAdapter);
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
        mRv.setLayoutManager(new LinearLayoutManager(MessageActivity
                .this));
        mMessageAdapter.setNewData(mList);
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
