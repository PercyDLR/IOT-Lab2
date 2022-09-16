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


        Button btnCompu = findViewById(R.id.btnComputadora);
        btnCompu.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,ComputerActivity.class);
            intent.putExtra("listaComputadoras",listaDispositivos);
            startActivity(intent);
        });

        Button btnTeclado = findViewById(R.id.btnTeclado);
        btnTeclado.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,TecladoActivity.class);
            intent.putExtra("listaDispositivos",listaDispositivos);
            startActivity(intent);
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.monitor_menu,menu);
        return true;
    }

    public void listarTodo (View view){

    }

    public void buscar (View view){

    }
}