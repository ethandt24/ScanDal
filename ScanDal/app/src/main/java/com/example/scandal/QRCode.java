package com.example.scandal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;
import com.google.mlkit.vision.common.InputImage;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicBoolean;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRCode {
    private Bitmap QRPic;
    private String QRToken;
    public boolean scanCode(Context context){
        GmsBarcodeScannerOptions options = new GmsBarcodeScannerOptions.Builder()
                .setBarcodeFormats(
                        Barcode.FORMAT_QR_CODE)
                .enableAutoZoom()
                .build();
        GmsBarcodeScanner scanner = GmsBarcodeScanning.getClient(context, options);
        AtomicBoolean ret = new AtomicBoolean(false);
        scanner
                .startScan()
                .addOnSuccessListener(
                        barcode -> {
                            // Task completed successfully
                            this.QRToken = barcode.getRawValue();
                            ret.set(true);

                        })
                .addOnCanceledListener(
                        () -> {
                            // Task canceled

                        })
                .addOnFailureListener(
                        e -> {
                            // Task failed with an exception
                        });
        return ret.get();
    }

    /**
     * sets the attribute QRPic to the BitMap
     * @param img
     */
    public void generateQR(ImageView img, String token){
        int width = img.getWidth();
        int height = img.getHeight();
        int dimension = height<width? height : width;
        QRGEncoder qrgEncoder = new QRGEncoder(QRToken, null, QRGContents.Type.TEXT, dimension);
        qrgEncoder.setColorBlack(Color.RED);
        qrgEncoder.setColorWhite(Color.BLUE);
        // Getting QR-Code as Bitmap
        QRPic = qrgEncoder.getBitmap();
        // Setting Bitmap to ImageView
        img.setImageBitmap(QRPic);

    }
    public boolean getQRFromImage(Bitmap bitmap, Context context) {
        InputImage image;
        BarcodeScanner scanner = BarcodeScanning.getClient();
        

        return false;
    }



    public String getQRToken() {
        return QRToken;
    }

    public Bitmap getQRPic() {
        return QRPic;
    }
}
