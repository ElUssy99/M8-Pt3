package com.example.listausuarios;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int contador = 0;

    public static ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
    final String f = "..\\ListaUsuarios\\app\\src\\usuariosDatos.dat";

    // Parametro para generar numero aleatorio
    int randomNum = new Random().nextInt(100)+1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adivinar_numero);

        if (usuarios.size()==0){
            loadInfo();
        }

        //cargarArray();

        String num = String.valueOf(randomNum);
        Log.i("randomNum", num);

        final EditText numero = findViewById(R.id.txtNumero);

        final TextView txtView = findViewById(R.id.textView);
        final TextView txtView2 = findViewById(R.id.contador);

        // Inicializo el boton con una funcion al pulsarlo:
        final Button button = findViewById(R.id.button);
        final Button buttonGuardar = findViewById(R.id.buttonGuardarDialog);
        //buttonGuardar.setEnabled(false);
        final Button buttonRanking = findViewById(R.id.buttonRanking);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                contador = contador + 1;

                txtView2.setText("Intentos : " + contador);

                // Recojo el contenido del EditText y lo paso a Int:
                String StrNumeroRecogido = numero.getText().toString();
                int numeroRecogido = Integer.valueOf(StrNumeroRecogido);

                // Comparo si el numero introducido por el usuario es el mismo que el numero que se ha generado aleatorio:
                if (numeroRecogido == randomNum) {
                    // Se muestra un mensaje conforme has hacertado el numero:
                    Context context = getApplicationContext();
                    CharSequence text = "HAS ACERTADO";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    // El boton se deshabilita en cuanto el usuario acierta el numero:
                    button.setEnabled(false);
                    buttonGuardar.setEnabled(true);
                }

                // Cada numero que introduzca el usuario se guarda en un texto (primero se recoge el contenido de este y se le añade una linea mas):
                txtView.setText(txtView.getText() + "Has utilizado el numero: " + StrNumeroRecogido + "\n");


            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_class);
                dialog.setTitle("Introduce login");

                final Button mButton = (Button) findViewById(R.id.buttonGuardarDialog);
                mButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent intent = new Intent(MainActivity.this, Dialog.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });

        buttonRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Ranking.class);
                startActivity(intent);
            }
        });
    }

    private void loadInfo(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("persistence.txt")));
            String linia;
            while((linia = br.readLine())!=null){
                usuarios.add(new Usuarios(linia.split(";")[1], Integer.parseInt(linia.split(";")[0]), Uri.parse(linia.split(";")[2])));
            }
            br.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cargarArray(){
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(f));
            usuarios = (ArrayList<Usuarios>)entrada.readObject();
            entrada.close();
        } catch (ClassNotFoundException cnfe) {
            Toast toast = Toast.makeText(getApplicationContext(), "ERROR: No se pudo acceder a la clase.", Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            Toast toast = Toast.makeText(getApplicationContext(), "ERROR: No se han cargado los datos en el archivo.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public ArrayList<Usuarios> pasarUsuarios(){
        return usuarios;
    }
    public int pasarContador() {
        return contador;
    }

}
