package com.devworms.pepsicorally;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.devworms.pepsicorally.mobile.AWSMobileClient;
import com.devworms.pepsicorally.mobile.user.IdentityManager;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


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
                    new LoadAlbums().execute();
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

   class LoadAlbums extends AsyncTask<String, String, String> {

        String nombreStr = nombre.getText().toString();
        String patStr = paterno.getText().toString();
        String matStr = materno.getText().toString();
        String mailStr = mail.getText().toString();
        String passStr = contrasena.getText().toString();

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Registrando...");
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
            RequestBody body = RequestBody.create(mediaType, "nombre=" + nombreStr + "&paterno=" + patStr + "&materno=" + matStr +
                    "&correo=" + mailStr + "&passw=" + passStr);
            Request request = new Request.Builder()
                    .url("http://app-pepsico.palindromo.com.mx/APP/registro.php")
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
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
            editor.putString("email", mailStr);
            editor.putBoolean("acceso", true);
            editor.commit();

            Intent registrarScreen = new Intent(MainActivity.this, RegistroExito.class);
            startActivity(registrarScreen);

            finishAffinity();
        }

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
}