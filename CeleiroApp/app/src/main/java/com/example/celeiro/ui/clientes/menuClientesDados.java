package com.example.celeiro.ui.clientes;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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
public class menuClientesDados extends Fragment {

    String IP="https://www.celeironisa.pt/app/php_app.php?f=";
    boolean foward=true;
     String password;

    public menuClientesDados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.nav_menu_clientes_dados, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button progpontos = view.findViewById(R.id.b_pontos);
        ImageButton promo = view.findViewById(R.id.promo);
        ImageButton encomenda = view.findViewById(R.id.encomenda);
        Button editar = view.findViewById(R.id.b_editar);
        final Bundle bundle= new Bundle();
        bundle.putString("username", getArguments().getString("username"));

        loadData(view);


        progpontos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_programaPontos, bundle);
            }
        });

        promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.menuClientesPromo,bundle);
            }
        });

        encomenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.menuClientesEncomenda, bundle);
            }
        });

        encomenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.menuClientesEncomenda, bundle);
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Alert Dialog aqui!!!!!*/

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme);
                final View alertview = LayoutInflater.from(getContext()).inflate(
                        R.layout.alert_dialog_layout,
                        (ConstraintLayout) v.findViewById(R.id.layoutDialog)
                );
                builder.setView(alertview);

                final AlertDialog alertDialog = builder.create();
                final EditText editPassword = (EditText) alertview.findViewById(R.id.password);

                alertview.findViewById(R.id.Cancelar).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertview.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        password=editPassword.getText().toString().trim();
                        //  showAlert(password+"4");
                        confirmPassword(view, getArguments().getString("username"), password);
                        alertDialog.dismiss();
                    }
                });

                if(alertDialog.getWindow() != null){
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }

                alertDialog.show();
            }
        });

    }


    public void loadData(final View view) {

        final String username=getArguments().getString("username").trim();

//é utilizado o volley
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = IP+"load_data";;

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("APPLOG", response);
                        parseJson_load(response, view);
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
    public  void parseJson_load(String jsonStr, View v){

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(jsonStr);
            // showAlert(jsonObject.toString());

            if(jsonObject.get("LOAD").equals("OK")){
//se foi encontrada a conta, "entra" na app

                //  showAlert(jsonObject.get("NOME").toString() + " "+jsonObject.get("SOBRENOME").toString());
                TextView textView_username = v.findViewById(R.id.textview_nome);
                EditText textView_nome = v.findViewById(R.id.nomeutilizador);
                EditText textView_sobrenome = v.findViewById(R.id.f_sobrenomeutilizador);
                EditText textView_rua = v.findViewById(R.id.f_morada);
                EditText textView_numero = v.findViewById(R.id.f_numero);
                EditText textView_localidade = v.findViewById(R.id.f_localidade);
                EditText textView_cp = v.findViewById(R.id.f_codpostal);
                EditText textView_email = v.findViewById(R.id.email);
                EditText textView_password = v.findViewById(R.id.password);
                EditText textView_nif = v.findViewById(R.id.nif);
                EditText textView_tlf = v.findViewById(R.id.tlf);

                textView_username.setText(jsonObject.get("USERNAME").toString().trim());
                textView_nome.setText(jsonObject.get("NOME").toString().trim());
                textView_sobrenome.setText(jsonObject.get("SOBRENOME").toString().trim());
                textView_rua.setText(jsonObject.get("RUA").toString().trim());
                textView_numero.setText(jsonObject.get("NUMERO").toString().trim());
                textView_localidade.setText(jsonObject.get("LOCALIDADE").toString().trim());
                textView_cp.setText(jsonObject.get("CP").toString().trim());
                textView_email.setText(jsonObject.get("EMAIL").toString().trim());
                textView_password.setText(jsonObject.get("PASSWORD").toString().trim());
                textView_nif.setText(jsonObject.get("NIF").toString().trim());
                textView_tlf.setText(jsonObject.get("TM").toString().trim());

            }
            else{
//não foi encontrada a conta
                Toast.makeText(getContext(),"Error loading data",Toast.LENGTH_LONG).show();
            }

        }catch (JSONException e){

        }
    }

    public  void confirmPassword(final View view, final String username, final String password){

//é utilizado o volley
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "http://"+IP+"/celeiro_app/conn.php?f=validatePassword";;

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("APPLOG", response);
                    parseJson_validadePassword(response, view, password);

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

    public void  editData(final View v){

         long nif_int=0;
         long textView_tlf=0;


        EditText nome= (EditText) v.findViewById(R.id.nomeutilizador);
        EditText sobrenome= (EditText) v.findViewById(R.id.f_sobrenomeutilizador);
        EditText rua= (EditText) v.findViewById(R.id.f_morada);
        EditText numero= (EditText) v.findViewById(R.id.f_numero);
        EditText localidade= (EditText) v.findViewById(R.id.f_localidade);
        EditText cp= (EditText) v.findViewById(R.id.f_codpostal);
        EditText email= (EditText) v.findViewById(R.id.email);
        EditText nif= (EditText) v.findViewById(R.id.nif);
        EditText tlf= (EditText) v.findViewById(R.id.tlf);
        EditText password= (EditText) v.findViewById(R.id.password);


        final String textView_nome = nome.getText().toString().trim();
        final String textView_sobrenome = sobrenome.getText().toString().trim();
        final String textView_rua = rua.getText().toString().trim();
        final String textView_localidade = localidade.getText().toString().trim();
        final String textView_cp = cp.getText().toString().trim();
        final String textView_email = email.getText().toString().trim();
        final String textView_nif = nif.getText().toString().trim();
        final String textView_password = password.getText().toString().trim();
        final String textView_numero = numero.getText().toString().trim();

        try {
              nif_int=Long.parseLong(textView_nif);
             textView_tlf = Long.parseLong(tlf.getText().toString().trim());
        }catch (Exception e){foward=false;}


        try {

                foward=true;

                if (textView_nome.isEmpty()) {
                    foward = false;
                } else if (textView_sobrenome.isEmpty()) {
                    foward = false;
                } else if (textView_rua.isEmpty()) {
                    foward = false;
                } else if (textView_numero.isEmpty()) {
                    foward = false;
                } else if (textView_localidade.isEmpty()) {
                    foward = false;
                } else if (textView_cp.isEmpty() || !textView_cp.contains("-")) {
                    foward = false;
                } else if (textView_email.isEmpty() || !textView_email.contains("@")) {
                    foward = false;
                }
                else if(nif_int<0 || String.valueOf(nif_int).length()!=9 || String.valueOf(nif_int).isEmpty()){foward=false;}
                else if(textView_tlf<0 || String.valueOf(textView_tlf).length()<9 || String.valueOf(textView_tlf).isEmpty()){foward=false;}
                else if(textView_password.isEmpty()){foward=false;}


                if (foward == false) {
                    showAlert("Erro ao editar dados");
                }

                else{

                    final String username=getArguments().getString("username").trim();


//é utilizado o volley
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    String url = "http://"+IP+"/celeiro_app/conn.php?f=edit_data";


                    final long finalNif_int = nif_int;
                    final long finalTextView_tlf = textView_tlf;


                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("APPLOG", response);
                                    parseJson_edit(response, v);
                                    //processa-se o Json
                                    //showAlert(textView_email);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    Log.d("APPLOG", error.toString());
                                    showAlert("Error editing data");

                                }
                            }
                    ){
                        @Override
                        protected Map<String,String> getParams()
                        {
//parametros usados no ficheiro php
                            Map<String,String> params = new HashMap<String,String>();

                            params.put("post_nome", textView_nome.trim());
                            params.put("post_username", username.trim());
                            params.put("post_sobrenome", textView_sobrenome.trim());
                            params.put("post_rua", textView_rua.trim());
                            params.put("post_num", textView_numero.trim());
                            params.put("post_localidade", textView_localidade.trim());
                            params.put("post_cp", textView_cp.trim());
                            params.put("post_email", textView_email.trim());
                            params.put("post_nif", String.valueOf(finalNif_int).trim());
                            params.put("post_telemovel", String.valueOf(finalTextView_tlf).trim());
                            params.put("post_password", textView_password.trim());

                            return params;
                        }

                    };
                    queue.add(postRequest);

                }

        }catch (Exception e){foward=false;
            showAlert("Invalid data, could not edit2");}


    }


    public  void parseJson_edit(String jsonStr, View v){

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(jsonStr);
            // showAlert(jsonObject.toString());


            if(jsonObject.get("EDIT").equals("OK")){
//se foi encontrada a conta, "entra" na app

              showAlert("Dados editados com sucesso!");
              loadData(v);

            }
            else {
//não foi encontrada a conta

                    showAlert(jsonObject.get("MSG").toString());
            }


        }catch (JSONException e){

        }
    }

    public  void  parseJson_validadePassword(String jsonStr, View v, String password){

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(jsonStr);
            // showAlert(jsonObject.toString());


            if(jsonObject.get("VALIDATE").equals("OK")){
//se foi encontrada a conta, "entra" na app

                if(password.equals(jsonObject.get("PASSWORD"))){ editData(v);} else{   showAlert("Password incorreta");}

            }
            else {
//não foi encontrada a conta

                showAlert("Password incorreta");

            }

        }catch (JSONException e){

        }

    }
}
