package com.example.androidretrofitjava.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {

    @SerializedName("currency") //bizim Api den alacagimz degiskenin adi ?
    public String currency;

    @SerializedName("price") //bizim Api den alacagimz degiskenin adi ?
    public String price;

}
