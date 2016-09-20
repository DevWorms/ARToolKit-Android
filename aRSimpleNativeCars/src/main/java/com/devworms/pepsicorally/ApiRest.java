package com.devworms.pepsicorally;


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

    public static List<menuPojo> consultarListadoMenu(String dia) {

        List<menuPojo> lMenu = new ArrayList<menuPojo>();

        try {
            Log.d("RestApi","respuesta  consulta");

            Request request = new Request.Builder()
                    .url("http://app-ecodsa.com.mx/daimler/"+dia+".php")
                    .get()
                    .build();
            JSONArray values = new RequestApi().execute(request).get();
            Log.d("RestApi","respuesta "+ values.length());
            for (int i = 0; i < values.length(); i++) {

                JSONObject sensorApi = values.getJSONObject(i);
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

    public static String[] consultarPreguntas(String pregunta) {

        String[] strings = new String[] { "", "", "", "", "", "" };

        try {
            Log.d("RestApi","respuesta  consulta");

            Request request = new Request.Builder()
                    .url("http://app-pepsico.palindromo.com.mx/APP/pregunta"+pregunta+".php")
                    .get()
                    .build();

            JSONArray values = new RequestApi().execute(request).get();
            Log.d("RestApi","respuesta "+ values.length());

            if( values.length() > 0 ) {

                JSONObject sensorApi = values.getJSONObject(0);

                Log.d("RestApi","respuesta "+sensorApi.getString("id"));
                strings[0] = sensorApi.getString("id");

                Log.d("RestApi","respuesta "+sensorApi.getString("pregunta"));
                strings[1] = sensorApi.getString("pregunta");

                Log.d("RestApi","respuesta "+sensorApi.getString("r1"));
                strings[2] = sensorApi.getString("r1");

                Log.d("RestApi","respuesta "+sensorApi.getString("r2"));
                strings[3] = sensorApi.getString("r2");

                Log.d("RestApi","respuesta "+sensorApi.getString("r3"));
                strings[4] = sensorApi.getString("r3");

                Log.d("RestApi","respuesta "+sensorApi.getString("respuesta_android"));
                strings[5] = sensorApi.getString("respuesta_android");
            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo");
        }

        return strings;
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

            JSONArray values = new RequestApi().execute(request).get();
            Log.d("RestApi","respuesta "+ values.length());

            if( values.length() > 0 ) {

                JSONObject sensorApi = values.getJSONObject(0);

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

            JSONArray values = new RequestApi().execute(request).get();
            Log.d("RestApi","respuesta "+ values.length());

            if( values.length() > 0 ) {

                JSONObject sensorApi = values.getJSONObject(0);

                Log.d("RestApi","respuesta "+sensorApi.getString("img"));
                strings = sensorApi.getString("img");

            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo");
        }

        return strings;
    }

    private static class RequestApi extends AsyncTask<Request, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Request... params) {
            try {

                OkHttpClient client = new OkHttpClient();
                Response response = client.newCall(params[0]).execute();

                String string = response.body().string();
                JSONArray jsonObjects = new JSONArray(string);

                return jsonObjects;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }

}
