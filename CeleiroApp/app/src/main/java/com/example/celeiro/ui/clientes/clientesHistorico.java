package com.example.celeiro.ui.clientes;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.celeiro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class clientesHistorico extends Fragment {

    public clientesHistorico() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clientes_historico, container, false);
    }
}
