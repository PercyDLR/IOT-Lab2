package com.example.iot_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.iot_lab2.devices.Computadora;
import com.example.iot_lab2.devices.Teclado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class TecladoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intentReci = getIntent();
        HashMap<String,Object> listaDispositivos = (HashMap<String, Object>) intentReci.getSerializableExtra("listaDispositivos");
        ArrayList<Teclado> listaTeclados = (ArrayList<Teclado>) listaDispositivos.get("teclados");
        if(listaTeclados.size() != 0){
            TextView textView = findViewById(R.id.textNoTeclado);
            String texto = "";
            for(Teclado teclado : listaTeclados){
                texto+="Activo: " + teclado.getActivo() +"\n";
                texto+="PC: " + teclado.getPc() +"\n";
                texto+="Marca: " + teclado.getMarca() +"\n";
                texto+="Año: " + teclado.getAnho() +"\n";
                texto+="Idioma: " + teclado.getIdioma() +"\n";
                texto+="Modelo: " + teclado.getModelo() +"\n";
            }
            textView.setText(texto);
        }
        setContentView(R.layout.activity_teclado);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingTeclado);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_teclado,menu);
        return true;
    }

}