package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.jessyan.mvparms.demo.di.module.GankModule;

import me.jessyan.mvparms.demo.mvp.ui.fragment.GankFragment;

@ActivityScope
@Component(modules = GankModule.class, dependencies = AppComponent.class)
public interface GankComponent {
    void inject(GankFragment fragment);
}