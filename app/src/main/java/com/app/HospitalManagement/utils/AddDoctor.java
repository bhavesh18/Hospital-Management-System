package com.app.HospitalManagement.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.HospitalManagement.DatabaseHandler;
import com.app.HospitalManagement.R;

public class AddDoctor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHandler myDb;
    EditText edt_name,edt_email,edt_quali;
    Button btn_add;
    Spinner spin;
    String id,spinvalue="";
    String[] designation = { "Heart Surgeon", "Dentist", "Gynecologist", "Brain Surgeon" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        getSupportActionBar().hide();
        init();
    }

    public void init(){
        myDb = new DatabaseHandler(this);
        edt_name=(EditText) findViewById(R.id.edt_name);
        edt_email=(EditText) findViewById(R.id.edt_email);
        edt_quali=(EditText) findViewById(R.id.edt_quali);
        btn_add=(Button) findViewById(R.id.btn_add) ;
        spin = (Spinner) findViewById(R.id.spinner);
        final String sessionId = getIntent().getStringExtra("screen");
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String quali = getIntent().getStringExtra("quali");
        id=getIntent().getStringExtra("id");
        Log.d( "btnn: ",sessionId);
        if(sessionId.equalsIgnoreCase("edit")){
            btn_add.setText("EDIT");
            edt_name.setText(name);
            edt_email.setText(email);
            edt_quali.setText(quali);
        }
        else{
            btn_add.setText("ADD");
        }
        setSpinner();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sessionId.equalsIgnoreCase("edit")){
                    UpdateData();
                }
                else{
                    AddData();
                }

            }
        });
    }

    public void UpdateData() {

        boolean isUpdate = myDb.updateDocData(edt_name.getText().toString(),
                edt_email.getText().toString(),
                spinvalue,edt_quali.getText().toString(),id);
        if(isUpdate == true)
            Toast.makeText(AddDoctor.this,"Data Update",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(AddDoctor.this,"Data not Updated",Toast.LENGTH_LONG).show();
    }

    public void setSpinner(){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, designation);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }

    public  void AddData() {
        btn_add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertDocData(AddDoctor.this,edt_name.getText().toString(),edt_email.getText().toString(),"heart",edt_quali.getText().toString());


                        if(isInserted)
                            Toast.makeText(AddDoctor.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddDoctor.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), "Selected User: "+designation[i] ,Toast.LENGTH_SHORT).show();
        spinvalue = designation[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
