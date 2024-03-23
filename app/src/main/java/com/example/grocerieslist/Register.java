package com.example.grocerieslist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Register extends AppCompatActivity {

    TextInputEditText editTextUserName, editTextPassword, editTextPhone;
    Button buttonRegister;
    FirebaseDatabase database;
    DatabaseReference reference;
    ProgressBar progressBar;
    TextView textView;
    boolean isUserExist = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUserName = findViewById(R.id.regUsername);
        editTextPassword = findViewById(R.id.regPassword);
        editTextPhone = findViewById(R.id.regPhone);
        buttonRegister = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginInReg);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                progressBar.setVisibility(View.VISIBLE);
                String userName, password, phone;
                userName = String.valueOf(editTextUserName.getText());
                password = String.valueOf(editTextPassword.getText());
                phone = String.valueOf(editTextPhone.getText());

                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(Register.this, "Enter user name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(Register.this, "Enter phone", Toast.LENGTH_SHORT).show();
                    return;
                }

                //checkUser();
                if(isUserExist == false){
                    UsersClass newUser = new UsersClass(userName, password, phone);
                    reference.child(userName).setValue(newUser);
                    Toast.makeText(Register.this, "Account created",
                            Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("username", userName);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void checkUser(){
        String userUserName = String.valueOf(editTextUserName.getText());
        String userPassword = String.valueOf(editTextPassword.getText());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUserName);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    Toast.makeText(Register.this, "User already exist",
                            Toast.LENGTH_SHORT).show();
                    isUserExist = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}