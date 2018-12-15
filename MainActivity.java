package com.example.peter.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//delcare stuff in the instance of the app
        final EditText amountNum = (EditText)findViewById(R.id.amountNum);
        final EditText workedNum = (EditText)findViewById(R.id.workedNum);
        final TextView hourlyAns = (TextView) findViewById(R.id.hourlyAns);
        final TextView weeklyAns = (TextView) findViewById(R.id.weeklyAns);
        final TextView biweeklyAns = (TextView) findViewById(R.id.biweeklyAns);
        final TextView monthlyAns = (TextView) findViewById(R.id.monthlyAns);
        final TextView quaterlyAns = (TextView) findViewById(R.id.quaterlyAns);
        final TextView annuallyAns = (TextView) findViewById(R.id.annuallyAns);

        Button calcBtn = (Button) findViewById(R.id.calcBtn);
        Button clrBtn = (Button) findViewById(R.id.clearBtn);

        clrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountNum.setText("0.00");
                workedNum.setText("0.00");
                hourlyAns.setText("$" + ".00");
                weeklyAns.setText("$" + ".00");
                biweeklyAns.setText("$" + ".00");
                monthlyAns.setText("$" + ".00");
                quaterlyAns.setText("$" + ".00");
                annuallyAns.setText("$" + ".00");
            }
        });

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                EditText amountNum = (EditText)findViewById(R.id.amountNum);
                EditText workedNum = (EditText)findViewById(R.id.workedNum);
                TextView hourlyAns = (TextView) findViewById(R.id.hourlyAns);
                TextView weeklyAns = (TextView) findViewById(R.id.weeklyAns);
                TextView biweeklyAns = (TextView) findViewById(R.id.biweeklyAns);
                TextView monthlyAns = (TextView) findViewById(R.id.monthlyAns);
                TextView quaterlyAns = (TextView) findViewById(R.id.quaterlyAns);
                TextView annuallyAns = (TextView) findViewById(R.id.annuallyAns);
                */

                double num1 = 0.0;
                double num2 = 0.0;
               /* String string1 = "yeet";
                string1 = amountNum.getText().toString();
                String string2 = "yeeet";
                string2 = workedNum.getText().toString();
*/

                if(amountNum.getText().length() > 0)        //make sure were not calculating a null string
                    num1 = Double.parseDouble(amountNum.getText().toString());
                else
                    amountNum.setText("0.00");
                if(workedNum.getText().length() > 0)        //make sure were not calculating a null string
                    num2 = Double.parseDouble(workedNum.getText().toString());
                else
                    workedNum.setText("0.00");

                double dpweek = 5.0;    //working days per week
                double wpyear = 52.0;   //working weeks per year

                double num3 = num1 + num2;

                double ans[];           //hold answers in an array
                ans = new double[6];
                /*ans[0] = 10;
                ans[1] = 20;
                ans[2] = 30;
                ans[3] = 40;
                ans[4] = 50;
                ans[5] = 60;
*/
                //--------------------------------math-----------------------------------//

                ans[0] = num1;                          //hourly
                ans[1] = num1 * num2;                   //weekly
                ans[2] = ans[1] * 2;                    //biweekly
                ans[3] = ans[1] * wpyear / 12;          //monthly   technically 4.3333333 weeks per month, not 4
                ans[4] = ans[3] * 3;                    //quaterly
                ans[5] = ans[4] * 4;                    //annually

                DecimalFormat df2 = new DecimalFormat(".00");   //always prints 2 decimals & auto rounds

                //hourlyAns.setText("$" + df2.format(num1));//test
                hourlyAns.setText("$" + df2.format(ans[0]));
                weeklyAns.setText("$" + df2.format(ans[1]));
                biweeklyAns.setText("$" + df2.format(ans[2]));
                monthlyAns.setText("$" + df2.format(ans[3]));
                quaterlyAns.setText("$" + df2.format(ans[4]));
                annuallyAns.setText("$" + df2.format(ans[5]));

            }
        });

    }
}
