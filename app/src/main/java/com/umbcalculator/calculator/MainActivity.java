package com.umbcalculator.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private String currentNumber = "";
    private String operator = "";
    private double result = 0;
    private boolean Error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);

        // Botón AC (Borrar todo)
        Button buttonAC = findViewById(R.id.buttonAC);
        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNumber = "";
                operator = "";
                result = 0;
                textViewResult.setText("0");
            }
        });

        // Botón Delete (Borrar último dígito)
        Button buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentNumber.isEmpty()) {
                    currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                    textViewResult.setText(currentNumber);
                }
            }
        });

        // Botones numéricos (0-9) y punto decimal
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDot = findViewById(R.id.buttonDot);

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                currentNumber += buttonText;
                textViewResult.setText(currentNumber);
            }
        };

        button0.setOnClickListener(numberClickListener);
        button1.setOnClickListener(numberClickListener);
        button2.setOnClickListener(numberClickListener);
        button3.setOnClickListener(numberClickListener);
        button4.setOnClickListener(numberClickListener);
        button5.setOnClickListener(numberClickListener);
        button6.setOnClickListener(numberClickListener);
        button7.setOnClickListener(numberClickListener);
        button8.setOnClickListener(numberClickListener);
        button9.setOnClickListener(numberClickListener);
        buttonDot.setOnClickListener(numberClickListener);

        // Botones de operación (+, -, *, /)
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);

        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();

                if (!currentNumber.isEmpty()) {
                    operator = buttonText;
                    result = Double.parseDouble(currentNumber);
                    currentNumber = "";
                }
            }
        };

        buttonPlus.setOnClickListener(operatorClickListener);
        buttonMinus.setOnClickListener(operatorClickListener);
        buttonMultiply.setOnClickListener(operatorClickListener);
        buttonDivide.setOnClickListener(operatorClickListener);

        // Botón de igual (=)
        Button buttonEqual = findViewById(R.id.buttonEqual);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentNumber.isEmpty() && !operator.isEmpty()) {
                    double number = Double.parseDouble(currentNumber);

                    switch (operator) {
                        case "+":
                            result += number;
                            break;
                        case "-":
                            result -= number;
                            break;
                        case "*":
                            result *= number;
                            break;
                        case "/":
                            if (number != 0) {
                                result /= number;
                            } else {
                                Error=true;
                            }
                            break;
                    }

                    // Actualizar el TextView con el resultado
                    if (Error) {
                        textViewResult.setText("Error");
                    } else {
                        textViewResult.setText(String.valueOf(result));
                    }

                    // Limpiar los valores actuales
                    currentNumber = "";
                    operator = "";
                    Error=false;
                }
            }
        });
    }
}