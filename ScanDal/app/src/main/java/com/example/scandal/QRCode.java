package com.example.scandal;


import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;
import com.google.mlkit.vision.common.InputImage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.util.concurrent.atomic.AtomicBoolean;
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
     */
    public void generateQR(ImageView img, String token){
        Integer dim =Math.min(img.getHeight(), img.getWidth());
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = writer.encode(token, BarcodeFormat.QR_CODE, dim, dim);
            BarcodeEncoder encoder = new BarcodeEncoder();
            QRPic = encoder.createBitmap(bitMatrix);
            img.setImageBitmap(QRPic);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

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
