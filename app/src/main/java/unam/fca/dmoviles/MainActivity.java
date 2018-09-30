package unam.fca.dmoviles;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final static int IMAGE_WIDTH = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Actividad 6 Recibir datos de otras aplicaciones parte 2
        //https://developer.android.com/training/sharing/receive
        //https://developer.android.com/reference/android/widget/ImageView.ScaleType
        //Las aplicaciones en android pueden recibir informacion de otras apps

        // El archivo del layout de la actividad se encuentra en la carpeta
        // res/layout/activity_main.xml
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        final ImageView iv = findViewById(R.id.iv);


        //Cuando nuestra aplicacion sea iniciada por otra  aplicacion, type no sera nulo
        if (Intent.ACTION_SEND.equals(action) && type != null) {

            if (type.startsWith("image/")) {

                Uri imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                if (imageUri != null) {
                    try {
                        //Es necesario crear una imagen reducida del original, para no generar errores de memoria en equipos con pocos recursos
                        Bitmap bitmap = ImageUtils.getScaledBitmapFromUri(getApplicationContext(),imageUri, IMAGE_WIDTH);
                        iv.setImageBitmap(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }

        }


        //Actividades
        //a) Revisa la url de referencia y el archivo AndroidManifest.xml, que tipo de informacion puede recibir la app?
        //b) Ejecuta la aplicacion en modo de depuracion, la primera vez que se ejecuta la app, que valor tiene Intent.ACTION_SEND?
        //c) Prueba compartiendo una imagen del navegador Chrome
        //d) Cuando la aplicacion es iniciada por otra app, que valor tiene Intent.ACTION_SEND?
        //e) Que valores puede tener el atributo scaleType del imageView iv?
        //f) Prueba la aplicacion con cada uno de los modos scaleType
        //f) Anota tus respuestas y las capturas de pantalla en un documento en Word
        //g) Sube tu codigo al repositorio.
        //h) Sube el documento Word a la plataforma Moodle. Incluye la liga a tu repositorio


    }
}
