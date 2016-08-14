package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.artoolkit.ar.samples.ARSimpleNativeCars.R;

public class RegistroExito extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_exito);
    }

    public void validarRegistro(View view){
        Intent registrarScreen = new Intent(this, MenuPepsico.class);
        startActivity(registrarScreen);
        finish();
    }

}

