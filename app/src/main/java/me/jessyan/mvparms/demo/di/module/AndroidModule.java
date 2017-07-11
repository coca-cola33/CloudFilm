package me.jessyan.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import me.jessyan.mvparms.demo.mvp.contract.AndroidContract;
import me.jessyan.mvparms.demo.mvp.model.AndroidModel;


@Module
public class AndroidModule {
    private AndroidContract.View view;

    /**
     * 构建AndroidModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AndroidModule(AndroidContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AndroidContract.View provideAndroidView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AndroidContract.Model provideAndroidModel(AndroidModel model) {
        return model;
    }
}