package com.example.gpahuangp1calc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText g1, g2, g3, g4,g5;
    TextView result;
    Button btn;
    int calcCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        g1 = (EditText) findViewById(R.id.enter1);
        g2 = (EditText) findViewById(R.id.enter2);
        g3 = (EditText) findViewById(R.id.enter3);
        g4 = (EditText) findViewById(R.id.enter4);
        g5 = (EditText) findViewById(R.id.enter5);

        // Credit: base code from StackOverflow
        // Edited to match app's needs
        // https://stackoverflow.com/questions/14212518/is-there-a-way-to-define-a-min-and-max-value-for-edittext-in-android
        // Limits data fields (grades entered) to 0-100, inclusive
        g1.setFilters(new InputFilter[]{ new gradeRangeFilter("0", "100")});
        g2.setFilters(new InputFilter[]{ new gradeRangeFilter("0", "100")});
        g3.setFilters(new InputFilter[]{ new gradeRangeFilter("0", "100")});
        g4.setFilters(new InputFilter[]{ new gradeRangeFilter("0", "100")});
        g5.setFilters(new InputFilter[]{ new gradeRangeFilter("0", "100")});
        // End credit
        // End code for limiting data field

        result = (TextView) findViewById(R.id.result);

        g1.setBackgroundColor(Color.parseColor("#bfbfbf"));
        g2.setBackgroundColor(Color.parseColor("#bfbfbf"));
        g3.setBackgroundColor(Color.parseColor("#bfbfbf"));
        g4.setBackgroundColor(Color.parseColor("#bfbfbf"));
        g5.setBackgroundColor(Color.parseColor("#bfbfbf"));

        btn = (Button) findViewById(R.id.calculate);

        // Sets initial app background color to light blue
        setAppBGColor(Color.parseColor("#bfe7fd"));
    }

    // Set background color of app
    public void setAppBGColor(int color) {
        View appBG = this.getWindow().getDecorView();
        appBG.setBackgroundColor(color);
    }

    public void computeGPA(View view) {
        float gr1, gr2, gr3, gr4, gr5;
        if (calcCount % 2 == 0) {
            // Checks for empty fields
            if (g1.getText().toString().equals("") || g2.getText().toString().equals("")
                    || g3.getText().toString().equals("") || g4.getText().toString().equals("") ||
                    g5.getText().toString().equals("")) {
                // Does not accept empty fields --> Prompts user to enter grade in all fields
                Toast.makeText(getApplicationContext(), "Please enter grades in all fields", Toast.LENGTH_LONG).show();
                // "If" loops below changes entry field to the color red if empty
                // Error message in entry field if empty
                if (g1.getText().toString().equals("")) {
                    g1.setBackgroundColor(Color.parseColor("#ff5c5c"));
                    g1.setError("Empty field detected");
                }
                if (g2.getText().toString().equals("")) {
                    g2.setBackgroundColor(Color.parseColor("#ff5c5c"));
                    g2.setError("Empty field detected");
                }
                if (g3.getText().toString().equals("")) {
                    g3.setBackgroundColor(Color.parseColor("#ff5c5c"));
                    g3.setError("Empty field detected");
                }
                if (g4.getText().toString().equals("")) {
                    g4.setBackgroundColor(Color.parseColor("#ff5c5c"));
                    g4.setError("Empty field detected");
                }
                if (g5.getText().toString().equals("")) {
                    g5.setBackgroundColor(Color.parseColor("#ff5c5c"));
                    g5.setError("Empty field detected");
                }
            } else { // All entry fields have the correct data
                g1.setBackgroundColor(Color.parseColor("#bfbfbf"));
                g2.setBackgroundColor(Color.parseColor("#bfbfbf"));
                g3.setBackgroundColor(Color.parseColor("#bfbfbf"));
                g4.setBackgroundColor(Color.parseColor("#bfbfbf"));
                g5.setBackgroundColor(Color.parseColor("#bfbfbf"));
                gr1 = Float.parseFloat(g1.getText().toString());
                gr2 = Float.parseFloat(g2.getText().toString());
                gr3 = Float.parseFloat(g3.getText().toString());
                gr4 = Float.parseFloat(g4.getText().toString());
                gr5 = Float.parseFloat(g5.getText().toString());

                // Computes average/GPA
                float average = (gr1 + gr2 + gr3 + gr4 + gr5) / 5;
                result.setText(Float.toString(average));

                // Changes background based on GPA
                if (average >= 80 && average <= 100) {
                    // Between 80-100, inclusive --> green background
                    setAppBGColor(Color.parseColor("#79ff4d"));
                    Toast.makeText(getApplicationContext(), "Great job! You're passing", Toast.LENGTH_LONG).show();
                } else if (average >= 61 && average <= 79) {
                    // Between 61-79, inclusive --> yellow background
                    setAppBGColor(Color.parseColor("#fff34d"));
                    Toast.makeText(getApplicationContext(), "Warning: in danger of failing", Toast.LENGTH_LONG).show();
                } else if (average <= 60) {
                    // Less than or equal to 60 --> red background
                    setAppBGColor(Color.parseColor("#ff5c5c"));
                    Toast.makeText(getApplicationContext(), "Warning: failing", Toast.LENGTH_LONG).show();
                }

                // Sets button text to "Clear form"
                btn.setText("Clear form");
                calcCount++;

            }
        } else { // Resets/clears form
            g1.setText("");
            g2.setText("");
            g3.setText("");
            g4.setText("");
            g5.setText("");
            result.setText("");
            btn.setText("Compute GPA"); // Resets button text to "Compute GPA"
            setAppBGColor(Color.parseColor("#bfe7fd")); // Resets app background color to light blue
            calcCount++;
        }
    }
}
