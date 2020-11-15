package com.example.celeiro.ui.menu;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.celeiro.R;

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

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView=inflater.inflate(resource,parent,false);

        TextView tvName=(TextView) convertView.findViewById(R.id.product_name);
        TextView tvPreco=(TextView) convertView.findViewById(R.id.produto_preco);

        tvName.setText(getItem(position).getName());
        tvPreco.setText(String.format("% .02f", getItem(position).getPreco())+"€");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Clicked on: "+getItem(position).getName(), Toast.LENGTH_LONG).show();

                /*Alert Dialog aqui!!!!!*/

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme);
                final View alertview = LayoutInflater.from(getContext()).inflate(
                        R.layout.layout_item_desc,
                        (ConstraintLayout) v.findViewById(R.id.layoutDialog)
                );

                TextView product_name= (TextView) alertview.findViewById(R.id.product_name);
                TextView product_price= (TextView) alertview.findViewById(R.id.produto_preco);
                TextView product_desc= (TextView) alertview.findViewById(R.id.descricaoText);

                product_name.setText(getItem(position).getName());
                product_price.setText(String.format("% .02f", getItem(position).getPreco())+"€");

                if(getItem(position).getDescr().isEmpty() ||getItem(position).getDescr().equals("") ||getItem(position).getDescr()==null ){
                    product_desc.setText("Sem descrição");}
                else{ product_desc.setText(getItem(position).getDescr());}


                builder.setView(alertview);

                final AlertDialog alertDialog = builder.create();

                alertview.findViewById(R.id.btn_cancelar).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertview.findViewById(R.id.btn_adicionar).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();
                    }
                });

                if(alertDialog.getWindow() != null){
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }

                alertDialog.show();
            }
        });

        return convertView;
    }
}
