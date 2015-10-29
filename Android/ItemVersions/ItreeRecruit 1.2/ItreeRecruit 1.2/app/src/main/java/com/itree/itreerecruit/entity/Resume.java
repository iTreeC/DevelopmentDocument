package com.itree.itreerecruit.entity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by tanyadong on 2015/10/17.
 */

//创建表结构
@Table(name="resume_table")

public class Resume {
    @Id(column = "resume_id")
    private int resume_id;
    @Column(column = "resume_name")
    private String resume_name;
    @Column(column = "name")
    private String name;
    @Column(column = "sex")
    private String sex;
    @Column(column = "birthday")
    private String birthday;
    @Column(column = "tel")
    private String tel;
    @Column(column = "email")
    private String email;
    @Column(column = "school_name")
    private String school_name;
    @Column(column = "stime")
    private String stime;
    @Column(column = "etime")
    private String etime;

    public Resume(){};

    public Resume(String resume_name, String sex, String birthday, String tel, String email, String school_name, String stime, String etime) {
        this.resume_name = resume_name;
        this.sex = sex;
        this.birthday = birthday;
        this.tel = tel;
        this.email = email;
        this.school_name = school_name;
        this.stime = stime;
        this.etime = etime;
    }

    public Resume(int resume_id, String resume_name, String sex, String birthday, String tel, String email, String school_name, String stime, String etime) {
        this.resume_id = resume_id;
        this.resume_name = resume_name;
        this.sex = sex;
        this.birthday = birthday;
        this.tel = tel;
        this.email = email;
        this.school_name = school_name;
        this.stime = stime;
        this.etime = etime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResume_id() {
        return resume_id;
    }

    public void setResume_id(int resume_id) {
        this.resume_id = resume_id;
    }

    public String getResume_name() {
        return resume_name;
    }

    public void setResume_name(String resume_name) {
        this.resume_name = resume_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "resume_id=" + resume_id +
                ", resume_name='" + resume_name + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", school_name='" + school_name + '\'' +
                ", stime='" + stime + '\'' +
                ", etime='" + etime + '\'' +
                '}';
    }
}
