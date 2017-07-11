package me.jessyan.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;
import me.jessyan.mvparms.demo.mvp.contract.SplashContract;
import me.jessyan.mvparms.demo.mvp.model.SplashModel;

/**
 * Created by phenix on 2017/6/14.
 */
@Module
public class SplashModule {
    private SplashContract.View view;

    public SplashModule(SplashContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SplashContract.View provideSplashView(){
        return view;
    }
    @ActivityScope
    @Provides
    SplashContract.Model provideSplashModel(SplashModel model){
        return model;
    }
}
