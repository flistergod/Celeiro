package com.example.celeiro.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.celeiro.R;


public class HomeFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final com.example.celeiro.ui.home.HomeViewModel homeViewModel = new ViewModelProvider(this).get(com.example.celeiro.ui.home.HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton menusemana = view.findViewById(R.id.btn_menusemana);
        ImageButton menu = view.findViewById(R.id.btn_menu);
        ImageButton clientes = view.findViewById(R.id.btn_clientes);
        ImageButton servicos = view.findViewById(R.id.btn_servicos);
        ImageButton galeria = view.findViewById(R.id.btn_galeria);
        ImageButton contactos = view.findViewById(R.id.btn_contactos);

        menusemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_menudia);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_menu);
            }
        });

        clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getActivity().findViewById(R.id.imageExit).getVisibility()==View.VISIBLE){

                    Navigation.findNavController(v).navigate(R.id.nav_menuClientesBase);
                }
                else {

                    Navigation.findNavController(v).navigate(R.id.nav_clientes);
                }
            }
        });

        servicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_servicos);
            }
        });

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_galeria_pratos);
            }
        });

        contactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_contactos);
            }
        });

    }
}
