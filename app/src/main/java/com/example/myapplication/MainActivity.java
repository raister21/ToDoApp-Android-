package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Currency;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> todos = new ArrayList<>();
    String input;
    Cursor res;

    RecyclerView recyclerView;
    DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);

        res = mydb.getAllData();
        getDataFromDB(res, todos);

        Button btn = (Button) findViewById(R.id.button);
        final EditText eTxt = (EditText) findViewById(R.id.editText);

        final MyAdapter myAdapter = new MyAdapter(this,todos);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Insert into program
                input = eTxt.getText().toString();
                todos.add(input);
                myAdapter.notifyItemChanged(todos.size());
                String row = Integer.toString(todos.size());

                //Insert into db
                Boolean isInserted = mydb.insertData(row,input);

                if (isInserted == true) {
                    System.out.println("Inserted");
                } else {
                    System.out.println("Not inserted");
                }

            }
        });

    }

    public void getDataFromDB(Cursor r, ArrayList<String> todos) {

        if (r.getCount() == 0) {
            return ;
        }

        while (r.moveToNext()){
            todos.add(r.getString(1));
        }

        System.out.println("Got data from db");

    }
}