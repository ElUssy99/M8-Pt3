package com.example.listausuarios;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Dialog extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public static ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
    public static int contador;

    File img;
    File dir = new File("data"+File.separator+"data"+File.separator+"com.example.listausuarios"+File.separator+"photos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_class);

        MainActivity ma = new MainActivity();
        usuarios = ma.pasarUsuarios();
        contador = ma.pasarContador();

        if (ContextCompat.checkSelfPermission(Dialog.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Dialog.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Dialog.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        TextView tv = findViewById(R.id.textViewLogin);
        tv.setVisibility(View.VISIBLE);
        EditText ed = findViewById(R.id.editTextLoginDialog);

        Button btnGuardar = findViewById(R.id.buttonGuardarDialog);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = ed.getText();
                usuarios.add(new Usuarios(nombre, contador, Uri.fromFile(img)));
                guardarInfo();
            }
        });

    }

    // No se por que me peta en los atributos de los usuarios
    private void guardarInfo() {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("usuarios.txt", Context.MODE_PRIVATE));
            for (int i=0; i<usuarios.size(); i++){
                osw.write(usuarios.get(i).nombre+";"+usuarios.get(i).contador+";"+usuarios.get(i).photoPath.toString());
                osw.append("\r\n");
            }
            osw.close();

        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            OutputStream os = null;
            try {
                if (dir.list().length>0){
                    img = new File(dir, (Integer.parseInt(dir.listFiles()[dir.listFiles().length-1].getName().substring(0,dir.listFiles()[dir.listFiles().length-1].getName().length()-4))+1) + ".png");
                }else{
                    img = new File(dir, "1.png");
                }
                os = new FileOutputStream(img);
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            } catch(IOException e) {
                System.out.println("ERROR al guardar la imagen");
            }
        }

    }
}
