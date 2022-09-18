package com.example.iot_lab2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.iot_lab2.devices.Computadora;

import java.util.ArrayList;
import java.util.HashMap;

public class EditComputerActivity extends AppCompatActivity {

    HashMap<String,Object> listaDispositivos;
    ArrayList<Computadora> listaComputadoras;
    String [] listaMarcas;
    String [] listaCPU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_computer);
        Intent intent = getIntent();
        listaDispositivos = (HashMap<String, Object>) intent.getSerializableExtra("listaDispositivos");
        listaComputadoras = (ArrayList<Computadora>) listaDispositivos.get("computadoras");
        listaCPU = (String[]) listaDispositivos.get("cpu");
        listaMarcas = (String[]) listaDispositivos.get("marcas");
        int indice = (Integer) intent.getIntExtra("indice",0);
        Computadora computadora = listaComputadoras.get(indice);
        TextView activo = findViewById(R.id.textActivo2);
        activo.setText(computadora.getActivo());
        TextView marca = findViewById(R.id.textMarca2);
        marca.setText(computadora.getMarca());
        TextView anho = findViewById(R.id.textAnho2);
        anho.setText(computadora.getAnho());
        TextView cpu = findViewById(R.id.textCPU2);
        cpu.setText(computadora.getCpu());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_computer,menu);
        return true;
    }

    public void abrirMarcasEdit(View view){
        final String [] marcas = listaMarcas;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona la marca");
        builder.setItems(marcas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView textView = findViewById(R.id.textMarca);
                textView.setText(marcas[i]);
            }
        });
        builder.show();
    }

}