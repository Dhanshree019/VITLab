package com.example.vitlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Intro extends AppCompatActivity {
TextView txt;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        txt=(TextView)findViewById(R.id.txt);
        String text=txt.getText().toString();

       btn =(Button)findViewById(R.id.btn);
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(Intro.this,Dashboard.class);
               intent.putExtra("User",text);
               startActivity(intent);
           }
       });
        Intent intent=getIntent();
        String username=intent.getStringExtra("Username");
        txt.setText("Welcome,"+"   "+username);
    }
}