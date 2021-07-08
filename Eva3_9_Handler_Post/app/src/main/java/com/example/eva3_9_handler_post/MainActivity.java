package com.example.eva3_9_handler_post;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView TxtVwMostrar;
    Thread thread;
    Handler handler = new Handler();

    //Trabajo pesado, en segundo plano
    Runnable Background = new Runnable() {
        @Override
        public void run() {
            while (true){
                try {
                    thread.sleep(1000);
                    handler.post(Foreground);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    };

    //Trabajo con la UI
    Runnable Foreground = new Runnable() {
        @Override
        public void run() {
            TxtVwMostrar.append("Hola Mundo!: \n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TxtVwMostrar = (TextView)findViewById(R.id.TxtVwMostrar);
        thread = new Thread(Background);
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }
}