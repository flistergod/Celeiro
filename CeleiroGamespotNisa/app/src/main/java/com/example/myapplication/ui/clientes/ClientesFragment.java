package com.example.myapplication.ui.clientes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.SessionManagement;
import com.example.myapplication.ui.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.app.ProgressDialog.show;

public class ClientesFragment extends Fragment {
    String IP="https://www.celeironisa.pt/app/php_app.php?f=";
    View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ClientesViewModel clientesViewModel = new ViewModelProvider(this).get(ClientesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_clientes, container, false);






        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity().findViewById(R.id.imageExit).getVisibility()==View.VISIBLE){
            Navigation.findNavController(view).navigate(R.id.nav_menuClientesBase);
        }


        Button progpontos = view.findViewById(R.id.b_pontos);
        Button login = view.findViewById(R.id.b_login);
        Button novoregisto = view.findViewById(R.id.b_novoreg);

        progpontos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_programaPontos);
            }
        });

        /**o botao exit so fica visivel no login e no confirmar registo*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(),"ola",Toast.LENGTH_LONG).show();


                /**tirar isto de comentario no fim*/
               fazLogin(v);
               // Navigation.findNavController(v).navigate(R.id.nav_menuClientesBase);



            }
        });

        novoregisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_novoRegisto);
            }
        });




    }



    @Override
    public void onStart() {
        super.onStart();

        //check if user is logged in
        //if user is logged in --> move to client profile


        SessionManagement sessionManagement= new SessionManagement(getContext());
        int userID= sessionManagement.getSession();

        if(userID!=-1){

            Bundle bundle= new Bundle();
            bundle.putString("username", sessionManagement.getSessionUsername());
           getActivity().findViewById(R.id.imageExit).setVisibility(View.VISIBLE);
            Navigation.findNavController(getView()).navigate(R.id.nav_menuClientesBase, bundle);


        } else{}
    }



    public void fazLogin(final View view) {



        //showAlert("A processar...");

        final EditText password = (EditText) getActivity().findViewById(R.id.editPassword);
        final EditText username = (EditText) getActivity().findViewById(R.id.editUtilizador);

        //testa se os dados são válidos
        if(username.getText().toString().trim().isEmpty() || password.getText().toString().trim().isEmpty()){
            showAlert("Impossível fazer login, verifique os dados inseridos!");
            return;

        }

//é utilizado o volley
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = IP+"login";

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
                        showAlert("Sem conexão!");


                    }
                }
        ){
            @Override
            protected Map<String,String> getParams()
            {
//parametros usados no ficheiro php
                Map<String,String> params = new HashMap<String,String>();
                params.put("post_username", username.getText().toString().trim());
                params.put("post_password", password.getText().toString().trim());
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

            if(jsonObject.get("LOGIN").equals("OK")){
//se foi encontrada a conta, "entra" na app
                Toast.makeText(getContext(),"Bem-vindo "+jsonObject.get("USERNAME")+"!",Toast.LENGTH_LONG).show();

                //guardar login

                User user = new User(Integer.parseInt(jsonObject.get("ID").toString()), jsonObject.get("USERNAME").toString());

                SessionManagement sessionManagement = new SessionManagement(getContext());
                sessionManagement.saveSession(user);

               // showAlert("User saved. "+Integer.parseInt(jsonObject.get("ID").toString()) + jsonObject.get("USERNAME").toString());

                Bundle bundle= new Bundle();
                bundle.putString("username", jsonObject.get("USERNAME").toString());
                Navigation.findNavController(v).navigate(R.id.nav_menuClientesBase, bundle);

                getActivity().findViewById(R.id.imageExit).setVisibility(View.VISIBLE);
            }
            else{
//não foi encontrada a conta
                Toast.makeText(getContext(),"Impossível fazer login, verifique os dados inseridos!",Toast.LENGTH_LONG).show();
            }

        }catch (JSONException e){

        }
    }
}
