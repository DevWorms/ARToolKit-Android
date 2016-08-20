package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.artoolkit.ar.samples.ARSimpleNativeCars.ARSimpleNativeCarsActivity;
import org.artoolkit.ar.samples.ARSimpleNativeCars.R;

import java.io.InputStream;
import java.net.URL;

public class PistasActivas extends Activity {

    String fondoUrl;
    RelativeLayout relativeLayout;
    ImageView imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pistas_activas);

        fondoUrl = ApiRest.consultarPista();

        relativeLayout = (RelativeLayout) findViewById(R.id.activity_pistas_activas);
        imageButton = (ImageView) findViewById(R.id.imageView3);

        if(!fondoUrl.equals("") ){

            Drawable fondo = LoadImageFromWebOperations(fondoUrl);

            imageButton.setBackgroundDrawable(fondo);
        }
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            Log.i("paps ","hubo pedo");
            return null;
        }
    }

    public void aRToolKit(View v){
        Intent intent = new Intent(this, ARSimpleNativeCarsActivity.class);
        startActivity(intent);
    }
}
