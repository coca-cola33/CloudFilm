package me.jessyan.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import me.jessyan.mvparms.demo.mvp.contract.OneContract;
import me.jessyan.mvparms.demo.mvp.model.OneModel;


@Module
public class OneModule {
    private OneContract.View view;

    /**
     * 构建OneModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public OneModule(OneContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    OneContract.View provideOneView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    OneContract.Model provideOneModel(OneModel model) {
        return model;
    }
}