package com.example.guochuang1.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public MutableLiveData<Map<String, Integer>> shoppingCar;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        shoppingCar=new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}