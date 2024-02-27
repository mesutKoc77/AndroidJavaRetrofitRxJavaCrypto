package com.example.androidretrofitjava.service;

import com.example.androidretrofitjava.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {


    //Get, post, update, delete

    //url base www.website.com
    //Get = proce?key=xxx

    //https://api.nomics.com/v1/
    // prices?key=2187154b76945f2373394aa34f7dc98a
    //https://raw.githubusercontent.com/
    // atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET ("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    //bu get islemini hangi method ile yapacagiz?
    //biz simdi gozlenlenebilir bir obje oilusturacgz ve bu obje yayin yapacak Yani bizim istedigimiz kaynkata bir degisiklik oldugunda bu degisikilleri gozlemleyen kaynaklara
    //haber veren veya yayin yapan bir obje denebilir.
    //Bunun aynisini Call ile yapiyorduk ama artik observable ile yapiyoouz.

    Observable<List<CryptoModel>> getData();

   // Call<List<CryptoModel>> getData(); //bana doneck olan uzunca bir liste oldugu icin donecek deger listtir.Burada List turu dedik ki kodun daha esnek olmasini saglayabilidk ve
    //ve Array olarak kodu darlatmadik.

    //RxJava ile calisiryorsak burada Call nesnesini kullanmmamiz geekiyor-









}
