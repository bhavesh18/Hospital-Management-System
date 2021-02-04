package com.app.HospitalManagement.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.HospitalManagement.R;

public class MainActivity extends AppCompatActivity {

    Button btn_doc,btn_patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
    }

    public void init(){
        btn_doc=(Button) findViewById(R.id.btn_doc);
        btn_patient=(Button) findViewById(R.id.btn_patient);

        btn_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), DoctorList.class);
                startActivity(i);
            }
        });

        btn_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), PatientList.class);
                startActivity(i);
            }
        });

    }
}
