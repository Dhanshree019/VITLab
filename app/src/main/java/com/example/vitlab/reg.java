package com.example.vitlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class reg extends AppCompatActivity {
//EditText email;
//EditText pass;
//private student student;
//FirebaseDatabase database;
//DatabaseReference ref;
EditText name, email, password;
    Button register;
    TextView send;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
//        email=(EditText) findViewById(R.id.email);
//        pass=(EditText) findViewById(R.id.pass);
//        student=new student();
//        database=FirebaseDatabase.getInstance();
//        ref = database.getReference().child("Student");
//
//
//    }
//    public void sub(View view){
//        student.setEmail(email.getText().toString());
//        student.setPass(pass.getText().toString());
//        ref.child(student.getEmail()).setValue(student);
//
//
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.email);
        register = findViewById(R.id.sub);


        register.setOnClickListener(v -> {
            registerAdmin();
        });


    }

    private void registerAdmin() {
        String aName, aEmail, aPass;
        aName = name.getText().toString().trim();
        aEmail = email.getText().toString().trim();
        aPass = password.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (aName == null || aEmail == null || aPass == null ||
                aName.isEmpty() || aEmail.isEmpty() || aPass.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else if (!aEmail.matches(emailPattern)) {
            Toast.makeText(getApplicationContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();

        } else {

            //check if email already exists in the database
//            Query query = myRef.child("Admins").orderByChild("email").equalTo(aEmail);
//            query.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()) {
//                        Toast.makeText(RegisterActivity.this, "This email is already registered", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Admin admin = new Admin(aName, aEmail, aPass);
//
//                        myRef.child("Admins").push().setValue(admin)
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            Toast.makeText(RegisterActivity.this, "Admin Account Created Successfully", Toast.LENGTH_SHORT).show();
//                                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                                            startActivity(intent);
//                                        } else {
//                                            Toast.makeText(RegisterActivity.this, "Error: "+task.getException().toString(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
//
//                }
//            });

            mAuth.createUserWithEmailAndPassword(aEmail, aPass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String id = mAuth.getCurrentUser().getUid();
                                Admin admin = new Admin(aName, aEmail, true);

                                myRef.child("Students").child(id).setValue(admin)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(reg.this, "Admin Account Created Successfully", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(reg.this, MainActivity.class);
                                                    startActivity(intent);
                                                } else {
                                                    Toast.makeText(reg.this, "Error: " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(reg.this, "Some error occurred: " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthUserCollisionException e) {
                                    Toast.makeText(reg.this, "email already used", Toast.LENGTH_SHORT).show();
                                    //checkIfAdmin(aEmail);
                                } catch (Exception e) {
                                    Toast.makeText(reg.this, "some error occurred: " + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

        }

    }




    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(reg.this, MainActivity.class));
            finish();
        }
   }

}