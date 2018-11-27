package com.yzd.jutils.algorithmExt;

import java.util.Random;

/***
 * 简单随机红包生成java实现
 * https://blog.csdn.net/gfdgdshhg/article/details/78428952
 */
public class HongbaoUtil {

    /**
     * 根据设定的参数，返回分配后的结果
     * @param n
     * @param total
     * @param min
     * @param max
     * @return
     */
    public static int [] hb(int n, int total, int min, int max){
        //先校验数据
        int [] arr=new int[n];
        if(n*min>total||n*max<total){
            System.out.println("错误的金额上下限");
            return null;
        }
        for(int i=0;i<n;i++){//先根据最低金额分配
            arr[i]=min;//保底
        }
        int surplus=total-n*min;//计算余额
        int iter=0;
        while(true){//根据余额随机分配，直到余额全部分完
            iter++;
            surplus=fenpei(arr,min,max,surplus);
            if(surplus<=0){
                System.out.println("迭代次数："+iter);
                break;
            }
        }
        return disorder(arr) ;
    }

    /**
     * 将余额平均分到数组中
     * @param arr 待分配对象集合
     * @param min 最小金额
     * @param max 最大金额
     * @param total 总金额（余额）
     * @return 余额
     */
    private static int fenpei(int []arr,int min,int max,int total){
        int surplus=total;
        if(surplus<=0)return 0;
        Random rd=new Random();
        int right=max-min+1;//计算可分配的金额上限
        for(int i=0;i<arr.length;i++){
            int r=rd.nextInt(right);//获取一个随机数
            int value=arr[i];
            if(value+r>max){//若超出，则取最大值
                r=max-value;
            }
            if(surplus<=r)r=surplus;//余额不够时，直接取余额
            value=value+r;
            arr[i]=value;//分配金额
            surplus=surplus-r;//重新计算余额
            if(surplus<=0){
                break;
            }
        }
        return surplus;
    }
    /**
     * 对随机分配后的结果进行乱序处理
     * @param arr
     * @return
     */
    private static int[] disorder(int []arr){
        Random rd=new Random();
        int temp=0;
        for(int i=0;i<arr.length;i++){
            int index=rd.nextInt(arr.length);
            if(index!=i){
                temp=arr[index];
                arr[index]=arr[i];
                arr[i]=temp;
            }
        }
        return arr;
    }
}
