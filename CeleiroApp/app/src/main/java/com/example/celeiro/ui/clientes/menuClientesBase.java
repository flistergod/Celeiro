package com.example.celeiro.ui.clientes;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.celeiro.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class menuClientesBase extends Fragment {

    String IP="https://www.celeironisa.pt/app/php_app.php?f=";

    public menuClientesBase() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_clientes_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button progpontos = view.findViewById(R.id.b_pontos);
        ImageButton dados = view.findViewById(R.id.dados);
        ImageButton promo = view.findViewById(R.id.promo);
        ImageButton encomenda = view.findViewById(R.id.encomenda);
        final Bundle bundle= new Bundle();
        bundle.putString("username", getArguments().getString("username"));

      loadData(view);
       // showAlert(getArguments().get("username").toString());

        progpontos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_programaPontos);
            }
        });

        dados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.menuClientesDados, bundle);
            }
        });

        promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.menuClientesPromo, bundle);
            }
        });

        encomenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.menuClientesEncomenda, bundle);
            }
        });

    }


    public void loadData(final View view) {

        final String username=getArguments().getString("username").trim();

//é utilizado o volley
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = IP+"load_data";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("APPLOG", response);
                        parseJson(response, view);
                        //processa-se o Json
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("APPLOG", error.toString());
                        showAlert("Error loading data");
                    }
                }
        ){
            @Override
            protected Map<String,String> getParams()
            {
//parametros usados no ficheiro php
                Map<String,String> params = new HashMap<String,String>();
                params.put("post_username", username.trim());

                return params;
            }
        };
        queue.add(postRequest);
    }


    // toast message
    private void showAlert(String msg) {
        Toast.makeText(getContext(),msg, Toast.LENGTH_LONG).show();
    }


    //processa o Json
    public  void parseJson(String jsonStr, View v){

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(jsonStr);
           // showAlert(jsonObject.toString());


            if(jsonObject.get("LOAD").equals("OK")){
//se foi encontrada a conta, "entra" na app

                TextView textView_nome = v.findViewById(R.id.textview_nome);
                textView_nome.setText(jsonObject.get("USERNAME").toString());
            }
            else{
//não foi encontrada a conta
                Toast.makeText(getContext(),"Error loading data",Toast.LENGTH_LONG).show();
            }

        }catch (JSONException e){

        }
    }
}
