package me.jessyan.mvparms.demo.mvp.presenter;

import android.app.Application;
import android.content.pm.ActivityInfo;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.mvparms.demo.app.ConstantsImgUrls;
import me.jessyan.mvparms.demo.app.utils.RxUtils;
import me.jessyan.mvparms.demo.mvp.model.entity.gank.GankIoDataBean;
import me.jessyan.mvparms.demo.mvp.model.entity.gank.ResultBean;
import me.jessyan.mvparms.demo.mvp.model.entity.zhihu.NewsTimeLine;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import me.jessyan.mvparms.demo.mvp.contract.WelfareContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;


@ActivityScope
public class WelfarePresenter extends BasePresenter<WelfareContract.Model, WelfareContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    private String type="福利";
    @Inject
    public WelfarePresenter(WelfareContract.Model model, WelfareContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void getContent(boolean uodate,int page){
        mModel.requestContent(uodate,type,page, ConstantsImgUrls.PER_PAGE)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3,2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mRootView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mRootView.hideLoading();
                    }
                })
                .compose(RxUtils.bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<GankIoDataBean>(mErrorHandler) {
                    @Override
                    public void onNext(GankIoDataBean gankIoDataBean) {

                        if(gankIoDataBean.isError()) {
                            //TODO 错误
                        }else {
                            if(gankIoDataBean!=null&&gankIoDataBean.getResults()!=null&&gankIoDataBean.getResults().size()>0) {
//                                for(int i=0;i<gankIoDataBean.getResults().size();i++){
//                                    urls.add(gankIoDataBean.getResults().get(i).getUrl());
//                                }

                                mRootView.showContent(gankIoDataBean.getResults());
                            }
                        }

                    }
                });
    }

//    public void requestPermission(){
//        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
//            @Override
//            public void onRequestPermissionSuccess() {
//                Matisse.from(getActivity())
//                        .choose(MimeType.ofAll(), false) // 选择 mime 的类型
//                        .countable(true)
//                        .maxSelectable(9) // 图片选择的最多数量
//                        //.gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
//                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                        .thumbnailScale(0.85f) // 缩略图的比例
//                        .imageEngine(new GlideEngine()) // 使用的图片加载引擎
//                        .forResult(1); // 设置作为标记的请求码
//            }
//        }, new RxPermissions(mRootView),this,mErrorHandler);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}