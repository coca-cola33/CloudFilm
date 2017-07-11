package me.jessyan.mvparms.demo.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictProvider;
import me.jessyan.mvparms.demo.mvp.contract.WelfareContract;
import me.jessyan.mvparms.demo.mvp.model.api.cache.CommonCache;
import me.jessyan.mvparms.demo.mvp.model.api.service.GankService;
import me.jessyan.mvparms.demo.mvp.model.entity.gank.GankIoDataBean;
import rx.Observable;


@ActivityScope
public class WelfareModel extends BaseModel implements WelfareContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public WelfareModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<GankIoDataBean> requestContent(boolean update,String type,int page,int per_page) {
        Observable<GankIoDataBean> gankIoDataBeanObservable=mRepositoryManager.obtainRetrofitService(GankService.class).getGankIoData(type,page,per_page);
        return mRepositoryManager.obtainCacheService(CommonCache.class).getWelfare(gankIoDataBeanObservable,new DynamicKey(page),new EvictProvider(update));
    }
}