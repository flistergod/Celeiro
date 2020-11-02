package com.example.myapplication.ui.galeria;

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

import com.example.myapplication.R;

public class GaleriaFragmentPratos extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GaleriaViewModelPratos galeriaViewModelPratos = new ViewModelProvider(this).get(GaleriaViewModelPratos.class);
        View root = inflater.inflate(R.layout.fragment_galeria_pratos, container, false);




        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton pratos = view.findViewById(R.id.button_1);
        ImageButton saladas = view.findViewById(R.id.button_2);
        ImageButton snacks = view.findViewById(R.id.button_3);
        ImageButton casa = view.findViewById(R.id.button_4);
        ImageButton festas = view.findViewById(R.id.button_5);

        pratos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_galeria_pratos);
            }
        });

        saladas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_galeria_saladas);
            }
        });

        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_galeria_snacks);
            }
        });

        casa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_galeria_casa);
            }
        });

        festas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_galeria_festas);
            }
        });

    }
}
