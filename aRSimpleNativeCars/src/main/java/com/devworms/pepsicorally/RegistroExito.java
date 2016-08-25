package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import org.artoolkit.ar.samples.ARSimpleNativeCars.R;

public class RegistroExito extends Activity {

    //  Preferencias
    SharedPreferences misPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_exito);

        misPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
    }

    public void validarRegistro(View view){

        boolean acceso = misPrefs.getBoolean("instrucciones", false); // segundo parametro es el que toma default

        if(acceso)
        {
            Intent intent = new Intent(this,MenuPepsico.class);
            startActivity(intent);
            finish();
        } else {

            Intent intent = new Intent(this, Instrucciones.class);
            startActivity(intent);
            finish();
        }
    }

}

