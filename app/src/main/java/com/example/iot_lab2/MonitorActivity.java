package com.example.iot_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iot_lab2.devices.Monitor;

import java.util.ArrayList;
import java.util.HashMap;

public class MonitorActivity extends AppCompatActivity {

    HashMap<String,Object> listaDispositivos;
    ArrayList<Monitor> listaMonitores;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        setTitle("Monitor");

        listaDispositivos= (HashMap<String,Object>) getIntent().getSerializableExtra("listaDispositivos");
        listaMonitores = (ArrayList<Monitor>) listaDispositivos.get("monitores");
        ll = findViewById(R.id.llMonitor);

        if (listaMonitores.size() == 0){
            TextView msg = new TextView(this);

            msg.setText("No hay monitores registrados");
            msg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            msg.setTextSize(20);
            msg.setGravity(Gravity.CENTER);
            ll.addView(msg);
        } else {
            ll.removeAllViews();
            int index = 0;
            for (Monitor monitor : listaMonitores){

                TextView infoMonitor = new TextView(this);

                String msg = "Activo: " + monitor.getActivo() + "\n" +
                        "PC: " + monitor.getActivo() + "\n" +
                        "Marca: " + monitor.getMarca() + "\n" +
                        "Pulgadas: " + monitor.getPulgadas() + "\n" +
                        "Año: " + monitor.getAnho() + "\n" +
                        "Modelo: " + monitor.getModelo();

                infoMonitor.setText(msg);
                infoMonitor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                infoMonitor.setTextSize(20);
                infoMonitor.setTag(index);

                infoMonitor.setOnClickListener(view -> {
                    Monitor monitorActual = listaMonitores.get((Integer) view.getTag());

                    Intent intent = new Intent(MonitorActivity.this, AddMonitorActivity.class);
                    intent.putExtra("listaDispositivos",listaDispositivos);
                    intent.putExtra("titulo","Actualizar");
                    intent.putExtra("monitorActual",monitorActual);
                    startActivity(intent);
                });

                ll.addView(infoMonitor);
                index++;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_monitor,menu);
        return true;
    }

    public void listarTodo (MenuItem menuItem){
        ll.removeAllViews();

        if (listaMonitores.size() == 0){
            TextView msg = new TextView(this);

            msg.setText("No hay monitores registrados");
            msg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            msg.setTextSize(20);
            msg.setGravity(Gravity.CENTER);
            ll.addView(msg);
        }

        int index = 0;
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
            infoMonitor.setTextSize(20);
            ll.addView(infoMonitor);
            infoMonitor.setTag(index);

            infoMonitor.setOnClickListener(view -> {
                Monitor monitorActual = listaMonitores.get((Integer) view.getTag());

                Intent intent = new Intent(MonitorActivity.this, AddMonitorActivity.class);
                intent.putExtra("listaDispositivos",listaDispositivos);
                intent.putExtra("titulo","Actualizar");
                intent.putExtra("monitorActual",monitorActual);
                startActivity(intent);
            });
            index++;
        }
        Log.d("msg", "# elemntos LL: " + ll.getChildCount());
    }

    public void mostrarDialogoBusqueda(MenuItem menuItem){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Buscar Monitor");

        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Activo");
        builder.setView(input);

        builder.setPositiveButton("Buscar", (dialog, which) -> buscar(input.getText().toString()));
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    public void buscar(String busqueda){

        ll.removeAllViews();
        boolean encontrado = false;
        int index = 0;

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
                infoMonitor.setTag(index);
                infoMonitor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                infoMonitor.setTextSize(20);

                infoMonitor.setOnClickListener(view -> {
                        Monitor monitorActual = listaMonitores.get((Integer) view.getTag());

                        Intent intent = new Intent(MonitorActivity.this, AddMonitorActivity.class);
                        intent.putExtra("listaDispositivos",listaDispositivos);
                        intent.putExtra("titulo","Actualizar");
                        intent.putExtra("monitorActual",monitorActual);
                        startActivity(intent);
                    });

                ll.addView(infoMonitor);
                encontrado = true;
                break;
            }
            index++;
        }

        if (!encontrado){
            Log.d("msg", "No se encontró el equipo de activo: " + busqueda);
            Log.d("msg", "# elemntos LL: " + ll.getChildCount());
            TextView msg = new TextView(this);

            msg.setText("No existe el equipo con Activo: " + busqueda);
            msg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            msg.setTextSize(20);
            msg.setGravity(Gravity.CENTER);
            ll.addView(msg);
        }
    }

    public void agregarMonitor(View view){
        Intent intent = new Intent(MonitorActivity.this,AddMonitorActivity.class);
        intent.putExtra("titulo","Nuevo");
        intent.putExtra("listaDispositivos",listaDispositivos);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ArrayList<Monitor> listaMonitores = (ArrayList<Monitor>) listaDispositivos.get("monitores");

        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(MonitorActivity.this, MainActivity.class);
            intent.putExtra("listaDispositivos", listaDispositivos);
            startActivity(intent);
        }
        return true;
    }
}