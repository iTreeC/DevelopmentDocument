package com.itree.itreerecruit.entity;

/**
 * Created by lenovo on 2015/10/14.
 * 工作地点表中的数据
 */
public class JobAddress {
    private int id;
    private String address;
    private int type;
    private int superaddress;
    private boolean biaoshi;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public int getSuperaddress() {
        return superaddress;
    }

    public void setSuperaddress(int superaddress) {
        this.superaddress = superaddress;
    }

    public boolean isBiaoshi() {
        return biaoshi;
    }

    public void setBiaoshi(boolean biaoshi) {
        this.biaoshi = biaoshi;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



    public String toString(){
        return "JobAddress{"+"id"+id+","+"address"+address+"}";
    }



}
