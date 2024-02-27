package com.example.androidretrofitjava.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidretrofitjava.R;
import com.example.androidretrofitjava.model.CryptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    //mainActivity de bana donen degerlelerin tutuldugu arraylisti buraya getirecegim

    private ArrayList<CryptoModel> cryptoModelArrayList;
    private String [] colors={"#ffff00","#ff8383","#fffd79","#421a92","#66e599","#ff7e8a","#48bf53","#eda323"};

    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoModelArrayList) {
        this.cryptoModelArrayList = cryptoModelArrayList;
    }


    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new RowHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(cryptoModelArrayList.get(position),colors,position);

    }

    @Override
    public int getItemCount() {
        //mainActivity de bana donen degerlelerin tutuldugu arraylisti buraya getirecegim.
        return cryptoModelArrayList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textPrice;
        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(CryptoModel cryptoModel, String [] colors, Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));
            textName=itemView.findViewById(R.id.text_name);
            textPrice=itemView.findViewById(R.id.text_price);
            textName.setText(cryptoModel.currency);
            textPrice.setText(cryptoModel.price);

        }


    }
}
