package com.myjeeva.poi.vo;

import java.io.Serializable;

/**
 * Created by xupeng on 16-5-18.
 */
public class RegionVO implements Serializable {

    private static final long serialVersionUID = 1136221349311396223L;

    private String regionId;
    private String regionName;
    private String parentRegionId;
    private String parentRegionName;


    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getParentRegionId() {
        return parentRegionId;
    }

    public void setParentRegionId(String parentRegionId) {
        this.parentRegionId = parentRegionId;
    }

    public String getParentRegionName() {
        return parentRegionName;
    }

    public void setParentRegionName(String parentRegionName) {
        this.parentRegionName = parentRegionName;
    }
}
