package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class PistasActivas extends Activity {

    String fondoUrl;
    RelativeLayout relativeLayout;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pistas_activas);

        fondoUrl = ApiRest.consultarPista();

        relativeLayout = (RelativeLayout) findViewById(R.id.activity_pistas_activas);

        new LoadImage().execute(fondoUrl);

    }

    public void aRToolKit(View v){
        Intent intent = new Intent(this, ARSimpleNativeCarsActivity.class);
        startActivity(intent);
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                //img.setImageBitmap(image);
                relativeLayout.setBackgroundDrawable(new BitmapDrawable(getResources(),image));
            }else{

                Toast.makeText(PistasActivas.this, "Regresa y vuelve a entrar a esta pantalla para darte las pistas", Toast.LENGTH_SHORT).show();

            }
        }
    }
}


