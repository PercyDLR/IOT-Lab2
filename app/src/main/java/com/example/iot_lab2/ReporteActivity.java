package com.example.iot_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iot_lab2.devices.Computadora;
import com.example.iot_lab2.devices.Monitor;
import com.example.iot_lab2.devices.Teclado;

import java.util.ArrayList;
import java.util.HashMap;

public class ReporteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        Intent intent = getIntent();
        HashMap<String, Object> listaDispositivos = (HashMap<String, Object>)intent.getSerializableExtra("listaDispositivos");
        ArrayList<Computadora> listaComputadoras;
        ArrayList<Teclado> listaTeclados;
        ArrayList<Monitor> listaMonitores;
        listaComputadoras = (ArrayList<Computadora>) listaDispositivos.get("computadoras");
        listaTeclados =  (ArrayList<Teclado>) listaDispositivos.get("teclados");
        listaMonitores = (ArrayList<Monitor>) listaDispositivos.get("monitores");
        TextView textView1 = findViewById(R.id.reportePc);
        TextView textView2 = findViewById(R.id.reporteTeclado);
        TextView textView3 = findViewById(R.id.reporteMonitor);
        int numAnho=0;
        for(Computadora computadora : listaComputadoras){
            if(computadora.getAnho()==2022){
                numAnho++;
            }
        }
        //Computadoras
        if(listaComputadoras.size() != 0){
            textView1.setText(
                    "-Total: " + listaComputadoras.size() +"\n"
                    +"Del año 2022: " + numAnho  +"\n"
                );
                textView1.setTextSize(20);
        }else{
            textView1.setText("No hay computadoras registradas");
            textView1.setTextSize(20);
            textView1.setGravity(Gravity.CENTER);
        }
        // Teclados
        if(listaTeclados.size() != 0){
            textView2.setText("Teclados: " + listaTeclados.size() +"\n");
            textView2.setTextSize(20);
        }else{
            textView2.setText("No hay teclados registrados");
            textView2.setTextSize(20);
            textView2.setGravity(Gravity.CENTER);
        }

        //Monitores
        if(listaMonitores.size() != 0){
            textView3.setText("Teclados: " + listaTeclados.size() +"\n");
            textView3.setTextSize(20);
        }else{
            textView3.setText("No hay teclados registrados");
            textView3.setTextSize(20);
            textView3.setGravity(Gravity.CENTER);
        }

    }
}