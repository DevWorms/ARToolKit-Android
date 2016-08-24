package com.devworms.pepsicorally;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.artoolkit.ar.samples.ARSimpleNativeCars.R;

/**
 * Created by salva on 21/08/2016.
 */
public class Instrucciones extends Activity {
    int seccion=1;
    ImageView contenido;
    Button btnRegre,btnConti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruccion);
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
            //Intent i = new Intent(this, MenuPepsico.class);
            //startActivity(i);
            finish();
        }

    }

}
