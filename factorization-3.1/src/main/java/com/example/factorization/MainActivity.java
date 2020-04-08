package com.example.factorization;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRes = findViewById(R.id.btn_res);
        btnRes.setOnClickListener(this);

        res = findViewById(R.id.text_res);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_res:
                EditText editN = findViewById(R.id.editN);

                if (editN.getText().toString().length() > 0 ) {
                    long N = Long.parseLong(editN.getText().toString());

                    Factorization f = new Factorization(3000);
                    ArrayList<Long> multipliers = f.fermaCalculate(N);

                    if (multipliers != null) {

                        Collections.sort(multipliers);

                        String toWrite = "";
                        for (long multiplier : multipliers) {
                            toWrite += multiplier + " ";
                        }
                        res.setText(toWrite.trim());
                    } else {
                        res.setText("Time is out of terms!");
                    }
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
