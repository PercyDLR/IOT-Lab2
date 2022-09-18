package com.example.iot_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iot_lab2.devices.Computadora;
import com.example.iot_lab2.devices.Teclado;

import java.util.ArrayList;
import java.util.HashMap;

public class AddTecladoActivity extends AppCompatActivity {

    HashMap<String,Object> listaDispositivos;
    ArrayList<Teclado> listaTeclados;
    ArrayList<Computadora> listaComputadoras;
    String [] listaMarcas;
    String [] listaIdiomas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teclado);
        Intent intent = getIntent();
        listaDispositivos = (HashMap<String, Object>) intent.getSerializableExtra("listaDispositivos");
        listaTeclados = (ArrayList<Teclado>) listaDispositivos.get("teclados");
        listaComputadoras = (ArrayList<Computadora>) listaDispositivos.get("computadoras");
        listaMarcas = (String[]) listaDispositivos.get("marcas");
        listaIdiomas = (String[]) listaDispositivos.get("idiomas");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_add_teclado,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.checkTeclado:
                TextView activo = findViewById(R.id.textActivoTeclado);
                TextView pc = findViewById(R.id.textPCActivoTeclado);
                TextView marca = findViewById(R.id.textMarcaTeclado);
                TextView anho = findViewById(R.id.textAhnoTeclado);
                TextView idioma = findViewById(R.id.textIdiomaTeclado);
                TextView modelo = findViewById(R.id.textModeloTeclado);
                Computadora computer = new Computadora();
                for(Computadora computadora : listaComputadoras){
                    if(pc.equals(computadora.getActivo())){
                        computer = computadora;
                    }
                }
                listaTeclados.add(new Teclado(String.valueOf(activo.getText()),computer,String.valueOf(marca.getText()),Integer.parseInt(String.valueOf(anho.getText())),String.valueOf(idioma.getText()),String.valueOf(modelo.getText())));
                listaDispositivos.put("teclados",listaTeclados);
                Intent intent = new Intent(AddTecladoActivity.this,TecladoActivity.class);
                intent.putExtra("listaDispositivos",listaDispositivos);
                startActivity(intent);
                return true;
            case android.R.id.home:
                Intent intent3 = new Intent(AddTecladoActivity.this,TecladoActivity.class);
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
                TextView textView = findViewById(R.id.textMarcaTeclado);
                textView.setText(marcas[i]);
            }
        });
        builder.show();
    }

    public void abrirIdiomas(View view){
        final String [] idiomas = listaIdiomas;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona el idioma");
        builder.setItems(idiomas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView textView = findViewById(R.id.textIdiomaTeclado);
                textView.setText(idiomas[i]);
            }
        });
        builder.show();
    }

    public void abrirPCs(View view){
        final String [] listaactivos = new String[listaComputadoras.size()];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona la pc");
        int i=0;
        for(Computadora computadora : listaComputadoras){
            listaactivos[i]= computadora.getActivo();
            Log.d("hh",computadora.getActivo());
            i++;
        }
        builder.setItems(listaactivos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView textView = findViewById(R.id.textPCActivoTeclado);
                textView.setText(listaactivos[i]);
            }
        });
        builder.show();
    }

}