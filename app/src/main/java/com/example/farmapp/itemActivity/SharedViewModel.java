package com.example.farmapp.itemActivity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<String> text = new MutableLiveData<>();


    void setText(String text) {

        this.text.setValue(text);
    }

    LiveData<String> getText() {

        return this.text;
    }

}
