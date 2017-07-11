package me.jessyan.mvparms.demo.mvp.model.api.service;



import me.jessyan.mvparms.demo.mvp.model.entity.zhihu.News;
import me.jessyan.mvparms.demo.mvp.model.entity.zhihu.NewsTimeLine;
import me.jessyan.mvparms.demo.mvp.model.entity.zhihu.SplashImage;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Werb on 2016/8/18.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * get Zhihu with retrofit
 */
public interface ZhihuService {
    String ZHIHU_BASE_URL = "http://news-at.zhihu.com/api/4/";

     String DAILY_BASE_URL = "http://app3.qdaily.com/app3/";


    @GET(ZHIHU_BASE_URL+"news/latest")
    Observable<NewsTimeLine> getLatestNews();

    @GET(ZHIHU_BASE_URL+"news/before/{time}")
    Observable<NewsTimeLine> getBeforetNews(@Path("time") String time);

    @GET(ZHIHU_BASE_URL+"news/{id}")
    Observable<News> getDetailNews(@Path("id") String id);
}
