package com.example.calculator_layout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

    //parameters indicating end of number input or pressing buttons for calculation performing or finishing
    int equalPressed = 0;
    int numberPressed = 0;
    int secondOperandPressed = 0;
    int calculDone = 0;

    //output parameter for history recorded
    String history = "";

    //decare here button to be able to use them outside of onCreate method
    static Button bC;
    static Button bDEL;
    static Button bpercent;
    static Button bdivision;
    static Button bmultiplication;
    static Button bdot;
    static Button bsign;
    static Button bsubtraction;
    static Button bsum;
    static Button bequal;


    //set default button background after pressing each next button
    public void buttonBackground() {
        bsum.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        bmultiplication.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        bpercent.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        bdivision.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        bsign.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        bsubtraction.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        bequal.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        bdot.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        bC.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        bDEL.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
    }


    public String getHistory() {
        return history;
    }

    //treating pressing number button

    protected void pressButton(int j, String number) {
        buttonBackground();
        TextView RESULT = findViewById(R.id.result);

        //if math operation performed and accomplished by pressing "=",
        // erase the current number on the screen to be ready for the new input
        if (j == 1 || equalPressed ==1) {
            RESULT.setText("");
        }
        //if first number is zero (without decimal part) avoid it and replace by number;
        // otherwise continue adding numbers to the input
        RESULT.setText((RESULT.getText().toString().equals("0")) ? number : RESULT.getText()+number);

        //new nunmber input - no math operations and executions performed so far
        i = 0;
        equalPressed = 0;

        numberPressed = 1; //signifies that number was entered to the input window
        history += number;
    }

    //keeping in memory first input
    protected void takeInputForMath() {
        //signifies consequent pressing math operation signs not interrupted by equal sign
        secondOperandPressed++;
        TextView RESULT = findViewById(R.id.result);

        //if input is not null consider performing operation (assigning i=1)
        if (!RESULT.getText().toString().isEmpty()) {
            i=1;
            //if math operation sign pressed second time without interrupting by "="
            //perform first calculation of precedent math operation if there is
            if (secondOperandPressed == 2) {
                calculate();
            }
            number1 = Double.parseDouble(RESULT.getText().toString());
        }
        //unset all math operation signs
        Arrays.fill(operandPressed, 0);
    }

    //perform chosen math operation
     protected void calculate () {

         Double total = 0.0;
         TextView RESULT = findViewById(R.id.result);

         //managing number1 and number2 when math calculation signs pressed twice
         // without interrupting by "=" sign
         if (secondOperandPressed == 2 && calculDone == 1 && equalPressed == 0) {
             Double number3 = 0.0;
             number3 = Double.parseDouble(RESULT.getText().toString());
             number1 = number3;
         } else number2 = Double.parseDouble(RESULT.getText().toString());

         //performing standard math operations
         if (operandPressed[0] == 1) {
             total = number1 + number2;
         } else if (operandPressed[1] == 1) {
             total = number1 - number2;
         } else if (operandPressed[2] == 1 && number2 != 0) {
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

         //unsetting one math calculation over two (if there is)
         //and signifying end of calculation
         secondOperandPressed = 1;
         calculDone = 1;
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
        bC = (Button) findViewById(R.id.btn_C);
        bDEL = (Button) findViewById(R.id.btn_DEL);
        bpercent = (Button) findViewById(R.id.btn_percent);
        bdivision = (Button) findViewById(R.id.btn_division);
        bmultiplication = (Button) findViewById(R.id.btn_multiplication);
        bdot = (Button) findViewById(R.id.btn_dot);
        bsign = (Button) findViewById(R.id.btn_sign);
        bsubtraction = (Button) findViewById(R.id.btn_subtraction);
        bsum = (Button) findViewById(R.id.btn_sum);
        bequal = (Button) findViewById(R.id.btn_equal);


        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView RESULT = findViewById(R.id.result);
                //usetting input field after first operation was performed
                //and one expect either initial input or second input for second math operation
                if (i == 1  || numberPressed == 0 || equalPressed == 1 || secondOperandPressed == 2) {
                    RESULT.setText("");
                }
                //display input of number "0"
                RESULT.setText((RESULT.getText().toString().equals("") ||
                        Integer.parseInt(RESULT.getText().toString()) == 0) ? "0" : RESULT.getText()+"0");
                i = 0;
                numberPressed = 1;
                history += "0";
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
                history += "%";
                buttonBackground();
                bpercent.getBackground().setColorFilter(Color.parseColor("#6567dc"), PorterDuff.Mode.MULTIPLY);
            }
        });

        bDEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView RESULT = findViewById(R.id.result);
                RESULT.setText("");
                history += "DEL";
                buttonBackground();
                bDEL.getBackground().setColorFilter(Color.parseColor("#6567dc"), PorterDuff.Mode.MULTIPLY);
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
                history += "C\n";
                buttonBackground();
                bC.getBackground().setColorFilter(Color.parseColor("#6567dc"), PorterDuff.Mode.MULTIPLY);
                Arrays.fill(operandPressed, 0);
                calculDone = 0;
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
                history += "*(-1)";
                buttonBackground();
                bsign.getBackground().setColorFilter(Color.parseColor("#6567dc"), PorterDuff.Mode.MULTIPLY);
            }
        });

        bdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView RESULT = findViewById(R.id.result);
                RESULT.setText(RESULT.getText().toString() + ".");
                history += ".";
                buttonBackground();
                bdot.getBackground().setColorFilter(Color.parseColor("#6567dc"), PorterDuff.Mode.MULTIPLY);
            }
        });

        bsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputForMath();
                operandPressed[0] = 1;
                history += "+";
                buttonBackground();
                bsum.getBackground().setColorFilter(Color.parseColor("#6567dc"), PorterDuff.Mode.MULTIPLY);
            }
        });


        bsubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputForMath();
                operandPressed[1] = 1;
                history += "-";
                buttonBackground();
                bsubtraction.getBackground().setColorFilter(Color.parseColor("#6567dc"), PorterDuff.Mode.MULTIPLY);
            }
        });

        bdivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputForMath();
                operandPressed[2] = 1;
                history += "/";
                buttonBackground();
                bdivision.getBackground().setColorFilter(Color.parseColor("#6567dc"), PorterDuff.Mode.MULTIPLY);
            }
        });

        bmultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputForMath();
                operandPressed[3] = 1;
                history += "*";
                buttonBackground();
                bmultiplication.getBackground().setColorFilter(Color.parseColor("#6567dc"), PorterDuff.Mode.MULTIPLY);
            }
        });

        bequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                secondOperandPressed = 0;
                Arrays.fill(operandPressed, 0);
                i = 0;
                equalPressed = 1;
                TextView RESULT = findViewById(R.id.result);
                history += "=";
                history += RESULT.getText().toString();
                history += "\n";
                buttonBackground();
                bequal.getBackground().setColorFilter(Color.parseColor("#6567dc"), PorterDuff.Mode.MULTIPLY);
            }
        });


        Button b = findViewById(R.id.btn_history);

        //using fragment to display calculations history
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new BlankFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentToReplace, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    //create Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //creating new activity with app info by choose the only option existing ("About")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.about:
                Intent i= new Intent(this, Main2Activity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
