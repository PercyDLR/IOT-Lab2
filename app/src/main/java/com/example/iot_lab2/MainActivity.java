package com.example.iot_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.iot_lab2.devices.Computadora;
import com.example.iot_lab2.devices.Monitor;
import com.example.iot_lab2.devices.Teclado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String,Object> listaDispositivos= new HashMap<String,Object>();
        ArrayList<Computadora> listaComputadoras = new ArrayList<Computadora>();
        ArrayList<Teclado> listaTeclados = new ArrayList<Teclado>();
        ArrayList<Monitor> listaMonitores = new ArrayList<Monitor>();
        listaDispositivos.put("computadoras",listaComputadoras);
        listaDispositivos.put("teclados",listaTeclados);
        listaDispositivos.put("monitores",listaMonitores);


        Button btnCompu = findViewById(R.id.btnComputadora);
        btnCompu.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,ComputerActivity.class);
            intent.putExtra("listaDispositivos",listaDispositivos);
            startActivity(intent);
        });

        Button btnTeclado = findViewById(R.id.btnTeclado);
        btnTeclado.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,TecladoActivity.class);
            intent.putExtra("listaDispositivos",listaDispositivos);
            startActivity(intent);
        });

        Button btnMonitor = findViewById(R.id.btnMonitor);
        btnMonitor.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,MonitorActivity.class);
            intent.putExtra("listaDispositivos",listaDispositivos);
            startActivity(intent);
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }
}