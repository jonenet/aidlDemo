package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.aidlserver.IAidlComputer;
import com.example.aidlserver.bean.Person;
import com.example.aidlserver.callback.IMyCallbackListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MyConnection myConnection;
    private View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            //从 Android 5.0开始 隐式Intent绑定服务的方式已不能使用,所以这里需要设置Service所在服务端的包名
            ComponentName componentName = new ComponentName("com.example.aidlserver", "com.example.aidlserver.service.CalService");
            intent.setAction("com.example.aidlserver.action");
            intent.setComponent(componentName);
            startService(intent);
            myConnection = new MyConnection();
            bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_service).setOnClickListener(l);

    }


    class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected: ");
            try {
                IAidlComputer iAidlComputer = IAidlComputer.Stub.asInterface(service);
                int cal = iAidlComputer.cal(2, 3);

                Person person = iAidlComputer.getPerson();
                if (null != person) {
                    Log.e(TAG, "onServiceConnected: " + person);
                }
                Log.e(TAG, "onServiceConnected: " + cal);

                //其实这个例子可以看到 aidl 对象可以携带 Parcelable 对象
                IMyCallbackListener callbackListener = iAidlComputer.getCallbackListener();
//              调用 stub 的 getCallbackListener 其实就是调用服务器端的 getCallbackListener

                if (null != callbackListener) {
                    Person person1 = callbackListener.getPerson();
                    person1.setAge(16);
                    person1.setName("fiona");
                    callbackListener.setPerson(person1);
                }
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
