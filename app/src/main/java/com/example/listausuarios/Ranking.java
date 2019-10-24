package com.example.listausuarios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Ranking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        ListView lista = findViewById(R.id.lwPersonas);
        Button boton = findViewById(R.id.btnLista);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void cambiarPantalla(String nombre, String apellido, String correo) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
