package com.example.neuron;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resW1, resW2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRes = findViewById(R.id.btnRes);
        btnRes.setOnClickListener(this);

        resW1 = findViewById(R.id.textW1);
        resW2 = findViewById(R.id.textW2);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRes:
                EditText editAx = findViewById(R.id.edit_Ax);
                EditText editAy = findViewById(R.id.edit_Ay);
                EditText editBx = findViewById(R.id.edit_Bx);
                EditText editBy = findViewById(R.id.edit_By);

                EditText editP = findViewById(R.id.editP);

                EditText editSpead = findViewById(R.id.editSpead);
                EditText editIter = findViewById(R.id.editIter);
                EditText editTime = findViewById(R.id.editTime);

                if (editAx.getText().toString().length() > 0 &&
                        editAy.getText().toString().length() > 0 &&
                        editBx.getText().toString().length() > 0 &&
                        editBy.getText().toString().length() > 0 &&
                        editP.getText().toString().length() > 0 &&
                        editSpead.getText().toString().length() > 0 &&
                        editIter.getText().toString().length() > 0 &&
                        editTime.getText().toString().length() > 0) {

                    double Ax = Double.parseDouble(editAx.getText().toString());
                    double Ay = Double.parseDouble(editAy.getText().toString());
                    double Bx = Double.parseDouble(editBx.getText().toString());
                    double By = Double.parseDouble(editBy.getText().toString());

                    double p = Double.parseDouble(editP.getText().toString());

                    double spead = Double.parseDouble(editSpead.getText().toString());
                    int iter = Integer.parseInt(editIter.getText().toString());
                    double time = Double.parseDouble(editTime.getText().toString());

                    Point A = new Point(Ax, Ay);
                    Point B = new Point(Bx, By);

                    Neuron n = new Neuron(p, A, B);
                    Point res[] = n.generate(spead, time, iter);

                    resW1.setText(Double.toString(res[0].x));
                    resW2.setText(Double.toString(res[0].y));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
