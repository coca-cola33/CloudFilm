package com.jess.arms.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.delegate.IFragment;
import com.jess.arms.mvp.IPresenter;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

/**
 * Created by jess on 2015/12/8.
 */
public abstract class BaseFragment<P extends IPresenter> extends RxFragment implements IFragment{
    protected final String TAG = this.getClass().getSimpleName();
    @Inject
    protected P mPresenter;
    //在Fragment基类里设置一个Activity mActivity的全局变量，在onAttach(Activity activity)里赋值，使用mActivity代替getActivity()，保证Fragment即使在onDetach后，仍持有Activity的引用（有引起内存泄露的风险，但是异步任务没停止的情况下，本身就可能已内存泄漏，相比Crash，这种做法“安全”些），
    private Activity mActivity;


    public BaseFragment() {
        //必须确保在Fragment实例化时setArguments()
        setArguments(new Bundle());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity=activity;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //绑定到butterknife
        return initView(inflater,container,savedInstanceState);
    }



    @Override
    public void onDestroy() {
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

}
