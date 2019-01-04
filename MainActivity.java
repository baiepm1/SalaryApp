package com.right.salary.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    String otchoice, statuschoice;
    int otpos, statuspos;
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

//spinner for over time setup
        final Spinner OTspinner = (Spinner) findViewById(R.id.OTspinner);     //drop down lists are called spinners

        ArrayAdapter<String> otadapter = new ArrayAdapter<String>(MainActivity.this,        //array adapters are arrays that can be molded into other containers...like spinners
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.overtime)); //(context, Resource file, data we need to populate)

        otadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);       //convert normal array list to drop down list (spinner)
        OTspinner.setAdapter(otadapter);    //have our spinner use the adapter we just made
        OTspinner.setOnItemSelectedListener(this);
//

//spinner for filing status setup
        final Spinner statusSpinner = (Spinner) findViewById(R.id.StatusSpinner);     //drop down lists are called spinners

        ArrayAdapter<String> statusadapter = new ArrayAdapter<String>(MainActivity.this,        //array adapters are arrays that can be molded into other containers...like spinners
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status)); //(context, Resource file, data we need to populate)

        statusadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);       //convert normal array list to drop down list (spinner)
        statusSpinner.setAdapter(statusadapter);    //have our spinner use the adapter we just made
        statusSpinner.setOnItemSelectedListener(this);
//

        clrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountNum.setText("");
                workedNum.setText("");
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

                double num1 = 0.0;  //num1 -> money per hour
                double num2 = 0.0;  //num2 -> hours worked in the week

                if(amountNum.getText().length() > 0)        //make sure were not calculating a null string
                    num1 = Double.parseDouble(amountNum.getText().toString());
                else
                    amountNum.setText("0.00");
                if(workedNum.getText().length() > 0)        //make sure were not calculating a null string
                    num2 = Double.parseDouble(workedNum.getText().toString());
                else
                    workedNum.setText("0.00");

                double wpyear = 52.0;   //working weeks per year

                double ans[];           //hold answers in an array
                ans = new double[6];

                double ottime = 0;  //ottime -> amount of overtime hours
                double otnum = 0.0; //otnum -> overtime pay

                //String OTtext = OTspinner.getSelectedItem().toString();
                //Toast.makeText(MainActivity.this ,OTtext, Toast.LENGTH_SHORT).show();

                if(otpos == 1)   //otpos -> position of spinner. 0=none 1=timeandahalf. if choice = over time, calc for over time
                {
                    //Toast.makeText(MainActivity.this ,OTtext, Toast.LENGTH_SHORT).show();
                    if(num2 >= 40.0){
                        ottime = num2 - 40;
                        num2=40.0;
                        otnum = num1 * 1.5 * ottime;
                    }
                }


                //--------------------------------payment math-----------------------------------//

                ans[0] = num1;                          //hourly
                ans[1] = num1 * num2 + otnum;           //weekly
                ans[2] = ans[1] * 2;                    //biweekly
                ans[3] = ans[1] * wpyear / 12;          //monthly   technically 4.3333333 weeks per month, not 4
                ans[4] = ans[3] * 3;                    //quaterly
                ans[5] = ans[4] * 4;                    //annually

                //--------------------------------tax math---------------------------------------//

                //learn more ab federal and state income tax deductions and exemptions




                DecimalFormat df2 = new DecimalFormat(".00");   //always prints 2 decimals & auto rounds

                //hourlyAns.setText("" + otpos);//test
                hourlyAns.setText("$" + df2.format(ans[0]));
                weeklyAns.setText("$" + df2.format(ans[1]));
                biweeklyAns.setText("$" + df2.format(ans[2]));
                monthlyAns.setText("$" + df2.format(ans[3]));
                quaterlyAns.setText("$" + df2.format(ans[4]));
                annuallyAns.setText("$" + df2.format(ans[5]));

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {       //this will run anytime any spinner is changed

        switch(parent.getId()){
            case R.id.OTspinner:    //only run otspinner is changed
                //String OTtext = parent.getItemAtPosition(position).toString();
                otchoice = parent.getItemAtPosition(position).toString();
                otpos = position;
                break;

            case R.id.StatusSpinner:    //only run when statusSpinner is changed
                //String OTtext = parent.getItemAtPosition(position).toString();
                statuschoice = parent.getItemAtPosition(position).toString();
                statuspos = position;
                break;
        }

        // Toast.makeText(parent.getContext(),statuschoice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
