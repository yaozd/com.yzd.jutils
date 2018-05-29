package com.yzd.jutils.classLoading;

public class Test1 {
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        Singleton s = Singleton.getSingleton();
        System.out.println("counter1 = "+ s.counter1);
        System.out.println("counter2 = "+s.counter2);
    }
}