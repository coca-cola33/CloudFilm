package me.jessyan.mvparms.demo.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jess.arms.base.AdapterViewPager;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.StatusBarUtil;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.app.utils.NoDoubleClickListener;
import me.jessyan.mvparms.demo.di.component.DaggerMainComponent;
import me.jessyan.mvparms.demo.di.module.MainModule;
import me.jessyan.mvparms.demo.mvp.contract.MainContract;
import me.jessyan.mvparms.demo.mvp.presenter.MainPresenter;
import me.jessyan.mvparms.demo.mvp.ui.fragment.BookFragment;
import me.jessyan.mvparms.demo.mvp.ui.fragment.GankFragment;
import me.jessyan.mvparms.demo.mvp.ui.fragment.OneFragment;


import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_layout)
    NavigationView mNavigationView;
    @BindView(R.id.iv_title_dou)
    ImageView iv_title_dou;
    @BindView(R.id.iv_title_gank)
    ImageView iv_title_gank;
    @BindView(R.id.iv_title_one)
    ImageView iv_title_one;
    @BindView(R.id.iv_title_menu)
    ImageView iv_title_menu;
    @BindView(R.id.vp_content)
    ViewPager vp_content;



    private LinearLayout llNavHomepage;

    @OnClick({R.id.fl_title_menu,R.id.iv_title_dou,R.id.iv_title_one,R.id.iv_title_gank})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fl_title_menu:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_title_gank:
                if(vp_content.getCurrentItem()!=0){
                    vp_content.setCurrentItem(0);
                    iv_title_gank.setSelected(true);
                    iv_title_one.setSelected(false);
                    iv_title_dou.setSelected(false);
                }
                break;
            case R.id.iv_title_one:
                if(vp_content.getCurrentItem()!=1){
                    vp_content.setCurrentItem(1);
                    iv_title_gank.setSelected(false);
                    iv_title_one.setSelected(true);
                    iv_title_dou.setSelected(false);
                }
                break;
            case R.id.iv_title_dou:
                if(vp_content.getCurrentItem()!=2) {
                    vp_content.setCurrentItem(2);
                    iv_title_gank.setSelected(false);
                    iv_title_one.setSelected(false);
                    iv_title_dou.setSelected(true);
                }
                break;
        }
    }


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        return R.layout.activity_main;
    }

    private void initDrawer(){
        View headerView=mNavigationView.getHeaderView(0);
        llNavHomepage= (LinearLayout) headerView.findViewById(R.id.ll_nav_homepage);
        llNavHomepage.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {

                mDrawerLayout.closeDrawer(GravityCompat.START);
                mDrawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        UiUtils.SnackbarText("HomePage!");
                    }
                },260);

            }
        });
    }
    @Override
    public void setStatusBar() {
        //super.setStatusBar();
        //设置顶部沉浸式状态栏
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, (DrawerLayout) findViewById(R.id.drawer_layout),UiUtils.getColor(this,R.color.colorTheme));
    }

    @Override
    public void initData() {
        initDrawer();
        initContent();
    }

    private void initContent(){
        ArrayList<BaseFragment> mFragmentList=new ArrayList<>();
        mFragmentList.add(new GankFragment());
        mFragmentList.add(new OneFragment());
        mFragmentList.add(new BookFragment());
        AdapterViewPager adapter=new AdapterViewPager(getSupportFragmentManager());
        adapter.bindData(mFragmentList);
        vp_content.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗少)
        vp_content.setOffscreenPageLimit(2);
        //初始化设置显示第一个
        iv_title_gank.setSelected(true);
        vp_content.setCurrentItem(0);
        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        iv_title_gank.setSelected(true);
                        iv_title_one.setSelected(false);
                        iv_title_dou.setSelected(false);
                        break;
                    case 1:
                        iv_title_one.setSelected(true);
                        iv_title_dou.setSelected(false);
                        iv_title_gank.setSelected(false);
                        break;
                    case 2:
                        iv_title_dou.setSelected(true);
                        iv_title_gank.setSelected(false);
                        iv_title_one.setSelected(false);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        finish();
    }


}