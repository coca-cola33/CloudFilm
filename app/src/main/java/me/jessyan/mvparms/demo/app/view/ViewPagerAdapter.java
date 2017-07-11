package me.jessyan.mvparms.demo.app.view;

/**
 * Created by phenix on 2017/7/7.
 */

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jess.arms.base.App;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import java.util.List;

import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.app.view.photoview.OnPhotoTapListener;
import me.jessyan.mvparms.demo.app.view.photoview.PhotoView;

/**
 * 图片浏览ViewPageAdapter
 *
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<String> imgUrls;
    private Activity mContext;
    private LayoutInflater inflater;


    public ViewPagerAdapter(List<String> imgUrls, Activity mContext) {
        this.imgUrls = imgUrls;
        this.mContext = mContext;
        inflater=mContext.getLayoutInflater();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.viewpager_big_photo, container, false);
        PhotoView zoom_photo_view= (PhotoView) view.findViewById(R.id.zoom_photo_view);
        ProgressBar pb_loading= (ProgressBar) view.findViewById(R.id.pb_loading);

        String img_url=imgUrls.get(position);
        pb_loading.setVisibility(View.VISIBLE);
        pb_loading.setClickable(false);
//        ImageLoader mImageLoader=((App)mContext.getApplicationContext()).getAppComponent().imageLoader();
//        mImageLoader.loadImage(mContext,
//                GlideImageConfig.builder()
//                        .url(img_url)
//                        .imageView(zoom_photo_view)
//                        .build()
//        );
        Glide.with(mContext).load(img_url)
                //使用默认的淡入淡出的动画
                .crossFade(700)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        UiUtils.SnackbarText("资源加载异常");
                        pb_loading.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        pb_loading.setVisibility(View.GONE);

                        /**这里应该是加载成功后图片的高*/
                        int height = zoom_photo_view.getHeight();

                        int wHeight = mContext.getWindowManager().getDefaultDisplay().getHeight();
                        if (height > wHeight) {
                            zoom_photo_view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        } else {
                            zoom_photo_view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        }
                        return false;
                    }
                }).into(zoom_photo_view);

        zoom_photo_view.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                mContext.finish();
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(imgUrls==null||imgUrls.size()==0){
            return 0;
        }
        return imgUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view =(View) object;
        container.removeView(view);
    }
}
