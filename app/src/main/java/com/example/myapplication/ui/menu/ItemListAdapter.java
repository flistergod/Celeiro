package com.example.myapplication.ui.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.ArrayList;


public class ItemListAdapter extends ArrayAdapter<Item> {

    private  static final String TAG ="CosmicBodyListAdapter";
    private Context context;
    int resource;

    public ItemListAdapter( Context context, int resource,  ArrayList<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name=getItem(position).getName();
        int id=getItem(position).getFamilia();
        double preco=getItem(position).getPreco();

        Item item = new Item(name, preco,id);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView=inflater.inflate(resource,parent,false);

        TextView tvName=(TextView) convertView.findViewById(R.id.product_name);
        TextView tvPreco=(TextView) convertView.findViewById(R.id.produto_preco);

        tvName.setText(name);
        tvPreco.setText(String.format("% .02f", preco)+"€");

        return convertView;
    }
}
