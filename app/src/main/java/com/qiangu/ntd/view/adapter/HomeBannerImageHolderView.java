package com.qiangu.ntd.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.bigkoo.convenientbanner.holder.Holder;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.utils.glide.GlideUtils;

/**
 * Created by Administrator on 2016/11/15.
 */

public class HomeBannerImageHolderView implements Holder<String> {
    private ImageView imageView;


    @Override public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }


    @Override public void UpdateUI(Context context, int position, String s) {
        GlideUtils.displayRound(imageView,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560252102415&di=e3d042062475c4d32defbffa6872379f&imgtype=0&src=http%3A%2F%2Fpic27.nipic.com%2F20130201%2F11568449_082704145133_2.jpg", R.drawable.bg_iv_default);
    }
}
