package com.care.idonate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
EditText edt_email,edt_pass;
Button btn_signin;
String id,pass,tid,tpass;
int number,i=0;
FirebaseDatabase database;
String arrayid[];
String arratpass[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_pass=(EditText)findViewById(R.id.edt_pass);
        btn_signin=(Button)findViewById(R.id.btn_sign_in);


        final Handler handler = new Handler();




        database = FirebaseDatabase.getInstance();
        /*DatabaseReference myRef = database.getReference("number");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                number= dataSnapshot.getValue(Integer.class);
               // String number1= Integer.toString(number);
               // Toast.makeText(login.this, number1, Toast.LENGTH_LONG).show();

                //tv4.setText(value.toString());
                //value1=Integer.toString(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });*/







        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=edt_email.getText().toString();
                pass=edt_pass.getText().toString();
                i=Integer.parseInt(id);





                    DatabaseReference dpass = database.getReference(i+"/password");
                    dpass.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            tpass= dataSnapshot.getValue(String.class);
                            //Toast.makeText(login.this, tpass, Toast.LENGTH_LONG).show();
//                            arratpass[i]=tpass;
                            //tv4.setText(value.toString());
                            //value1=Integer.toString(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                        }
                    });


                    if (  pass.equals(tpass))
                    {
                        if(id.equals("0"))
                        {
                            Toast.makeText(login.this, "Welcome Doctor", Toast.LENGTH_LONG).show();
                            Intent i1=new Intent(login.this,request_blood.class);
                            startActivity(i1);
                        }
                        else
                        {
                            Toast.makeText(login.this, "done", Toast.LENGTH_LONG).show();
                            Intent i=new Intent(login.this,dashboard.class);
                            i.putExtra("key",id);
                            startActivity(i);
                        }

                    }
                    else
                    {
                        Toast.makeText(login.this, "Processing...Click Again!", Toast.LENGTH_LONG).show();
                    }


                //}




                // Intent i=new Intent(login.this,donor.class);
                //startActivity(i);
                }
            });





      /*  btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=edt_email.getText().toString();
                pass=edt_pass.getText().toString();
                //Toast.makeText(login.this, pass, Toast.LENGTH_LONG).show();
                if (id.equals("abc") && pass.equals("abc"))
                {
                    Toast.makeText(login.this, "done", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(login.this, "fail", Toast.LENGTH_LONG).show();
                }


               }
            });*/










    }
}
