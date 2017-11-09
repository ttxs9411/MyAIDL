package com.example.jian.myaidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    public IPerson iPerson;
    ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iPerson=IPerson.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iPerson=null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Intent intent=new Intent(this,PersonService.class);
        startService(intent);
        if(!bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)){
            Toast.makeText(this,"bind service failed!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"bind service successfully!",Toast.LENGTH_SHORT).show();
        }
    }
}
