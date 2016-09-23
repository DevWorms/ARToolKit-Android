package com.devworms.pepsicorally;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.devworms.pepsicorally.mobile.AWSMobileClient;
import com.devworms.pepsicorally.mobile.user.IdentityManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    private IdentityManager identityManager;

    //  Datos Usuario
    EditText nombre;
    EditText paterno;
    EditText materno;
    EditText mail;
    EditText contrasena;
    EditText repiteContrasena;

    //  Preferencias
    SharedPreferences misPrefs;

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Datos Usuario
        nombre = (EditText)findViewById(R.id.nombreText);
        paterno = (EditText)findViewById(R.id.apellidoPText);
        materno = (EditText)findViewById(R.id.apellidoMText);
        mail = (EditText)findViewById(R.id.mailText);
        contrasena = (EditText)findViewById(R.id.passText);
        repiteContrasena = (EditText)findViewById(R.id.cnfPassText);

        misPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);


        // Obtain a reference to the mobile client. It is created in the Application class,
        // but in case a custom Application class is not used, we initialize it here if necessary.
        AWSMobileClient.initializeMobileClientIfNecessary(this);

        // Obtain a reference to the mobile client. It is created in the Application class.
        final AWSMobileClient awsMobileClient = AWSMobileClient.defaultMobileClient();

        // Obtain a reference to the identity manager.
        identityManager = awsMobileClient.getIdentityManager();
    }

    public void moduloRegistro (View view){
        String pass = contrasena.getText().toString();
        String rePass = repiteContrasena.getText().toString();
        String email = mail.getText().toString();
        String nombreString = nombre.getText().toString();
        String patString = paterno.getText().toString();
        String matString = materno.getText().toString();

        if(pass.trim().length() == 0 || rePass.trim().length() == 0 ||
                email.trim().length() == 0 || nombreString.trim().length() == 0 ||
                patString.trim().length() == 0 || matString.trim().length() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Faltan campos", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if( validateEmail(email) ){
                if (pass.equals(rePass)) {

                    String string = ApiRest.registro( (nombreString+" "+patString+" "+matString),email,pass );

                    if (!string.equals("No se pudo registrar usuario")) {
                        SharedPreferences.Editor editor = misPrefs.edit();
                        editor.putString("email", email);
                        //editor.putBoolean("acceso", true);
                        editor.commit();

                        Intent registrarScreen = new Intent(this, RegistroExito.class);
                        startActivity(registrarScreen);

                        new LoadAlbums();
                    } else {
                        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Las contraseñas no coinciden";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Correo inválido", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public static boolean validateEmail(String email) {

        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @Override
    protected void onResume() {
        super.onResume();


        final AWSMobileClient awsMobileClient = AWSMobileClient.defaultMobileClient();

        // register notification receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(notificationReceiver,
                new IntentFilter(PushListenerService.ACTION_SNS_NOTIFICATION));
    }
    private final BroadcastReceiver notificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Push", "Received notification from local broadcast. Display it in a dialog.");

            Bundle data = intent.getBundleExtra(PushListenerService.INTENT_SNS_NOTIFICATION_DATA);
            String message = PushListenerService.getMessage(data);

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getString(R.string.push_title))
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        }
    };


    @Override
    protected void onPause() {
        super.onPause();

        // unregister notification receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(notificationReceiver);
    }


    class LoadAlbums  {

        LoadAlbums(){

            Log.d("clase mamona", "elimina todo atras.");

            finishAffinity();
        }
    }
}