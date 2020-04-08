package com.care.idonate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class noact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noact);
        Toast.makeText(this, "Thank You, We hope you might consider donating blood later.", Toast.LENGTH_SHORT).show();
    }
}
