/*
package com.example.listausuarios;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
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
    String login;

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
        final Button buttonGuardar = findViewById(R.id.buttonGuardar);
        final Button buttonRanking = findViewById(R.id.buttonRanking);

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
                }

                // Cada numero que introduzca el usuario se guarda en un texto (primero se recoge el contenido de este y se le añade una linea mas):
                txtView.setText(txtView.getText() + "Has utilizado el numero: " + StrNumeroRecogido + "\n");

                numero.setText("");
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rcoger el nombre del usuario que quiere guardar su puntuacion:
                dialog();
                // Guardar los datos en el Archivo:
                if (!login.isEmpty() || login != null) {
                    escribirArchivo(login, contador);
                }
            }
        });
    }

    // IMPORTANTE ACABAR //
    public void dialog(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdivinarNumero.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_class, null);
        final EditText mLogin = (EditText) mView.findViewById(R.id.editTextLogin);
        Button mButton = (Button) mView.findViewById(R.id.buttonLogin);

        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (!mLogin.getText().toString().isEmpty()) {
                    login = mLogin.getText().toString();
                }
            }
        });
    }

    // Metodo para añadir los datos del usuario en el archivo:
    public void escribirArchivo(String login, int contador){
        int contador2;

        try {
            FileWriter fw = new FileWriter(f, true);
            fw.write(" " + login + " " + contador);
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
*/

/*
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

    // CMBIAR CODIGO DE ADIVINARNUMERO A MAINACTIVITY //

    ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
    final File f = new File("..\\ListaUsuarios\\app\\src\\usuariosDatos.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adivinar_numero);

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
*/