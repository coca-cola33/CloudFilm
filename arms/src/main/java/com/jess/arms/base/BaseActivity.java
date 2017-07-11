package com.jess.arms.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.View;

import com.jess.arms.R;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.utils.StatusBarUtil;
import com.jess.arms.utils.UiUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import javax.inject.Inject;

import static com.jess.arms.base.delegate.ActivityDelegate.LAYOUT_FRAMELAYOUT;
import static com.jess.arms.base.delegate.ActivityDelegate.LAYOUT_LINEARLAYOUT;
import static com.jess.arms.base.delegate.ActivityDelegate.LAYOUT_RELATIVELAYOUT;

public abstract class BaseActivity<P extends IPresenter> extends RxAppCompatActivity implements IActivity{
    protected final String TAG = this.getClass().getSimpleName();
    @Inject
    protected P mPresenter;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    /**
     * 设置沉侵式状态栏以及状态栏颜色
     */
    public void setStatusBar(){
        StatusBarUtil.setColor(this, UiUtils.getColor(this, R.color.colorTheme));
    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }
        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }
        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }
        return view == null ? super.onCreateView(name, context, attrs) : view;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }

    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */
    @Override
    public boolean useEventBus() {
        return true;
    }

    /**
     * 这个Activity是否会使用Fragment,框架会根据这个属性判断是否注册{@link android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回false,那意味着这个Activity不需要绑定Fragment,那你再在这个Activity中绑定继承于 {@link com.jess.arms.base.BaseFragment} 的Fragment将不起任何作用
     * @return
     */
    @Override
    public boolean useFragment() {
        return true;
    }
}
