package me.jessyan.mvparms.demo.app.view;


import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.mvparms.demo.R;

/**
 * Created by phenix on 2017/7/6.
 */

public class BigPhotoActiviy extends RxFragmentActivity{

    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.vp_content)
    FixedViewPager vp_content;
    @OnClick(R.id.tv_save)
    void onClick(){
        /**保存图片**/


    }
    private List<String> imgUrls;
    private ViewPagerAdapter adapter;
    private int mCurrentItem=0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_big_photo);

        imgUrls=new ArrayList<>();
        /**获取数据**/
        Bundle bundle=getIntent().getExtras();
        imgUrls=bundle.getStringArrayList("list");
        mCurrentItem=bundle.getInt("position");

        adapter=new ViewPagerAdapter(imgUrls,this);
        vp_content.setAdapter(adapter);
        vp_content.setCurrentItem(mCurrentItem);
        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_count.setText((position+1)+"/"+imgUrls.size());
                mCurrentItem=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



}
