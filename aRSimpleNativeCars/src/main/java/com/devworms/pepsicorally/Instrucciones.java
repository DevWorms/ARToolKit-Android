package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.artoolkit.ar.samples.ARSimpleNativeCars.R;

public class Instrucciones extends Activity {
    int seccion=1;
    ImageView contenido;
    Button btnRegre,btnConti;

    //  Preferencias
    SharedPreferences misPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruccion);

        misPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        contenido= (ImageView)findViewById(R.id.imgInstru);
        btnConti=(Button)findViewById(R.id.btnContunuar);
        btnConti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seccion +=1;
                cambioTexto(seccion);
            }
        });
        btnRegre= (Button)findViewById(R.id.btnRegresar);
        btnRegre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seccion -=1;
                cambioTexto(seccion);
            }
        });

    }
    public void cambioTexto(int secciones){

        if(secciones==1){
            btnRegre.setVisibility(View.INVISIBLE);
            contenido.setImageResource(R.drawable.hojauno);
        }
        else if(secciones==2){
            btnRegre.setVisibility(View.VISIBLE);
            contenido.setImageResource(R.drawable.hojados);
        }
        else if(secciones==3){
            contenido.setImageResource(R.drawable.hojatres);

        }else if(secciones==4){

            contenido.setImageResource(R.drawable.hojacuatro);
        }else if(secciones==5){
            contenido.setImageResource(R.drawable.hojacinco);
        }else{

            boolean acceso = misPrefs.getBoolean("instrucciones", false); // segundo parametro es el que toma default

            if(!acceso) {

                SharedPreferences.Editor editor = misPrefs.edit();
                editor.putBoolean("instrucciones", true);
                editor.commit();

                Intent intent = new Intent(this, MenuPepsico.class);
                startActivity(intent);
            }

            finish();
        }

    }

}
