package com.itree.itreerecruit.entity;

/**
 * Created by dell1 on 2015/10/10.
 */
public class UserInfo {

    private String userName;
    private int id;
    private String sex;
    private String birthday;
//    private String workTime;
//    private String cityLive;
//    private String cityLocation;
    private String tel;
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String isSex() {
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
//
//    public String getWorkTime() {
//        return workTime;
//    }
//
//    public void setWorkTime(String workTime) {
//        this.workTime = workTime;
//    }
//
//    public String getCityLive() {
//        return cityLive;
//    }
//
//    public void setCityLive(String cityLive) {
//        this.cityLive = cityLive;
//    }
//
//    public String getCityLocation() {
//        return cityLocation;
//    }
//
//    public void setCityLocation(String cityLocation) {
//        this.cityLocation = cityLocation;
//    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", id=" + id +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
//                ", workTime='" + workTime + '\'' +
//                ", cityLive='" + cityLive + '\'' +
//                ", cityLocation='" + cityLocation + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
