package com.example.statemenagmentextended;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.app.Application;

public class StateViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> counter;
    private MutableLiveData<String> editTextContent;
    private MutableLiveData<Boolean> isCheckBoxChecked;
    private MutableLiveData<Boolean> isSwitchOn;

    public StateViewModel(@NonNull Application application) {
        super(application);
        counter = new MutableLiveData<>(0);
        editTextContent = new MutableLiveData<>("");
        isCheckBoxChecked = new MutableLiveData<>(false);
        isSwitchOn = new MutableLiveData<>(false);
    }

    public MutableLiveData<Integer> getCounter() {
        return counter;
    }

    public MutableLiveData<String> getEditTextContent() {
        return editTextContent;
    }

    public MutableLiveData<Boolean> getIsCheckBoxChecked() {
        return isCheckBoxChecked;
    }

    public MutableLiveData<Boolean> getIsSwitchOn() {
        return isSwitchOn;
    }

    public void incrementCounter() {
        counter.setValue(counter.getValue() + 1);
    }

    public void setEditTextContent(String content) {
        editTextContent.setValue(content);
    }

    public void setIsCheckBoxChecked(boolean isChecked) {
        isCheckBoxChecked.setValue(isChecked);
    }

    public void setIsSwitchOn(boolean isOn) {
        isSwitchOn.setValue(isOn);
    }

    public void saveState(Bundle outState) {
        outState.putInt("counter", counter.getValue());
        outState.putString("editTextContent", editTextContent.getValue());
        outState.putBoolean("isCheckBoxChecked", isCheckBoxChecked.getValue());
        outState.putBoolean("isSwitchOn", isSwitchOn.getValue());
    }

    public void restoreState(Bundle savedState) {
        if (savedState != null) {
            counter.setValue(savedState.getInt("counter"));
            editTextContent.setValue(savedState.getString("editTextContent"));
            isCheckBoxChecked.setValue(savedState.getBoolean("isCheckBoxChecked"));
            isSwitchOn.setValue(savedState.getBoolean("isSwitchOn"));
        }
    }
}
