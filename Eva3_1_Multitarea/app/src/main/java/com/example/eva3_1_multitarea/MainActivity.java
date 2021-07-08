package com.example.eva3_1_multitarea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linux --> Proceso --> Tiene un hilo de ejecución principal
        //THREAD --> Clase para crear hilos (Java)
        //clase Thread() --> metodo run es generico
        //Crear propia clase que herede de Thread
        //Crear clase anonima
        //Sobeescribir el metodo run(){}

        Thread mihilo = new Thread(){
            //Aqui van las tareas en segundo plano
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 10; i++){
                    try {
                        Thread.sleep(1000); //Detiene la ejecución del hilo actual
                        Log.wtf("Hilo Principal", " i = " + (i + 1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mihilo.start();//Iniciamos el hilo de ejecución
        MiHilote miHilote = new MiHilote();
        //miHilote.run();//Llamada a función dentro del hilo principal
        miHilote.start();
    }

    class  MiHilote extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 10; i++){
                try {
                    Thread.sleep(100); //Detiene la ejecución del hilo actual
                    Log.wtf("Hilo Principal", " x = " + (i + 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}