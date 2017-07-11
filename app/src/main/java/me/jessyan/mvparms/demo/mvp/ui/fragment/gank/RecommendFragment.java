package me.jessyan.mvparms.demo.mvp.ui.fragment.gank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.DateUtils;
import com.jess.arms.utils.UiUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.jessyan.mvparms.demo.app.utils.GlideImageLoader;
import me.jessyan.mvparms.demo.di.component.DaggerRecommendComponent;
import me.jessyan.mvparms.demo.di.module.RecommendModule;
import me.jessyan.mvparms.demo.mvp.contract.RecommendContract;
import me.jessyan.mvparms.demo.mvp.model.api.cache.CommonCache;
import me.jessyan.mvparms.demo.mvp.model.entity.gank.AndroidBean;
import me.jessyan.mvparms.demo.mvp.model.entity.zhihu.NewsTimeLine;
import me.jessyan.mvparms.demo.mvp.model.entity.zhihu.TopStories;
import me.jessyan.mvparms.demo.mvp.presenter.RecommendPresenter;

import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.mvp.ui.adapter.RecommendAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 *
 * 每日推荐
 */
public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.View {

    @BindView(R.id.xrv_everyday)
    XRecyclerView xrv_everyday;
    @BindView(R.id.ll_loading)
    LinearLayout ll_loading;
    @BindView(R.id.iv_loading)
    ImageView iv_loading;

    private String date;
    private TextView tv_daily_text;
    private Banner banner;
    private RotateAnimation animation;
    private List<String> mBannerImages;
    private List<String> mBannerTitles;
    private List<String> mBannerIds;
    private RecommendAdapter mRecommendAdapter;


    public static RecommendFragment newInstance() {
        RecommendFragment fragment = new RecommendFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerRecommendComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void initData() {

        initAnimation();
        initXRecyclerView();
        mPresenter.requestContent();
    }

    //旋转动画
    private void initAnimation() {
        animation=new RotateAnimation(0f,360f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(3000);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(10);
        iv_loading.startAnimation(animation);
        animation.startNow();
    }

    private void initXRecyclerView(){
        View headerView=LayoutInflater.from(getActivity()).inflate(R.layout.header_item_everday,null,false);
        banner= (Banner) headerView.findViewById(R.id.banner);
        tv_daily_text= (TextView) headerView.findViewById(R.id.tv_daily_text);
        //设置日期显示
        tv_daily_text.setText(getTodayTime().get(2).indexOf("0")==0?getTodayTime().get(2).replace("0",""):getTodayTime().get(2));

        xrv_everyday.addHeaderView(headerView);
        xrv_everyday.setPullRefreshEnabled(false);
        xrv_everyday.setLoadingMoreEnabled(false);
        xrv_everyday.setLayoutManager(new LinearLayoutManager(getContext()));
        //需加，不然滑动不流畅
        xrv_everyday.setNestedScrollingEnabled(false);
        xrv_everyday.setHasFixedSize(false);
        xrv_everyday.setItemAnimator(new DefaultItemAnimator());

    }


    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {
        ll_loading.setVisibility(View.VISIBLE);
        xrv_everyday.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        ll_loading.setVisibility(View.GONE);
        xrv_everyday.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }


    /**
     * 展示轮播图
     * @param topStories
     */
    private void showBanner(List<TopStories> topStories){
        if(mBannerImages==null){
            mBannerImages=new ArrayList<>();
        }else{
            mBannerImages.clear();
        }
        if(mBannerTitles==null){
            mBannerTitles=new ArrayList<>();
        }else {
            mBannerTitles.clear();
        }
        if(mBannerIds==null){
            mBannerIds=new ArrayList<>();
        }else{
            mBannerIds.clear();
        }
        if(topStories!=null&&topStories.size()>0){
            for(int i=0;i<topStories.size();i++){
                TopStories topStorie=topStories.get(i);
                mBannerImages.add(topStorie.getImage());
                mBannerTitles.add(topStorie.getTitle());
                mBannerIds.add(topStorie.getId());
            }
            banner.setImages(mBannerImages)
                    .setBannerTitles(mBannerTitles)
                    .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                    .setIndicatorGravity(BannerConfig.RIGHT)
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            String id=mBannerIds.get(position);
                                //TODO  跳转网页界面
                        }
                    })
                    .start();

        }
    }

    /**
     * 获取当天日期
     */
    private ArrayList<String> getTodayTime() {
        String data = DateUtils.getData();
        String[] split = data.split("-");
        String year = split[0];
        String month = split[1];
        String day = split[2];
        ArrayList<String> list = new ArrayList<>();
        list.add(year);
        list.add(month);
        list.add(day);
        return list;
    }

    @Override
    public void showContent(NewsTimeLine newsTimeLine) {
        date=newsTimeLine.getDate();
        //展示头部滚动条
        showBanner(newsTimeLine.getTop_stories());
        //展示下方内容
        if(mRecommendAdapter==null){
            mRecommendAdapter=new RecommendAdapter(newsTimeLine.getStories());
        }else{
            mRecommendAdapter.clear();
        }
        xrv_everyday.setAdapter(mRecommendAdapter);
        mRecommendAdapter.notifyDataSetChanged();
    }


}