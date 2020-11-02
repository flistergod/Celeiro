package com.example.myapplication.ui.menudodia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MenudodiaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MenudodiaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("MENU DA SEMANA");
    }

    public LiveData<String> getText() {
        return mText;
    }
}