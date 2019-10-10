package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Summoner {

    private String id;
    private String accountId;
    private String puuid;
    private int profileIconId;
    private long revisionDate;
    private String name;
    private int level;
    private Bitmap imagenInvocador;
    final private String apiKey = "RGAPI-c005b8c1-0ac5-4e84-b7ae-e511568dc4e4";

    public String getId(){return id;}

    public void setId(String id){this.id = id;}

    public int getProfileIconId(){return profileIconId;}

    public Bitmap getImagenInvocador(){return imagenInvocador;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public int getLevel(){return level;}

    public void setLevel(int level){this.level = level;}

    private boolean error = false;

    public boolean getError(){
        return error;
    }


    private String json_string;

    public Summoner(String nombre){
        final String url = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + nombre + "?api_key=" + apiKey;

        try {
            json_string = new DownLoadStringToJSONTask(url).json_string;
            ponerAtributos(new JSONObject(json_string));
        } catch (JSONException exception) {
            System.out.println("Fallo lectura: " + exception.getMessage());
        }

    }

    private void ponerAtributos(JSONObject obj) throws JSONException{
        if(obj.isNull("status_code")){
            profileIconId = obj.getInt("profileIconId");
            name = obj.getString("name");
            level = obj.getInt("summonerLevel");
            revisionDate = obj.getLong("revisionDate");
            id = obj.getString("id");
            accountId = obj.getString("accountId");
        }else{
            error = true;
        }
    }

    private String conseguirString(String urlQ) {
        StringBuilder content = new StringBuilder();

        // many of these calls can throw exceptions, so i've just
        // wrapped them all in one try/catch statement.
        try
        {
            // create a url object
            URL url = new URL(urlQ);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            // read from the urlconnection via the bufferedreader
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
