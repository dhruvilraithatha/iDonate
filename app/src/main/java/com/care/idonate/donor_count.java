package com.care.idonate;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class donor_count extends AppCompatActivity {
int count;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_count);
        String sessionId = getIntent().getStringExtra("d_count");
        Toast.makeText(this, sessionId, Toast.LENGTH_SHORT).show();
        database = FirebaseDatabase.getInstance();
        DatabaseReference noti = database.getReference(sessionId+"/count");
        noti.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                count = dataSnapshot.getValue(Integer.class);
count=count+1;


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        DatabaseReference cnt = database.getReference(sessionId + "/count");
        cnt.setValue(count);
        Intent i=new Intent(donor_count.this,dashboard.class);
    }
}
