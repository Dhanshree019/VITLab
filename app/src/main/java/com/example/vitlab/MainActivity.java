package com.example.vitlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button login,reg;


    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.sign);
        reg = findViewById(R.id.reg);


        login.setOnClickListener(v -> {
            loginAdmin();
        });
        reg.setOnClickListener(v -> {
            sendToRegister();
        });



    }

    private void loginAdmin() {
        String aEmail, aPass;
        aEmail = email.getText().toString().trim();
        aPass = password.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (aEmail == null || aPass == null ||
                aEmail.isEmpty() || aPass.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else if (!aEmail.matches(emailPattern)) {
            Toast.makeText(getApplicationContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(aEmail, aPass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String id = mAuth.getCurrentUser().getUid();
                                myRef.child("Students").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                        Admin admin = snapshot.getValue(Admin.class);

                                        if (admin.getAdmin()) {
                                            Toast.makeText(MainActivity.this, "Login successful, welcome admin", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(MainActivity.this, Intro.class));
                                            finish();
                                        } else {
                                            Toast.makeText(MainActivity.this, "This is not an admin account", Toast.LENGTH_SHORT).show();
                                            mAuth.signOut();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                    }
                                });
                            }
                        }
                    });

        }
    }

    public void sendToRegister() {
        startActivity(new Intent(MainActivity.this, reg.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, Intro.class));
            finish();
        }
    }



}