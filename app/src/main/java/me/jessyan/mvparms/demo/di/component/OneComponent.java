package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.jessyan.mvparms.demo.di.module.OneModule;

import me.jessyan.mvparms.demo.mvp.ui.fragment.OneFragment;

@ActivityScope
@Component(modules = OneModule.class, dependencies = AppComponent.class)
public interface OneComponent {
    void inject(OneFragment fragment);
}