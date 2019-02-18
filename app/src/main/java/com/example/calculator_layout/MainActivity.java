package com.example.calculator_layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Double number1;
    Double number2;
    String operandPressed = "no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = findViewById(R.id.btn_1);
        Button b2 = findViewById(R.id.btn_2);
        Button b3 = findViewById(R.id.btn_3);
        Button b4 = findViewById(R.id.btn_4);
        Button b5 = findViewById(R.id.btn_5);
        Button b6 = findViewById(R.id.btn_6);
        Button b7 = findViewById(R.id.btn_7);
        Button b8 = findViewById(R.id.btn_8);
        Button b9 = findViewById(R.id.btn_9);
        Button bC = findViewById(R.id.btn_C);
        Button bDEL = findViewById(R.id.btn_DEL);
        Button bpercent = findViewById(R.id.btn_percent);
        Button bdivision = findViewById(R.id.btn_division);
        Button bdot = findViewById(R.id.btn_dot);
        Button bsign = findViewById(R.id.btn_sign);
        Button bsubtraction = findViewById(R.id.btn_subtraction);
        Button bsum = findViewById(R.id.btn_sum);
        Button bequal = findViewById(R.id.btn_equal);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView RESULT = findViewById(R.id.result);
                if (operandPressed.equals("yes")) {
                    RESULT.setText("");
                }
                RESULT.setText(RESULT.getText()+"1");
                operandPressed = "no";
            }
        });

        bsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView RESULT = findViewById(R.id.result);
                number1 = Double.parseDouble(RESULT.getText().toString());
                operandPressed = "yes";
            }
        });

        bequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView RESULT = findViewById(R.id.result);
                number2 = Double.parseDouble(RESULT.getText().toString());
                RESULT.setText(String.valueOf(number1+number2));
            }
        });
    }
}
