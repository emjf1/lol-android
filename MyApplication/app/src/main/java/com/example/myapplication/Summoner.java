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

    private Global global = new Global();
    private String id;
    private String accountId;
    private String puuid;
    private int profileIconId;
    private long revisionDate;
    private String name;
    private int level;
    private boolean error = false;
    final private String apiKey = "RGAPI-b0f9ae04-cc81-4048-ab4e-b8d29c056526";

    public String getId(){return id;}

    public void setId(String id){this.id = id;}

    public String getAccountId(){return accountId;}

    public void setAccountId(String accountId){this.accountId = accountId;}

    public String getPuuid(){return puuid;}

    public void setPuuid(String puuid){this.puuid = puuid;}

    public long getRevisionDate(){return revisionDate;}

    public void setRevisionDate(long revisionDate){this.revisionDate = revisionDate;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public int getProfileIconId(){return profileIconId;}

    public int getLevel(){return level;}

    public void setLevel(int level){this.level = level;}

    public boolean getError(){ return error;}

    public void setError(boolean error){this.error = error;}

    public Summoner(String nombre, String server){
        String prueba = global.regionesCodigo[global.devolverPosicion(server)];
        final String url = "https://" + global.regionesCodigo[global.devolverPosicion(server)] + ".api.riotgames.com/lol/summoner/v4/summoners/by-name/" + nombre + "?api_key=" + apiKey;

        try {
            ponerAtributos(new JSONObject(new DownLoadStringToJSONTask(url).json_string));
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
}
