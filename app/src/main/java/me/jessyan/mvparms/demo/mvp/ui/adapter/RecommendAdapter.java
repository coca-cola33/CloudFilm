package me.jessyan.mvparms.demo.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.mvp.model.entity.gank.AndroidBean;
import me.jessyan.mvparms.demo.mvp.model.entity.zhihu.Stories;
import me.jessyan.mvparms.demo.mvp.ui.holder.StoriesItemHolder;

/**
 * 每日推荐
 */
public class RecommendAdapter extends DefaultAdapter<Stories> {


    public RecommendAdapter(List<Stories> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<Stories> getHolder(View v, int viewType) {
        return new StoriesItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.adapter_recommend_item;
    }
}
