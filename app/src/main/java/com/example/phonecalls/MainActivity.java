package com.example.phonecalls;

import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBtn = (Button) findViewById(R.id.makeCall);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                makeCall();
            }
        });
    } /* * --------------------------------- * Method: Making Phone Calls * --------------------------------- */

    private void RequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 0);
        }
    }

    private void makeCall() {
        Log.i("Make a call", "");
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:25-123-123"));
        try {
            RequestPermissions();
            startActivity(phoneIntent);
            finish();
            Log.i("Finish making a call...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "Call disconnected! Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}
