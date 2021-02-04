package com.app.HospitalManagement.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.HospitalManagement.DatabaseHandler;
import com.app.HospitalManagement.R;
import com.app.HospitalManagement.adapter.DoctorAdapter;
import com.app.HospitalManagement.modals.DoctorListModal;

import java.util.ArrayList;

public class DoctorList extends AppCompatActivity {

    DatabaseHandler myDb;
    RecyclerView recyclerView;
    ArrayList<DoctorListModal> showdata= new ArrayList<>();
    DoctorAdapter adapter;
    Button btn_add;
    TextView txt_nothing,txt_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        getSupportActionBar().hide();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showdata= new ArrayList<>();
        viewAll();
        setdata();
    }

    public void init()
    {
        myDb = new DatabaseHandler(this);
        txt_nothing=(TextView) findViewById(R.id.txt_nothing);
        txt_delete=(TextView) findViewById(R.id.txt_delete);
        btn_add=(Button) findViewById(R.id.btn_add);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        showdata=UtilClass.getArrayListrocode(UtilClass.showdatalist,getApplicationContext());


        viewAll();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), AddDoctor.class);
                i.putExtra("screen", "add");
                startActivity(i);
            }
        });
        setdata();

        txt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog alertDialog = new AlertDialog.Builder(DoctorList.this)

                        .setTitle("Do you want to delete all the records?")

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                if(myDb.deleteAllDocData()){
                                    Toast.makeText(getApplicationContext(),"All Data Deleted", Toast.LENGTH_SHORT).show();
                                    showdata= new ArrayList<>();
                                    viewAll();
                                    setdata();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Error Occurred", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();

            }
        });

    }

    public void setdata()
    {
        adapter = new DoctorAdapter(DoctorList.this, showdata);
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }


    public void UpdateData() {
//        btnviewUpdate.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
//                                editName.getText().toString(),
//                                editSurname.getText().toString(),editMarks.getText().toString());
//                        if(isUpdate == true)
//                            Toast.makeText(DoctorList.this,"Data Update",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(DoctorList.this,"Data not Updated",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
    }


    public void viewAll() {
        Cursor res = myDb.getAllDocData();
        if(res.getCount() == 0) {
            // show message
            txt_nothing.setVisibility(View.VISIBLE);
            txt_delete.setVisibility(View.GONE);
//            showMessage("Error","Nothing found");
            return;
        }
        else{
            txt_nothing.setVisibility(View.GONE);
            txt_delete.setVisibility(View.VISIBLE);
//            showMessage("Error","Nothing found");

        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            DoctorListModal resultModal = new DoctorListModal(res.getString(0),res.getString(1), res.getString(2),res.getString(4), res.getString(3));
            showdata.add(resultModal);
//            buffer.append("DOC_ID :"+ res.getString(0)+"\n");
//            buffer.append("DOC_NAME :"+ res.getString(1)+"\n");
//            buffer.append("DOC_EMAIL :"+ res.getString(2)+"\n");
//            buffer.append("DOC_DESIGNATION :"+ res.getString(3)+"\n\n");
        }

        // Show all data
//        showMessage("Data",buffer.toString());
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
