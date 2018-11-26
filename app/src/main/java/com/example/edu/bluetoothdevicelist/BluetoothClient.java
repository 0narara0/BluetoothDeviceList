package com.example.edu.bluetoothdevicelist;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.UUID;

public class BluetoothClient extends AppCompatActivity implements View.OnClickListener {
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    String address;
    BluetoothSocket bluetoothSocket;
    BluetoothAdapter bluetoothAdapter=null;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_client);
        Intent newint = getIntent();
        address = newint.getStringExtra("device_address");
        progressBar = findViewById(R.id.progressBar);
        new ConnectBluetoothTask().execute();
        ((Button)findViewById(R.id.buttonU)).setOnClickListener(this);
        ((Button)findViewById(R.id.buttonD)).setOnClickListener(this);
        ((Button)findViewById(R.id.buttonL)).setOnClickListener(this);
        ((Button)findViewById(R.id.buttonR)).setOnClickListener(this);
        ((Button)findViewById(R.id.buttonC)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_a)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_b)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_c)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_d)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_e)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String message = "";
        switch (v.getId()) {
            case R.id.buttonU:
                message = "U";
                break;


            case R.id.buttonD:
                message = "D";
                break;

            case R.id.buttonL:
                message = "L";
                break;


            case R.id.buttonR:
                message = "R";
                break;

            case R.id.buttonC:
                message = "C";
                break;

            case R.id.btn_a:
                message = "a";
                break;

            case R.id.btn_b:
                message = "b";
                break;

            case R.id.btn_c:
                message = "c";
                break;

            case R.id.btn_d:
                message = "d";
                break;

            case R.id.btn_e:
                message = "e";
                break;

        }
            try {
                bluetoothSocket.getOutputStream().write(message.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }



    }

    private class ConnectBluetoothTask extends AsyncTask<Void, Void, Void>{
        private boolean ConnectSuccess = true;
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(bluetoothSocket==null){
                bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(address);
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                try {
                    bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(myUUID);
                    bluetoothSocket.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //start connection
            }
            return null;
        }
        protected void onPostExecute(Void result){
//            finish();
            System.out.println();
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
