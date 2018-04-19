package com.example.aidlserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.aidlserver.IAidlComputer;
import com.example.aidlserver.bean.Person;

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

//        private IMyCallbackListener mListener;

        @Override
        public int cal(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public Person getPerson() throws RemoteException {
            Person person = new Person();
            person.setAge(18);
            person.setName("jone");
            return person;
        }

//        @Override
//        public void registerListener(IMyCallbackListener listener) throws RemoteException {
//            mListener = listener;
//        }

    }


}
