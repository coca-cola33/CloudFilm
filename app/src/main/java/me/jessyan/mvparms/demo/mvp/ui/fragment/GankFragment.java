package me.jessyan.mvparms.demo.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.AdapterViewPager;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;

import butterknife.BindView;
import me.jessyan.mvparms.demo.di.component.DaggerGankComponent;
import me.jessyan.mvparms.demo.di.module.GankModule;
import me.jessyan.mvparms.demo.mvp.contract.GankContract;
import me.jessyan.mvparms.demo.mvp.presenter.GankPresenter;

import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.mvp.ui.fragment.gank.AndroidFragment;
import me.jessyan.mvparms.demo.mvp.ui.fragment.gank.CustomFragment;
import me.jessyan.mvparms.demo.mvp.ui.fragment.gank.RecommendFragment;
import me.jessyan.mvparms.demo.mvp.ui.fragment.gank.WelfareFragment;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class GankFragment extends BaseFragment<GankPresenter> implements GankContract.View {
    @BindView(R.id.tab_gank)
    TabLayout tab_gank;
    @BindView(R.id.vp_gank)
    ViewPager vp_gank;

    private String[] mTitleList=new String[]{"知乎","福利","电影","图书"};
    private ArrayList<BaseFragment> mFragments=new ArrayList<>();

    public static GankFragment newInstance() {
        GankFragment fragment = new GankFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerGankComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .gankModule(new GankModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gank, container, false);
    }

    @Override
    public void initData() {
        initTab();
    }

    private void initTab(){
//        mTitleList.add("每日推荐");
//        mTitleList.add("福利");
//        mTitleList.add("干货订制");
//        mTitleList.add("大安卓");
        mFragments.add(RecommendFragment.newInstance());
        mFragments.add(WelfareFragment.newInstance());
        mFragments.add(CustomFragment.newInstance());
        mFragments.add(AndroidFragment.newInstance());
        AdapterViewPager adapter=new AdapterViewPager(getChildFragmentManager());
        adapter.bindData(mFragments,mTitleList);
        vp_gank.setAdapter(adapter);
        // 左右预加载页面的个数
        vp_gank.setOffscreenPageLimit(3);
        tab_gank.setTabMode(TabLayout.MODE_FIXED);
        tab_gank.setupWithViewPager(vp_gank);
//        tab_gank.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
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

    }

    @Override
    public void hideLoading() {

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

}