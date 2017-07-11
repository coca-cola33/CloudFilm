package me.jessyan.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import me.jessyan.mvparms.demo.mvp.contract.BookContract;
import me.jessyan.mvparms.demo.mvp.model.BookModel;


@Module
public class BookModule {
    private BookContract.View view;

    /**
     * 构建BookModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public BookModule(BookContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    BookContract.View provideBookView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    BookContract.Model provideBookModel(BookModel model) {
        return model;
    }
}