package com.example.scandal;
import android.content.Context;

import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

import java.util.concurrent.atomic.AtomicBoolean;

public class QRCodeScanner {
    /**
     * This Class manages QR code scanning
     */
    private String QR;


    /**
     * initializes the QR code to be an empty String
     */
    public QRCodeScanner(){
        QR = "";
    }

    /**
     * Takes the current context usually will be "this" if used in MainActivity.java
     * and scans a QR code storing the value from the QR code in the attribute QR
     * which can be retrieved using the getQR method. Returns a boolean value
     * indicating whether a QR was read successfully or not
     * @param context The context that this method is being called
     * @return boolean whether a QR code was successfully read
     */
    public boolean ScanQR(Context context){
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
                            this.QR = barcode.getRawValue();
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
     * returns the QR code attribute
     * @return the String QR
     */
    public String getQR() {
        return QR;
    }
}
