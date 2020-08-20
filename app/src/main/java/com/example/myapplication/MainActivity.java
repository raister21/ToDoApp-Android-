package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> todos = new ArrayList<>();
    String input;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        Button btn = (Button) findViewById(R.id.button);
        final EditText eTxt = (EditText) findViewById(R.id.editText);

        final MyAdapter myAdapter = new MyAdapter(this,todos);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = eTxt.getText().toString();
                todos.add(input);
                myAdapter.notifyItemChanged(todos.size());
                System.out.println(todos.size());

            }
        });

    }
}