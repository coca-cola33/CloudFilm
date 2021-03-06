package me.jessyan.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import me.jessyan.mvparms.demo.mvp.contract.RecommendContract;
import me.jessyan.mvparms.demo.mvp.model.RecommendModel;


@Module
public class RecommendModule {
    private RecommendContract.View view;

    /**
     * 构建RecommendModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public RecommendModule(RecommendContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    RecommendContract.View provideRecommendView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    RecommendContract.Model provideRecommendModel(RecommendModel model) {
        return model;
    }
}