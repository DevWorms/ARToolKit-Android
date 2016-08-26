package com.devworms.pepsicorally;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.devworms.pepsicorally.mobile.AWSMobileClient;
import com.devworms.pepsicorally.mobile.user.IdentityManager;

public class SplashInicioActivity extends Activity {

    //  Preferencias
    SharedPreferences misPrefs;

    private IdentityManager identityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_inicio);



        // Obtain a reference to the mobile client. It is created in the Application class,
        // but in case a custom Application class is not used, we initialize it here if necessary.
        AWSMobileClient.initializeMobileClientIfNecessary(this);

        // Obtain a reference to the mobile client. It is created in the Application class.
        final AWSMobileClient awsMobileClient = AWSMobileClient.defaultMobileClient();

        // Obtain a reference to the identity manager.
        identityManager = awsMobileClient.getIdentityManager();


        misPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = misPrefs.edit();
        //editor.putBoolean("acceso", false);
        //editor.commit();


        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    boolean acceso = misPrefs.getBoolean("acceso", false); // segundo parametro es el que toma default

                    if(acceso)
                    {
                        Intent intent = new Intent(SplashInicioActivity.this,MenuPepsico.class);
                        startActivity(intent);
                        finish();
                    } else {

                        Intent intent = new Intent(SplashInicioActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        // unregister notification receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(notificationReceiver);
        finish();
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

            new AlertDialog.Builder(SplashInicioActivity.this)
                    .setTitle(getString(R.string.push_title))
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        }
    };


}
