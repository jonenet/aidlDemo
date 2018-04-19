// IAidlComputer.aidl
package com.example.aidlserver;

import com.example.aidlserver.bean.Person;

import com.example.aidlserver.callback.IMyCallbackListener;

// Declare any non-default types here with import statements

interface IAidlComputer {
    int cal(int a,int b);

    Person getPerson();

    IMyCallbackListener getCallbackListener();

}
