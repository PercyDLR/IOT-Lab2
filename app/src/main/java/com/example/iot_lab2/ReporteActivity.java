package com.example.iot_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;

public class ReporteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        Intent intent = getIntent();
        HashMap<String, Object> listaDispositivos = (HashMap<String, Object>)intent.getSerializableExtra("listaDispositivos");

    }
}