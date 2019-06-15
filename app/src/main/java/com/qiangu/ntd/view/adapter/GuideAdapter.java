package com.qiangu.ntd.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.util.List;

/**
 * <请描述这个类是干什么的>
 *
 * @author xiaoxm@cncn.com
 * @data: 2016/4/28 14:38
 */
public class GuideAdapter extends PagerAdapter {

    private Context mContext;
    private List<ImageView> imageViewsList;

    public GuideAdapter(Context context, List<ImageView> imageViewsList) {
        this.mContext = context;
        this.imageViewsList = imageViewsList;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        // ((ViewPag.er)container).removeView((View)object);
        ((ViewPager) container).removeView(imageViewsList.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {

        ((ViewPager) container).addView(imageViewsList.get(position));
        return imageViewsList.get(position);
    }


    @Override
    public int getCount() {
        return imageViewsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
