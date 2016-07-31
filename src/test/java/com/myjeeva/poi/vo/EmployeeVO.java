package com.myjeeva.poi.vo;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * Created by xupeng on 16-5-18.
 */
public class EmployeeVO  implements Serializable {

    private static final long serialVersionUID = 1236211849311396223L;

    private String fgsname;
    private String name;
    private String passwd;
    private String email;
    private String phone;
    private String regionid;
    private String address;
    private String createtime;

    public String getFgsname() {
        return fgsname;
    }

    public void setFgsname(String fgsname) {
        this.fgsname = fgsname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return StringUtils.defaultString(passwd);
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return StringUtils.defaultString(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "EmployeeVO{" +
                "fgsname='" + fgsname + '\'' +
                ", name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", regionid='" + regionid + '\'' +
                ", address='" + address + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
