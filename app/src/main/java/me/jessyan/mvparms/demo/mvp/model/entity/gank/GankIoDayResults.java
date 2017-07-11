package me.jessyan.mvparms.demo.mvp.model.entity.gank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by phenix on 2017/7/4.
 */

public class GankIoDayResults  implements Serializable{
    private List<AndroidBean> Android;

    private List<AndroidBean> iOS;

    private List<AndroidBean> 前端;

    private List<AndroidBean> 休息视频;

    private List<AndroidBean> 拓展资源;

    private List<AndroidBean> 瞎推荐;

    private List<AndroidBean> 福利;

    public List<AndroidBean> getAndroid() {
        return Android;
    }

    public void setAndroid(List<AndroidBean> android) {
        Android = android;
    }

    public List<AndroidBean> getiOS() {
        return iOS;
    }

    public void setiOS(List<AndroidBean> iOS) {
        this.iOS = iOS;
    }

    public List<AndroidBean> get前端() {
        return 前端;
    }

    public void set前端(List<AndroidBean> 前端) {
        this.前端 = 前端;
    }

    public List<AndroidBean> get休息视频() {
        return 休息视频;
    }

    public void set休息视频(List<AndroidBean> 休息视频) {
        this.休息视频 = 休息视频;
    }

    public List<AndroidBean> get拓展资源() {
        return 拓展资源;
    }

    public void set拓展资源(List<AndroidBean> 拓展资源) {
        this.拓展资源 = 拓展资源;
    }

    public List<AndroidBean> get瞎推荐() {
        return 瞎推荐;
    }

    public void set瞎推荐(List<AndroidBean> 瞎推荐) {
        this.瞎推荐 = 瞎推荐;
    }

    public List<AndroidBean> get福利() {
        return 福利;
    }

    public void set福利(List<AndroidBean> 福利) {
        this.福利 = 福利;
    }
}
