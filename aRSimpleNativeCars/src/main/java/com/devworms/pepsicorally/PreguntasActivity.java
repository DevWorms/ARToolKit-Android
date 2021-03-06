package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PreguntasActivity extends Activity {

    private String botonStr;
    private String[] list = new String[6];
    private ListView listView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        listView = (ListView) findViewById(R.id.list);
        textView = (TextView) findViewById(R.id.textPregunta);

        botonStr = getIntent().getStringExtra("boton");

        list = ApiRest.consultarPreguntas( getIntent().getStringExtra("idAchiev") );

        if(!list[1].equals("") ){
            textView.setText(list[1]);
        }

        String[] values = new String[] {
                list[2],
                list[3],
                list[4]
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String  itemValue    = (String) listView.getItemAtPosition(position);
                if(     position == 0 && list[5].equals("true") ||
                        position == 1 && list[6].equals("true") ||
                        position == 2 && list[7].equals("true") ){
                    rallyCode();
                } else {
                    Toast.makeText(getApplicationContext(), "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void rallyCode(){
        Intent i = new Intent(this, RallyCode.class);
        i.putExtra("boton", botonStr);
        i.putExtra("idAchiev", getIntent().getStringExtra("idAchiev"));
        //para que te regrese de la actividad RallyCode
        startActivity(i);
        finish();
    }
}
