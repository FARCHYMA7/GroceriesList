package com.example.grocerieslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<DataModel> dataSet;
    private CustomeAdapter adapter;
    private EditText input;

    FirebaseAuth auth;
    Button btn_logout;
    Button btn_addItem;
    TextView userName;
    FirebaseUser userCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        userCard = auth.getCurrentUser();
        if (userCard == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else {
            dataSet = new ArrayList<>();
            btn_addItem = findViewById(R.id.btn_add);
            btn_logout = findViewById(R.id.btn_logout);
            userName = findViewById(R.id.textView_user);
            userName.setText(userCard.getEmail());
            recyclerView = findViewById(R.id.res);
            input = findViewById(R.id.et_input);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            adapter = new CustomeAdapter(dataSet);
            recyclerView.setAdapter(adapter);

            btn_addItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a = String.valueOf(input.getText());
                    Toast.makeText(MainActivity.this, a, Toast.LENGTH_SHORT).show();
                    if (a.compareTo("") != 0)
                    {
                        dataSet.add(new DataModel(a));
                        input.getText().clear();
                        adapter.notifyItemInserted(dataSet.size()-1);
                    }

                }
            });

            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                }
            });





        }

    }
}