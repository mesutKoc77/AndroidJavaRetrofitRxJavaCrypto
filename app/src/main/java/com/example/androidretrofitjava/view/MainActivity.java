package com.example.androidretrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.example.androidretrofitjava.R;
import com.example.androidretrofitjava.adapter.RecyclerViewAdapter;
import com.example.androidretrofitjava.model.CryptoModel;
import com.example.androidretrofitjava.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.ArrayList;

import java.util.List;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CryptoModel> arrayListCryptoModels;
    private String BASE_URL="https://raw.githubusercontent.com/";
    Retrofit retrofit;

    RecyclerView recyclerView;

    RecyclerViewAdapter recyclerViewAdapter;

    CompositeDisposable compositeDisposable; //Api den ektigimiz bilgiler veya api den yapilan call lar belli bir sure sonra hafizadan temizlenmesi gerekiyor. Bunlari hafizadan temizlenmemiz gerekiyor
    //


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://api.nomics.com/v1/prices?key=2187154b76945f2373394aa34f7dc98a
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

        //REtrofit & Json
        recyclerView=findViewById(R.id.recyclerView);
        Gson gson=new GsonBuilder().setLenient().create();

        retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        loadData();





    }

    private void loadData(){



        final CryptoAPI cryptoAPI=retrofit.create(CryptoAPI.class);
        compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(cryptoAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse) //burada ben api den bilguileri aldim ve handleReponse methodundaki PArametreye atdim ve bu method da bu donen bilgileri isledi.
                //gelen veriler List<CryptoModel> turunden olacagi icin handle eden Methiod da parametre olarak bunu alacak ve isleyecek.
                //this::handleResponse ifadesi, Observable'ın sonucunu işlemek için handleResponse adlı bir metodun kullanılacağını belirtir.
        );


        /*
        Call<List<CryptoModel>> call=cryptoAPI.getData();
        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if(response.isSuccessful()){

                    List<CryptoModel> responseList=response.body();

                    assert responseList != null;
                    arrayListCryptoModels =new ArrayList<>(responseList);


                    //recylerView islmelerine burda devam ediyouz.
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter=new RecyclerViewAdapter(arrayListCryptoModels); //verileri aldik ve bu veriler ile de Adaptoru muzu olusutrudruk.
                    recyclerView.setAdapter(recyclerViewAdapter);


                    for (CryptoModel cryptoModel: arrayListCryptoModels){
                        System.out.println(cryptoModel.currency);
                        System.out.println(cryptoModel.price);

                    }




                }

            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace(); //hatayi benim Logcat ime yazdir demek.

            }
        });

         */

    }
    private void handleResponse(List<CryptoModel> listCryptoModels){
        arrayListCryptoModels=new ArrayList<>(listCryptoModels);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter=new RecyclerViewAdapter(arrayListCryptoModels);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}