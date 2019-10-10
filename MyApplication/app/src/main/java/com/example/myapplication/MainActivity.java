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
        inicializar();
        Button buscarUser = (Button) findViewById(R.id.BuscarUser);
        buscarUser.setOnClickListener(buscar);
    }

    private View.OnClickListener buscar = new View.OnClickListener(){
        public void onClick(View v) {
            Summoner summoner = encontrarSummoner();
            if(summoner != null){
                System.out.println("No soy nulo");
                nivelYNombre(summoner);
                imagenInvocador(summoner);
            }
            System.out.println("Soy nulo");
        }
    };

    private Summoner encontrarSummoner(){
        EditText user = (EditText) findViewById(R.id.IntroducirInvocador);
        Summoner summoner = new Summoner(user.getText().toString());
        if (summoner.getError()){
            inicializar();
            return null;
        }
        return summoner;
    }

    private void imagenInvocador(Summoner summoner){
        final ImageView imagen = (ImageView) findViewById(R.id.ImagenInvocador);
        final String urlImage = "http://ddragon.leagueoflegends.com/cdn/9.20.1/img/profileicon/" +  summoner.getProfileIconId() + ".png";
        DownLoadImageTask dit = new DownLoadImageTask(imagen);
        dit.execute(urlImage);
    }

    private void nivelYNombre(Summoner summoner){
        TextView level = (TextView) findViewById(R.id.LevelUser);
        level.setText("Level: " + Integer.toString(summoner.getLevel()));
        TextView name = findViewById(R.id.NameUser);
        name.setText(summoner.getName());
    }

    private void inicializar(){
        TextView level = (TextView) findViewById(R.id.LevelUser);
        level.setText("Level: 0");
        TextView name = findViewById(R.id.NameUser);
        name.setText("");
    }

}
