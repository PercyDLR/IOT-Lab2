package com.example.iot_lab2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewTreeViewModelKt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iot_lab2.devices.Computadora;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class ComputerActivity extends AppCompatActivity {
    HashMap<String,Object> listaDispositivos;
    ArrayList<Computadora> listaComputadoras;
    String [] listaMarcas;
    String [] listaCPU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);
        Intent intentReci = getIntent();
        listaDispositivos = (HashMap<String, Object>) intentReci.getSerializableExtra("listaDispositivos");
        listaComputadoras = (ArrayList<Computadora>) listaDispositivos.get("computadoras");
        listaCPU = (String[]) listaDispositivos.get("cpu");
        listaMarcas = (String[]) listaDispositivos.get("marcas");

        LinearLayout linearLayout = findViewById(R.id.textComputer);
        if(listaComputadoras.size() != 0){
            int i = 0;
            for(Computadora computadora : listaComputadoras){
                TextView textView =  new TextView(this);
                textView.setText(
                        "Activo: " + computadora.getActivo() +"\n"
                        +"Marca: " + computadora.getMarca() +"\n"
                        +"Año: " + computadora.getAnho() +"\n"
                        +"CPU: " + computadora.getCpu() +"\n"
                );
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                int finalI = i;
                textView.setOnClickListener(view -> {
                    Intent intent = new Intent(ComputerActivity.this,EditComputerActivity.class);
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
            textView.setText("No hay computadoras registradas");
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            linearLayout.addView(textView);
        }

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnBuscarComputadora:
                AlertDialog.Builder alertDialog  = new AlertDialog.Builder(this);
                alertDialog.setTitle("Buscar computadora");
                EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setHint("Activo");
                alertDialog.setView(input);
                alertDialog.setPositiveButton("BUSCAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String activo = String.valueOf(input.getText());
                        int ir = 0;
                        LinearLayout linearLayout = findViewById(R.id.textComputer);
                        linearLayout.removeAllViews();
                        boolean existe = false;
                        for(Computadora computadora : listaComputadoras){
                            TextView textView = new TextView(ComputerActivity.this);
                            if(computadora.getActivo().equals(activo)){
                                existe = true;
                                textView.setText("Activo: " + computadora.getActivo() +"\n"
                                        +"Marca: " + computadora.getMarca() +"\n"
                                        +"Año: " + computadora.getAnho() +"\n"
                                        +"CPU: " + computadora.getCpu() +"\n");
                                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                                int finalI = ir;
                                textView.setOnClickListener(view -> {
                                    Intent intent = new Intent(ComputerActivity.this,EditComputerActivity.class);
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
                        if(!existe){
                            TextView textView =  new TextView(ComputerActivity.this);
                            textView.setText("No existe el equico con el activo " + activo);
                            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                            textView.setTextSize(20);
                            textView.setGravity(Gravity.CENTER);
                            linearLayout.addView(textView);
                        }
                    }

                });
                alertDialog.setNegativeButton("CANCELAR", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });
                alertDialog.show();
                return true;
            case R.id.btnMostrarComputadora:
                LinearLayout linearLayout = findViewById(R.id.textComputer);
                linearLayout.removeAllViews();
                if(listaComputadoras.size() != 0){
                    int i = 0;
                    for(Computadora computadora : listaComputadoras){
                        TextView textView =  new TextView(this);
                        textView.setText(
                                "Activo: " + computadora.getActivo() +"\n"
                                        +"Marca: " + computadora.getMarca() +"\n"
                                        +"Año: " + computadora.getAnho() +"\n"
                                        +"CPU: " + computadora.getCpu() +"\n"
                        );
                        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                        int finalI = i;
                        textView.setOnClickListener(view -> {
                            Intent intent = new Intent(ComputerActivity.this,EditComputerActivity.class);
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
                    textView.setText("No hay computadoras registradas");
                    textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView.setTextSize(20);
                    textView.setGravity(Gravity.CENTER);
                    linearLayout.addView(textView);
                }
                return true;
            case android.R.id.home:
                Intent intent3 = new Intent(ComputerActivity.this,MainActivity.class);
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
}