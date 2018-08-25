package com.yzd.jutils.interviewExt;

public class T1 {

    public static void main(String[] args) {
        Long userId=127L;
        Long authorId=127L;
        System.out.println(userId==authorId);//true
        userId=128L;
        authorId=128L;
        System.out.println(userId==authorId);//false
        Long x=new Long(127);
        Long y=new Long(127);
        System.out.println(x==y);//false
        System.out.println(x.equals(y));//true
    }
}
