package com.example.genetic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRes = findViewById(R.id.btnRes);
        btnRes.setOnClickListener(this);

        res = findViewById(R.id.textViewRes);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRes:
                EditText editA = findViewById(R.id.edit_A);
                EditText editB = findViewById(R.id.edit_B);
                EditText editC = findViewById(R.id.edit_C);
                EditText editD = findViewById(R.id.edit_D);
                EditText editY = findViewById(R.id.edit_Y);

                EditText editMutation = findViewById(R.id.editMutation);
                EditText editGSize = findViewById(R.id.editGSize);
                EditText editMaxTime = findViewById(R.id.editMaxTime);

                if (editA.getText().toString().length() > 0 &&
                        editB.getText().toString().length() > 0 &&
                        editC.getText().toString().length() > 0 &&
                        editD.getText().toString().length() > 0 &&
                        editY.getText().toString().length() > 0 &&
                        editMutation.getText().toString().length() > 0 &&
                        editMaxTime.getText().toString().length() > 0 &&
                        editGSize.getText().toString().length() > 0) {

                    int A = Integer.parseInt(editA.getText().toString());
                    int B = Integer.parseInt(editB.getText().toString());
                    int C = Integer.parseInt(editC.getText().toString());
                    int D = Integer.parseInt(editD.getText().toString());

                    int Y = Integer.parseInt(editY.getText().toString());

                    int gsize = Integer.parseInt(editGSize.getText().toString());
                    double mutation = Double.parseDouble(editMutation.getText().toString());
                    long maxTime = Long.parseLong(editMaxTime.getText().toString());

                    Genetic ga = new Genetic();
                    ga.setData(A, B, C, D, Y);
                    String statementRes = ga.calculate(gsize, mutation, maxTime*1000);

                    res.setText(statementRes);
                }
                break;
            default:
                break;
        }
    }
}
