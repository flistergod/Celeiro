package com.example.myapplication.ui.menudodia;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.myapplication.R;


public class MenudodiaFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MenudodiaViewModel menudodiaViewModel = new ViewModelProvider(this).get(MenudodiaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_menudodia, container, false);



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView diassemana = view.findViewById(R.id.menudias);
        TextView Segundaprato = view.findViewById(R.id.segundaPrato);
        TextView Segundasopa = view.findViewById(R.id.segundaSopa);
        TextView Segundasobremesa = view.findViewById(R.id.segundaSobremesa);
        TextView Tercaprato = view.findViewById(R.id.tercaPrato);
        TextView Tercasopa = view.findViewById(R.id.tercaSopa);
        TextView Tercasobremesa = view.findViewById(R.id.tercaSobremesa);
        TextView Quartaprato = view.findViewById(R.id.quartaPrato);
        TextView Quartasopa = view.findViewById(R.id.quartaSopa);
        TextView Quartasobremesa = view.findViewById(R.id.quartaSobremesa);
        TextView Quintaprato = view.findViewById(R.id.quintaPrato);
        TextView Quintasopa = view.findViewById(R.id.quintaSopa);
        TextView Quintasobremesa = view.findViewById(R.id.quintaSobremesa);
        TextView Sextaprato = view.findViewById(R.id.sextaPrato);
        TextView Sextasopa = view.findViewById(R.id.sextaSopa);
        TextView Sextasobremesa = view.findViewById(R.id.sextaSobremesa);
        TextView Sabadoprato = view.findViewById(R.id.sabadoPrato);
        TextView Sabadosopa = view.findViewById(R.id.sabadoSopa);
        TextView Sabadosobremesa = view.findViewById(R.id.sabadoSobremesa);

        String segundaprato = "Prato do dia";
        String segundasopa = "Sopa do dia";
        String segundasobremesa = "";

        String tercaprato = "Prato do dia";
        String tercasopa = "Sopa do dia";
        String tercasobremesa = "";

        String quartaprato = "Prato do dia";
        String quartasopa = "Sopa do dia";
        String quartasobremesa = "";

        String quintaprato = "Prato do dia";
        String quintasopa = "Sopa do dia";
        String quintasobremesa = "";

        String sextaprato = "Prato do dia";
        String sextasopa = "Sopa do dia";
        String sextasobremesa = "";

        String sabadoprato = "Prato do dia";
        String sabadosopa = "Sopa do dia";
        String sabadosobremesa = "";

        String diasSemana = "18/05 - 23/05";

        diassemana.setText(diasSemana);

        Segundaprato.setText(segundaprato);
        Segundasopa.setText(segundasopa);
        if (segundasobremesa.equals ("")){
        Segundasobremesa.setVisibility(View.GONE);
        }else{
            Segundasobremesa.setText(segundasobremesa);
        }

        Tercaprato.setText(tercaprato);
        Tercasopa.setText(tercasopa);
        if (tercasobremesa.equals ("")){
            Tercasobremesa.setVisibility(View.GONE);
        }else{
            Tercasobremesa.setText(tercasobremesa);
        }

        Quartaprato.setText(quartaprato);
        Quartasopa.setText(quartasopa);
        if (quartasobremesa.equals ("")){
            Quartasobremesa.setVisibility(View.GONE);
        }else{
            Quartasobremesa.setText(quartasobremesa);
        }

        Quintaprato.setText(quintaprato);
        Quintasopa.setText(quintasopa);
        if (segundasobremesa.equals ("")){
            Quintasobremesa.setVisibility(View.GONE);
        }else{
            Quintasobremesa.setText(quintasobremesa);
        }

        Sextaprato.setText(sextaprato);
        Sextasopa.setText(sextasopa);
        if (segundasobremesa.equals ("")){
            Sextasobremesa.setVisibility(View.GONE);
        }else{
            Sextasobremesa.setText(sextasobremesa);
        }

        Sabadoprato.setText(sabadoprato);
        Sabadosopa.setText(sabadosopa);
        if (segundasobremesa.equals ("")){
            Sabadosobremesa.setVisibility(View.GONE);
        }else{
            Sabadosobremesa.setText(sabadosobremesa);
        }
    }
}
