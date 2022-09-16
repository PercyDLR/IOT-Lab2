package com.example.iot_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.example.iot_lab2.devices.Computadora;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class ComputerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intentReci = getIntent();
        HashMap<String,Object> listaDispositivos = (HashMap<String, Object>) intentReci.getSerializableExtra("listaDispositivos");
        ArrayList<Computadora> listaComputadoras = (ArrayList<Computadora>) listaDispositivos.get("computadoras");
        if(listaComputadoras.size() != 0){
            TextView textView = findViewById(R.id.textComputadora);
            String texto = "";
            for(Computadora computadora : listaComputadoras){
                texto+="Activo: " + computadora.getActivo() +"\n";
                texto+="Marca: " + computadora.getMarca() +"\n";
                texto+="Año: " + computadora.getMarca() +"\n";
                texto+="CPU: " + computadora.getCpu() +"\n";
            }
            textView.setText(texto);
        }
        setContentView(R.layout.activity_computer);
        ((FloatingActionButton) findViewById(R.id.floatingActionButton2)).setOnClickListener(view -> {
            Intent intent =  new Intent(ComputerActivity.this,AddComputerActivity.class);
            intent.putExtra("listaDispositivos",listaDispositivos);
            startActivity(intent);
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_computadora,menu);
        return true;
    }

}