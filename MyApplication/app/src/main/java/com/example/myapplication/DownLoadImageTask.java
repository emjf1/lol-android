package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.InputType;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
    ImageView imageView;

    public DownLoadImageTask(ImageView imageView){
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String...urls){
        try {
            URL url = new URL(urls[0]);
            URLConnection urlConnection = url.openConnection();

            InputStream is = url.openStream();
            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Bitmap result){
        imageView.setImageBitmap(result);
    }
}