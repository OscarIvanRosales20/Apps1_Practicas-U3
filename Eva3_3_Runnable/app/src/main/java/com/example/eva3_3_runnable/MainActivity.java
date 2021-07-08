package com.example.eva3_3_runnable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Runnable miRunnable = new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.FROYO)
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    try {
                        Thread.sleep(2000); //Detiene la ejecución del hilo actual
                        Log.wtf("Runnable miRunnable", " i = " + (i + 1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(miRunnable);
        thread.start();
    }

    class MiclaseRun implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++){
                try {
                    Thread.sleep(1000); //Detiene la ejecución del hilo actual
                    Log.wtf("Runnable miRunnable", " x = " + (i + 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}