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
import android.widget.TextView;

import com.example.iot_lab2.devices.Computadora;
import com.example.iot_lab2.devices.Teclado;

import java.util.ArrayList;
import java.util.HashMap;

public class EditTecladoActivity extends AppCompatActivity {

    HashMap<String,Object> listaDispositivos;
    ArrayList<Computadora> listaComputadoras;
    ArrayList<Teclado> listaTeclados;
    String [] listaMarcas;
    String [] listaIdiomas;
    int indice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teclado);
        Intent intent = getIntent();
        listaDispositivos = (HashMap<String, Object>) intent.getSerializableExtra("listaDispositivos");
        listaTeclados = (ArrayList<Teclado>) listaDispositivos.get("teclados");
        listaMarcas = (String[]) listaDispositivos.get("marcas");
        listaIdiomas = (String[]) listaDispositivos.get("idiomas");
        indice = (Integer) intent.getIntExtra("indice",0);
        Teclado teclado = listaTeclados.get(indice);
        TextView activo = findViewById(R.id.editTextActivo);
        activo.setText(teclado.getActivo());
        TextView pcActivo = findViewById(R.id.editTextPCActivo);
        pcActivo.setText(teclado.getPc().getActivo());
        TextView marca = findViewById(R.id.editTextMarca);
        marca.setText(teclado.getMarca());
        TextView anho = findViewById(R.id.editTextAnho);
        anho.setText(String.valueOf(teclado.getAnho()));
        TextView idioma = findViewById(R.id.editTextIdioma);
        idioma.setText(teclado.getIdioma());
        TextView modelo = findViewById(R.id.editTextModelo);
        modelo.setText(String.valueOf(teclado.getModelo()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_teclado,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.tachoEditTeclado:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("¿Está seguro que desea borrar el teclado?");
                Intent intent = new Intent(EditTecladoActivity.this,TecladoActivity.class);
                alert.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listaTeclados.remove(indice);
                        listaDispositivos.put("teclados",listaTeclados);
                        intent.putExtra("listaDispositivos",listaDispositivos);
                        startActivity(intent);
                        finish();
                    }
                });
                alert.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intent.putExtra("listaDispositivos",listaDispositivos);
                        startActivity(intent);
                    }
                });
                alert.show();
                return true;
            case R.id.checkEditTeclado:
                Intent intent2 = new Intent(EditTecladoActivity.this,TecladoActivity.class);
                Teclado teclado = listaTeclados.get(indice);
                TextView activo = findViewById(R.id.editTextActivo);
                activo.setText(teclado.getActivo());
                TextView pcActivo = findViewById(R.id.editTextPCActivo);
                pcActivo.setText(teclado.getPc().getActivo());
                TextView marca = findViewById(R.id.editTextMarca);
                marca.setText(teclado.getMarca());
                TextView anho = findViewById(R.id.editTextAnho);
                anho.setText(String.valueOf(teclado.getAnho()));
                TextView idioma = findViewById(R.id.editTextIdioma);
                idioma.setText(teclado.getIdioma());
                TextView modelo = findViewById(R.id.editTextModelo);
                modelo.setText(String.valueOf(teclado.getModelo()));
                listaTeclados.set(indice,teclado);
                listaDispositivos.put("teclados",listaTeclados);
                intent2.putExtra("listaDispositivos",listaDispositivos);
                startActivity(intent2);
                return true;
            case android.R.id.home:
                Intent intent3 = new Intent(EditTecladoActivity.this,TecladoActivity.class);
                intent3.putExtra("listaDispositivos",listaDispositivos);
                startActivity(intent3);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void abrirMarcasEditTeclado(View view){
        final String [] marcas = listaMarcas;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona la marca");
        builder.setItems(marcas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView textView = findViewById(R.id.editTextMarca);
                textView.setText(marcas[i]);
            }
        });
        builder.show();
    }

    public void abrirIdiomasEditTeclado(View view){
        final String [] idiomas = listaIdiomas;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona el Idioma");
        builder.setItems(idiomas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView textView = findViewById(R.id.editTextIdioma);
                textView.setText(idiomas[i]);
            }
        });
        builder.show();
    }

    public void abrirPCsEditTeclado(View view){
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
                TextView textView = findViewById(R.id.editTextPCActivo);
                textView.setText(listaactivos[i]);
            }
        });
        builder.show();
    }
}