package me.jessyan.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import me.jessyan.mvparms.demo.mvp.contract.GankContract;
import me.jessyan.mvparms.demo.mvp.model.GankModel;


@Module
public class GankModule {
    private GankContract.View view;

    /**
     * 构建GankModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public GankModule(GankContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GankContract.View provideGankView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GankContract.Model provideGankModel(GankModel model) {
        return model;
    }
}