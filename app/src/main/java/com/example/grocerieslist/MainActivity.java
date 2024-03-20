package com.example.grocerieslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
    private Button btn_logout;
    private Button btn_addItem;
    private TextView userName;
    FirebaseAuth auth;
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
            btn_addItem = findViewById(R.id.btn_add);
            btn_logout = findViewById(R.id.btn_logout);
            userName = findViewById(R.id.textView_user);
            recyclerView = findViewById(R.id.res);
            input = findViewById(R.id.et_input);
            dataSet = new ArrayList<>();

            String dbName = String.valueOf(userCard.getEmail());
            String userNameToDisplay = dbName.split("@")[0];
            userName.setText("Hello " + userNameToDisplay);


            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());


            adapter = new CustomeAdapter(dataSet);
            recyclerView.setAdapter(adapter);

            btn_addItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nameItem = String.valueOf(input.getText());
                    if (nameItem.compareTo("") != 0)
                    {
                        if (!isExist(dataSet, nameItem))
                        {
                            dataSet.add(new DataModel(nameItem));
                            input.getText().clear();
                            adapter.notifyItemInserted(dataSet.size()-1);
                        }

                        else {
                            Toast.makeText(MainActivity.this, "Item already exist",
                                    Toast.LENGTH_SHORT).show();
                        }
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

    public boolean isExist(ArrayList<DataModel> dataSet, String nameCheck) {
        for (DataModel i : dataSet)
        {
            if (i.getNameItem().compareTo(nameCheck) == 0)
                return true;
        }

        return false;
    }
}