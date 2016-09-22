package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class agendaActivity extends Activity {

    private String[] list = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
    }


    public void agendaLunes(View view){
        Toast.makeText(this, "Cargando, por favor espere...", Toast.LENGTH_LONG).show();
        list = ApiRest.consultarListadoDias(0);
        Intent newScreen = new Intent(this, menuActActivity.class);
        newScreen.putExtra("dia",list[1]);
        newScreen.putExtra("idDia",list[0]);
        startActivity(newScreen);

    }

    public void agendaMartes(View view){
        Toast.makeText(this, "Cargando, por favor espere...", Toast.LENGTH_LONG).show();
        list = ApiRest.consultarListadoDias(1);
        Intent newScreen = new Intent(this, menuActActivity.class);
        newScreen.putExtra("dia",list[1]);
        newScreen.putExtra("idDia",list[0]);
        startActivity(newScreen);

    }

    public void agendaMiercoles(View view){
        Toast.makeText(this, "Cargando, por favor espere...", Toast.LENGTH_LONG).show();
        list = ApiRest.consultarListadoDias(2);
        Intent newScreen = new Intent(this, menuActActivity.class);
        newScreen.putExtra("dia",list[1]);
        newScreen.putExtra("idDia",list[0]);
        startActivity(newScreen);

    }
}
