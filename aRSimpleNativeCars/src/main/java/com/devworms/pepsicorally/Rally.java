package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Rally extends Activity {

    //  Preferencias
    SharedPreferences misPrefs;
    int MI_RESULT = 100;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15;

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

        reload();
    }

    public void code(String botonStr){
        Intent i = new Intent(this, PreguntasActivity.class);
        i.putExtra("boton", botonStr);
        //para que te regrese de la actividad RallyCode
        startActivityForResult(i, MI_RESULT);
    }

    public void pistaUno(View v){
        code("1");
    }

    public void pistaDos(View v){
        code("2");
    }

    public void pistaTres(View v){
        code("3");
    }

    public void pistaCuatro(View v){
        code("4");
    }

    public void pistaCinco(View v){
        code("5");
    }

    public void pistaSeis(View v){
        code("6");
    }

    public void pistaSiete(View v){
        code("7");
    }

    public void pistaOcho(View v){
        code("8");
    }

    public void pistaNueve(View v){
        code("9");
    }

    public void pistaDiez(View v){
        code("10");
    }

    public void pistaOnce(View v){
        code("11");
    }

    public void pistaDoce(View v){
        code("12");
    }

    public void pistaTrece(View v){
        code("13");
    }

    public void pistaCatorce(View v){
        code("14");
    }

    public void pistaQuince(View v){
        code("15");
    }

    public void reload() {
        btn1.setEnabled(misPrefs.getBoolean("1", true));
        btn2.setEnabled(misPrefs.getBoolean("2", true));
        btn3.setEnabled(misPrefs.getBoolean("3", true));
        btn4.setEnabled(misPrefs.getBoolean("4", true));
        btn5.setEnabled(misPrefs.getBoolean("5", true));
        btn6.setEnabled(misPrefs.getBoolean("6", true));
        btn7.setEnabled(misPrefs.getBoolean("7", true));
        btn8.setEnabled(misPrefs.getBoolean("8", true));
        btn9.setEnabled(misPrefs.getBoolean("9", true));
        btn10.setEnabled(misPrefs.getBoolean("10", true));
        btn11.setEnabled(misPrefs.getBoolean("11", true));
        btn12.setEnabled(misPrefs.getBoolean("12", true));
        btn13.setEnabled(misPrefs.getBoolean("13", true));
        btn14.setEnabled(misPrefs.getBoolean("14", true));
        btn15.setEnabled(misPrefs.getBoolean("15", true));

        if( misPrefs.getBoolean("1", true) ){
            btn1.setBackgroundResource(R.drawable.btn1b);
        } else{
            btn1.setBackgroundResource(R.drawable.btn1);
        }

        if( misPrefs.getBoolean("2", true) ){
            btn2.setBackgroundResource(R.drawable.btn2b);
        } else{
            btn2.setBackgroundResource(R.drawable.btn2);
        }

        if( misPrefs.getBoolean("3", true) ){
            btn3.setBackgroundResource(R.drawable.btn3b);
        } else{
            btn3.setBackgroundResource(R.drawable.btn3);
        }

        if( misPrefs.getBoolean("4", true) ){
            btn4.setBackgroundResource(R.drawable.btn4b);
        } else{
            btn4.setBackgroundResource(R.drawable.btn4);
        }

        if( misPrefs.getBoolean("5", true) ){
            btn5.setBackgroundResource(R.drawable.btn5b);
        } else{
            btn5.setBackgroundResource(R.drawable.btn5);
        }

        if( misPrefs.getBoolean("6", true) ){
            btn6.setBackgroundResource(R.drawable.btn6b);
        } else{
            btn6.setBackgroundResource(R.drawable.btn6);
        }

        if( misPrefs.getBoolean("7", true) ){
            btn7.setBackgroundResource(R.drawable.btn7b);
        } else{
            btn7.setBackgroundResource(R.drawable.btn7);
        }

        if( misPrefs.getBoolean("8", true) ){
            btn8.setBackgroundResource(R.drawable.btn8b);
        } else{
            btn8.setBackgroundResource(R.drawable.btn8);
        }

        if( misPrefs.getBoolean("9", true) ){
            btn9.setBackgroundResource(R.drawable.btn9b);
        } else{
            btn9.setBackgroundResource(R.drawable.btn9);
        }

        if( misPrefs.getBoolean("10", true) ){
            btn10.setBackgroundResource(R.drawable.btn10b);
        } else{
            btn10.setBackgroundResource(R.drawable.btn10);
        }

        if( misPrefs.getBoolean("11", true) ){
            btn11.setBackgroundResource(R.drawable.btn11b);
        } else{
            btn11.setBackgroundResource(R.drawable.btn11);
        }

        if( misPrefs.getBoolean("12", true) ){
            btn12.setBackgroundResource(R.drawable.btn12b);
        } else{
            btn12.setBackgroundResource(R.drawable.btn12);
        }

        if( misPrefs.getBoolean("13", true) ){
            btn13.setBackgroundResource(R.drawable.btn13b);
        } else{
            btn13.setBackgroundResource(R.drawable.btn13);
        }

        if( misPrefs.getBoolean("14", true) ){
            btn14.setBackgroundResource(R.drawable.btn14b);
        } else{
            btn14.setBackgroundResource(R.drawable.btn14);
        }

        if( misPrefs.getBoolean("15", true) ){
            btn15.setBackgroundResource(R.drawable.btn15b);
        } else{
            btn15.setBackgroundResource(R.drawable.btn15);
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
}
