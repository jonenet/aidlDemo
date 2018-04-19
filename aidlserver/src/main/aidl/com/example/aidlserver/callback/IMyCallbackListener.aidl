// IMyCallbackListener.aidl
package com.example.aidlserver.callback;
import com.example.aidlserver.bean.Person;

// Declare any non-default types here with import statements

interface IMyCallbackListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void setData(in Person person);

    Person getPerson();
}
