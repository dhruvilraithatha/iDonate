package com.care.idonate;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class test extends AppCompatActivity {
Button bt;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        bt=(Button)findViewById(R.id.button);
        String sessionId = getIntent().getStringExtra("key");
        database = FirebaseDatabase.getInstance();
        DatabaseReference noti = database.getReference(sessionId+"/notification");
        noti.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String noty = dataSnapshot.getValue(String.class);
                if(noty.equals("1"))
                {
                    NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notify=new Notification.Builder
                            (getApplicationContext()).setContentTitle("requirement").setContentText("body").
                            setContentTitle("subject").setSmallIcon(R.drawable.ic_launcher_background).build();

                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    notif.notify(0, notify);
                }
                //tv4.setText(value.toString());
                //value1=Integer.toString(value);



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


    }
}
