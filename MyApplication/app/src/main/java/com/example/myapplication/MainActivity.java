package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Summoner summonerEnrike9 = new Summoner("enrike9");
        TextView level = (TextView) findViewById(R.id.LevelUser);
        level.setText("Level: " + Integer.toString(summonerEnrike9.getLevel()));
        TextView name = findViewById(R.id.NameUser);
        name.setText(summonerEnrike9.getName());*/
        TextView level = (TextView) findViewById(R.id.LevelUser);
        level.setText("Level: 0");
        TextView name = findViewById(R.id.NameUser);
        name.setText("");
        Button buscarUser = (Button) findViewById(R.id.BuscarUser);
        buscarUser.setOnClickListener(buscar);
    }

    private Bitmap imagenInvocador = null;

    private View.OnClickListener buscar = new View.OnClickListener(){
        public void onClick(View v) {
            EditText user = (EditText) findViewById(R.id.IntroducirInvocador);
            Summoner summoner = new Summoner(user.getText().toString());
            TextView level = (TextView) findViewById(R.id.LevelUser);
            level.setText("Level: " + Integer.toString(summoner.getLevel()));
            TextView name = findViewById(R.id.NameUser);
            name.setText(summoner.getName());
            final ImageView imagen = (ImageView) findViewById(R.id.ImagenInvocador);
            final String urlImage = "http://ddragon.leagueoflegends.com/cdn/9.20.1/img/profileicon/" +  summoner.getProfileIconId() + ".png";
            Thread thread = new Thread() {
                @Override
                public void run() {
                    imagenInvocador = conseguirImagen(urlImage);
                }
            };
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            imagen.setImageBitmap(imagenInvocador);
        }
    };

    private Bitmap conseguirImagen(String urlImage){
        try {
            URL url = new URL(urlImage);
            URLConnection urlConnection = url.openConnection();

            InputStream is = url.openStream();
            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }



}
