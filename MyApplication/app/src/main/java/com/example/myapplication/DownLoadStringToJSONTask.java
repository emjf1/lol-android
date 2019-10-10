package com.example.myapplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DownLoadStringToJSONTask{

    public String json_string;

    public DownLoadStringToJSONTask(String urlString){
        final String url = urlString;
        Thread thread = new Thread() {
            @Override
            public void run() {
                json_string = conseguirString(url);
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected String conseguirString(String urlString){
        StringBuilder content = new StringBuilder();
        try
        {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
}