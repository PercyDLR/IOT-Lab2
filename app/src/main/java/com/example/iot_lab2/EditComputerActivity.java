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

public class EditComputerActivity extends AppCompatActivity {

    HashMap<String,Object> listaDispositivos;
    ArrayList<Computadora> listaComputadoras;
    String [] listaMarcas;
    String [] listaCPU;
    int indice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_computer);
        Intent intent = getIntent();
        listaDispositivos = (HashMap<String, Object>) intent.getSerializableExtra("listaDispositivos");
        listaComputadoras = (ArrayList<Computadora>) listaDispositivos.get("computadoras");
        listaCPU = (String[]) listaDispositivos.get("cpu");
        listaMarcas = (String[]) listaDispositivos.get("marcas");
        indice = (Integer) intent.getIntExtra("indice",0);
        Computadora computadora = listaComputadoras.get(indice);
        TextView activo = findViewById(R.id.textActivo2);
        activo.setText(computadora.getActivo());
        TextView marca = findViewById(R.id.textMarca2);
        marca.setText(computadora.getMarca());
        TextView anho = findViewById(R.id.textAnho2);
        anho.setText(String.valueOf(computadora.getAnho()));
        TextView cpu = findViewById(R.id.textCPU2);
        cpu.setText(computadora.getCpu());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_computer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteComputer:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("¿Está seguro que desea borrar la computadora?");
                Intent intent = new Intent(EditComputerActivity.this,ComputerActivity.class);
                alert.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listaComputadoras.remove(indice);
                        listaDispositivos.put("computadoras",listaComputadoras);
                        intent.putExtra("listaDispositivos",listaDispositivos);
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
                return true;
            case R.id.editCheck:
                Intent intent2 = new Intent(EditComputerActivity.this,ComputerActivity.class);
                Computadora computadora = listaComputadoras.get(indice);
                TextView activo = findViewById(R.id.textActivo2);
                computadora.setActivo(String.valueOf(activo.getText()));
                TextView marca = findViewById(R.id.textMarca2);
                computadora.setMarca(String.valueOf(marca.getText()));
                TextView anho = findViewById(R.id.textAnho2);
                computadora.setAnho(Integer.parseInt(String.valueOf(anho.getText())));
                TextView cpu = findViewById(R.id.textCPU2);
                computadora.setCpu(String.valueOf(cpu.getText()));
                listaComputadoras.set(indice,computadora);
                listaDispositivos.put("computadoras",listaComputadoras);
                intent2.putExtra("listaDispositivos",listaDispositivos);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void abrirMarcasEdit(View view){
        final String [] marcas = listaMarcas;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona la marca");
        builder.setItems(marcas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView textView = findViewById(R.id.textMarca2);
                textView.setText(marcas[i]);
            }
        });
        builder.show();
    }
}