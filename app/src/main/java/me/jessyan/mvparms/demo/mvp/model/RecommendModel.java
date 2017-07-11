package me.jessyan.mvparms.demo.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import io.rx_cache.EvictProvider;
import me.jessyan.mvparms.demo.mvp.contract.RecommendContract;
import me.jessyan.mvparms.demo.mvp.model.api.cache.CommonCache;
import me.jessyan.mvparms.demo.mvp.model.api.service.ZhihuService;
import me.jessyan.mvparms.demo.mvp.model.entity.zhihu.NewsTimeLine;
import rx.Observable;


@ActivityScope
public class RecommendModel extends BaseModel implements RecommendContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public RecommendModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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

    /**
     *
     * @param update update==true   如果设置为true，缓存数据将被清理，并且向服务器请求数据
     * @return
     */
    @Override
    public Observable<NewsTimeLine> getContent(boolean update) {
        Observable<NewsTimeLine> newsTimeLineObservable=mRepositoryManager.obtainRetrofitService(ZhihuService.class).getLatestNews();
        return  mRepositoryManager.obtainCacheService(CommonCache.class).getContent(newsTimeLineObservable,new EvictProvider(update));
    }

//    @Override
//    public Observable<GankIoDayBean> getContent(boolean update,String year,String month,String day) {
//        Observable<GankIoDayBean> gankIoDayBeanObservable=mRepositoryManager.obtainRetrofitService(GankService.class).getGankIoDay(year,month,day);
//        return mRepositoryManager.obtainCacheService(CommonCache.class)
//                .getContent(gankIoDayBeanObservable,new EvictProvider(update));
//    }
}