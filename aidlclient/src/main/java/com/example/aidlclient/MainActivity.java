package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.aidlserver.IAidlComputer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MyConnection myConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("server.computer.aidlaction");
                //从 Android 5.0开始 隐式Intent绑定服务的方式已不能使用,所以这里需要设置Service所在服务端的包名
                intent.setPackage("com.example.aidlserver");
                startService(intent);
                myConnection = new MyConnection();
                bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
            }
        });

    }

    private class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected: ");
            try {
                IAidlComputer iAidlComputer = IAidlComputer.Stub.asInterface(service);
                int cal = iAidlComputer.cal(2, 3);
                Log.e(TAG, "onServiceConnected: " + cal);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: ");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != myConnection) {
            unbindService(myConnection);
        }
    }
}
