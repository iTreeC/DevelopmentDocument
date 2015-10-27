package com.itree.itreerecruit.entity;

/**
 * Created by dell1 on 2015/10/10.
 */
public class EducateBG {
    private int id;
    private String school_name;
    private String stime;
    private String etime;
    public EducateBG(){};

    public EducateBG(String school_name, String etime, String stime) {
        this.school_name = school_name;
        this.etime = etime;
        this.stime = stime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "EducateBG{" +
                "id=" + id +
                ", school_name='" + school_name + '\'' +
                ", stime='" + stime + '\'' +
                ", etime='" + etime + '\'' +
                '}';
    }
}
