package com.example.myapplication.ui.menu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.LoadBackground;
import com.example.myapplication.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MenuFragment extends Fragment {


    ListView myListView;
    Spinner mySpinner;
   ItemListAdapter adapter;
    String[] categories ={"All", "Planets", "Stars", "Galaxies"};
   /* String[] seccoes = new String[] {"Entradas","Pratos","Hambúrguers","Tostas","Cachorros","Baguetes","Menús","Águas","Sumos","Cervejas",
            "Vinhos","Cafetaria Bebidas","Cafetaria Comidas","Diversos","Bebidas Alcoólicas e Bebidas Brancas","Extras",
            "Takeway"};*/


   private void initViews(View view){

       mySpinner=view.findViewById(R.id.spinner);
       mySpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,categories));

       myListView=view.findViewById(R.id.listview);

       myListView.setAdapter(new ItemListAdapter(getContext(), R.layout.layout_list_menu,getCosmicBodies()));

       mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               if(position>=0 && position<categories.length){
                        getSelectedCategoryData(position);
               }else{
                    Toast.makeText(getActivity(),"Selected Category does not exist!", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
   }

   private ArrayList<CosmicBody> getCosmicBodies(){

       ArrayList<CosmicBody> data = new ArrayList<>();
       data.clear();

       data.add(new CosmicBody("Mercury", 1));
       data.add(new CosmicBody("Mercury1", 1));
       data.add(new CosmicBody("Mercury2", 1));
       data.add(new CosmicBody("Mercury3", 1));
       data.add(new CosmicBody("Mercury4", 1));
       data.add(new CosmicBody("Mercury5", 1));
       data.add(new CosmicBody("Mercury6", 1));
       data.add(new CosmicBody("Mercury7", 1));
       data.add(new CosmicBody("Mercury8", 1));
       data.add(new CosmicBody("Mercury9", 1));
       data.add(new CosmicBody("Mercury10", 1));
       data.add(new CosmicBody("Mercury11", 1));
       data.add(new CosmicBody("Mercury12", 1));
       data.add(new CosmicBody("Mercury13", 2));
       data.add(new CosmicBody("Mercury14", 2));
       data.add(new CosmicBody("Mercury15", 2));
       data.add(new CosmicBody("Mercury16", 2));
       data.add(new CosmicBody("Mercury17", 2));
       data.add(new CosmicBody("Mercury18", 2));
       data.add(new CosmicBody("Mercury19", 2));
       data.add(new CosmicBody("Mercury20", 2));
       data.add(new CosmicBody("Mercury21", 2));
       data.add(new CosmicBody("Mercury22", 3));
       data.add(new CosmicBody("Mercury23", 3));
       data.add(new CosmicBody("Mercury24", 3));
       data.add(new CosmicBody("Mercury25", 3));
       data.add(new CosmicBody("Mercury22", 3));
       data.add(new CosmicBody("Mercury23", 3));
       data.add(new CosmicBody("Mercury24", 3));
       data.add(new CosmicBody("Mercury25", 3));
       data.add(new CosmicBody("Mercury22", 3));
       data.add(new CosmicBody("Mercury23", 3));
       data.add(new CosmicBody("Mercury24", 3));
       data.add(new CosmicBody("Mercury25", 3));



        return data;
   }

   private void getSelectedCategoryData(int categoryID){

       ArrayList<CosmicBody> cosmicBodies = new ArrayList<>();

       if(categoryID==0){

           adapter=new ItemListAdapter(getContext(), R.layout.layout_list_menu,getCosmicBodies());
       }
       else{

           for(CosmicBody cosmicBody: getCosmicBodies()){

               if(cosmicBody.getCategoryID()==categoryID){
                   cosmicBodies.add(cosmicBody);
               }
           }

           adapter =new ItemListAdapter(getContext(), R.layout.layout_list_menu,cosmicBodies);
       }

       myListView.setAdapter(adapter);
   }




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MenuViewModel menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        View root = inflater.inflate(R.layout.fragment_menu, container, false);


        initViews(root);

        



        return root;
    }




}
