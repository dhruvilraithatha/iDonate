package com.care.idonate;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.util.Log.println;

public class request_blood extends AppCompatActivity {
    String[ ] bgroup = {"Select Blood Group","A+","A-","B+","B-","AB+","AB-","O+","O-"};
    String[ ] count = {"1","2","3","4","5","6","7","8","9"};
    String blood_group,blood_count;
    Button btn_go;
    int i,i1;
    int value;
    TextView tv;
    int[] arr=new int[100];
            int j=0;
    String s;
    FirebaseDatabase database;
    int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood);

        Spinner spn_bg = (Spinner)findViewById(R.id.spn_bg);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bgroup);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_bg.setAdapter(aa);
        spn_bg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                blood_group = parent.getItemAtPosition(position).toString();


                database = FirebaseDatabase.getInstance();
                DatabaseReference myRef1 = database.getReference("number");
                myRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        value = dataSnapshot.getValue(Integer.class);
                        }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spn_count = (Spinner)findViewById(R.id.spn_count);
        ArrayAdapter arc = new ArrayAdapter(this,android.R.layout.simple_spinner_item,count);
        arc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_count.setAdapter(arc);
        spn_count.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                blood_count = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btn_go=(Button)findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(request_blood.this, blood_group, Toast.LENGTH_SHORT).show();
                 i1=1;
                for ( i=1;i<=value;i++)
                {

                   // Toast.makeText(request_blood.this, Integer.toString(i), Toast.LENGTH_SHORT).show();

                    Log.d("ok4","hiii");

                            database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef1 = database.getReference(i+"/blood group");
                            myRef1.addValueEventListener(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // This method is called once with the initial value and again
                                    // whenever data at this location is updated.

                                    //i1=i;
                                    s = dataSnapshot.getValue(String.class);
                                    if(s.equals(blood_group))
                                    {

                                        DatabaseReference dbnoti = database.getReference(i1+"/notification");
                                        dbnoti.setValue("1");
                                        i1++;
                                        //Toast.makeText(request_blood.this, Integer.toString(i), Toast.LENGTH_SHORT).show();
                               Toast.makeText(request_blood.this, Integer.toString(arr[j]), Toast.LENGTH_SHORT).show();



                                Log.d("ok5","hiii");
                                    }
                                    else
                                    {
                                        i1++;
                                    }

                                    // Toast.makeText(request_blood.this, s, Toast.LENGTH_SHORT).show();

                                }


                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value

                                }
                            });
          }





            }
        });


    }



}
