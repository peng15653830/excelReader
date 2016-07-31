package com.myjeeva.poi.vo;

import java.io.Serializable;

/**
 * Created by xupeng on 16-5-18.
 */
public class CompanyVO implements Serializable {

    private static final long serialVersionUID = 1136221349311396223L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
