package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.artoolkit.ar.samples.ARSimpleNativeCars.R;

public class agendaActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_agenda);

        }


    public void agendaActScreen(View view){
        Log.d("RestApi","D"+((Button)view).getId());
        Intent newScreen = new Intent(agendaActivity.this, menuActActivity.class);

        if(((Button)view).getId()== Integer.parseInt("2131558512")){
            newScreen.putExtra("dia","dia1");
        }else if (((Button)view).getId()== Integer.parseInt("2131558513")){
            newScreen.putExtra("dia","dia2");
        }else{
            newScreen.putExtra("dia","dia3");
        }

        startActivity(newScreen);

    }
}
