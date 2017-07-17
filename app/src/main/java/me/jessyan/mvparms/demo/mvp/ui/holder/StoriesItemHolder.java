package me.jessyan.mvparms.demo.mvp.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.App;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.autolayout.AutoCardView;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import butterknife.BindView;
import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.mvp.model.entity.zhihu.Stories;

/**
 * Created by phenix on 2017/7/5.
 */

public class StoriesItemHolder extends BaseHolder<Stories> {

    @BindView(R.id.card_stories)
    AutoCardView card_stories;
    @BindView(R.id.tv_stories_title)
    TextView title;
    @BindView(R.id.iv_stories_img)
    ImageView img_stories;
    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架



    public StoriesItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mAppComponent = ((App) itemView.getContext().getApplicationContext()).getAppComponent();
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public void setData(Stories data, int position) {
        title.setText(data.getTitle());
        mImageLoader.loadImage(mAppComponent.appManager().getCurrentActivity() == null
                        ? mAppComponent.Application() : mAppComponent.appManager().getCurrentActivity(),
                GlideImageConfig.builder()
                        .url(data.getImages()[0])
                        .errorPic(R.drawable.img_two_bi_one)
                        .imageView(img_stories)
                        .build()
        );
        card_stories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.SnackbarText("toWebView");
            }
        });

    }
}
