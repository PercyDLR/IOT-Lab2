package com.example.iot_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.iot_lab2.devices.Monitor;

import java.util.ArrayList;
import java.util.HashMap;

public class AddMonitorActivity extends AppCompatActivity {

    HashMap<String,Object> listaDispositivos;
    Monitor monitorActual;
    String titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_monitor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titulo = getIntent().getStringExtra("titulo");
        setTitle(titulo);
        listaDispositivos= (HashMap<String,Object>) getIntent().getSerializableExtra("listaDispositivos");


        if(titulo.equals("Actualizar")){
            monitorActual = (Monitor) getIntent().getSerializableExtra("monitorActual");

            ((EditText) findViewById(R.id.etMonitorActivo)).setText(monitorActual.getActivo());
            ((EditText) findViewById(R.id.etMonitorPcActivo)).setText(monitorActual.getPc().getActivo());
            ((EditText) findViewById(R.id.etMonitorMarca)).setText(monitorActual.getMarca());
            ((EditText) findViewById(R.id.etMonitorPulgadas)).setText(String.valueOf(monitorActual.getPulgadas()));
            ((EditText) findViewById(R.id.etMonitorAnho)).setText(monitorActual.getAnho());
            ((EditText) findViewById(R.id.etMonitorModelo)).setText(monitorActual.getModelo());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_monitor_context,menu);

        // Oculta el boton de borrar si se agrega un nuevo monitor
        if(titulo.equals("Nuevo")){
            MenuItem borrar = menu.findItem(R.id.optEliminarMonitor);
            borrar.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(getApplicationContext(), MonitorActivity.class);
        ArrayList<Monitor> listaMonitores = (ArrayList<Monitor>) listaDispositivos.get("monitores");

        switch (item.getItemId()){
            case android.R.id.home:
                intent.putExtra("listaDispositivos",listaDispositivos);
                startActivity(intent);
                break;
            case R.id.optGuardarMonitor:
                Monitor monitor = new Monitor();
                monitor.setActivo(((EditText) findViewById(R.id.etMonitorActivo)).getText().toString());
                //monitor.setPc(((EditText) findViewById(R.id.etMonitorActivo)).getText().toString());
                monitor.setMarca(((EditText) findViewById(R.id.etMonitorMarca)).getText().toString());
                monitor.setPulgadas(Float.parseFloat(((EditText) findViewById(R.id.etMonitorPulgadas)).getText().toString()));
                monitor.setAnho(Integer.parseInt(((EditText) findViewById(R.id.etMonitorAnho)).getText().toString()));
                monitor.setModelo(((EditText) findViewById(R.id.etMonitorModelo)).getText().toString());

                listaMonitores.remove(monitorActual);
                listaMonitores.add(monitor);

                intent.putExtra("listaDispositivos",listaDispositivos);
                startActivity(intent);
                listaDispositivos.put("monitores", listaMonitores);

                break;
            case R.id.optEliminarMonitor:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Eliminar Monitor");
                builder.setMessage("¿Estás seguro de querer eliminar este monitor?");

                builder.setPositiveButton("Sí", (dialog, which) -> {

                    if (listaMonitores.contains(monitorActual)) {
                        listaMonitores.remove(monitorActual);
                        listaDispositivos.put("monitores", listaMonitores);
                    }

                    intent.putExtra("listaDispositivos",listaDispositivos);
                    startActivity(intent);
                });
                builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
                builder.show();
        }

        return true;
    }
}