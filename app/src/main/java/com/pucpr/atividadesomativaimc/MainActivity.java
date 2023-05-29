package com.pucpr.atividadesomativaimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    EditText weightNumberDecimal;
    TextInputLayout weightInputLayout;
    EditText heightNumberDecimal;
    TextInputLayout heightInputLayout;
    EditText resultEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getElements();
    }

    private void getElements() {
        weightNumberDecimal = findViewById(R.id.weightNumberDecimal);
        weightInputLayout = findViewById(R.id.weightNumberDecimalTextInputLayout);
        heightNumberDecimal = findViewById(R.id.heightNumberDecimal);
        heightInputLayout = findViewById(R.id.heightNumberDecimalTextInputLayout);
        resultEditText = findViewById(R.id.resultEditText);
    }
    private float getNumFromEditText(int id) {
        String number = "";

        if (id == 1) {
            number = weightNumberDecimal.getText().toString();
        } else {
            number = heightNumberDecimal.getText().toString();
        }

        if (number.isEmpty()) {
            return 0;
        }

        try {
            return Float.parseFloat(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void resultButtonOnClick(View v) {

        float weight = getNumFromEditText(1);
        float height = getNumFromEditText(2);

        if ((weightNumberDecimal.getText().toString().trim().equals("")) || (weight == 0.0f)) {
            weightInputLayout.setError(getString(R.string.errorMesssage));
            weightNumberDecimal.setText(null);
        } else {
            weightInputLayout.setError(null);
        }

        if ((heightNumberDecimal.getText().toString().trim().equals("")) || (height == 0.0f)) {
            heightInputLayout.setError(getString(R.string.errorMesssage));
            heightNumberDecimal.setText(null);
        } else {
            heightInputLayout.setError(null);
        }

        if (weight == 0 || height == 0) {

            resultEditText.setText(null);
            Toast.makeText(this, getString(R.string.toast_coloque_peso_altura), Toast.LENGTH_SHORT).show();
            return;
        }

        float resultHeight = height * height;

        float result = weight / resultHeight;

        resultEditText.setText(String.valueOf(result));

        String bmiCategory = getBMICategory(result);
        Toast.makeText(this, getString(R.string.categoria_ibm) + bmiCategory, Toast.LENGTH_SHORT).show();
    }

    private String getBMICategory(float bmi) {
        if (bmi < 18.5) {
            return getString(R.string.abaixo_peso);
        } else if (bmi < 25) {
            return getString(R.string.peso_normal);
        } else if (bmi < 30) {
            return getString(R.string.acima_peso);
        } else {
            return getString(R.string.obesidade);
        }
    }
}