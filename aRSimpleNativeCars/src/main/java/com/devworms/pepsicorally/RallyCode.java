package com.devworms.pepsicorally;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.artoolkit.ar.samples.ARSimpleNativeCars.R;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RallyCode extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    private String botonStr;
    private EditText editTextCode;

    private LinearLayout relativeLayout;

    //  Preferencias
    SharedPreferences misPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rally_code);

        botonStr = getIntent().getStringExtra("boton");
        editTextCode = (EditText)findViewById(R.id.editTextCode);

        misPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);


        relativeLayout = (LinearLayout) findViewById(R.id.rl);

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
        }

        //---- TODO

        else if( botonStr.equals("10") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_9);
        } else if( botonStr.equals("11") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_9);
        } else if( botonStr.equals("12") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_9);
        } else if( botonStr.equals("13") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_9);
        } else if( botonStr.equals("14") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_9);
        } else if( botonStr.equals("15") ){
            relativeLayout.setBackgroundResource(R.drawable.pista_9);
        }
    }

    public void verificarCode(View v){

        Log.i("boton ",botonStr);

        if (    (botonStr.equals("1") && editTextCode.getText().toString().equals("camara")) ||
                (botonStr.equals("2") && editTextCode.getText().toString().equals("camara2")) ||
                (botonStr.equals("3") && editTextCode.getText().toString().equals("camara3")) ||
                (botonStr.equals("4") && editTextCode.getText().toString().equals("camara4")) ||
                (botonStr.equals("5") && editTextCode.getText().toString().equals("camara5")) ||
                (botonStr.equals("6") && editTextCode.getText().toString().equals("camara6")) ||
                (botonStr.equals("7") && editTextCode.getText().toString().equals("camara7")) ||
                (botonStr.equals("8") && editTextCode.getText().toString().equals("camara8")) ||
                (botonStr.equals("9") && editTextCode.getText().toString().equals("camara9")) ||
                (botonStr.equals("10") && editTextCode.getText().toString().equals("camara")) ||
                (botonStr.equals("11") && editTextCode.getText().toString().equals("camara")) ||
                (botonStr.equals("12") && editTextCode.getText().toString().equals("camara")) ||
                (botonStr.equals("13") && editTextCode.getText().toString().equals("camara")) ||
                (botonStr.equals("14") && editTextCode.getText().toString().equals("camara")) ||
                (botonStr.equals("15") && editTextCode.getText().toString().equals("camara")) ) {
            new PostCode().execute();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Código incorrecto", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    class PostCode extends AsyncTask<String, String, String> {


        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RallyCode.this);
            pDialog.setMessage("Validando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting Albums JSON
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "correo=" + "V" ); //--> cache mail
            Request request = new Request.Builder()
                    .url("http://app-pepsico.palindromo.com.mx/APP/respuesta"+botonStr+".php")
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();

                /*if (response.isSuccessful()){
                    Intent registrarScreen = new Intent(RallyCode.this, RegistroExito.class);
                    startActivity(registrarScreen);
                }*/

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all albums

            pDialog.dismiss();

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
