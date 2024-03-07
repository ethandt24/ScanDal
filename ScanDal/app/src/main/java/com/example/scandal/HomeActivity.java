package com.example.scandal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private ImageView profile;
    private Button scan;
    private TextView attendeeEvents;
    private TextView eventBrowser;
    private ImageView gear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_scanning_page);
         profile = findViewById(R.id.profilePicture);
         scan = findViewById(R.id.buttonScanQRCode);
         attendeeEvents = findViewById(R.id.buttonViewMyAttendeeEvents);
         eventBrowser = findViewById(R.id.buttonBrowseEvents);
         eventBrowser.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myintent = new Intent(HomeActivity.this, );
             }
         });
         attendeeEvents.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myintent = new Intent(HomeActivity.this, PlaceHolder.class); // replace placeholder class with the activity for attendee
                 myintent.putExtra("userProfile", );
                 startActivity(myintent);
             }
         });
         scan.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent myintent = new Intent(HomeActivity.this, QRCodeScanner.class);
                 startActivity(myintent);
             }
         });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(HomeActivity.this, Profile.class);
                startActivity(myintent);
            }
        });

    }
}
