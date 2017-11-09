package com.example.jian.myaidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button bt;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private TextView tv;
    public IPerson mIPerson;
    ServiceConnection sc=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIPerson=IPerson.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIPerson=null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt=(Button)findViewById(R.id.bt);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.bt3);
        bt4=(Button)findViewById(R.id.bt4);
        tv=(TextView)findViewById(R.id.text);

        final Intent intent=new Intent(this,PersonService.class);
//        intent.setAction("com.example.jian.myaidl.PersonService");
//        intent.setPackage("com.example.jian.myaidl");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName cn=startService(intent);
                if(cn != null){
                    Toast.makeText(MainActivity.this,"start service successfully!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"start service failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent,sc,Context.BIND_AUTO_CREATE);
                if(bindService(intent,sc,Context.BIND_AUTO_CREATE)){
                    Toast.makeText(MainActivity.this,"bind service successfully!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"bind service failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=null;
                try {
                    if (mIPerson != null) {
                        name = mIPerson.getAllName();
                        tv.setText(name);
                    }
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(sc);
    }
}
