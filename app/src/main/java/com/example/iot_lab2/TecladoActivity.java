package com.example.iot_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
        setContentView(R.layout.activity_teclado);
        Intent intentReci = getIntent();
        listaDispositivos = (HashMap<String, Object>) intentReci.getSerializableExtra("listaDispositivos");
        listaTeclados = (ArrayList<Teclado>) listaDispositivos.get("teclados");
        LinearLayout linearLayout = findViewById(R.id.textTeclado);
        if(listaTeclados.size() != 0){
            int i = 0;
            for(Teclado teclado : listaTeclados){
                TextView textView =  new TextView(this);
                String texto = "";
                texto+="Activo: " + teclado.getActivo() +"\n";
                texto+="PC: " + teclado.getPc().getActivo() +"\n";
                texto+="Marca: " + teclado.getMarca() +"\n";
                texto+="Año: " + teclado.getAnho() +"\n";
                texto+="Idioma: " + teclado.getIdioma() +"\n";
                texto+="Modelo: " + teclado.getModelo() +"\n";
                textView.setText(texto);
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                int finalI = i;
                textView.setOnClickListener(view -> {
                    Intent intent = new Intent(TecladoActivity.this,EditTecladoActivity.class);
                    intent.putExtra("indice", finalI);
                    intent.putExtra("listaDispositivos", listaDispositivos);
                    startActivity(intent);
                    finish();
                });
                textView.setTextSize(20);
                linearLayout.addView(textView);
                i++;
            }
        }else{
            TextView textView =  new TextView(this);
            textView.setText("No hay teclados registrados");
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            linearLayout.addView(textView);
        }

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.btnBuscarTeclado:
                busquedaTeclado();
                return true;
            case R.id.btnTodoTeclado:
                listarTodo();
                return true;
            case android.R.id.home:
                Intent intent3 = new Intent(TecladoActivity.this,MainActivity.class);
                intent3.putExtra("listaDispositivos",listaDispositivos);
                startActivity(intent3);
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Intent getParentActivityIntent() {
        return super.getParentActivityIntent();
    }

    public void busquedaTeclado(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Buscar Teclado");

        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Activo");
        alertDialog.setView(input);

        alertDialog.setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                buscar(input.getText().toString());
            }
        });
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertDialog.show();
    }

    public void buscar(String busqueda){

        int ir = 0;
        LinearLayout linearLayout = findViewById(R.id.textTeclado);
        linearLayout.removeAllViews();
        boolean encontrado = false;
        for (Teclado teclado : listaTeclados){
            TextView textView = new TextView(TecladoActivity.this);
            if (teclado.getActivo().equals(busqueda)){
                encontrado = true;
                String msg = "Activo: " + teclado.getActivo() + "/n" +
                        "PC: " + teclado.getPc().getActivo() + "/n" +
                        "Marca: " + teclado.getMarca() + "/n" +
                        "Año: " + teclado.getAnho() + "/n" +
                        "Idioma: " + teclado.getIdioma() + "/n" +
                        "Modelo: " + teclado.getModelo() + "/n";
                textView.setText(msg);
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                int finalI = ir;
                textView.setOnClickListener(view -> {
                    Intent intent = new Intent(TecladoActivity.this,EditTecladoActivity.class);
                    intent.putExtra("indice", finalI);
                    intent.putExtra("listaDispositivos", listaDispositivos);
                    startActivity(intent);
                    finish();
                });
                textView.setTextSize(20);
                linearLayout.addView(textView);
            }
            ir++;
        }
        if (!encontrado){
            TextView textView =  new TextView(TecladoActivity.this);
            textView.setText("No existe el equico con el activo " + busqueda);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            linearLayout.addView(textView);
        }
    }

    public void listarTodo(){
        LinearLayout linearLayout = findViewById(R.id.textTeclado);
        linearLayout.removeAllViews();
        if(listaTeclados.size() != 0){
            int i = 0;
            for(Teclado teclado : listaTeclados){
                TextView textView =  new TextView(this);
                String texto = "";
                texto+="Activo: " + teclado.getActivo() +"\n";
                texto+="PC: " + teclado.getPc() +"\n";
                texto+="Marca: " + teclado.getMarca() +"\n";
                texto+="Año: " + teclado.getAnho() +"\n";
                texto+="Idioma: " + teclado.getIdioma() +"\n";
                texto+="Modelo: " + teclado.getModelo() +"\n";
                textView.setText(texto);
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                int finalI = i;
                textView.setOnClickListener(view -> {
                    Intent intent = new Intent(TecladoActivity.this,EditTecladoActivity.class);
                    intent.putExtra("indice", finalI);
                    intent.putExtra("listaDispositivos", listaDispositivos);
                    startActivity(intent);
                    finish();
                });
                textView.setTextSize(20);
                linearLayout.addView(textView);
                i++;
            }
        }else{
            TextView textView =  new TextView(this);
            textView.setText("No hay teclados registrados");
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            linearLayout.addView(textView);
        }
    }
}