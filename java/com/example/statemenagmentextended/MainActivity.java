package com.example.statemenagmentextended;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private StateViewModel viewModel;
    private TextView counterTextView;
    private EditText editText;
    private CheckBox checkBox;
    private Switch switchWidget;
    private TextView optionSelectedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(StateViewModel.class);

        counterTextView = findViewById(R.id.counterTextView);
        editText = findViewById(R.id.editText);
        checkBox = findViewById(R.id.checkBox);
        switchWidget = findViewById(R.id.switchWidget);
        optionSelectedTextView = findViewById(R.id.optionSelectedTextView);
        Button incrementButton = findViewById(R.id.incrementButton);

        incrementButton.setOnClickListener(v -> viewModel.incrementCounter());

        viewModel.getCounter().observe(this, counter -> counterTextView.setText(String.valueOf(counter)));
        viewModel.getEditTextContent().observe(this, content -> editText.setText(content));
        viewModel.getIsCheckBoxChecked().observe(this, isChecked -> {
            checkBox.setChecked(isChecked);
            if (isChecked) {
                optionSelectedTextView.setVisibility(View.VISIBLE);
            } else {
                optionSelectedTextView.setVisibility(View.GONE);
            }
        });
        viewModel.getIsSwitchOn().observe(this, isOn -> {
            switchWidget.setChecked(isOn);
            if (isOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        editText.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(android.text.Editable s) {
                viewModel.setEditTextContent(s.toString());
            }
        });

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.setIsCheckBoxChecked(isChecked);
            if (isChecked) {
                optionSelectedTextView.setVisibility(View.VISIBLE);
            } else {
                optionSelectedTextView.setVisibility(View.GONE);
            }
        });
        switchWidget.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.setIsSwitchOn(isChecked);
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        if (savedInstanceState != null) {
            viewModel.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        viewModel.saveState(outState);
    }
}