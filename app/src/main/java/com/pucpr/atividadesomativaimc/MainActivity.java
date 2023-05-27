package com.pucpr.atividadesomativaimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
            return;
        } else {
            weightInputLayout.setError(null);
        }

        if ((heightNumberDecimal.getText().toString().trim().equals("")) || (height == 0.0f)) {
            heightInputLayout.setError(getString(R.string.errorMesssage));
            return;
        } else {
            heightInputLayout.setError(null);
        }

        if (weight == 0 || height == 0) {

            resultEditText.setText(null);
            Toast.makeText(this, "Por favor, adicione seu peso e altura", Toast.LENGTH_SHORT).show();
            return;
        }

        float resultHeight = height * height;

        float result = weight / resultHeight;

        resultEditText.setText(String.valueOf(result));

        String bmiCategory = getBMICategory(result);
        Toast.makeText(this, "Categoria IMC: " + bmiCategory, Toast.LENGTH_SHORT).show();
    }

    private String getBMICategory(float bmi) {
        if (bmi < 18.5) {
            return "Abaixo do peso";
        } else if (bmi < 25) {
            return "Peso Normal";
        } else if (bmi < 30) {
            return "Acima do Peso";
        } else {
            return "Obesidade";
        }
    }
}