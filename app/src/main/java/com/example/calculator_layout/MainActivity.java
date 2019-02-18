package com.example.calculator_layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    Double number1;
    Double number2;
    //parameter responsible for ANY math button pressed
    int i=0;
    // 0 ->"plus"; 1 ->"minus"; 2 ->"divide"; 3 ->"multiply"
    // 0 values correspond to nonPressed buttons (1 will be attributed to pressed math function)
    int[] operandPressed = new int[] {0,0,0,0};
    int equalPressed = 0;
    int numberPressed = 0;

    //treating pressing number button
    protected void pressButton(int j, String number) {
        TextView RESULT = findViewById(R.id.result);
        if (j == 1 ) {
            RESULT.setText("");
        }
        RESULT.setText((RESULT.getText().toString().equals("0")) ? number : RESULT.getText()+number);
        i = 0;
        numberPressed = 1;
    }

    protected void takeInputForMath() {
        TextView RESULT = findViewById(R.id.result);
        if (!RESULT.getText().toString().isEmpty()) {
            number1 = Double.parseDouble(RESULT.getText().toString());
            i = 1;
        }
        Arrays.fill(operandPressed, 0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b0 = findViewById(R.id.btn_0);
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
        Button bmultiplication = findViewById(R.id.btn_multiplication);
        Button bdot = findViewById(R.id.btn_dot);
        Button bsign = findViewById(R.id.btn_sign);
        Button bsubtraction = findViewById(R.id.btn_subtraction);
        Button bsum = findViewById(R.id.btn_sum);
        Button bequal = findViewById(R.id.btn_equal);

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView RESULT = findViewById(R.id.result);
                if (i == 1 ) {
                    RESULT.setText("");
                }
                RESULT.setText(((Integer.parseInt(RESULT.getText().toString()) == 0 && numberPressed == 0) ? "0" : RESULT.getText()+"0"));
                i = 0;
                numberPressed = 1;
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(i, "1");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(i, "2");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(i, "3");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(i, "4");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(i, "5");
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(i, "6");
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(i, "7");
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(i, "8");
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton(i, "9");
            }
        });

        bpercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView RESULT = findViewById(R.id.result);
                Double total;
                total = Double.parseDouble(RESULT.getText().toString())/100;
                RESULT.setText(String.valueOf(total));
            }
        });

        bDEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView RESULT = findViewById(R.id.result);
                RESULT.setText("");
            }
        });

        bC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView RESULT = findViewById(R.id.result);
                RESULT.setText("0");
                number1 = 0.0;
                number2 = 0.0;
                i = 0;
                numberPressed = 0;
            }
        });

        bsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView RESULT = findViewById(R.id.result);
                Double total = -Double.parseDouble(RESULT.getText().toString());
                //formatting output
                if (total %1 ==0) {
                    RESULT.setText(String.format("%.0f", total));
                } else {
                    RESULT.setText(String.valueOf(total));
                }
            }
        });

        bdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView RESULT = findViewById(R.id.result);
                RESULT.setText(RESULT.getText().toString() + ".");
            }
        });

        bsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputForMath();
                operandPressed[0] = 1;

            }
        });

        bsubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputForMath();
                operandPressed[1] = 1;
            }
        });

        bdivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputForMath();
                operandPressed[2] = 1;
            }
        });

        bmultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputForMath();
                operandPressed[3] = 1;
            }
        });

        bequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double total;
                TextView RESULT = findViewById(R.id.result);
                number2 = Double.parseDouble(RESULT.getText().toString());

                if (operandPressed[0] == 1) {
                    total = number1 + number2;
                } else if (operandPressed[1] == 1) {
                    total = number1 - number2;
                } else if (operandPressed[2] == 1) {
                    total = number1 / number2;
                } else if (operandPressed[3] == 1) {
                    total = number1 * number2;
                } else total = number2;

                 //formatting output
                if (total %1 ==0) {
                    RESULT.setText(String.format("%.0f", total));
                } else {
                    RESULT.setText(String.valueOf(total));
                }

                Arrays.fill(operandPressed, 0);
                i = 0;
                equalPressed = 1;
            }
        });


    }
}
