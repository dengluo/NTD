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

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
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
import com.qiangu.ntd.base.utils.FileUtils;
import com.qiangu.ntd.base.utils.js.HostJsScope;
import com.qiangu.ntd.base.utils.js.InjectedChromeClient;
import java.io.File;

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

    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private final int RESULT_CODE_PICK_FROM_ALBUM_BELLOW_LOLLILOP = 1;
    private final int RESULT_CODE_PICK_FROM_ALBUM_ABOVE_LOLLILOP = 2;
    String compressPath = "";


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

        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);//开启硬件加速
        //if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
        //    mWebView.getSettings()
        //            .setMixedContentMode(
        //                    WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        //}
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
        this.mWebView.getSettings().setAllowContentAccess(true);
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
                LayoutParams layoutParams = new LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
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
                        new ChromeClient("ntd", HostJsScope.class, context));
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
                Log.d("result", "6.0以上执行网络加载错误" + "onReceivedError: " +
                        "------->errorCode" + webResourceError.getErrorCode() +
                        ":" + webResourceError.getDescription());
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
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        webParentView.addView(mErrorView, 0, layoutParams); //添加自定义的错误提示的View
    }


    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }


    public void loadDataWithBaseURL(String var1, String var2, String var3, String var4, String var5) {
        mWebView.loadDataWithBaseURL(var1, var2, var3, var4, var5);
    }


    public WebView getWebView() {
        return mWebView != null ? mWebView : null;
    }


    /**
     * 选择照片后结束
     */
    public Uri afterChosePic(Intent data) {
        if (data == null) {
            return null;
        }
        String path = getRealFilePath(data.getData());
        String[] names = path.split("\\.");
        String endName = null;
        if (names != null) {
            endName = names[names.length - 1];
        }
        if (endName != null) {
            compressPath = compressPath.split("\\.")[0] + "." + endName;
        }
        File newFile;
        try {
            newFile = FileUtils.compressFile(path, compressPath);
        } catch (Exception e) {
            newFile = null;
        }
        return Uri.fromFile(newFile);
    }


    /**
     * 根据Uri获取图片文件的绝对路径
     */
    public String getRealFilePath(final Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        }
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        }
        else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = mContext.getContentResolver()
                                    .query(uri, new String[] {
                                                    MediaStore.Images.ImageColumns.DATA },
                                            null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndexOrThrow(
                            MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    /**
     * 打开图库,同时处理图片（项目业务需要统一命名）
     */
    private void selectImage(int resultCode) {
        compressPath = Environment.getExternalStorageDirectory().getPath() +
                "/temp";
        File file = new File(compressPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        compressPath = compressPath + File.separator + "compress.png";
        File image = new File(compressPath);
        if (image.exists()) {
            image.delete();
        }
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((BaseActivity) mContext).startActivityForResult(intent, resultCode);
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


        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            if (mUploadMessage != null) return;
            mUploadMessage = uploadMsg;
            selectImage(RESULT_CODE_PICK_FROM_ALBUM_BELLOW_LOLLILOP);
        }


        // For Android < 3.0
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            openFileChooser(uploadMsg, "");
        }


        // For Android > 4.1.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            openFileChooser(uploadMsg, acceptType);
        }


        // For Android 5.0+
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            mUploadCallbackAboveL = filePathCallback;
            selectImage(RESULT_CODE_PICK_FROM_ALBUM_ABOVE_LOLLILOP);
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


    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (mUploadMessage == null && mUploadCallbackAboveL == null) {
            return;
        }
        Uri uri = null;
        switch (requestCode) {
            case RESULT_CODE_PICK_FROM_ALBUM_BELLOW_LOLLILOP:
                uri = afterChosePic(data);
                if (mUploadMessage != null) {
                    mUploadMessage.onReceiveValue(uri);
                    mUploadMessage = null;
                }
                break;
            case RESULT_CODE_PICK_FROM_ALBUM_ABOVE_LOLLILOP:
                try {
                    uri = afterChosePic(data);
                    if (uri == null) {
                        mUploadCallbackAboveL.onReceiveValue(new Uri[] {});
                        mUploadCallbackAboveL = null;
                        break;
                    }
                    if (mUploadCallbackAboveL != null && uri != null) {
                        mUploadCallbackAboveL.onReceiveValue(new Uri[] { uri });
                        mUploadCallbackAboveL = null;
                    }
                } catch (Exception e) {
                    mUploadCallbackAboveL = null;
                    e.printStackTrace();
                }
                break;
        }
    }


    //点击返回上一页面而不是退出浏览器
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return false;
    }
}
