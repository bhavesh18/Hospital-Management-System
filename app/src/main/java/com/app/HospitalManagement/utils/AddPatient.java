package com.app.HospitalManagement.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
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
import com.app.HospitalManagement.modals.DoctorListModal;

import java.util.ArrayList;

public class AddPatient extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHandler myDb;
    EditText edt_name,edt_email;
    Button btn_add;
    Spinner spin;
    ArrayList<DoctorListModal> showdata= new ArrayList<>();
    ArrayList<String> showname=new ArrayList<>();
    String[] doctors = {};
    String spinvalue="", id;
    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        getSupportActionBar().hide();
        init();
    }

    public void init(){
        myDb = new DatabaseHandler(this);
        edt_name=(EditText) findViewById(R.id.edt_name);
        edt_email=(EditText) findViewById(R.id.edt_email);
        btn_add=(Button) findViewById(R.id.btn_add) ;
        spin = (Spinner) findViewById(R.id.spinner);
        viewAll();
        setSpinner();
        final String sessionId = getIntent().getStringExtra("screen");
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String doctorid = getIntent().getStringExtra("quali");
        id=getIntent().getStringExtra("id");
        Log.d( "btnn: ",sessionId);
        if(sessionId.equalsIgnoreCase("edit")){
            btn_add.setText("EDIT");
            edt_name.setText(name);
            edt_email.setText(email);
        }
        else{
            btn_add.setText("ADD");
        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sessionId.equalsIgnoreCase("edit")){
                 UpdateData();
                }
                else{
                    if(value == 1){
                        boolean isInserted = myDb.insertPatData(edt_name.getText().toString(),edt_email.getText().toString(),spinvalue);

                        if(isInserted)
                            Toast.makeText(AddPatient.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddPatient.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddPatient.this);
                        builder.setCancelable(true);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.setMessage("There is no Doctor, So, first add Doctor");
                        builder.show();
                    }

                }

            }
        });
    }

    public void setSpinner(){

        Log.d("list", showname.toString());
        Log.d("list22", String.valueOf(doctors));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,doctors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }

    public void viewAll() {
        Cursor res = myDb.getAllDocData();
        if(res.getCount() == 0) {
            value = 0;
            showname.add("No Doctors added");
            doctors = showname.toArray(doctors);
            return;
        }

        while (res.moveToNext()) {
            DoctorListModal resultModal = new DoctorListModal(res.getString(0),res.getString(1), res.getString(2), res.getString(3),res.getString(4));
            showdata.add(resultModal);
            showname.add(res.getString(1));
            value = 1;

        }
        Log.d( "viewAll: ", showdata.toString());
        doctors = showname.toArray(doctors);

    }

    public void UpdateData() {

                        boolean isUpdate = myDb.updatePatData(edt_name.getText().toString(),
                                edt_email.getText().toString(),
                                spinvalue,id);
                        if(isUpdate == true)
                            Toast.makeText(AddPatient.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddPatient.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getApplicationContext(), "Selected User: "+ showdata.get(i).getId() ,Toast.LENGTH_SHORT).show();
        if(value == 1){
            spinvalue = showdata.get(i).getName();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
