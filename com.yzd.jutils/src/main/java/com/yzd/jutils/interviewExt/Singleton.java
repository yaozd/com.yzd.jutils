package com.yzd.jutils.interviewExt;

public class Singleton{
    private static Singleton singleton = new Singleton();
    public static int counter1;
    public static int counter2 = 0;
    public Singleton(){
        counter1++;
        counter2++;
    }
    public static Singleton getSingleton(){
        return singleton;
    }
}
