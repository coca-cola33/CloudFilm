package me.jessyan.mvparms.demo.mvp.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.base.App;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.widget.autolayout.AutoCardView;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import butterknife.BindView;
import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.mvp.model.entity.gank.ResultBean;
import rx.Observable;

/**
 * Created by phenix on 2017/7/6.
 */

public class WelfareItemHolder extends BaseHolder<ResultBean> {
    @BindView(R.id.iv_welfare)
    ImageView iv_welfare;
    @BindView(R.id.tv_welfare)
    TextView tv_welfare;
    @BindView(R.id.cardView_welfare)
    AutoCardView cardView_welfare;
    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架


    public WelfareItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mAppComponent = ((App) itemView.getContext().getApplicationContext()).getAppComponent();
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public void setData(ResultBean data, int position) {
        Observable.just(data.getDesc())
                .subscribe(RxTextView.text(tv_welfare));
        mImageLoader.loadImage(mAppComponent.appManager().getCurrentActivity() == null
                        ? mAppComponent.Application() : mAppComponent.appManager().getCurrentActivity(),
                GlideImageConfig.builder()
                        .url(data.getUrl())
                        .errorPic(R.drawable.img_two_bi_one)
                        .imageView(iv_welfare)
                        .build()
        );
//        cardView_welfare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //
//                mOnViewClickListener.onViewClick(v,position);
//            }
//        });

    }
}
