package com.example.aidlserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.aidlserver.IAidlComputer;
import com.example.aidlserver.bean.Person;
import com.example.aidlserver.callback.IMyCallbackListener;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/4/18 23:58
 */

public class CalService extends Service {

    private static final String TAG = "CalService";

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

        @Override
        public IMyCallbackListener getCallbackListener() throws RemoteException {
            return new IMyCallbackListener.Stub() {
                @Override
                public void setData(Person person) throws RemoteException {
                    Log.e(TAG, "setData: " + person);
                }


                @Override
                public Person getPerson() throws RemoteException {
                    return new Person();
                }
            };

        }

//        @Override
//        public void registerListener(IMyCallbackListener listener) throws RemoteException {
//            mListener = listener;
//        }

    }


}
