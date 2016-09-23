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
import android.widget.Button;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Rally extends Activity {

    //  Preferencias
    SharedPreferences misPrefs;
    int MI_RESULT = 100;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15;
    private List<Button> buttonList = new ArrayList<>();
    List<achievPojo> listAchievs;
    List<String> listAchievsMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rally);

        misPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        btn1 = (Button)findViewById(R.id.uno);
        btn2 = (Button)findViewById(R.id.dos);
        btn3 = (Button)findViewById(R.id.tres);
        btn4 = (Button)findViewById(R.id.cuatro);
        btn5 = (Button)findViewById(R.id.cinco);
        btn6 = (Button)findViewById(R.id.seis);
        btn7 = (Button)findViewById(R.id.siete);
        btn8 = (Button)findViewById(R.id.ocho);
        btn9 = (Button)findViewById(R.id.nueve);
        btn10 = (Button)findViewById(R.id.diez);
        btn11 = (Button)findViewById(R.id.once);
        btn12 = (Button)findViewById(R.id.doce);
        btn13 = (Button)findViewById(R.id.trece);
        btn14 = (Button)findViewById(R.id.catorce);
        btn15 = (Button)findViewById(R.id.quince);

        buttonList.add(btn1);
        buttonList.add(btn2);
        buttonList.add(btn3);
        buttonList.add(btn4);
        buttonList.add(btn5);
        buttonList.add(btn6);
        buttonList.add(btn7);
        buttonList.add(btn8);
        buttonList.add(btn9);
        buttonList.add(btn10);
        buttonList.add(btn11);
        buttonList.add(btn12);
        buttonList.add(btn13);
        buttonList.add(btn14);
        buttonList.add(btn15);

        reload();
    }

    public void code(String botonStr, String idAchiev){
        Intent i = new Intent(this, PreguntasActivity.class);
        i.putExtra("boton", botonStr);
        i.putExtra("idAchiev", idAchiev);
        //para que te regrese de la actividad RallyCode
        startActivityForResult(i, MI_RESULT);
    }

    public void pistaUno(View v){
        code(listAchievs.get(0).getCode_img(), listAchievs.get(0).getId());
    }

    public void pistaDos(View v){
        code(listAchievs.get(1).getCode_img(), listAchievs.get(1).getId());
    }

    public void pistaTres(View v){
        code(listAchievs.get(2).getCode_img(), listAchievs.get(2).getId());
    }

    public void pistaCuatro(View v){
        code(listAchievs.get(3).getCode_img(), listAchievs.get(3).getId());
    }

    public void pistaCinco(View v){
        code(listAchievs.get(4).getCode_img(), listAchievs.get(4).getId());
    }

    public void pistaSeis(View v){
        code(listAchievs.get(5).getCode_img(), listAchievs.get(5).getId());
    }

    public void pistaSiete(View v){
        code(listAchievs.get(6).getCode_img(), listAchievs.get(6).getId());
    }

    public void pistaOcho(View v){
        code(listAchievs.get(7).getCode_img(), listAchievs.get(7).getId());
    }

    public void pistaNueve(View v){
        code(listAchievs.get(8).getCode_img(), listAchievs.get(8).getId());
    }

    public void pistaDiez(View v){
        code(listAchievs.get(9).getCode_img(), listAchievs.get(9).getId());
    }

    public void pistaOnce(View v){
        code(listAchievs.get(10).getCode_img(), listAchievs.get(10).getId());
    }

    public void pistaDoce(View v){
        code(listAchievs.get(11).getCode_img(), listAchievs.get(11).getId());
    }

    public void pistaTrece(View v){
        code(listAchievs.get(12).getCode_img(), listAchievs.get(12).getId());
    }

    public void pistaCatorce(View v){
        code(listAchievs.get(13).getCode_img(), listAchievs.get(13).getId());
    }

    public void pistaQuince(View v){
        code(listAchievs.get(14).getCode_img(), listAchievs.get(14).getId());
    }

    public void reload() {

        listAchievs = ApiRest.consultarAchievs();
        listAchievsMe = ApiRest.consultarAchievsMe(misPrefs);

        String imgUrl;

        if ( listAchievsMe.size() > 0 ){
            for (int i = 0; i< listAchievs.size(); i++){
                for (int iMe = 0; iMe< listAchievsMe.size(); iMe++){

                    if (listAchievsMe.get(iMe).equals( listAchievs.get(i).getId() )) {
                        Log.d("Iteracion", "numero de boton " + i);

                        imgUrl = listAchievs.get(i).getOn_img();
                        buttonList.get(i).setEnabled(false);
                    } else {
                        imgUrl = listAchievs.get(i).getOff_img();
                    }

                    new LoadImage(buttonList.get(i)).execute( imgUrl );
                }
            }
        } else {
            for (int i = 0; i< listAchievs.size(); i++){

                imgUrl = listAchievs.get(i).getOff_img();
                new LoadImage(buttonList.get(i)).execute( imgUrl );
            }
        }

    }

    //para que te regrese de la actividad RallyCode
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MI_RESULT){
            reload();
        }
    }


    private class LoadImage extends AsyncTask<String, Void, Bitmap> {

        ProgressDialog pDialog;
        Bitmap bitmap;

        Button mBtn;
        public LoadImage(Button btn){
            mBtn = btn;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Rally.this);
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
                Toast.makeText(Rally.this, "Regresa y vuelve a entrar a esta pantalla", Toast.LENGTH_SHORT).show();
            } else {
                mBtn.setBackgroundDrawable(new BitmapDrawable(getResources(),image));
            }
        }
    }
}
