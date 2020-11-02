package com.example.myapplication.ui.menu;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuFragment extends Fragment {


    String IP="https://www.celeironisa.pt/app/php_app.php?f=";
    ListView myListView;
    Spinner mySpinner;
    ItemListAdapter adapter;
    ArrayList<String> categories = new ArrayList<String>();







    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MenuViewModel menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        categories.add("Tudo");

      teste();


        getMenuCategories();

        try {
            initViews(root);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return root;
    }

    private void teste() {


    }

    public void getMenuCategories(){

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = IP+"getCategories";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("APPLOG", response);
                       parseJson(response);
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
                return params;
            }

        };
        queue.add(postRequest);


    }

    private void showAlert(String msg) {
        Toast.makeText(getContext(),msg, Toast.LENGTH_LONG).show();

    }

    //processa o Json
    public  void parseJson(String jsonStr){

        JSONArray jsonarray = null;

        try {
            jsonarray = new JSONArray(jsonStr);
          //  Log.d("ARRAY: ",jsonObject.toString() + " size: "+ jsonObject.length());

            for(int i=0;i<jsonarray.length();i++){

             JSONObject jsonObject = jsonarray.getJSONObject(i);
                categories.add(jsonObject.getString("familias"));

            }

        }catch (JSONException e){

        }
    }


    private void initViews(View view) throws JSONException {

        mySpinner=view.findViewById(R.id.spinner);
        mySpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,categories));

        myListView=view.findViewById(R.id.listview);



        adapter=new ItemListAdapter(getContext(), R.layout.layout_list_menu,  getItems());

        myListView.setAdapter(adapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position>=0 && position<categories.size()){
                    try {
                        getSelectedCategoryData(position);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getActivity(),"Selected Category does not exist!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public ArrayList<Item> getItems() throws JSONException {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try{

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL(IP+"getMenuItems");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

//            int status= connection.getResponseCode();


            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((line=reader.readLine())!=null){
                responseContent.append(line);
            }
            reader.close();

            System.out.println(responseContent.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ArrayList<Item> items= new ArrayList<Item>();
      items.clear();

        JSONArray jsonarray = null;

        try {
            jsonarray = new JSONArray(responseContent.toString());
            //  Log.d("ARRAY: ",jsonObject.toString() + " size: "+ jsonObject.length());

            for(int i=0;i<jsonarray.length();i++){

                JSONObject jsonObject = jsonarray.getJSONObject(i);
                items.add(new Item(
                            jsonObject.getString("produto"),
                            Double.parseDouble(jsonObject.getString("preco")),
                            Integer.parseInt(jsonObject.getString("familia"))));
            }

        }catch (JSONException e){

        }

        return items;

    }

    private void getSelectedCategoryData(int categoryID) throws JSONException {

        ArrayList<Item> cosmicBodies = new ArrayList<>();

        if(categoryID==0){

            adapter=new ItemListAdapter(getContext(), R.layout.layout_list_menu, getItems());
        }
        else{

            for(Item cosmicBody: getItems()){

                if(cosmicBody.getFamilia()==categoryID){
                    cosmicBodies.add(cosmicBody);
                }
            }

            adapter =new ItemListAdapter(getContext(), R.layout.layout_list_menu,cosmicBodies);
        }

        myListView.setAdapter(adapter);
    }


    public  void parseJsonItem(ArrayList<Item> items){


    }




}
