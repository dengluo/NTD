/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qiangu.ntd.view.widget.loading;

import android.view.View;
import android.widget.TextView;
import com.qiangu.ntd.R;
import com.qiangu.ntd.app.AppContext;
import com.qiangu.ntd.base.utils.EmptyUtils;

public class VaryViewHelperController {

    private IVaryViewHelper helper;


    public VaryViewHelperController(View view) {
        this(new VaryViewHelperX(view));
    }


    public VaryViewHelperController(IVaryViewHelper helper) {
        super();
        this.helper = helper;
    }


    public void showNetworkError(View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.layout_message);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        textView.setText(helper.getContext()
                               .getResources()
                               .getString(R.string.common_no_network_msg));
        layout.findViewById(R.id.message_info_two).setVisibility(View.VISIBLE);
        //ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
        //imageView.setImageResource(R.drawable.ic_exception);

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }


    public void showError(String errorMsg, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.layout_message);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        if (!EmptyUtils.isEmpty(errorMsg)) {
            textView.setText(errorMsg);
        }
        else {
            if (!AppContext.isNetworkAvailable()) {
                textView.setText(helper.getContext()
                                       .getResources()
                                       .getString(R.string.no_network1));
                //Drawable drawable = helper.getContext()
                //                          .getResources()
                //                          .getDrawable(R.drawable.group8);
                //drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                //        drawable.getMinimumHeight());
                //textView.setCompoundDrawables(null, drawable, null, null);
            }
            else {
                textView.setText(helper.getContext()
                                       .getResources()
                                       .getString(R.string.common_error_msg));
                //获取更换的图片
                //Drawable drawable = helper.getContext()
                //                          .getResources()
                //                          .getDrawable(R.drawable.group9);
                //drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                //        drawable.getMinimumHeight());
                //textView.setCompoundDrawables(null, drawable, null, null);
            }
        }

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }


    public void showEmpty(String emptyMsg, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.layout_message);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        if (!EmptyUtils.isEmpty(emptyMsg)) {
            textView.setText(emptyMsg);
        }
        else {
            textView.setText(helper.getContext()
                                   .getResources()
                                   .getString(R.string.common_empty_msg));
        }

        //ImageView imageView = (ImageView) layout.findViewById(
        //        R.id.message_icon);
        // imageView.setImageResource(R.drawable.ic_exception);

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }


    public void showLoading(String msg) {
        View layout = helper.inflate(R.layout.layout_loading);
        if (!EmptyUtils.isEmpty(msg)) {
            TextView textView = (TextView) layout.findViewById(
                    R.id.loading_msg);
            textView.setText(msg);
        }
        helper.showLayout(layout);
    }


    public void restore() {
        helper.restoreView();
    }
}
