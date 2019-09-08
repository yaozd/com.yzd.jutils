package com.yzd.jutils.bitMapExt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author zd.yao
 * @description
 * @date 2019/9/7
 **/

public class IsNumberExist {
    //[使用bitmap进行大量数据的排序、判断存在与否](https://blog.csdn.net/qq_33240946/article/details/83929158)
    private int[] bitmap;
    private int size;
    private int SHIFT = 5;//2的5次方是32
    public  boolean isNumberExist(int number){
        int bit = number>>SHIFT;
        int index = number&((1<<SHIFT)-1);
        return ((1<<index)&bitmap[bit])!=0;
    }
    public IsNumberExist(int size){
        this.size = size;
        bitmap = new int[(size>>SHIFT)+1];
    }
    public void insertDate(int number){
        int bit = number>>SHIFT;
        int index = number&((1<<SHIFT)-1);
        bitmap[bit] = bitmap[bit]|(1<<index);
    }
    public void insertFromTxt(String filename){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String str = null;
            while ((str = br.readLine())!=null){
                insertDate(Integer.valueOf(str));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        System.out.println("当前JVM所占内存："+(rt.totalMemory()-rt.freeMemory())/1024/1024+"M");
        IsNumberExist tool = new IsNumberExist(1000000000);
        System.out.println("当前JVM所占内存："+(rt.totalMemory()-rt.freeMemory())/1024/1024+"M");
        //Date.makeNumbers(100000000);//生成一亿个数到number.txt
        tool.insertFromTxt("numbers.txt");//使用这个一亿个数初始化bitmap的状态
        System.out.println(tool.isNumberExist(88888888));//判断88888888是否在这个文件中
        System.out.println(tool.isNumberExist(99999999));//判断99999999是否在这个文件中
        System.out.println(tool.isNumberExist(91725151));//判断91725151是否在这个文件中


    }
}

