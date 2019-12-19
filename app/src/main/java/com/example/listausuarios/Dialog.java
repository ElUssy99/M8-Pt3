package com.example.listausuarios;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Dialog extends AppCompatActivity {

    Uri photoURI;
    static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_class);

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

            }
        });


    }

    private void hacerFoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = crearArchivo();
            } catch (IOException ex) {}
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this, "com.capturaimagen.hacerfoto", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    public File crearArchivo() throws IOException {
        String tiempo = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "Foto_"+tiempo+"_";
        File storage = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageName,".jpg",storage);
        guardarFoto(image.getAbsolutePath());
        return image;
    }

    private void guardarFoto(String ruta) {
        File archivo = new File(getFilesDir(), "ultimaFoto.txt");
        try {
            if (!archivo.exists()) {
                archivo.createNewFile(); //SI EL ARCHIVO NO EXISTE LO CREO
            }
            PrintStream escribir = new PrintStream(archivo);
            escribir.println(ruta); //ESCRIBO EN UN ARCHIVO LA RUTA DE LA FOTO
            escribir.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String leerFoto() {
        File archivo = new File(getFilesDir(), "ultimaFoto.txt");
        try {
            if (archivo.exists()) {
                archivo.createNewFile(); //SI EL ARCHIVO NO EXISTE LO CREO
            }
            Scanner lector = new Scanner(archivo);
            return lector.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
