package com.example.iot_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AddMonitorActivity extends AppCompatActivity {

    String titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_monitor);

        titulo = getIntent().getStringExtra("titulo");
        setTitle(titulo);
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

    public void guardarMonitor(){

    }

    public void borrarMonitor(){

    }

}