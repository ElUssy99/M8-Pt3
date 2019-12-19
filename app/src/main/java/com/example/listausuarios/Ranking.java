package com.example.listausuarios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {

    ArrayList<Usuarios> usuariosRanking = new ArrayList<Usuarios>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        usuariosRanking = MainActivity.usuarios;

        TextView tv = findViewById(R.id.titulo);
        tv.setVisibility(View.VISIBLE);
        ListView lista = findViewById(R.id.lwPersonas);
        lista.setVisibility(View.VISIBLE);
        Button boton = findViewById(R.id.btnLista);
        boton.setVisibility(View.VISIBLE);

        ArrayAdapter<Usuarios> adapterUsuarios = new ArrayAdapter<>(this, android.R.layout.list_content, usuariosRanking);
        lista.setAdapter(adapterUsuarios);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ranking.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void cambiarPantalla(String nombre, String apellido, String correo) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
