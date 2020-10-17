package com.example.myapplication.ui.clientes;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class NovoRegisto extends Fragment {
    private WebView myWebView;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String avatar;
    boolean foward=true;
    String IP="192.168.43.206";


    public NovoRegisto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_novo_registo, container, false);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        myWebView = (WebView) view.findViewById(R.id.webview_nr);
         myWebView.loadUrl("file:///android_asset/declaracao.html");

        radioButton =(RadioButton) view.findViewById(R.id.av_hamburguer);
        avatar= (String) radioButton.getTooltipText().toString().trim();


        Button submeter = view.findViewById(R.id.b_submeter);

        radioGroup= view.findViewById(R.id.f_avatar);



        submeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register(view);

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                radioButton =(RadioButton) view.findViewById(checkedId);
                avatar= (String) radioButton.getTooltipText().toString().trim();

            }
        });

    }

    public  void register(final View v){



        int  whatsapp_value=0;
        long nif_int=0;
        long textView_tlf=0;



        EditText nome= (EditText) v.findViewById(R.id.f_nome);
        EditText sobrenome= (EditText) v.findViewById(R.id.f_sobrenome);
        EditText email= (EditText) v.findViewById(R.id.f_Email);
        EditText tlf= (EditText) v.findViewById(R.id.f_telemovel);
         EditText username= (EditText) v.findViewById(R.id.f_nomeutilizador);
        EditText rua= (EditText) v.findViewById(R.id.f_morada);
        EditText numero= (EditText) v.findViewById(R.id.f_numero);
        EditText cp= (EditText) v.findViewById(R.id.f_codpostal);
        EditText localidade= (EditText) v.findViewById(R.id.f_localidade);
        EditText nif= (EditText) v.findViewById(R.id.nif);
        EditText password= (EditText) v.findViewById(R.id.password);

        CheckBox whatsapp = (CheckBox) v.findViewById(R.id.f_whatsapp);


        final String textView_nome = nome.getText().toString().trim();
        final String textView_sobrenome = sobrenome.getText().toString().trim();
        final String textview_email = email.getText().toString().trim();
        final String textview_username = username.getText().toString().trim();
        final String textView_rua = rua.getText().toString().trim();
        final String textView_localidade = localidade.getText().toString().trim();
        final String textView_cp = cp.getText().toString().trim();
        final String textView_nif = nif.getText().toString().trim();
        final String textView_password = password.getText().toString().trim();
        final String textView_numero = numero.getText().toString().trim();


        try {
            nif_int=Long.parseLong(textView_nif);
            textView_tlf = Long.parseLong(tlf.getText().toString().trim());
        }catch (Exception e){foward=false;}




        if(whatsapp.isChecked()){whatsapp_value=1;} else{whatsapp_value=0;}


        try {

            foward=true;

            if (textView_nome.isEmpty()) {
                foward = false;
            } else if (textView_sobrenome.isEmpty()) {
                foward = false;
            } else if (textView_rua.isEmpty()) {
                foward = false;
            } else if ( String.valueOf(textView_numero).isEmpty()) {
                foward = false;
            } else if (textView_localidade.isEmpty()) {
                foward = false;
            } else if (textView_cp.isEmpty() || !textView_cp.contains("-")) {
                foward = false;
            } else if (textview_email.isEmpty() || !textview_email.contains("@")) {
                foward = false;
            }
            else if(nif_int<0 || String.valueOf(nif_int).length()!=9 || String.valueOf(nif_int).isEmpty()){foward=false;}
            else if(textView_tlf<0 || String.valueOf(textView_tlf).length()<9 || String.valueOf(textView_tlf).isEmpty()){foward=false;}
            else if(textView_password.isEmpty()){foward=false;}



            if (foward == false) {
                showAlert("Dados inválidos, tente outra vez");
            }

            else{




//é utilizado o volley
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                String url = "http://"+IP+"/celeiro_app/conn.php?f=regist_user";


                final long finalNif_int = nif_int;
                final long finalTextView_tlf = textView_tlf;


                final int finalWhatsapp_value = whatsapp_value;
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("APPLOG", response);
                                parseJson_regist(response, v);
                                //processa-se o Json

                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.d("APPLOG", error.toString());
                                showAlert("Invalid data");

                            }
                        }
                ){
                    @Override
                    protected Map<String,String> getParams()
                    {
//parametros usados no ficheiro php
                        Map<String,String> params = new HashMap<String,String>();

                        params.put("post_nome", textView_nome.trim());
                        params.put("post_username", textview_username.trim());
                        params.put("post_sobrenome", textView_sobrenome.trim());
                        params.put("post_rua", textView_rua.trim());
                        params.put("post_num", textView_numero.trim());
                        params.put("post_localidade", textView_localidade.trim());
                        params.put("post_cp", textView_cp.trim());
                        params.put("post_email", textview_email.trim());
                        params.put("post_nif", String.valueOf(finalNif_int).trim());
                        params.put("post_telemovel", String.valueOf(finalTextView_tlf).trim());
                        params.put("post_password", textView_password.trim());
                        params.put("post_whatsapp", String.valueOf(finalWhatsapp_value).trim());
                        params.put("post_avatar", avatar.trim());

                        return params;
                    }

                };
                queue.add(postRequest);

            }



        }catch (Exception e){foward=false;
            showAlert("Invalid data, could not edit2");}


    //    Navigation.findNavController(v).navigate(R.id.nav_confirmarRegisto);



    }

    private void showAlert(String msg) {
        Toast.makeText(getContext(),msg, Toast.LENGTH_LONG).show();

    }

    public  void parseJson_regist(String jsonStr, View v){

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(jsonStr);
            // showAlert(jsonObject.toString());


            if(jsonObject.get("REGISTER").equals("OK")){
//se foi encontrada a conta, "entra" na app

                showAlert("Utilizador registado com sucesso");

            }
            else {
//não foi encontrada a conta

                showAlert(jsonObject.get("MSG").toString());
            }


        }catch (JSONException e){

        }
    }


}
