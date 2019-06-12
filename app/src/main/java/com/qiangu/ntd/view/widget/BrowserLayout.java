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

package com.qiangu.ntd.view.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.appcompat.app.AlertDialog;
import com.qiangu.ntd.R;
import com.qiangu.ntd.app.AppContext;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.base.utils.js.HostJsScope;
import com.qiangu.ntd.base.utils.js.InjectedChromeClient;

/**
 * @author jianhao025@gmail.com
 * @data: 2016/07/27 08:59
 * @version: V1.0
 */
public class BrowserLayout extends LinearLayout {

    private Context mContext = null;
    private WebView mWebView = null;
    private int mBarHeight = 5;
    private ProgressBar mProgressBar = null;

    private String mLoadUrl;
    private LinearLayout webParentView;
    private View mErrorView;
    private WebViewClient webViewClient;

    public boolean loadError = false;
    public LinearLayout errorBtn;


    public BrowserLayout(Context context) {
        super(context);
        init(context);
    }


    public BrowserLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        mContext = context;
        setOrientation(VERTICAL);
        mProgressBar = (ProgressBar) LayoutInflater.from(context)
                                                   .inflate(
                                                           R.layout.progress_horizontal,
                                                           null);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);
        addView(mProgressBar, LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        mBarHeight, getResources().getDisplayMetrics()));

        mWebView = new WebView(context);
        mWebView.getSettings().setJavaScriptEnabled(true);//支持js
        //mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        this.mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.setHorizontalScrollBarEnabled(false);//水平不显示
        mWebView.setVerticalScrollBarEnabled(false); //垂直不显示
        mWebView.setScrollbarFadingEnabled(true);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            //mWebView.getSettings().setMixedContentMode(WebSettings
            // .LOAD_NORMAL);
        }
        mWebView.getSettings().setBlockNetworkImage(false);
        this.mWebView.requestFocusFromTouch();//支持获取手势焦点，输入用户名、密码或其他
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.getSettings()
                     .setJavaScriptCanOpenWindowsAutomatically(
                             true);//支持通过JS打开新窗口
        this.mWebView.getSettings()
                     .setLoadsImagesAutomatically(true);  //支持自动加载图片
        this.mWebView.getSettings().setDefaultTextEncodingName("utf-8");//设置编码格式

        this.mWebView.getSettings()
                     .setLayoutAlgorithm(
                             WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局
        this.mWebView.getSettings().supportMultipleWindows();  //多窗口
        this.mWebView.getSettings().setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        this.mWebView.getSettings()
                     .setUseWideViewPort(true);//将图片调整到适合webview的大小
        this.mWebView.getSettings()
                     .setSupportZoom(true);//支持缩放，默认为true。是下面那个的前提。
        this.mWebView.getSettings()
                     .setBuiltInZoomControls(
                             true);//设置内置的缩放控件。若上面是false，则该WebView不可缩放，这个不管设置什么都不能缩放。
        this.mWebView.getSettings().setDisplayZoomControls(false); //隐藏原生的缩放控件
        this.mWebView.getSettings().setAllowFileAccess(true);//设置可以访问文件
        //this.mWebView.getSettings().setUserAgentString(App
        //        .getUserAgent());
        this.mWebView.getSettings().setAppCacheEnabled(true);
        this.mWebView.getSettings().setDatabaseEnabled(true);
        this.mWebView.getSettings().setAppCacheMaxSize(Long.MAX_VALUE);
        this.mWebView.getSettings()
                     .setRenderPriority(WebSettings.RenderPriority.HIGH);
        LayoutParams lps = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
        addView(mWebView, lps);

        webParentView = (LinearLayout) mWebView.getParent(); //获取父容器
        mErrorView = View.inflate(getContext(), R.layout.layout_load_error,
                null);

        errorBtn = mErrorView.findViewById(R.id.btnReload);
        errorBtn.setOnClickListener(v -> {
            if (AppContext.isNetworkAvailable()) {
                loadError = false;
                webParentView.removeAllViews(); //移除加载网页错误时，默认的提示信息
                LayoutParams layoutParams
                        = new LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT);
                webParentView.setOrientation(VERTICAL);
                webParentView.addView(mProgressBar, LayoutParams.MATCH_PARENT,
                        (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_PX, mBarHeight,
                                getResources().getDisplayMetrics()));
                mProgressBar.setVisibility(View.VISIBLE);
                webParentView.addView(mWebView, 1,
                        layoutParams); //添加自定义的错误提示的View

                mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                mWebView.loadUrl(mLoadUrl);
                mWebView.clearHistory();
                mWebView.setWebChromeClient(
                        new ChromeClient("wallet", HostJsScope.class, context));
                mWebView.setWebViewClient(webViewClient);
            }
        });
        webViewClient = new WebViewClient() {

            /**
             * 防止加载网页时调起系统浏览器
             */
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //跳转到外部url才会打印log
                Log.d("result", "------加载重定向的url " + url);
                return true;
            }


            @Override public void onLoadResource(WebView webView, String s) {
                //所有资源
                //Log.d("result", "加载的url----- " + s);
                super.onLoadResource(webView, s);
            }


            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                //isShowLoading(true);
            }


            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //Log.d("result", "加载的url----- " + url);
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                //6.0以下执行
                mLoadUrl = view.getUrl();
                loadError = true;
                Log.i("result",
                        "onReceivedError: ------->errorCode" + errorCode + ":" +
                                description);
                //网络未连接
                showErrorPage();
                if (loadError) {
                    view.loadUrl("about:blank");
                }
            }


            @Override
            public void onReceivedError(WebView view, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(view, webResourceRequest,
                        webResourceError);
                mLoadUrl = view.getUrl();
                //6.0以上执行
                Log.d("result", "6.0以上执行网络加载错误");
                loadError = true;
                //网络未连接
                showErrorPage();
                if (loadError) {
                    view.loadUrl("about:blank");
                    Log.d("result", view.getUrl());
                }
            }


            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                //handler.proceed();//接受信任所有网站的证书
                if (sslError.getPrimaryError() ==
                        android.net.http.SslError.SSL_INVALID) {// 校验过程遇到了bug
                    sslErrorHandler.proceed();
                }
                else {
                    sslErrorHandler.cancel();
                }
            }
        };
        mWebView.setWebChromeClient(
                new ChromeClient("ntd", HostJsScope.class, context));
        mWebView.setWebViewClient(webViewClient);


    }


    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */
    private void showErrorPage() {
        webParentView.removeAllViews(); //移除加载网页错误时，默认的提示信息
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        webParentView.addView(mErrorView, 0, layoutParams); //添加自定义的错误提示的View
    }


    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    public void loadDataWithBaseURL(String var1, String var2, String var3, String var4, String var5) {
        mWebView.loadDataWithBaseURL(var1,var2,var3,var4,var5);
    }


    public WebView getWebView() {
        return mWebView != null ? mWebView : null;
    }


    public ValueCallback<Uri> uploadMessage;
    public ValueCallback<Uri[]> uploadMessageAboveL;
    public final static int FILE_CHOOSER_RESULT_CODE = 10000;


    private void openImageChooserActivity() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        ((BaseActivity) getContext()).startActivityForResult(
                Intent.createChooser(i, "Image " + "Chooser"),
                FILE_CHOOSER_RESULT_CODE);
    }


    private class ChromeClient extends InjectedChromeClient {

        private Context mContext;
        private String mTitle;


        public ChromeClient(String injectedName, Class injectedCls, Context context) {
            super(injectedName, injectedCls);
            this.mContext = context;
        }


        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            // to do your work
            // ...
            AlertDialog.Builder b2 = new AlertDialog.Builder(mContext).setTitle(
                    "提示")
                                                                      .setMessage(
                                                                              message)
                                                                      .setPositiveButton(
                                                                              "确定",
                                                                              (dialog, which) -> {
                                                                                  // TODO Auto-generated method stub
                                                                                  result.confirm();
                                                                              });

            b2.setCancelable(false);
            b2.create();
            b2.show();
            Log.d("result", message + "onJsAlert");
            return super.onJsAlert(view, url, message, result);
        }


        @Override public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            // to do your work
            // ...
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            }
            else {
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(newProgress);
            }
        }


        //For Android  >= 4.1
        public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
            uploadMessage = valueCallback;
            openImageChooserActivity();
        }


        // For Android >= 5.0
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            uploadMessageAboveL = filePathCallback;
            openImageChooserActivity();
            return true;
        }


        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            // to do your work
            // ...
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }


        /**
         * 当WebView加载之后，返回 HTML 页面的标题 Title
         */
        @Override public void onReceivedTitle(WebView view, String title) {
            //判断标题 title 中是否包含有“error”字段，如果包含“error”字段，则设置加载失败，显示加载失败的视图
            if (!TextUtils.isEmpty(title) &&
                    title.toLowerCase().contains("error")) {
                Log.d("result", "error--------------------");
                loadError = true;
            }
            if (TextUtils.isEmpty(mTitle) && !TextUtils.isEmpty(title)) {
                mTitle = title;
            }
            if (!TextUtils.isEmpty(mTitle) && !TextUtils.isEmpty(title)) {
                if (!mTitle.equals(title)) {
                    //当title改变，就更新title
                    mTitle = title;
                }
            }

            //if (title.contains("404")) {
            //    showErrorPage();
            //}
        }
    }
}
