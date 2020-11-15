package com.example.celeiro.ui.clientes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.celeiro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class confirmarRegisto extends Fragment {

    public confirmarRegisto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmar_registo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button novosms = view.findViewById(R.id.b_pedirnovosms);
        Button confirmardados = view.findViewById(R.id.b_verificardados);
        Button submeter = view.findViewById(R.id.b_confirmarcodigo);

        novosms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                Navigation.findNavController(v).navigate(R.id.nav_confirmarRegisto);
            */
            }
        });

        confirmardados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_novoRegisto);
            }
        });

        submeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().findViewById(R.id.imageExit).setVisibility(View.VISIBLE);
                Navigation.findNavController(v).navigate(R.id.nav_menuClientesBase);
            }
        });

    }
}
