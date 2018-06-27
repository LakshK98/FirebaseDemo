package com.example.szdemo;

import java.io.Serializable;

/**
 * Created by lakshkotian on 04/04/18.
 */

public class PG implements Serializable{
    private String address;
     private long contact;
    private String gender;
    private String name;


    private int price;
    private String sharing;

    public PG(){}
    public PG(String address, long contact, String gender, String name, int price, String sharing){
        this.address = address;
        this.contact = contact;
        this.gender = gender;
        this.name = name;
        this.price = price;
        this.sharing = sharing;
    }


    public String getName() {
        return name;
    }
    public long getContact() {
        return contact;
    }
    public String getGender() {
        return gender;
    }

    public int getPrice() {
        return price;
    }

    public String getSharing() {
        return sharing;
    }

    public String getAddress() {
        return address;
    }
}
