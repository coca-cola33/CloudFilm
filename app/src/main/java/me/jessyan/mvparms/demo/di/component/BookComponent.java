package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.jessyan.mvparms.demo.di.module.BookModule;

import me.jessyan.mvparms.demo.mvp.ui.fragment.BookFragment;

@ActivityScope
@Component(modules = BookModule.class, dependencies = AppComponent.class)
public interface BookComponent {
    void inject(BookFragment fragment);
}