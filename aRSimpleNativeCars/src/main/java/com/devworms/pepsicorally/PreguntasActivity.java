package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.artoolkit.ar.samples.ARSimpleNativeCars.R;

public class PreguntasActivity extends Activity {

    private String botonStr;
    int MI_RESULT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        botonStr = getIntent().getStringExtra("boton");
    }

    public void rallyCode(String botonStr){
        Intent i = new Intent(this, RallyCode.class);
        i.putExtra("boton", botonStr);
        //para que te regrese de la actividad RallyCode
        startActivityForResult(i, MI_RESULT);
    }
}
