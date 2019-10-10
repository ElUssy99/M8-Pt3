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

    final File f = new File("..\\ListaUsuarios\\app\\src\\usuariosDatos.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adivinar_numero);

        // Recoger los datos de otra pantalla:
        final String nombre = getIntent().getExtras().getString("nombre");
        final String apellido = getIntent().getExtras().getString("apellido");
        final String correo = getIntent().getExtras().getString("correo");

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

                    // Guardar los datos en el Archivo:
                    escribirArchivo(nombre, apellido, correo, contador);
                }

                // Cada numero que introduzca el usuario se guarda en un texto (primero se recoge el contenido de este y se le añade una linea mas):
                txtView.setText(txtView.getText() + "Has utilizado el numero: " + StrNumeroRecogido + "\n");

                numero.setText("");
            }
        });
    }

    // Metodo para añadir los datos del usuario en el archivo:
    public void escribirArchivo(String nombre, String apellido, String correo, int contador){
        int contador2;


        try {
            FileWriter fw = new FileWriter(f, true);
            fw.write("Nombre: " + nombre + "\n");
            fw.write("Apellido: " + apellido + "\n");
            fw.write("Correo: " + correo + "\n");
            fw.write("Contador: " + contador + "\n");
            fw.close();
        }catch (IOException e){
            System.out.print(e.getMessage());
        }
    }

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

}
