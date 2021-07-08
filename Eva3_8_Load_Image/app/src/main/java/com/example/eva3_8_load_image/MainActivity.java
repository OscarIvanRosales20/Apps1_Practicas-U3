package com.example.eva3_8_load_image;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView ImgVwLoad;
    Bitmap bitmap;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //Aqui mostramos la imagen
            ImgVwLoad.setImageBitmap(bitmap);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImgVwLoad = (ImageView) findViewById(R.id.ImgVwLoad);
        //Meter esta linea dentro de un hilo de ejecución
        //Bitmap bitmap = descargarImagen("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.ambientum.com%2Fambientum%2Fcambio-climatico%2Fel-color-del-mar-esta-cambiando.asp&psig=AOvVaw1jIgz4thZHGBn3kO1Czt4O&ust=1621628842462000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLiH5qqM2fACFQAAAAAdAAAAABAN");


        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                //Aqui hacemos la conexión
                bitmap = descargarImagen("https://th.bing.com/th/id/R.3a97e63fc748556de77913efefe949b5?rik=YLUrzfvi%2bRgBcA&pid=ImgRaw");
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        };
        thread.start();
    }


    private Bitmap descargarImagen(String url){
        try {
            InputStream inputStream = (InputStream) new URL(url).getContent();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}