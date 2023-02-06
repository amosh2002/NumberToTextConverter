package com.example.classproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button convertButton;
    private TextInputEditText inputText;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertButton = findViewById(R.id.button_convert);
        inputText = findViewById(R.id.text_input_field);
        resultText = findViewById(R.id.text_view_result);

        inputText.addTextChangedListener(mTextWatcher);
        checkFieldsForProhibitedValues();
        extendTheFieldTo10CharsIfNegative();

        convertButton.setOnClickListener(v -> {
            String inputtedText = Objects.requireNonNull(inputText.getText()).toString();
            resultText.setText(Converter.convert(inputtedText));
        });
    }

    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForProhibitedValues();
            extendTheFieldTo10CharsIfNegative();
        }
    };

    private void checkFieldsForProhibitedValues() {
        String inputtedText = Objects.requireNonNull(inputText.getText()).toString();
        convertButton.setEnabled(
                !inputtedText.equals("") &&
                        !inputtedText.equals("-") &&
                        !(inputtedText.startsWith("0") && inputtedText.length() != 1) &&
                        !inputtedText.startsWith("-0")
        );
    }

    private void extendTheFieldTo10CharsIfNegative() {
        if (Objects.requireNonNull(inputText.getText()).toString().startsWith("-")) {
            inputText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        } else {
            inputText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
        }
    }


}