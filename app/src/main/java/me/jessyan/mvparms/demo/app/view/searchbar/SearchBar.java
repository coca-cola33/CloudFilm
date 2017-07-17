package me.jessyan.mvparms.demo.app.view.searchbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import me.jessyan.mvparms.demo.R;


/**
 * Created by phenix on 2017/7/12.
 */

public class SearchBar extends RelativeLayout {

    private float mHeight=0f;

    public SearchBar(Context context) {
        this(context,null);
    }

    public SearchBar(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta=attrs==null?null:getContext().obtainStyledAttributes(attrs, R.styleable.SearchBar);
        if(ta!=null){
            mHeight=ta.getDimension(R.styleable.SearchBar_search_bar_float_height,mHeight);
            ta.recycle();
        }
        initView();
    }

    private void initView() {

    }


}
