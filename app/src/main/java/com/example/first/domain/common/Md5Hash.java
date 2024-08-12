package com.example.first.domain.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import android.graphics.Bitmap;
import android.util.Log;

import com.squareup.picasso.Picasso;

import java.security.NoSuchAlgorithmException;

public class Md5Hash {

    private final String flugHash;

    private static Md5Hash instance = null;

    private Md5Hash(){
        try {
            Bitmap bitmap = Picasso
                .get()
                .load("https://st.kp.yandex.net/images/no-poster.gif")
                .get();
            flugHash = getMd5Hash(bitmap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Md5Hash getInstance(){
        if (instance == null)
            instance = new Md5Hash();
        return instance;
    }


    public byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public String getMd5Hash(Bitmap bitmap){
        return getMD5Hash(bitmapToByteArray(bitmap));
    }

    public String getMD5Hash(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(data);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isPlug(Bitmap bitmap){
        Log.d("aa99", "IIIIISSSSSPLUUUG");

        Log.i("aa99", flugHash + "  " + getMd5Hash(bitmap));
        Log.d("aa99", "=======");
        return flugHash.equals(getMd5Hash(bitmap));
    }
}
