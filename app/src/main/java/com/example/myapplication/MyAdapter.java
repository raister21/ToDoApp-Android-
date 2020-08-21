package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> data;
    private String edited;
    private boolean updated;

    DatabaseHelper mydb;


    public MyAdapter(Context ct, ArrayList<String> dat) {
        context = ct;
        data = dat;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.data_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.MyViewHolder holder, final int position) {
        //set
        holder.txt.setText(data.get(position));

        //delete
        holder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.remove(position);
                notifyItemRemoved(position);
            }
        });

        //edit
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_dialog(view, position);
                holder.txt.setText(data.get(position));
                String posi = holder.txt.getText().toString();
                String id = null;
                id = Integer.toString((position + 1));


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt;
        Button cancelBtn, editBtn;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            txt = itemView.findViewById(R.id.textRow);
            cancelBtn = itemView.findViewById(R.id.deleteBtn);
            editBtn = itemView.findViewById(R.id.editBtn);

        }
    }

    public void edit_dialog(View view,final int focus) {

        final String id = Integer.toString(focus + 1);
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        LayoutInflater dialogInflator = LayoutInflater.from(context);
        View view1 = dialogInflator.inflate(R.layout.edit_layout, null);

        final EditText editText = (EditText) view1.findViewById(R.id.editToDo);
        Button confirmEditBtn = (Button) view1.findViewById(R.id.confirmEdit);

        alert.setView(view1);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(true);

        confirmEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edited = editText.getText().toString();
                data.set(focus, edited);

                notifyItemChanged(focus);
                alertDialog.dismiss();

            }
        });

        alertDialog.show();



    }


}
