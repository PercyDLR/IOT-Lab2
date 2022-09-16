package com.example.iot_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iot_lab2.devices.Monitor;

import java.util.ArrayList;
import java.util.HashMap;

public class MonitorActivity extends AppCompatActivity {

    ArrayList<Monitor> listaMonitores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        HashMap<String,Object> listaDispositivos= (HashMap<String,Object>) getIntent().getSerializableExtra("listaDispositivos");
        listaMonitores = (ArrayList<Monitor>) listaDispositivos.get("monitores");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.monitor_menu,menu);
        return true;
    }

    LinearLayout ll = findViewById(R.id.llMonitor);



    public void listarTodo (View view){
        ll.removeAllViews();

        for (Monitor monitor : listaMonitores){

            TextView infoMonitor = new TextView(this);

            String msg = "Activo: " + monitor.getActivo() + "/n" +
                    "PC: " + monitor.getPc().getActivo() + "/n" +
                    "Marca: " + monitor.getMarca() + "/n" +
                    "Pulgadas: " + monitor.getPulgadas() + "/n" +
                    "Año: " + monitor.getAnho() + "/n" +
                    "Modelo: " + monitor.getModelo() + "/n";

            infoMonitor.setText(msg);
            infoMonitor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

            ll.addView(infoMonitor);

        }
    }

    public void buscar (View view){
        String busqueda = "xd";
        boolean encontrado = false;

        ll.removeAllViews();

        for (Monitor monitor : listaMonitores){
            if (monitor.getActivo().equals(busqueda)){
                TextView infoMonitor = new TextView(this);

                String msg = "Activo: " + monitor.getActivo() + "/n" +
                        "PC: " + monitor.getPc().getActivo() + "/n" +
                        "Marca: " + monitor.getMarca() + "/n" +
                        "Pulgadas: " + monitor.getPulgadas() + "/n" +
                        "Año: " + monitor.getAnho() + "/n" +
                        "Modelo: " + monitor.getModelo() + "/n";

                infoMonitor.setText(msg);
                infoMonitor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

                ll.addView(infoMonitor);
                encontrado = true;
                break;
            }
        }

        if (!encontrado){
            TextView msg = new TextView(this);

            msg.setText("No existe el equipo con Activo: " + busqueda);
            msg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

            ll.addView(msg);
        }

    }
}