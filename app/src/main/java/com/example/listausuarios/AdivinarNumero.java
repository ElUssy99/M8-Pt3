package com.example.listausuarios;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AdivinarNumero extends AppCompatActivity {

    int contador = 0;

    ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
    final File f = new File("..\\ListaUsuarios\\app\\src\\usuariosDatos.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adivinar_numero);

        // Recoger los datos de otra pantalla (// INSERVIBLE //):
        // final String nombre = getIntent().getExtras().getString("nombre");
        // final String apellido = getIntent().getExtras().getString("apellido");
        // final String correo = getIntent().getExtras().getString("correo");

        // Parametro para generar numero aleatorio
        final int randomNum = new Random().nextInt(100)+1;

        final EditText numero = findViewById(R.id.txtNumero);

        final TextView txtView = findViewById(R.id.textView);
        final TextView txtView2 = findViewById(R.id.contador);

        // Inicializo el boton con una funcion al pulsarlo:
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                contador = contador + 1;

                txtView2.setText("Intentos : " + contador);

                // Recojo el contenido del EditText y lo paso a Int:
                String StrNumeroRecogido = numero.getText().toString();
                int numeroRecogido = Integer.valueOf(StrNumeroRecogido);

                // Comparo si el numero introducido por el usuario es el mismo que el numero que se ha generado aleatorio:
                if (numeroRecogido == 41) {
                    // Se muestra un mensaje conforme has hacertado el numero:
                    Context context = getApplicationContext();
                    CharSequence text = "HAS ACERTADO";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    // El boton se deshabilita en cuanto el usuario acierta el numero:
                    button.setEnabled(false);

                    // Rcoger el nombre del usuario que quiere guardar su puntuacion:
                    dialog();

                    // IMPORTANTE ACABAR //
                    // Guardar los datos en el Archivo:
                    // escribirArchivo(nombre, contador);
                }

                // Cada numero que introduzca el usuario se guarda en un texto (primero se recoge el contenido de este y se le añade una linea mas):
                txtView.setText(txtView.getText() + "Has utilizado el numero: " + StrNumeroRecogido + "\n");

                numero.setText("");
            }
        });
    }

    // IMPORTANTE ACABAR //
    public void dialog(){

    }

    // Metodo para añadir los datos del usuario en el archivo:
    public void escribirArchivo(String nombre, String apellido, String correo, int contador){
        int contador2;

        try {
            FileWriter fw = new FileWriter(f, true);
            fw.write(" " + nombre + " " + contador);
            fw.close();
        }catch (IOException e){
            System.out.print(e.getMessage());
        }
    }

    // Metodo para copiar y pegar (para tener a mano):
    public void leerArchivo(){
        try{
            String cadena;
            FileReader fr = new FileReader(f);
            BufferedReader b = new BufferedReader(fr);
            while((cadena = b.readLine())!=null) {
                System.out.println(cadena);
            }
            b.close();
        }catch (IOException e){
            System.out.print(e.getMessage());
        }
    }

    // Metodo para guardar los datos en el ArrayList:
    public void guardarArray(){
        // Leo el archivo:
        try{
            String cadena;
            FileReader fr = new FileReader(f);
            BufferedReader b = new BufferedReader(fr);
            // Leo el archivo y creo una variable para cada dato:
            while((cadena = b.readLine())!=null) {
                // Hago un split para que cada vez que haya un espacio (" ") recoja los datos:
                String[] datos = cadena.split(" ");
                String nombre = null;
                int contador = 0;
                // Guardo cada dato en una variable y los guardo en un ArrayList despues de hacer un objeto de Usuarios:
                // Primera vuelta: La posicion de "i" recoge el dato de nombre, se le suma 1 a "i" para recoger luego el contador.
                // Segunda vuelta: La posicion de "i" es la del contador, el FOR, automaticamente, le suma 1 a la posicion de "i"
                //                 para recoger de nuevo otro nombre y luego se le suma 1 a "i" para el contador.
                // ...
                for (int i = 0; i < datos.length; i++) {
                    nombre = datos[i];
                    contador = Integer.parseInt(datos[i + 1]);
                }

                // Creo un objeto de Usuarios y lo añado al ArrayList:
                Usuarios u = new Usuarios(nombre, contador);
                usuarios.add(u);
            }
            b.close();
        }catch (IOException e){
            System.out.print(e.getMessage());
        }
    }

}
