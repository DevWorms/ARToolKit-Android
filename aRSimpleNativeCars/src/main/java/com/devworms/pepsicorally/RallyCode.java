package com.devworms.pepsicorally;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

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

        new LoadImage(relativeLayout).execute(botonStr);

    }

    public void verificarCode(View v){

        doCodeHere(editTextCode.getText().toString(), getIntent().getStringExtra("idAchiev"));

    }

    public void doCodeHere(String code, String achiev){

        String text = ApiRest.doCode(code, achiev, misPrefs);

        Log.d("mmmm.. ",text);

        if(text.equals("Código incorrecto") || text.equals("false"))  {
            Toast toast = Toast.makeText(getApplicationContext(), "Código incorrecto", Toast.LENGTH_SHORT);
            toast.show();

        } else {
            /*SharedPreferences.Editor editor = misPrefs.edit();
            editor.putBoolean(botonStr, false);
            editor.commit();*/

            //para que te regrese de la actividad Rally y recargue
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);

            finish();
        }
    }


    private class LoadImage extends AsyncTask<String, Void, Bitmap> {

        ProgressDialog pDialog;
        Bitmap bitmap;

        RelativeLayout mBtn;
        public LoadImage(RelativeLayout btn){
            mBtn = btn;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RallyCode.this);
            pDialog.setMessage("Cargando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }

        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        protected void onPostExecute(Bitmap image) {

            pDialog.dismiss();

            if(image == null){
                Toast.makeText(RallyCode.this, "Regresa y vuelve a entrar a esta pantalla", Toast.LENGTH_SHORT).show();
            } else {
                mBtn.setBackgroundDrawable(new BitmapDrawable(getResources(),image));
            }
        }
    }
}
