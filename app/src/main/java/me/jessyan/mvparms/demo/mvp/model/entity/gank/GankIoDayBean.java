package me.jessyan.mvparms.demo.mvp.model.entity.gank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by phenix on 2017/7/4.
 */

public class GankIoDayBean  implements Serializable{

    private List<String> category;
    private boolean error;
    private GankIoDayResults results;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public GankIoDayResults getResults() {
        return results;
    }

    public void setResults(GankIoDayResults results) {
        this.results = results;
    }
}
