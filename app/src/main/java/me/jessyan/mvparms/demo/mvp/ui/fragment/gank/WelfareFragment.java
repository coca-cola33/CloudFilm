package me.jessyan.mvparms.demo.mvp.ui.fragment.gank;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;

import com.jess.arms.utils.UiUtils;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.jessyan.mvparms.demo.app.view.BigPhotoActiviy;
import me.jessyan.mvparms.demo.di.component.DaggerWelfareComponent;
import me.jessyan.mvparms.demo.di.module.WelfareModule;
import me.jessyan.mvparms.demo.mvp.contract.WelfareContract;
import me.jessyan.mvparms.demo.mvp.model.entity.gank.ResultBean;
import me.jessyan.mvparms.demo.mvp.presenter.WelfarePresenter;

import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.mvp.ui.adapter.WelfareAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class WelfareFragment extends BaseFragment<WelfarePresenter> implements WelfareContract.View {

    @BindView(R.id.xrv_welfare)
    XRecyclerView xrv_welfare;

    private int page=1;

    private WelfareAdapter mWelfareAdapter;

    private List<ResultBean> resultBeanList;
    private  ArrayList<String> imgList;

    private boolean isRefresh=false;

    public static WelfareFragment newInstance() {
        WelfareFragment fragment = new WelfareFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerWelfareComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .welfareModule(new WelfareModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welfare, container, false);
    }

    @Override
    public void initData() {
        initXRecyclerView();
        mPresenter.getContent(isRefresh,page);

    }

    private void initXRecyclerView(){
        if(resultBeanList==null){
            resultBeanList=new ArrayList<>();}
        if(imgList==null){
            imgList=new ArrayList<>();
        }
        mWelfareAdapter=new WelfareAdapter(resultBeanList);


        xrv_welfare.setPullRefreshEnabled(true);
        xrv_welfare.setLoadingMoreEnabled(true);
        //构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流
        xrv_welfare.setLayoutManager(new GridLayoutManager(getContext(),2));
        xrv_welfare.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                resultBeanList.clear();
                imgList.clear();
                isRefresh=true;
                mPresenter.getContent(isRefresh,page);
            }

            @Override
            public void onLoadMore() {
                page++;
                isRefresh=false;
                mPresenter.getContent(isRefresh,page);
            }
        });
        xrv_welfare.setAdapter(mWelfareAdapter);
    }


    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void showContent(List<ResultBean> list) {
        if(mWelfareAdapter==null){
            mWelfareAdapter=new WelfareAdapter(resultBeanList);
        }
        for(int i=0;i<list.size();i++){
            ResultBean bean=list.get(i);
            imgList.add(bean.getUrl());
        }
        resultBeanList.addAll(list);
        mWelfareAdapter.notifyDataSetChanged();
        mWelfareAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                UiUtils.SnackbarText("onItemClick");


//                LogUtils.debugInfo("-----" + imgList.toString());
//                LogUtils.debugInfo("----imgList.size():  " + imgList.size());
//                Bundle bundle=new Bundle();
//                bundle.putInt("position",position);
//                bundle.putStringArrayList("list",imgList);
//                Intent intent=new Intent(getActivity(), BigPhotoActiviy.class);
//                intent.putExtras(bundle);
//                getActivity().startActivity(intent);
            }
        });
        if(isRefresh){
            xrv_welfare.refreshComplete();
        }else{
            xrv_welfare.loadMoreComplete();
        }
    }
}