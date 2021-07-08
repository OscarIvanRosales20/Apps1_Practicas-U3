package com.example.eva3_6_handler_message;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView TxtVwMensaje;
    Thread thread;

    //Metodo encargado de modificar la interfaz
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //Aqui podemos modificar la interfaz
            //Trabajo ligero  --> una tarea intensa va a trabar la UI
            String cade = (String)msg.obj;
            int what = msg.what;
            TxtVwMensaje.append("El hilo = " + what + " Imprime: " + cade + "\n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TxtVwMensaje  = (TextView)findViewById(R.id.TxtVwMensaje);
        //TxtVwMensaje.setText("Hola Mundo!!");
        Runnable runnable = new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.FROYO)
            @Override
            public void run() {
                int i = 0;
                while (true){
                    try {
                        Thread.sleep(1000);
                        String cade = "i = " + i;
                        i++;
                        //TxtVwMensaje.append(cade + "\n");
                        //Solicitamos un mensaje
                        //Poner info en el mensaje
                        Message message = handler.obtainMessage(1000, cade);
                        //Devolverlo
                        handler.sendMessage(message);
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