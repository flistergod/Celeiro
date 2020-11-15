package com.example.celeiro.ui.clientes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClientesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ClientesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("CLIENTES");
    }

    public LiveData<String> getText() {
        return mText;
    }
}