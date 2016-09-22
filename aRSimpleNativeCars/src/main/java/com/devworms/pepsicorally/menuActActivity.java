package com.devworms.pepsicorally;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class menuActActivity extends Activity {

    List<menuPojo> list = new ArrayList<menuPojo>();
    String fechaCompuesta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_act);
        List<String> nombres = new ArrayList<String>();
        final ListView listview = (ListView) findViewById(R.id.listview);

        // consultar el jason
        String dia = getIntent().getStringExtra("dia");
        String idDia = getIntent().getStringExtra("idDia");


        TextView fechaCTV = (TextView)findViewById(R.id.fechaCompleta);

        if(dia.equals("Jueves"))
            fechaCompuesta = dia+" 29 de Septiembre";
        else if(dia.equals("Viernes"))
            fechaCompuesta = dia+" 30 de Septiembre";
        else if(dia.equals("Miercoles"))
            fechaCompuesta = dia+" 28 de Septiembre";

        fechaCTV.setText(fechaCompuesta);

        //***********************

        if (idDia.equals("")) return;

        list = ApiRest.consultarListadoMenu(idDia);


        for (int i = 0; i< list.size(); i++){
            nombres.add(list.get(i).getNombre());
        }


        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, nombres);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                Intent newScreen = new Intent(menuActActivity.this, agendaDetalleActivity
                        .class);
                newScreen.putExtra("nombre", list.get(position).getNombre());
                newScreen.putExtra("salon", list.get(position).getSalon());
                newScreen.putExtra("horario", list.get(position).getHorario());
                newScreen.putExtra("codigo", list.get(position).getCodigo());
                newScreen.putExtra("fecha", fechaCompuesta);
                startActivity(newScreen);

            }

        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
