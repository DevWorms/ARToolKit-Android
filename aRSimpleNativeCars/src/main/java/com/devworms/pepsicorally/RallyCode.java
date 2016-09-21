package com.devworms.pepsicorally;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RallyCode extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    private String botonStr;
    private EditText editTextCode;

    private RelativeLayout relativeLayout;

    //  Preferencias
    SharedPreferences misPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rally_code);

        botonStr = getIntent().getStringExtra("boton");
        editTextCode = (EditText)findViewById(R.id.editTextCode);

        misPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);


        relativeLayout = (RelativeLayout) findViewById(R.id.rl);

        if ( botonStr.equals("1") ) {
            relativeLayout.setBackgroundResource(R.drawable.pista_1);
        } else if( botonStr.equals("2") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_2);
        } else if( botonStr.equals("3") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_3);
        } else if( botonStr.equals("4") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_4);
        } else if( botonStr.equals("5") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_5);
        } else if( botonStr.equals("6") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_6);
        } else if( botonStr.equals("7") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_7);
        } else if( botonStr.equals("8") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_8);
        } else if( botonStr.equals("9") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_9);
        } else if( botonStr.equals("10") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_10);
        } else if( botonStr.equals("11") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_11);
        } else if( botonStr.equals("12") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_12);
        }  else if( botonStr.equals("13") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_13);
        } else if( botonStr.equals("14") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_14);
        } else if( botonStr.equals("15") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_15);
        }
    }

    public void verificarCode(View v){

        Log.i("boton ",botonStr);

        if (    (botonStr.equals("1") && editTextCode.getText().toString().equals("1946")) ||
                (botonStr.equals("2") && editTextCode.getText().toString().equals("6290")) ||
                (botonStr.equals("3") && editTextCode.getText().toString().equals("4193")) ||
                (botonStr.equals("4") && editTextCode.getText().toString().equals("4025")) ||
                (botonStr.equals("5") && editTextCode.getText().toString().equals("2549")) ||
                (botonStr.equals("6") && editTextCode.getText().toString().equals("3582")) ||
                (botonStr.equals("7") && editTextCode.getText().toString().equals("6853")) ||
                (botonStr.equals("8") && editTextCode.getText().toString().equals("2004")) ||
                (botonStr.equals("9") && editTextCode.getText().toString().equals("3912")) ||
                (botonStr.equals("10") && editTextCode.getText().toString().equals("7430")) ||
                (botonStr.equals("11") && editTextCode.getText().toString().equals("9365")) ||
                (botonStr.equals("12") && editTextCode.getText().toString().equals("6432")) ||
                (botonStr.equals("13") && editTextCode.getText().toString().equals("9228")) ||
                (botonStr.equals("14") && editTextCode.getText().toString().equals("7354")) ||
                (botonStr.equals("15") && editTextCode.getText().toString().equals("5194")) ) {

            // --- TODO logica para codigo de achievement
            doCodeHere(editTextCode.getText().toString(), "9d7f40ac-2c4d-4d5c-a0e9-32f3deb910fa");

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Código incorrecto", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void doCodeHere(String code, String achiev){

        String text = ApiRest.doCode(code, achiev, misPrefs);

        Log.d("mmmm.. ",text);

        if(text.equals("Código incorrecto") || text.equals("false"))  {
            Toast toast = Toast.makeText(getApplicationContext(), "Código incorrecto", Toast.LENGTH_SHORT);
            toast.show();

        } else {
            SharedPreferences.Editor editor = misPrefs.edit();
            editor.putBoolean(botonStr, false);
            editor.commit();

            //para que te regrese de la actividad Rally y recargue
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);

            finish();
        }
    }
}
