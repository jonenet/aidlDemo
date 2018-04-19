// IAidlComputer.aidl
package com.example.aidlserver;
import com.example.aidlserver.bean.Person;
// Declare any non-default types here with import statements

interface IAidlComputer {
    int cal(int a,int b);

    Person getPerson();

}
