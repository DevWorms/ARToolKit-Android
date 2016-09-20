package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class agendaActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_agenda);

        }


    public void agendaLunes(View view){
        Toast.makeText(this, "Cargando, por favor espere...", Toast.LENGTH_LONG).show();
        Intent newScreen = new Intent(this, menuActActivity.class);
        newScreen.putExtra("dia","lunes");
        startActivity(newScreen);

    }

    public void agendaMartes(View view){
        Toast.makeText(this, "Cargando, por favor espere...", Toast.LENGTH_LONG).show();
        Intent newScreen = new Intent(this, menuActActivity.class);
        newScreen.putExtra("dia","martes");
        startActivity(newScreen);

    }

    public void agendaMiercoles(View view){
        Toast.makeText(this, "Cargando, por favor espere...", Toast.LENGTH_LONG).show();
        Intent newScreen = new Intent(this, menuActActivity.class);
        newScreen.putExtra("dia","miercoles");
        startActivity(newScreen);

    }
}
