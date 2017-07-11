package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.jessyan.mvparms.demo.di.module.AndroidModule;

import me.jessyan.mvparms.demo.mvp.ui.fragment.gank.AndroidFragment;

@ActivityScope
@Component(modules = AndroidModule.class, dependencies = AppComponent.class)
public interface AndroidComponent {
    void inject(AndroidFragment fragment);
}