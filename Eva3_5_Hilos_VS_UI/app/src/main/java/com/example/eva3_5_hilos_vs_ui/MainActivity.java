package com.example.eva3_5_hilos_vs_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView TxtVwMensaje;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TxtVwMensaje  = (TextView)findViewById(R.id.TxtVwMensaje);
        //TxtVwMensaje.setText("Hola Mundo!!");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true){
                    try {
                        Thread.sleep(1000);
                        String cade = "i = " + i;
                        i++;
                        TxtVwMensaje.append(cade + "\n");
                        Log.wtf("Runnable", cade);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        thread = new Thread(runnable);
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Thread.interrupted();   
    }
}