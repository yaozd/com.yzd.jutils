package com.yzd.jutils.visualVMExt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zd.yao on 2017/9/15.
 */
public class OOMObjectDemo {

    static class OOMObject {
        public byte[] placeholder = new byte[60 * 1024];
    }

    public static void fileHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作延迟，令监视曲线的变化更加明显
            list.add(new OOMObject());
            Thread.sleep(50);
            // System.out.println(i);
        }
        System.gc();
    }

    //模拟-内存溢出--VisualVM
/*    内存溢出生成配置：需要在启动时配置jvm参数
    -XX:+HeapDumpOnOutOfMemoryError
    -XX:HeapDumpPath=/var/logs/jvm/heap/dump/
    -Xmx900m （备注：设置最大内存，可以用来模拟内存溢出比较方便）*/
    public static void main(String[] args) throws InterruptedException {
        //Thread.sleep(10000);
        fileHeap(100000);
    }

//    public static void main(String[] args) throws InterruptedException {
////        Thread.sleep(10000);
//        List<byte[]> datas = new ArrayList<byte[]>();
//            while ( true ) {
//            datas.add(new byte[1024 * 1024]);
//        }
//    }
}
