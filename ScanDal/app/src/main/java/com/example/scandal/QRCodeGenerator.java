package com.example.scandal;

import android.widget.ImageView;

public class QRCodeGenerator {
    private String QRToken;
    private String QR="";
    public QRCodeGenerator(){
        QRToken="";
    }
    public QRCodeGenerator(String QRToken){
        this.QRToken = QRToken;
    }

    public void setQRToken(String QRToken) {
        this.QRToken = QRToken;
    }

    public boolean generateQR(ImageView img){
        int width = img.getWidth();
        int height = img.getHeight();
        int dimension = height<width? height : width;
        QRGEncoder qrgEncoder = new QRGEncoder(QRToken, null, QRGContents.Type.TEXT, smallerDimension);
    }
}
