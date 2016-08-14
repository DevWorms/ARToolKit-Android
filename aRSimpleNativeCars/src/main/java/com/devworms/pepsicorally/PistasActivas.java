package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.artoolkit.ar.samples.ARSimpleNativeCars.ARSimpleNativeCarsActivity;
import org.artoolkit.ar.samples.ARSimpleNativeCars.R;

public class PistasActivas extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pistas_activas);
    }

    public void aRToolKit(View v){
        Intent intent = new Intent(this, ARSimpleNativeCarsActivity.class);
        startActivity(intent);
    }
}
