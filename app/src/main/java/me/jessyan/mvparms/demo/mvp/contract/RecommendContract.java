package me.jessyan.mvparms.demo.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import me.jessyan.mvparms.demo.mvp.model.entity.gank.AndroidBean;
import me.jessyan.mvparms.demo.mvp.model.entity.gank.GankIoDayBean;
import me.jessyan.mvparms.demo.mvp.model.entity.zhihu.NewsTimeLine;
import rx.Observable;


public interface RecommendContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        //void showContent(List<List<AndroidBean>> lists);
       void showContent(NewsTimeLine newsTimeLine);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
        Observable<NewsTimeLine> getContent(boolean update);
        //Observable<GankIoDayBean> getContent(boolean update,String year,String month,String day);
    }
}