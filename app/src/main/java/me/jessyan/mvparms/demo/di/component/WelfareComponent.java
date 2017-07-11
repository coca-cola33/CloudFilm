package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.jessyan.mvparms.demo.di.module.WelfareModule;

import me.jessyan.mvparms.demo.mvp.ui.fragment.gank.WelfareFragment;

@ActivityScope
@Component(modules = WelfareModule.class, dependencies = AppComponent.class)
public interface WelfareComponent {
    void inject(WelfareFragment fragment);
}