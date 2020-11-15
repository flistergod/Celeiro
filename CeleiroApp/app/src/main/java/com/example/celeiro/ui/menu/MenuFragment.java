package com.example.celeiro.ui.menu;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
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
import com.example.celeiro.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
    int spinner_id;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        com.example.celeiro.ui.menu.MenuViewModel menuViewModel = new ViewModelProvider(this).get(com.example.celeiro.ui.menu.MenuViewModel.class);
        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        categories.add("Tudo");
        getMenuCategories();
        initViews(root);


        return root;
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


    private void initViews(View view)  {

        ArrayList<Item> items=getItems();
        mySpinner=view.findViewById(R.id.spinner);
        mySpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,categories));
        myListView=view.findViewById(R.id.listview);



        adapter=new ItemListAdapter(getContext(), R.layout.layout_list_menu,  items);


        myListView.setAdapter(adapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position>=0 && position<categories.size()){

                        getSelectedCategoryData(position);
                        spinner_id=position;

                }else{
                    Toast.makeText(getActivity(),"Selected Category does not exist!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public ArrayList<Item> getItems() {

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

              Item item=  new Item(
                        jsonObject.getString("produto"),
                        Double.parseDouble(jsonObject.getString("preco")),
                        Integer.parseInt(jsonObject.getString("familia")),
                        jsonObject.getString("descr"),
                      Integer.parseInt(jsonObject.getString("indisponivel")),
                      Integer.parseInt(jsonObject.getString("takeaway")),
                      Integer.parseInt(jsonObject.getString("novidade")),
                      Integer.parseInt(jsonObject.getString("mostrar")),
                      Integer.parseInt(jsonObject.getString("aipo")),
                      Integer.parseInt(jsonObject.getString("amendoins")),
                      Integer.parseInt(jsonObject.getString("crustaceos")),
                      Integer.parseInt(jsonObject.getString("diox")),
                      Integer.parseInt(jsonObject.getString("frutsec")),
                      Integer.parseInt(jsonObject.getString("gluten")),
                      Integer.parseInt(jsonObject.getString("leite")),
                      Integer.parseInt(jsonObject.getString("moluscos")),
                      Integer.parseInt(jsonObject.getString("mostarda")),
                      Integer.parseInt(jsonObject.getString("ovos")),
                      Integer.parseInt(jsonObject.getString("peixe")),
                      Integer.parseInt(jsonObject.getString("sesamo")),
                      Integer.parseInt(jsonObject.getString("tremocos")),
                      Integer.parseInt(jsonObject.getString("soja")),
                      jsonObject.getString("classe"),
                      Double.parseDouble(jsonObject.getString("precomeia")));



              System.out.println("boas "+jsonObject.getString("descr"));
                items.add(item);




            }

        }catch (JSONException e){

        }


        return items;

    }

    private   ArrayList<Item> getSelectedCategoryData(int categoryID)  {

        ArrayList<Item>  items0=getItems();
        ArrayList<Item> items = new ArrayList<>();


        if(categoryID==0){

            adapter=new ItemListAdapter(getContext(), R.layout.layout_list_menu, items0);
        }
        else{

            for(Item item: items0){

                if(item.getFamilia()==categoryID){
                    items.add(item);
                }
            }

            adapter =new ItemListAdapter(getContext(), R.layout.layout_list_menu,items);
            items0.clear();
            items0=items;

        }

        myListView.setAdapter(adapter);
        return items0;
    }


    public  void parseJsonItem(ArrayList<Item> items){


    }




}
