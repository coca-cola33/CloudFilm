package me.jessyan.mvparms.demo.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import me.jessyan.mvparms.demo.mvp.contract.SplashContract;

/**
 * Created by phenix on 2017/6/14.
 */
@ActivityScope
public class SplashModel extends BaseModel implements SplashContract.Model{
    @Inject
    public SplashModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
