package me.jessyan.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import me.jessyan.mvparms.demo.mvp.contract.CustomContract;
import me.jessyan.mvparms.demo.mvp.model.CustomModel;


@Module
public class CustomModule {
    private CustomContract.View view;

    /**
     * 构建CustomModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CustomModule(CustomContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CustomContract.View provideCustomView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CustomContract.Model provideCustomModel(CustomModel model) {
        return model;
    }
}