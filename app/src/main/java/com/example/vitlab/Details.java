package com.example.vitlab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Details extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;

TextView tvlab,tvtime,tvincharge,tvlunch,tvemail,email;
Button btn1,btn2,btn3,btn4,btn5,btn6;
    SimpleDateFormat simpleDateFormat;
    private Button button;
    Dialog dialog;
    String Time;
    private Timeslot timeslot;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        calendar=Calendar.getInstance();
        database=FirebaseDatabase.getInstance();
        ref = database.getReference().child("Lab");
        tvlab=(TextView) findViewById(R.id.tvalb);
        email=(TextView)findViewById(R.id.tvemail);
//        email=(TextView)findViewById(R.id.txt);
        tvtime=(TextView) findViewById(R.id.tvtime);
        tvincharge=(TextView) findViewById(R.id.tvincharge);
        tvlunch=(TextView) findViewById(R.id.tvlunch);

        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        btn5=(Button)findViewById(R.id.btn5);
        btn6=(Button)findViewById(R.id.btn6);
        String text1=btn1.getText().toString();
        String text2=btn2.getText().toString();
        String text3=btn3.getText().toString();
        String text4=btn4.getText().toString();
        String text5=btn5.getText().toString();
        String text6=btn6.getText().toString();
        String text=email.getText().toString();


        tvlab.setText(getIntent().getExtras().getString("Lab"));
        tvtime.setText(getIntent().getExtras().getString("Time"));
        tvincharge.setText(getIntent().getExtras().getString("Incharge"));
        tvlunch.setText(getIntent().getExtras().getString("Lunchtime"));
        timeslot=new Timeslot();
        simpleDateFormat=new SimpleDateFormat("HH:mm aa");
        Time=simpleDateFormat.format(calendar.getTime());
        dialog=new Dialog (Details.this );
        dialog.setContentView(R.layout.custom_dialog);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow ().setBackgroundDrawable (getDrawable (R.drawable.background));
        }

        dialog.getWindow ().setLayout (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable (false);

        dialog.getWindow ().getAttributes ().windowAnimations=R.style.animation;

        Button ok=dialog.findViewById (R.id.button3);
        Button cancel=dialog.findViewById (R.id.button2);

        ok.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (Details.this , "Okay", Toast.LENGTH_LONG).show();
                dialog.dismiss ();
            }
        });

        cancel.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (Details.this ,"Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss ();

            }
        });





        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text3.compareTo(Time) > 0) {

                    timeslot.setBtn3(text3);
                    timeslot.setEmail(email.getText().toString());
                    timeslot.setTvlab(tvlab.getText().toString());
                    ref.child(timeslot.getBtn3()).setValue(timeslot);
                    dialog.show ();






                } else if (text3.compareTo(Time) < 0) {
                    Toast.makeText(Details.this, "Past Time selected. Please kindly select time according give time", Toast.LENGTH_LONG).show();
                    btn1.setEnabled(false);


                } else if (text3.compareTo(Time) == 0) {
                    Toast.makeText(Details.this, "Past Time selected. Please kindly select time according give time", Toast.LENGTH_LONG).show();
                }
            }
        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (text2.compareTo(Time)>0 ){
//
//                    timeslot.setBtn2(text2);
//                    timeslot.setTvlab(tvlab.getText().toString());
//                    student.setEmail(email.getText().toString());
//
//                    ref.child(student.getEmail()).setValue(student);
//
//
//
//                }else if (text2.compareTo(Time)<0){
//                    Toast.makeText(Details.this, "Past Time selected. Please kindly select time according give time", Toast.LENGTH_LONG).show();
//                    btn2.setEnabled(false);
//
//
//                }else if (text2.compareTo(Time) == 0) {
//                    Toast.makeText(Details.this, "Past Time selected. Please kindly select time according give time", Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (text3.compareTo(Time)>0 ){
//
//                    timeslot.setBtn3(text3);
//                    timeslot.setTvlab(tvlab.getText().toString());
//                    student.setEmail(email.getText().toString());
//
//                    ref.child(student.getEmail()).setValue(student);
//
//
//
//                }else if (text3.compareTo(Time)<0){
//                    Toast.makeText(Details.this, "Past Time selected. Please kindly select time according give time", Toast.LENGTH_LONG).show();
//                    btn3.setEnabled(false);
//
//
//                }else if (text3.compareTo(Time) == 0) {
//                    Toast.makeText(Details.this, "Past Time selected. Please kindly select time according give time", Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (text4.compareTo(Time)>0 ){
//
//                    timeslot.setBtn4(text4);
//
//
//
//                }else if (text4.compareTo(Time)<0){
//                    Toast.makeText(Details.this, "Past Time selected. Please kindly select time according give time", Toast.LENGTH_LONG).show();
//                    btn4.setEnabled(false);
//
//
//                }else if (text4.compareTo(Time) == 0) {
//                    Toast.makeText(Details.this, "Past Time selected. Please kindly select time according give time", Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });
//        btn5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (text5.compareTo(Time)>0 ){
//
//                    timeslot.setBtn5(text5);
//
//
//
//                }else if (text5.compareTo(Time)<0){
//                    Toast.makeText(Details.this, "Past Time selected. Please kindly select time according give time", Toast.LENGTH_LONG).show();
//                    btn5.setEnabled(false);
//
//
//                }else if (text5.compareTo(Time) == 0) {
//                    Toast.makeText(Details.this, "Past Time selected. Please kindly select time according give time", Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });
//        btn6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (text6.compareTo(Time)>0 ){
//
//                    timeslot.setBtn6(text6);
//
//
//
//                }else if (text6.compareTo(Time)<0){
//                    Toast.makeText(Details.this, "Past Time selected. Please kindly select time according give time", Toast.LENGTH_LONG).show();
//                    btn6.setEnabled(false);
//
//
//                }else if (text6.compareTo(Time) == 0) {
//                    Toast.makeText(Details.this, "Past Time selected. Please kindly select time according give time", Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });
//
//

    }

}
