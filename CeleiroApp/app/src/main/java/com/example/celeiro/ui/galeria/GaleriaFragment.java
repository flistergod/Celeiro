package com.example.celeiro.ui.galeria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.celeiro.R;

public class GaleriaFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        com.example.celeiro.ui.galeria.GaleriaViewModel galeriaViewModel = new ViewModelProvider(this).get(com.example.celeiro.ui.galeria.GaleriaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_galeria, container, false);

        return root;
    }
}
