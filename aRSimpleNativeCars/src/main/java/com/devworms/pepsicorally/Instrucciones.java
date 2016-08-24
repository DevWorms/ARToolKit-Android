package com.devworms.pepsicorally;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.artoolkit.ar.samples.ARSimpleNativeCars.R;

/**
 * Created by salva on 21/08/2016.
 */
public class Instrucciones extends Activity {
    int seccion=1;
    TextView txtIntro,txtConte,txtConclu;
    Button btnRegre,btnConti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruccion);
        txtIntro=(TextView)findViewById(R.id.txtIntro);
        txtConte=(TextView)findViewById(R.id.txtConte);
        txtConclu=(TextView)findViewById(R.id.txtConclusion);
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
            txtIntro.setText("Bienvenido a la aplicación de HR meeting.");
            txtConte.setText("\nAquí podrás encontrar información que te será de utilidad a lo largo de este evento.");
            txtConclu.setText("\nPuedes revisar el itinerario de actividades que tenemos preparado para ti en la sección de “Agenda”");
        }
        else if(secciones==2){
            btnRegre.setVisibility(View.VISIBLE);
            txtIntro.setText("");
            txtConte.setText("También puedes participara en la dinámica de “Trofeos virtuales” que preparamos para ti, la cual esta basada en encontrar pistas y recolectar trofeos.");
            txtConclu.setText("");
        }
        else if(secciones==3){
            txtIntro.setText("Para participar recolectar trofeos, debes de seguir los siguientes pasos:");
            txtConte.setText("\nPaso1: Debes esperar a que las pistas se activen, si hay una pista activa lo puedes revisar en la sección de “Pistas”.");
            txtConclu.setText("\nCuando una pista este activa, se te darán las instrucciones de cómo y en donde buscar el trofeo.\n" +
                    "\nPaso 2: Cuando llegues al lugar indicado deberás encontrar un dibujo o patrón\n" +
                    "impreso sobre una hoja.");

        }else if(secciones==4){

            txtIntro.setText("Paso 3: Activa el botón de cámara, que esta debajo de las instrucciones y escanea el patrón.");
            txtConte.setText("\nPaso 4: Deberás visualizar una imagen en 3D con un código sobre la imagen.\n\nGuarda o escribe en algún lugar tu código, ya que lo necesitaras en el siguiente paso.");
            txtConclu.setText("");
        }else if(secciones==5){

            txtIntro.setText("");
            txtConte.setText("Paso 5: Abre la sección de trofeos y elige el trofeo que acabas de ver en 3D.\n\nAntes de poder meter el código deberás contestar una pregunta correctamente.\n\nUna vez" +

                    "contestada la pregunta, ingresa tu código y listo! Ya habrás recolectado la primera pista.");
            txtConclu.setText("");
        }else{
            //Intent i = new Intent(this, MenuPepsico.class);
            //startActivity(i);
            finish();
        }

    }

}
