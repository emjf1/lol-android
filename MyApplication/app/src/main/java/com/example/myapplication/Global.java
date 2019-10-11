package com.example.myapplication;

import org.json.JSONArray;
import org.json.JSONException;

public class Global {
    public final String[] regionesPublico = {"BR", "EUW", "EUNE", "JP", "KR", "LAN", "LAS", "NA", "OCE", "TR", "RU", "PBE"};
    public static final String[] regionesCodigo = {"br1", "euw1", "eune1", "jp1", "kr", "la1", "la2", "na", "oc1", "tr1", "ru", "pbe1"};
    public static final String versionFinal = cogerUltimaVersion();

    private static String cogerUltimaVersion(){
        try{
            final String urlVersion = "https://ddragon.leagueoflegends.com/api/versions.json";
            return (String) new JSONArray(new DownLoadStringToJSONTask(urlVersion).json_string).get(0);
        }catch(JSONException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public int devolverPosicion(String region){
        for (int i = 0; i < regionesPublico.length; i++) {
            if(region.equals(regionesPublico[i])){
                return i;
            }
        }
        return -1;
    }
}
