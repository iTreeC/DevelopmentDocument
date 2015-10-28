package com.itree.itreerecruit.entity;

/**
 * Created by lenovo on 2015/10/17.
 * 行业的数据
 */
public class Hyll {
    private int id;
    private String hyll;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHyll() {
        return hyll;
    }

    public void setHyll(String hyll) {
        this.hyll = hyll;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String toString(){
        return "Hyll{"+"id"+id+","+"hyll"+hyll+"}";
    }
}
