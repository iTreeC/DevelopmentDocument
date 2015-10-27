package com.itree.itreerecruit.entity;

/**
 * Created by lenovo on 2015/10/17.
 */
public class Zwll {
    private int id;
    private String Zwll;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZwll() {
        return Zwll;
    }

    public void setZwll(String zwll) {
        Zwll = zwll;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String toString(){
        return "Zwll{"+"id"+id+","+"Zwll"+Zwll+"}";
    }


}
