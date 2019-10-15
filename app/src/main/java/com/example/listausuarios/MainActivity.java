package com.example.listausuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //////////////////////////// NO UTILIZAR ESTA CLASE ////////////////////////////

    ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
    final File f = new File("..\\ListaUsuarios\\app\\src\\usuariosDatos.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usuarios u = new Usuarios("David", 5);
        usuarios.add(u);

        final EditText etNombre = findViewById(R.id.editTextNombre);
        final EditText etApellido = findViewById(R.id.editTextApellido);
        final EditText etCorreo = findViewById(R.id.editTextCorreo);

        final TextView etLista = findViewById(R.id.textViewLista);

        int contadorUsuario = 0;
        for (Usuarios usuario: usuarios){
            contadorUsuario++;
            String datos = "USUARIO " + contadorUsuario + "\n" + usuario.getNombre() + " " + usuario.getContador() + "\n";
            etLista.setText(etLista.getText() + datos);
        }

        Button boton = findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String correo = etCorreo.getText().toString();

                // Guardo los datos en el archivo:
                guardarArray();

                // Llamo al metodo para cambiar de pantalla:
                cambiarPantalla(nombre, apellido, correo);
            }
        });

        // final ListView lw = findViewById(R.id.lista);
    }

    public void cambiarPantalla(String nombre, String apellido, String correo){
        Intent intent = new Intent (this, AdivinarNumero.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellido", apellido);
        intent.putExtra("correo", correo);
        startActivity(intent);
    }

    // Metodo para leer el archivo y guardar los datos en el ArrayList:
    public void guardarArray(){
        try{
            String cadena;
            FileReader fr = new FileReader(f);
            BufferedReader b = new BufferedReader(fr);
            // Leo el archivo y creo una variable para cada dato:
            while((cadena = b.readLine())!=null) {
                String[] datos = cadena.split(": ");
                String nombre = null;
                String apellido = null;
                String correo = null;
                int contador = 0;
                // Guardo cada dato en una variable y los guardo en un ArrayList despues de hacer un objeto de Usuarios:
                for (int i = 0; i < datos.length; i++){
                    nombre = datos[i];
                    apellido = datos[i+1];
                    correo = datos[i+2];
                    String contadorStr = datos[i+3];
                    contador = Integer.parseInt(contadorStr);
                }
                Usuarios u = new Usuarios(nombre, contador);
                usuarios.add(u);
            }
            b.close();
        }catch (IOException e){
            System.out.print(e.getMessage());
        }
    }
}
