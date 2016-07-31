package com.myjeeva.poi.vo;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * Created by xupeng on 16-5-18.
 */
public class MemberVO implements Serializable {

    private static final long serialVersionUID = 1245111349311396223L;

    private String id;
    private String name;
    private String fgsname;
    private String fgsygname;
    private String mobile;
    private String email;
    private String loginname;
    private String loginpassword;
    private String realname;
    private String address;
    private String regionid;
    private String businesslicense;
    private String regtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return StringUtils.trim(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFgsname() {
        return StringUtils.trim(fgsname);
    }

    public void setFgsname(String fgsname) {
        this.fgsname = fgsname;
    }

    public String getFgsygname() {
        return StringUtils.trim(fgsygname);
    }

    public void setFgsygname(String fgsygname) {
        this.fgsygname = fgsygname;
    }

    public String getMobile() {
        return StringUtils.trim(mobile);
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return StringUtils.defaultString(StringUtils.trim(email));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginname() {
        return StringUtils.trim(loginname);
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpassword() {
        return StringUtils.defaultString(loginpassword);
    }

    public void setLoginpassword(String loginpassword) {
        this.loginpassword = loginpassword;
    }

    public String getRealname() {
        return StringUtils.trim(realname);
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAddress() {
        return StringUtils.trim(address);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegionid() {
        return StringUtils.trim(regionid);
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getBusinesslicense() {
        return StringUtils.defaultString(StringUtils.trim(businesslicense));
    }

    public void setBusinesslicense(String businesslicense) {
        this.businesslicense = businesslicense;
    }

    public String getRegtime() {
        return StringUtils.trim(regtime);
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "name='" + name + '\'' +
                ", fgsname='" + fgsname + '\'' +
                ", fgsygname='" + fgsygname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + getEmail() + '\'' +
                ", loginname='" + loginname + '\'' +
                ", loginpassword='" + loginpassword + '\'' +
                ", realname='" + realname + '\'' +
                ", address='" + address + '\'' +
                ", regionid='" + regionid + '\'' +
                ", businesslicense='" + businesslicense + '\'' +
                ", regtime='" + regtime + '\'' +
                '}';
    }
}
