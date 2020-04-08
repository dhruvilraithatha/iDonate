package com.care.idonate;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class donor extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button btn_male,btn_female,btn_done,btn_submit;
    String item,sex,name,email,pass;
    Integer value;
    EditText edt_name,edt_email,edt_pass;
 FirebaseDatabase database;
  //  DatabaseReference myRef;
    TextView tv4;
    String noty,vn;
    String[ ] bgroup = {"Select Blood Group","A+","A-","B+","B-","AB+","AB-","O+","O-"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        String sessionId = getIntent().getStringExtra("key");
       // Toast.makeText(this, sessionId, Toast.LENGTH_SHORT).show();
        Spinner spin = (Spinner)findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bgroup);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_pass=(EditText)findViewById(R.id.edt_pass);
        edt_name=(EditText)findViewById(R.id.edt_name);




        btn_male=(Button)findViewById(R.id.btn_sex_male);
        btn_female=(Button)findViewById(R.id.btn_sex_female);
        btn_done=(Button)findViewById(R.id.btn_done);
        btn_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_male.setBackgroundResource(R.drawable.malep);
                btn_female.setBackgroundResource(R.drawable.female);
                sex="male";

                database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("number");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        value = dataSnapshot.getValue(Integer.class);
                        value=value+1;
                        //tv4.setText(value.toString());
                        //value1=Integer.toString(value);
                      // Toast.makeText(donor.this, value.toString(), Toast.LENGTH_LONG).show();



                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                    }
                });
            }
        });
        btn_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_male.setBackgroundResource(R.drawable.male);
                btn_female.setBackgroundResource(R.drawable.femalep);
                //sex="female";
                database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("number");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        value = dataSnapshot.getValue(Integer.class);
                        value=value+1;
                        //tv4.setText(value.toString());
                        //value1=Integer.toString(value);
                       // Toast.makeText(donor.this, value.toString(), Toast.LENGTH_LONG).show();



                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                    }
                });
            }
        });


        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edt_name.getText().toString();
                email = edt_email.getText().toString();
                pass = edt_pass.getText().toString();

                DatabaseReference num = database.getReference( "number");
                num.setValue(value);
                DatabaseReference dbname = database.getReference(value + "/name");
                dbname.setValue(name);

                DatabaseReference dbmail = database.getReference(value + "/email");
                dbmail.setValue(email);

                DatabaseReference dbsex = database.getReference(value + "/sex");
                dbsex.setValue(sex);

                DatabaseReference bgroup = database.getReference(value + "/blood group");
                bgroup.setValue(item);

                DatabaseReference dbpass = database.getReference(value + "/password");
                dbpass.setValue(pass);

                DatabaseReference dbnoti = database.getReference(value + "/notification");
                dbnoti.setValue("0");
                DatabaseReference cnt = database.getReference(value + "/count");
                cnt.setValue(0);
               Toast.makeText(donor.this, value.toString(), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(donor.this,login.class);
                startActivity(i);







            }
        });





        }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item = parent.getItemAtPosition(i).toString();
               // Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }




            public void validat()
            {
                //Validations
                if(edt_name.length() == 0 || edt_email.length() == 0)
                {
                    Toast.makeText(donor.this, "All fields are compulsory!", Toast.LENGTH_SHORT).show();
                }

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(edt_email.getText().toString().matches(emailPattern))
                {
                    Toast.makeText(donor.this, "Valid Email", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(donor.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
            }

}
