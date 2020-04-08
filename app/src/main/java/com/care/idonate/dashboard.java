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

public class dashboard extends AppCompatActivity {

    FirebaseDatabase database;
    String noty,vn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        final String sessionId = getIntent().getStringExtra("key");
        Toast.makeText(this, sessionId, Toast.LENGTH_SHORT).show();
        database = FirebaseDatabase.getInstance();
        DatabaseReference noti = database.getReference(sessionId+"/notification");
        noti.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                noty = dataSnapshot.getValue(String.class);
                vn="1";

                if(noty.equals(vn))
                {
                    Intent i = new Intent(dashboard.this, yesact.class);
                    Intent i2 = new Intent(dashboard.this, noact.class);
                    i.putExtra("d_count",sessionId);
                    PendingIntent yesact = PendingIntent.getActivity(dashboard.this, 0, i, PendingIntent.FLAG_ONE_SHOT);
                    PendingIntent noact = PendingIntent.getActivity(dashboard.this,0,i2, PendingIntent.FLAG_ONE_SHOT);

                    NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notify=new Notification.Builder
                            (getApplicationContext()).setContentTitle("requirement").setContentText("Do you want to donate?").
                            setContentTitle("Your blood is needed").setSmallIcon(R.drawable.ic_launcher_background)
                            .addAction(R.drawable.ic_launcher_background,"Yes",yesact)
                            .addAction(R.drawable.ic_launcher_background,"No",noact).build();

                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    notif.notify(0, notify);
                    DatabaseReference dbnoti = database.getReference(sessionId +"/notification");
                    dbnoti.setValue("0");





                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
}
