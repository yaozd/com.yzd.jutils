package com.yzd.jutils.algorithmExt.byteArrayExt;

import java.util.LinkedList;

/**
 * @Author: yaozh
 * @Description:
 */
public class ByteArrayJava {
    private int totalLength=0;
    private int lenght;
    private int begin =0;
    LinkedList<byte[]> linkedList=new LinkedList<>();
    byte[] bytes;
    boolean bytesFull=false;
    public ByteArrayJava(int initLength){
        bytes=new byte[initLength];
        lenght=initLength;
    }

    public ByteArrayJava add(byte[] value){
        int srcLength=value.length;
        if(srcLength==0){
            return this;
        }
        totalLength+=srcLength;
        if(bytesFull){
            linkedList.add(value);
        }
        if(srcLength+ begin >lenght){
            linkedList.add(value);
            bytesFull=true;
            return this;
        }
        System.arraycopy(value, 0, bytes, begin,srcLength );
        begin +=srcLength;
        return this;
    }
    public byte[] getBytes(){
        byte[] tempBytes=new byte[totalLength];
        System.arraycopy(bytes,0,tempBytes,0, begin);
        if(!bytesFull){
            return tempBytes;
        }
        int tempBegin= begin;
        for (byte[] bytes1 : linkedList) {
            int tempLength=bytes1.length;
            System.arraycopy(bytes1,0,tempBytes,tempBegin,tempLength);
            tempBegin+=tempLength;
        }
        return tempBytes;
    }
}
