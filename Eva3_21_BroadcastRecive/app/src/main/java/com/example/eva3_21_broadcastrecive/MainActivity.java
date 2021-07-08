package com.example.eva3_21_broadcastrecive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtVwVizualizar;
    Intent InService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVwVizualizar = findViewById(R.id.txtVwVizualizar);
        InService = new Intent(this, MyService.class);

        BroadcastReceiver broadcastReceiver = new MiBroadcast();
        IntentFilter intentFilter = new IntentFilter("Mi_Mensaje");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    public void iniciar(View v){
        startService(InService);
    }

    public void detener(View v){
        stopService(InService);
    }

    class MiBroadcast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            //Aqui leer los mensajes del servicio
            txtVwVizualizar.append(intent.getStringExtra("Mensaje") + "\n");
        }
    }
}