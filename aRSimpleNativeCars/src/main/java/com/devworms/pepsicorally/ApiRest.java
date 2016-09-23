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

    public static String[] consultarListadoDias(int obj) {
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

                if( valuesIn.length() > 0 ) {
                    Log.d("RestApi","respuesta dias "+ valuesIn.getJSONObject(obj));

                    strings[0] = valuesIn.getJSONObject(obj).getString("id");
                    Log.d("RestApi","respuesta dias id "+  valuesIn.getJSONObject(obj).getString("id"));
                    strings[1] = valuesIn.getJSONObject(obj).getString("name");
                    strings[2] = valuesIn.getJSONObject(obj).getString("date");
                }
            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo listado dias");
        }

        return strings;
    }

    public static List<menuPojo> consultarListadoMenu(String dia) {

        List<menuPojo> lMenu = new ArrayList<menuPojo>();

        try {
            Log.d("RestApi","respuesta  consulta");

            Request request = new Request.Builder()
                    .url("https://event-ar.herokuapp.com/api/v1/activities/?day="+dia)
                    .get()
                    .build();

            JSONObject value = new RequestApi().execute(request).get();

            if( value.length() > 0 ) {

                Log.d("RestApi","respuesta listado actividades "+ value.length());

                JSONArray sensorApi = new JSONArray(value.getString("results"));

                if( sensorApi.length() > 0 ) {
                    for (int i = 0; i < sensorApi.length(); i++) {

                        menuPojo menPojo = new menuPojo();
                        Log.d("RestApi","respuesta "+sensorApi.getJSONObject(i).getString("id"));
                        menPojo.setId(sensorApi.getJSONObject(i).getString("id"));
                        Log.d("RestApi","respuesta "+sensorApi.getJSONObject(i).getString("name"));
                        menPojo.setNombre(sensorApi.getJSONObject(i).getString("name"));
                        Log.d("RestApi","respuesta "+sensorApi.getJSONObject(i).getString("place"));
                        menPojo.setSalon(sensorApi.getJSONObject(i).getString("place"));
                        Log.d("RestApi","respuesta "+sensorApi.getJSONObject(i).getString("start")+"-"+sensorApi.getJSONObject(i).getString("end"));
                        menPojo.setHorario(sensorApi.getJSONObject(i).getString("start")+" - "+sensorApi.getJSONObject(i).getString("end"));
                        Log.d("RestApi","respuesta "+sensorApi.getJSONObject(i).getString("recomendations"));
                        menPojo.setCodigo(sensorApi.getJSONObject(i).getString("recomendations"));
                        lMenu.add(menPojo);
                    }
                }
            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo listado actividades");
        }

        return lMenu;
    }

    public static String consultarPista() {

        String strings = "";

        try {
            Log.d("RestApi","respuesta  consulta");

            Request request = new Request.Builder()
                    .url("https://event-ar.herokuapp.com/api/v1/achievements/?event=e451ca62-86dc-4e88-bf54-20c9b476f60e&clue_is_active=True")
                    .get()
                    .build();

            JSONObject value = new RequestApi().execute(request).get();

            if( value.length() > 0 ) {

                JSONArray valuesIn = new JSONArray(value.getString("results"));

                if( valuesIn.length() > 0 ) {
                    Log.d("RestApi","respuesta clue "+ valuesIn.getJSONObject(0));

                    strings = valuesIn.getJSONObject(0).getString("clue_img");
                }
            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo pistas");
        }

        return strings;
    }

    public static String[] consultarPreguntas(String idAchiev) {

        String[] strings = new String[] { "", "", "", "", "", "", "", "" };

        try {
            Log.d("RestApi","respuesta  consulta");

            Request request = new Request.Builder()
                    .url("https://event-ar.herokuapp.com/api/v1/questions/")
                    .get()
                    .build();

            JSONObject value = new RequestApi().execute(request).get();

            if( value.length() > 0 ) {

                JSONArray values = new JSONArray(value.getString("results"));

                if( values.length() > 0 ) {

                    for (int i = 0; i < values.length(); i++) {

                        if (values.getJSONObject(i).getString("achievement").equals( idAchiev )) {

                            strings[0] = values.getJSONObject(i).getString("id");

                            Log.d("RestApi","question "+values.getJSONObject(i).getString("question"));
                            strings[1] = values.getJSONObject(i).getString("question");

                            JSONArray valuesIn = new JSONArray(values.getJSONObject(i).getString("answer_set"));

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
                }






            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo preguntas");
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

            if( value.length() > 0 ) {

                Log.d("RestApi","respuesta acce "+value.getString("access_token"));
                strings = value.getString("access_token");

            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo login");
            strings = "No se encontró el usuario";
        }

        return strings;
    }

    public static String registro(String name, String mailStr, String passStr) {

        String strings = "";

        try {
            Log.d("RestApi","respuesta Registro");

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "first_name=" + name + "&email=" + mailStr + "&password=" + passStr + "&events=e451ca62-86dc-4e88-bf54-20c9b476f60e");
            Request request = new Request.Builder()
                    .url("https://event-ar.herokuapp.com/api/v1/users/")
                    .post(body)
                    .build();

            JSONObject value = new RequestApi().execute(request).get();

            if( value.length() > 0 ) {

                Log.d("RestApi","respuesta registro "+value.getString("first_name"));
                strings = "Bienvenido/a "+value.getString("first_name");

            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo registro");
            strings = "No se pudo registrar usuario";
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
                Log.d("RestApi","respuesta code "+value.getString("success"));
                strings = value.getString("success");

            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo code");
            strings = "Código incorrecto";
        }

        return strings;
    }

    public static List<achievPojo> consultarAchievs() {

        List<achievPojo> achievs = new ArrayList<>();

        try {
            Log.d("RestApi","respuesta achievs");

            Request request = new Request.Builder()
                    .url("https://event-ar.herokuapp.com/api/v1/achievements/?event=e451ca62-86dc-4e88-bf54-20c9b476f60e")
                    .get()
                    .build();

            JSONObject value = new RequestApi().execute(request).get();

            if( value.length() > 0 ) {

                JSONArray sensorApi = new JSONArray(value.getString("results"));

                if( sensorApi.length() > 0 ) {
                    for (int i = 0; i < sensorApi.length(); i++) {

                        achievPojo achievPojo = new achievPojo();

                        achievPojo.setId(sensorApi.getJSONObject( i ).getString("id"));
                        achievPojo.setCode_img(sensorApi.getJSONObject( i ).getString("code_img"));
                        achievPojo.setNombre(sensorApi.getJSONObject( i ).getString("name"));
                        achievPojo.setOff_img(sensorApi.getJSONObject( i ).getString("off_img"));
                        achievPojo.setOn_img(sensorApi.getJSONObject( i ).getString("on_img"));

                        achievs.add(achievPojo);
                    }
                }


            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo achievs");
        }

        return achievs;
    }

    public static List<String> consultarAchievsMe(SharedPreferences misPrefs) {

        List<String> achievs = new ArrayList<>();

        try {
            Log.d("RestApi","respuesta achievs me");
            Log.d("RestApi","respuesta achievs me"+ misPrefs.getString("email", ""));

            Request request = new Request.Builder()
                    .url("https://event-ar.herokuapp.com/api/v1/users/me/")
                    .addHeader("Authorization","Bearer "+ misPrefs.getString("email", ""))
                    .get()
                    .build();

            JSONObject value = new RequestApi().execute(request).get();

            if( value.length() > 0 ) {

                JSONArray sensorApi = new JSONArray(value.getString("achievements"));

                if( sensorApi.length() > 0 ) {

                    Log.d("RestApi","respuesta  consulta me " + sensorApi.length());

                    for (int i = 0; i < sensorApi.length(); i++) {
                        achievs.add(sensorApi.getString(0));
                    }
                }
            }
        }
        catch (Exception ex){
            Log.d("RestApi","no hay nada por el mometo achievs me");
        }

        return achievs;
    }

    private static class RequestApi extends AsyncTask<Request, Void, JSONObject> {

        ProgressDialog pDialog;

        /*private Context mContext;
        public RequestApi (Context context){
            mContext = context;
        }*/

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
             if (pDialog != null){
                 pDialog.dismiss();
             }
        }
    }

}
