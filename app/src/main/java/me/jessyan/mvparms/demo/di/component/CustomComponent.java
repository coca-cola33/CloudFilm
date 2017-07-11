package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.jessyan.mvparms.demo.di.module.CustomModule;

import me.jessyan.mvparms.demo.mvp.ui.fragment.gank.CustomFragment;

@ActivityScope
@Component(modules = CustomModule.class, dependencies = AppComponent.class)
public interface CustomComponent {
    void inject(CustomFragment fragment);
}