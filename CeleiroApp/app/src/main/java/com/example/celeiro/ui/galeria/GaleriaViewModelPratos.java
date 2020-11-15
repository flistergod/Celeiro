package com.example.celeiro.ui.galeria;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GaleriaViewModelPratos extends ViewModel {

    private MutableLiveData<String> mText;

    public GaleriaViewModelPratos() {
        mText = new MutableLiveData<>();
        mText.setValue("GALERIA");
    }

    public LiveData<String> getText() {
        return mText;
    }
}