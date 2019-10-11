package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    public final Global global = new Global();

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
                nivelYNombre(summoner);
                imagenInvocador(summoner);
            }
        }
    };

    private Summoner encontrarSummoner(){
        EditText user = (EditText) findViewById(R.id.IntroducirInvocador);
        Spinner spinner = (Spinner) findViewById(R.id.Region);
        String hola = spinner.getSelectedItem().toString();
        Summoner summoner = new Summoner(user.getText().toString(), spinner.getSelectedItem().toString());
        if (summoner.getError()){
            inicializar();
            return null;
        }
        return summoner;
    }

    private void imagenInvocador(Summoner summoner){
        final ImageView imagen = (ImageView) findViewById(R.id.ImagenInvocador);
        final String urlImage = "http://ddragon.leagueoflegends.com/cdn/" + global.versionFinal + "/img/profileicon/" +  summoner.getProfileIconId() + ".png";
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
        // Configuración Spinner
        Spinner region = (Spinner) findViewById(R.id.Region);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.regionPublico, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        region.setAdapter(adapter);
    }

}
