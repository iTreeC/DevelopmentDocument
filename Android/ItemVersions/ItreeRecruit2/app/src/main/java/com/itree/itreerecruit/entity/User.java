package com.itree.itreerecruit.entity;

/**
 * Created by guanjiwei on 2015/10/24.
 */
public class User {
    private int id;
    private String tel;
    private String email;
    private String permission;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPermission() {
        return permission;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setPermission(String permission) {
        this.permission = permission;
    }
}
