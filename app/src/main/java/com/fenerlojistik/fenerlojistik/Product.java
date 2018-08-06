package com.fenerlojistik.fenerlojistik;

import org.parceler.Parcel;

@Parcel
public class Product {
    public String sender;
    public String sentTo;
    public String code;
    public String statue;

    public Product(String sender,String sentTo, String code, String statue) {
        this.sender = sender;
        this.sentTo =sentTo;
        this.code = code;
        this.statue = statue;
    }
    public Product(){

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }
}
