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

public class LoginActivity extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;
    private IdentityManager identityManager;

    //  Datos Usuario
    EditText mail;
    EditText contrasena;

    //  Preferencias
    SharedPreferences misPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail = (EditText)findViewById(R.id.mailText);
        contrasena = (EditText)findViewById(R.id.passText);

        misPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);


        // Obtain a reference to the mobile client. It is created in the Application class,
        // but in case a custom Application class is not used, we initialize it here if necessary.
        AWSMobileClient.initializeMobileClientIfNecessary(this);

        // Obtain a reference to the mobile client. It is created in the Application class.
        final AWSMobileClient awsMobileClient = AWSMobileClient.defaultMobileClient();

        // Obtain a reference to the identity manager.
        identityManager = awsMobileClient.getIdentityManager();




    }

    public void moduloLogin(View view){

        String mailStr = mail.getText().toString();
        String passStr = contrasena.getText().toString();

        String text = ApiRest.Login(mailStr, passStr);

            Log.d("mmmm.. ",text);

            if(text.equals("No se encontró el usuario"))  {
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                toast.show();

            } else {
                SharedPreferences.Editor editor = misPrefs.edit();
                editor.putString("email", text);
                editor.putBoolean("acceso", true);
                editor.commit();

                Intent llamarScreenCodigo = new Intent(LoginActivity.this, MenuPepsico.class);
                startActivity(llamarScreenCodigo);

                finish();
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

            new AlertDialog.Builder(LoginActivity.this)
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