package com.karandeepsingh.rollball;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

public class AsyncGettingBitmapFromUrl extends AsyncTask<String, Void, Bitmap> {


    @Override
    protected Bitmap doInBackground(String... params) {

        Bitmap bmp=null;
        try {

            URL url = new URL("https://www.w3schools.com/w3css/img_lights.jpg");
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        System.out.println("bitmap" + bitmap);

    }
}
