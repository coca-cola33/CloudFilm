package me.jessyan.mvparms.demo.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import me.jessyan.mvparms.demo.mvp.model.entity.gank.GankIoDataBean;
import me.jessyan.mvparms.demo.mvp.model.entity.gank.ResultBean;
import rx.Observable;


public interface WelfareContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void showContent(List<ResultBean> urls);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
        Observable<GankIoDataBean> requestContent(boolean update,String type,int page,int per_page);
    }
}