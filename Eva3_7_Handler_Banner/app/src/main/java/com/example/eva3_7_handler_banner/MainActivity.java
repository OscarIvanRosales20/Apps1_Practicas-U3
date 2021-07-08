package com.example.eva3_7_handler_banner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imgVwBanner;
    Thread tBanner;
    int Cont = 0;

    //Atravez de un Handler (Método HANDLERMESSAGE) Interactuar con la UI
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //Interactuar con la UI
            int image;
            if (Cont == 0){
                image = R.drawable.f1;
                Cont++;
            }else if(Cont == 1){
                image = R.drawable.f2;
                Cont++;
            }else{
                image = R.drawable.f3;
                Cont = 0;
            }
            imgVwBanner.setImageResource(image);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwBanner = (ImageView)findViewById(R.id.imgVwBanner);

        tBanner = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        Thread.sleep(1000);
                        //Solicitar un mensaje
                        Message message = handler.obtainMessage();
                        //Enviar el mensaje
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        tBanner.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tBanner.interrupt();
    }
}