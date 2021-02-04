package com.app.HospitalManagement.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.HospitalManagement.DatabaseHandler;
import com.app.HospitalManagement.R;
import com.app.HospitalManagement.modals.DoctorListModal;
import com.app.HospitalManagement.utils.AddDoctor;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ProductViewHolder>
{
    private Context mCtx;
    DatabaseHandler myDb;

    //we are storing all the products in a list
    private List<DoctorListModal> productList;

    //getting the context and product list with constructor
    public DoctorAdapter(Context mCtx, List<DoctorListModal> productList) {
        this.mCtx = mCtx;
        this.productList = productList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.doctor_list_item, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        //getting the product of the specified position
        final DoctorListModal product = productList.get(position);

        //binding the data with the viewholder views
        holder.txt_name.setText(":  "+product.getName());
        holder.txt_email.setText(":  "+product.getEmail());
        holder.txt_design.setText(":  "+product.getDesignation());
        holder.txt_qualification.setText(":  "+product.getQualification());

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
//                Toast.makeText(mCtx,product.getId(),Toast.LENGTH_LONG).show();

                    showAlert(product.getId(),position);

            }

        });

        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(mCtx, AddDoctor.class);
                intent.putExtra("screen", "edit");
                intent.putExtra("name", product.getName());
                intent.putExtra("email", product.getEmail());
                intent.putExtra("quali", product.getQualification());
                intent.putExtra("id", product.getId());
                mCtx.startActivity(intent);
            }
        });

    }

    public void showAlert(final String id, final int position){
        final AlertDialog alertDialog = new AlertDialog.Builder(mCtx)

                .setTitle("Do you want to delete this record?")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myDb = new DatabaseHandler(mCtx);

                        Integer deletedRows = myDb.deleteDocData(id);
                        if(deletedRows > 0)
                        {
                            Toast.makeText(mCtx,"Data Deleted",Toast.LENGTH_LONG).show();
//                            .remove(adapter.getItem(position));
                            productList.remove(position);
                            notifyDataSetChanged();
                        }
                        else
                        {
                            Toast.makeText(mCtx,"Data not Deleted",Toast.LENGTH_LONG).show();
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


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name,txt_design,txt_email,txt_qualification;
        ImageView img_edit,img_delete;

        public ProductViewHolder(View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name);
            txt_design = itemView.findViewById(R.id.txt_design);
            txt_email = itemView.findViewById(R.id.txt_email);
            txt_qualification = itemView.findViewById(R.id.txt_qualification);
            img_edit = itemView.findViewById(R.id.img_edit);
            img_delete = itemView.findViewById(R.id.img_delete);
        }
    }
}
