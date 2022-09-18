package com.example.iot_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.iot_lab2.devices.Computadora;

import java.util.ArrayList;
import java.util.HashMap;

public class AddComputerActivity extends AppCompatActivity {
    HashMap<String,Object> listaDispositivos;
    ArrayList<Computadora> listaComputadoras;
    String [] listaMarcas;
    String [] listaCPU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_computer);
        Intent intent = getIntent();
        listaDispositivos = (HashMap<String, Object>) intent.getSerializableExtra("listaDispositivos");
        listaComputadoras = (ArrayList<Computadora>) listaDispositivos.get("computadoras");
        listaCPU = (String[]) listaDispositivos.get("cpu");
        listaMarcas = (String[]) listaDispositivos.get("marcas");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_computer_ok,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.checkComputer:
                TextView activo = findViewById(R.id.textActivo);
                TextView marca = findViewById(R.id.textMarca);
                TextView anho = findViewById(R.id.textAnho);
                TextView cpu= findViewById(R.id.textCPU);
                listaComputadoras.add(new Computadora(String.valueOf(activo.getText()),String.valueOf(marca.getText()),Integer.parseInt(String.valueOf(anho.getText())),String.valueOf(cpu.getText())));
                listaDispositivos.put("computadoras",listaComputadoras);
                Intent intent = new Intent(AddComputerActivity.this,ComputerActivity.class);
                intent.putExtra("listaDispositivos",listaDispositivos);
                startActivity(intent);
                return true;
            case android.R.id.home:
                Intent intent3 = new Intent(AddComputerActivity.this,ComputerActivity.class);
                intent3.putExtra("listaDispositivos",listaDispositivos);
                startActivity(intent3);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void abrirMarcas(View view){
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