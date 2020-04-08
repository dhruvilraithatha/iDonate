package com.care.idonate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class yesact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yesact);
        Toast.makeText(this, "Thank You, Kindly get to the hospital as soon as you can.", Toast.LENGTH_SHORT).show();
    }
}
