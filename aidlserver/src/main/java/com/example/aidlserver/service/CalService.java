package com.example.aidlserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.aidlserver.IAidlComputer;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/4/18 23:58
 */

public class CalService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new CalBinder();
    }


    public class CalBinder extends IAidlComputer.Stub {

        @Override
        public int cal(int a, int b) throws RemoteException {
            return a + b;
        }
    }
}
