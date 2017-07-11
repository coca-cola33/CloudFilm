package me.jessyan.mvparms.demo.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.mvparms.demo.mvp.contract.SplashContract;

/**
 * Created by phenix on 2017/6/14.
 */
@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.Model,SplashContract.View> {
    @Inject
    public SplashPresenter(SplashContract.Model model, SplashContract.View rootView) {
        super(model, rootView);
    }

}
