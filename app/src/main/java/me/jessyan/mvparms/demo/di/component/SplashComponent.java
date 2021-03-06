package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import me.jessyan.mvparms.demo.di.module.SplashModule;
import me.jessyan.mvparms.demo.mvp.ui.activity.SplashActivity;

/**
 * Created by phenix on 2017/6/14.
 */
@ActivityScope
@Component(modules = SplashModule.class,dependencies = AppComponent.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}
