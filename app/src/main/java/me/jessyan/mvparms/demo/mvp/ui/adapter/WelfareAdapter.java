package me.jessyan.mvparms.demo.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.mvp.model.entity.gank.ResultBean;
import me.jessyan.mvparms.demo.mvp.ui.holder.WelfareItemHolder;

/**
 * Created by phenix on 2017/7/6.
 */

public class WelfareAdapter extends DefaultAdapter<ResultBean> {
    public WelfareAdapter(List<ResultBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<ResultBean> getHolder(View v, int viewType) {
        return new WelfareItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.adapter_welfare_item;
    }
}
