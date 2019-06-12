package com.qiangu.ntd.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.heaven7.android.dragflowlayout.DragFlowLayout;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.view.adapter.HistorySearchAdapter;
import com.qiangu.ntd.view.adapter.KeywordAdapter;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.rv) RecyclerView mRv;
    @BindView(R.id.etContent) EditText mEtContent;
    private HistorySearchAdapter mHistorySearchAdapter;
    private KeywordAdapter mKeywordAdapter;
    private List<String> mList;
    @BindView(R.id.drag_flowLayout) DragFlowLayout mDragflowLayout;


    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_search;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {
        mList = new ArrayList<>();
        mList.add("学好Java");
        mList.add("走遍天");
        mList.add("学好Java、Android、C#、");
        mList.add("走遍天");
        mList.add("学好Java、Android、C#、");
        mList.add("走遍天下都不怕");
        mList.add("不是我吹，就怕你做不到，哈哈");
        mHistorySearchAdapter = new HistorySearchAdapter();
        mKeywordAdapter = new KeywordAdapter();
        mKeywordAdapter.openLoadAnimation();
        mRv.setAdapter(mKeywordAdapter);
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

        // mHistorySearchAdapter.setNewData(mList);

        //mDragflowLayout.setOnItemClickListener(
        //        new ClickToDeleteItemListenerImpl(R.id.tvDelete) {
        //            @Override
        //            protected void onDeleteSuccess(DragFlowLayout dfl, View child, Object data) {
        //                //删除成功后的处理。
        //            }
        //
        //
        //            @Override
        //            public boolean performClick(DragFlowLayout dragFlowLayout, View child, MotionEvent event, int dragState) {
        //                super.performClick(dragFlowLayout, child, event,
        //                        dragState);
        //                return true;
        //            }
        //        });
        mDragflowLayout.setDragAdapter(mHistorySearchAdapter);
        //预存指定个数的Item. 这些Item view会反复使用，避免重复创建, 默认10个
        mDragflowLayout.prepareItemsByCount(10);

        //设置拖拽状态监听器
        mDragflowLayout.setOnDragStateChangeListener((dfl, dragState) -> {
            //editOrAdd(true);

        });
        mDragflowLayout.setOnItemClickListener(
                (dragFlowLayout, child, event, dragState) -> {
                    TextView textView = child.findViewById(R.id.tvContent);
                    mEtContent.setText(textView.getText());
                    return false;
                });

        mDragflowLayout.getDragItemManager().addItems(mList);
        mRv.setLayoutManager(new LinearLayoutManager(SearchActivity
                .this));

        mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    mKeywordAdapter.setKeyword(s.toString().trim());
                    mKeywordAdapter.setNewData(mList);
                    mRv.setVisibility(View.VISIBLE);
                    mDragflowLayout.setVisibility(View.GONE);
                }else{
                    mRv.setVisibility(View.GONE);
                    mDragflowLayout.setVisibility(View.VISIBLE);
                }
            }


            @Override public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override protected void initToolbar() {

    }


    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }


    @OnClick({ R.id.tvCancel }) public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvCancel:
                finish();
                break;
        }
    }
}
