package com.example.iot_lab2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iot_lab2.devices.Computadora;
import com.example.iot_lab2.devices.Monitor;
import com.example.iot_lab2.devices.Teclado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class TecladoActivity extends AppCompatActivity {

    HashMap<String,Object> listaDispositivos;
    ArrayList<Teclado> listaTeclados;

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
                Intent intent = new Intent(TecladoActivity.this,AddTecladoActivity.class);
                intent.putExtra("listaDispositivos",listaDispositivos);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_teclado,menu);
        return true;
    }

    public void mostrarDialogoBusquedaTeclado(MenuItem menuItem){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Buscar Teclado");

        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Activo");
        builder.setView(input);

        builder.setPositiveButton("Buscar", (dialog, which) -> buscar(input.getText().toString()));
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    public void buscar(String busqueda){
        boolean encontrado = false;
        int index = 0;

        for (Teclado teclado : listaTeclados){
            if (teclado.getActivo().equals(busqueda)){
                TextView text = new TextView(this);

                String msg = "Activo: " + teclado.getActivo() + "/n" +
                        "PC: " + teclado.getPc().getActivo() + "/n" +
                        "Marca: " + teclado.getMarca() + "/n" +
                        "Año: " + teclado.getAnho() + "/n" +
                        "Idioma: " + teclado.getIdioma() + "/n" +
                        "Modelo: " + teclado.getModelo() + "/n";

                text.setText(msg);
                text.setTag(index);
                text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                text.setTextSize(20);

                text.setOnClickListener(view -> {
                    Teclado tecladoActual = listaTeclados.get((Integer) view.getTag());

                    Intent intent = new Intent(TecladoActivity.this, AddTecladoActivity.class);
                    intent.putExtra("listaDispositivos",listaDispositivos);
                    intent.putExtra("titulo","Actualizar");
                    intent.putExtra("monitorActual",tecladoActual);
                    startActivity(intent);
                });

                encontrado = true;
                break;
            }
            index++;
        }

        if (!encontrado){

            TextView msg = new TextView(this);

            msg.setText("No existe el equipo con Activo: " + busqueda);
            msg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            msg.setTextSize(20);
            msg.setGravity(Gravity.CENTER);

        }
    }

}