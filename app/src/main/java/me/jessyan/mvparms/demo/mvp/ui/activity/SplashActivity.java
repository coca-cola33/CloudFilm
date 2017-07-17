package me.jessyan.mvparms.demo.mvp.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import java.util.Random;

import butterknife.BindView;
import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.app.ConstantsImgUrls;
import me.jessyan.mvparms.demo.app.utils.NoDoubleClickListener;
import me.jessyan.mvparms.demo.di.component.DaggerSplashComponent;
import me.jessyan.mvparms.demo.di.module.SplashModule;
import me.jessyan.mvparms.demo.mvp.contract.SplashContract;
import me.jessyan.mvparms.demo.mvp.presenter.SplashPresenter;

/**
 * Created by phenix on 2017/6/14.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View{

    @BindView(R.id.iv_default_pic)
    ImageView iv_default_pic;
    @BindView(R.id.tv_jump)
    TextView tv_jump;
    @BindView(R.id.iv_pic)
    ImageView iv_pic;
    private boolean isIn;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSplashComponent.builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        iv_default_pic.setImageResource(R.drawable.img_transition_default);

        //随机生成一个数组长度内的数
        int i = new Random().nextInt(ConstantsImgUrls.TRANSITION_URLS.length);
        Glide.with(this).load(ConstantsImgUrls.TRANSITION_URLS[i])
                .placeholder(R.drawable.img_transition_default)
                .error(R.drawable.img_transition_default)
                .into(iv_pic);

        tv_jump.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                toMainActivity();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(iv_default_pic!=null) {
                    iv_default_pic.setVisibility(View.GONE);
                }
            }
        },3500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               toMainActivity();
            }
        }, 3500);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }
    private void toMainActivity() {
        if (isIn) {
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
        isIn = true;
    }
}
