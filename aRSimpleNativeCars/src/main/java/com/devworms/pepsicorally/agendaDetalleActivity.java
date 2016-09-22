package com.devworms.pepsicorally;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class agendaDetalleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_detalle);

        String nombre = getIntent().getStringExtra("nombre");
        String salon = getIntent().getStringExtra("salon");
        String horario = getIntent().getStringExtra("horario");
        String codigo = getIntent().getStringExtra("codigo");
        String fecha = getIntent().getStringExtra("fecha");

        TextView fechaCTV = (TextView)findViewById(R.id.fechaCompleta);
        TextView nombreTV = (TextView)findViewById(R.id.nombre);
        TextView salonTV = (TextView)findViewById(R.id.lugar);
        TextView horarioTV = (TextView)findViewById(R.id.horario);
        TextView codigoTV = (TextView)findViewById(R.id.recom);

        fechaCTV.setText(fecha);
        nombreTV.setText(nombre);
        salonTV.setText(salon);
        horarioTV.setText(horario);
        codigoTV.setText(codigo);
    }

}
