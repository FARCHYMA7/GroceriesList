package com.example.grocerieslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.text.TextWatcher;
import android.text.Editable;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private ArrayList<DataModel> dataSet;

    private CustomeAdapter adapter;

    private EditText input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSet = new ArrayList<>();

        recyclerView = findViewById(R.id.res);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        input = findViewById(R.id.et_input);

        recyclerView.setItemAnimator(new DefaultItemAnimator());


        adapter = new CustomeAdapter(dataSet);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn_add).setOnClickListener(view -> {
            String a = String.valueOf(input.getText());
            dataSet.add(new DataModel(a));
            input.getText().clear();
            adapter.notifyItemInserted(dataSet.size()-1);

        });


    }
}