package com.example.scandal;

import static androidx.core.content.ContextCompat.getSystemService;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_qr_page);
        EditText text = findViewById(R.id.check);
        QRCode QR1 = new QRCode();
        ImageView QrView = findViewById(R.id.QRPview);
        QR1.generateQR(QrView, "Halloween is spooky");
//        setContentView(R.layout.activity_main);
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(v -> {
//            QRCode QR = new QRCode();
//            String code;
//            QR.scanCode(this);
//            code = QR.getQRToken();
//        });

    }
}