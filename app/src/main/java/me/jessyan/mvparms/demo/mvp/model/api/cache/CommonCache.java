package me.jessyan.mvparms.demo.mvp.model.api.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictProvider;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import me.jessyan.mvparms.demo.mvp.model.entity.User;
import me.jessyan.mvparms.demo.mvp.model.entity.gank.GankIoDataBean;
import me.jessyan.mvparms.demo.mvp.model.entity.gank.GankIoDayBean;
import me.jessyan.mvparms.demo.mvp.model.entity.zhihu.NewsTimeLine;
import rx.Observable;

/**
 * 默认如果未配置以下参数，所有请求如果有缓存数据将不会重新从服务器获取
 @LifeCache设置缓存过期时间. 如果没有设置@LifeCache , 数据将被永久缓存理除非你使用了 EvictProvider,EvictDynamicKey or EvictDynamicKeyGroup .
 EvictProvider可以明确地清理清理所有缓存数据.
 EvictDynamicKey可以明确地清理指定的数据 DynamicKey.
 EvictDynamicKeyGroup 允许明确地清理一组特定的数据. DynamicKeyGroup.
 DynamicKey驱逐与一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求

 DynamicKeyGroup。驱逐一组与key关联的数据，使用EvictDynamicKeyGroup。比如分页，排序或筛选要求

 update==true   如果设置为true，缓存数据将被清理，并且向服务器请求数据
 DynamicKey(userName), new EvictDynamicKey(update)
 */
public interface CommonCache {



    @LifeCache(duration = 60, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<User>>> getUsers(Observable<List<User>> oUsers, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    /* 首页知乎缓存*/
    @LifeCache(duration = 60, timeUnit = TimeUnit.MINUTES)
    Observable<NewsTimeLine> getContent(Observable<NewsTimeLine> newsTimeLineObservable, EvictProvider evictProvider);

    /*福利页面*/
    @LifeCache(duration = 60, timeUnit = TimeUnit.MINUTES)
    Observable<GankIoDataBean> getWelfare(Observable<GankIoDataBean> gankIoDataBeanObservable, DynamicKey dynamicKey,EvictProvider evictProvider);

}
