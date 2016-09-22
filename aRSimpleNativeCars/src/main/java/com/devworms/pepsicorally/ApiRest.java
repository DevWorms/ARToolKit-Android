package com.devworms.pepsicorally;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ApiRest {

    public static String[] consultarListadoDias(int obj){
        String[] strings = new String[] { "", "", "" };

        try {
            Log.d("RestApi","respuesta  consulta");

            Request request = new Request.Builder()
                    .url("https://event-ar.herokuapp.com/api/v1/days/?event=e451ca62-86dc-4e88-bf54-20c9b476f60e")
                    .get()
                    .build();

            JSONObject value = new RequestApi().execute(request).get();

            if( value.length() > 0 ) {

                JSONArray valuesIn = new JSONArray(value.getString("results"));
                Log.d("RestApi","respuesta results dias "+ valuesIn.length());

                if( value.length() > 0 ) {
                    Log.d("RestApi","respuesta dias "+ valuesIn);

                    strings[0] = valuesIn.getJSONObject(obj).getString("id");
                    strings[1] = valuesIn.getJSONObject(obj).getString("name");
                    strings[2] = valuesIn.getJSONObject(obj).getString("date");
                }


            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo");
        }

        return strings;
    }



    public static List<menuPojo> consultarListadoMenu(String dia) {

        List<menuPojo> lMenu = new ArrayList<menuPojo>();

        try {
            Log.d("RestApi","respuesta  consulta");

            Request request = new Request.Builder()
                    .url("http://app-ecodsa.com.mx/daimler/"+dia+".php")
                    .get()
                    .build();
            JSONObject sensorApi = new RequestApi().execute(request).get();
            Log.d("RestApi","respuesta "+ sensorApi.length());
            for (int i = 0; i < sensorApi.length(); i++) {

                //JSONObject sensorApi = values.getJSONObject(i);
                menuPojo menPojo = new menuPojo();
                Log.d("RestApi","respuesta "+sensorApi.getString("id"));
                menPojo.setId(sensorApi.getString("id"));
                Log.d("RestApi","respuesta "+sensorApi.getString("nombre"));
                menPojo.setNombre(sensorApi.getString("nombre"));
                Log.d("RestApi","respuesta "+sensorApi.getString("salon"));
                menPojo.setSalon(sensorApi.getString("salon"));
                Log.d("RestApi","respuesta "+sensorApi.getString("horario"));
                menPojo.setHorario(sensorApi.getString("horario"));
                Log.d("RestApi","respuesta "+sensorApi.getString("codigo"));
                menPojo.setCodigo(sensorApi.getString("codigo"));
                Log.d("RestApi","respuesta "+sensorApi.getString("img"));
               // menPojo.setRecomendaciones(sensorApi.getString("img"));
                Log.d("RestApi","respuesta "+sensorApi.getString("dia"));
                menPojo.setFecha(sensorApi.getString("dia"));
                lMenu.add(menPojo);
            }
        }
        catch (Exception ex){

        }

        return lMenu;
    }

    public static String[] consultarCodes(SharedPreferences misPrefs) {

        String[] strings = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };

        try {
            Log.d("RestApi","respuesta  consulta preguntas");

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "correo=" + misPrefs.getString("email", "") ); //--> cache mail
            Request request = new Request.Builder()
                    .url("http://app-pepsico.palindromo.com.mx/APP/trofeos.php")
                    .post(body)
                    .build();

            JSONObject sensorApi = new RequestApi().execute(request).get();
            Log.d("RestApi","respuesta "+ sensorApi.length());

            if( sensorApi.length() > 0 ) {

                //JSONObject sensorApi = values.getJSONObject(0);

                Log.d("RestApi","respuesta "+sensorApi.getString("correo"));
                strings[0] = sensorApi.getString("correo");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_1"));
                strings[1] = sensorApi.getString("pista_1");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_2"));
                strings[2] = sensorApi.getString("pista_2");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_3"));
                strings[3] = sensorApi.getString("pista_3");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_4"));
                strings[4] = sensorApi.getString("pista_4");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_5"));
                strings[5] = sensorApi.getString("pista_5");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_6"));
                strings[6] = sensorApi.getString("pista_6");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_7"));
                strings[7] = sensorApi.getString("pista_7");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_8"));
                strings[8] = sensorApi.getString("pista_8");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_9"));
                strings[9] = sensorApi.getString("pista_9");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_10"));
                strings[10] = sensorApi.getString("pista_10");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_11"));
                strings[11] = sensorApi.getString("pista_11");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_12"));
                strings[12] = sensorApi.getString("pista_12");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_13"));
                strings[13] = sensorApi.getString("pista_13");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_14"));
                strings[14] = sensorApi.getString("pista_14");

                Log.d("RestApi","respuesta "+sensorApi.getString("pista_15"));
                strings[15] = sensorApi.getString("pista_15");
            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo");
        }

        return strings;
    }

    public static String consultarPista() {

        String strings = "";

        try {
            Log.d("RestApi","respuesta  consulta");

            Request request = new Request.Builder()
                    .url("http://app-pepsico.palindromo.com.mx/APP/pistas.php")
                    .get()
                    .build();

            JSONObject values = new RequestApi().execute(request).get();
            Log.d("RestApi","respuesta "+ values.length());

            if( values.length() > 0 ) {

                //JSONObject sensorApi = values.getJSONObject(0);

                Log.d("RestApi","respuesta "+values.getString("img"));
                strings = values.getString("img");

            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo");
        }

        return strings;
    }

    public static String[] consultarPreguntas(String pregunta) {

        String[] strings = new String[] { "", "", "", "", "", "", "", "" };

        try {
            Log.d("RestApi","respuesta  consulta");

            Request request = new Request.Builder()
                    .url("https://event-ar.herokuapp.com/api/v1/questions/0129cfff-1686-478e-a67e-853c4e856c9e"+pregunta)
                    .get()
                    .build();

            JSONObject value = new RequestApi().execute(request).get();
            Log.d("RestApi","respuesta "+ value.length());

            if( value.length() > 0 ) {

                strings[0] = value.getString("id");

                Log.d("RestApi","question "+value.getString("question"));
                strings[1] = value.getString("question");

                JSONArray valuesIn = new JSONArray(value.getString("answer_set"));

                //Log.d("RestApi","respuesta "+sensorApi.getString("r1"));
                strings[2] = valuesIn.getJSONObject(0).getString("answer");

                //Log.d("RestApi","respuesta "+sensorApi.getString("r2"));
                strings[3] = valuesIn.getJSONObject(1).getString("answer");

                //Log.d("RestApi","respuesta "+sensorApi.getString("r3"));
                strings[4] = valuesIn.getJSONObject(2).getString("answer");

                strings[5] = valuesIn.getJSONObject(0).getString("is_correct");//valor de resp1
                strings[6] = valuesIn.getJSONObject(1).getString("is_correct");//valor de resp2
                strings[7] = valuesIn.getJSONObject(2).getString("is_correct");//valor de resp3
            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo");
        }

        return strings;
    }

    public static String Login(String mailStr, String passStr) {

        String strings = "";

        try {
            Log.d("RestApi","respuesta  Login");

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "username=" + mailStr + "&password=" + passStr + "&grant_type=password");
            Request request = new Request.Builder()
                    .url("https://event-ar.herokuapp.com/o/token/")
                    .addHeader("Authorization","Basic eXozS2FQaVNseEpSTXdqaHFseXpJZDJybVV6eG9jQ2N0QkdOWEdCczpXRWdEbE9yZzhraDBtaXJteXhnYjZkNGNKM2xiS21ZWFJZNWxnVFB3N2lENG5qTzBmUEdsWndHcDRha2lGejRvMFVtaEpPaWFCcXdXd3pUeFJvaWJyNzZtTWVscG5rSXQ4dWtFdTJ3dk5lR21kUERCYXhxajNURGN5RTBWclpONg==")
                    .post(body)
                    .build();

            JSONObject value = new RequestApi().execute(request).get();
            Log.d("RestApi","respuesta "+ value.length());

            if( value.length() > 0 ) {

                Log.d("RestApi","respuesta acce "+value.getString("access_token"));
                strings = value.getString("access_token");

            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo");
            strings = "No se encontró el usuario";
        }

        return strings;
    }

    public static String doCode(String code, String achievement, SharedPreferences misPrefs) {

        String strings = "";

        try {
            Log.d("RestApi","respuesta  code");

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "code=" + code + "&achievement=" + achievement );
            Request request = new Request.Builder()
                    .url("https://event-ar.herokuapp.com/api/v1/achievements/add/")
                    .addHeader("Authorization","Bearer "+ misPrefs.getString("email", ""))
                    .post(body)
                    .build();

            JSONObject value = new RequestApi().execute(request).get();
            Log.d("RestApi","respuesta "+ value.length());

            if( value.length() > 0 ) {
                Log.d("RestApi","respuesta acce "+value.getString("success"));
                strings = value.getString("success");

            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo");
            strings = "Código incorrecto";
        }

        return strings;
    }

    private static class RequestApi extends AsyncTask<Request, Void, JSONObject> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ARSimpleNativeCarsApplication.getContext());
            pDialog.setMessage("Ingresando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            //pDialog.show();
        }

        protected JSONObject doInBackground(Request... params) {
            try {

                OkHttpClient client = new OkHttpClient();
                Response response = client.newCall(params[0]).execute();

                String string = response.body().string();
                JSONObject jsonObject = new JSONObject(string);

                Log.d("se pasan", "doInBackground: "+jsonObject);

                return jsonObject;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            //pDialog.dismiss();
        }
    }

}
