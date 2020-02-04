package com.yzd.jutils.algorithmExt;

import java.util.Random;

public class Hongbao {
    /**
     * MIN：最小值，MAX:最大值，TOTAL:总金额,N:人数
     */
    private static int MIN = 8, MAX = 14, TOTAL = 42, N = 4;

    /****概率统计/均值计算*/
    public static void main(String[] ww) {
        int[] arr = test();
        int i = 0;
        for (; i < 1000; i++) {
            int[] arr1 = test();
            for (int j = 0; j < arr.length; j++) {//搜集数据，计算分布情况
                arr[j] = arr[j] + arr1[j];
            }
        }
        System.out.println();
        for (int item : arr) {
            int avg = item / (i + 1);
            System.out.print(item + "(" + avg + ")" + " ");//输出每个人获得的钱数的平均值，以观察分配是否随机
        }
        //904(8) 928(9) 958(9) 966(9) 1003(9) 994(9) 1086(10) 1071(10) 1097(10) 1093(10)
        //从计算的结果来看，概率向后倾斜，也就是后面获得大红包的几率比前面大

        //加随机乱序后的结果
        //1027(10) 1029(10) 1020(10) 1009(9) 991(9) 983(9) 987(9) 1032(10) 1010(10) 1012(10)
        //1021(10) 1011(10) 1022(10) 1019(10) 995(9) 1000(9) 1004(9) 988(9) 1017(10) 1023(10)
        //993(9) 1031(10) 1052(10) 1021(10) 1009(9) 984(9) 985(9) 1013(10) 1011(10) 1001(9)
        //1032(10) 994(9) 1013(10) 1016(10) 1008(9) 1023(10) 1016(10) 1007(9) 972(9) 1019(10)
    }

    /****测试**/
    public static int[] test() {
        int min = MIN, max = MAX, total = TOTAL, n = N;
        int[] arr = hb(n, total, min, max);
        int sum = 0;
        for (int item : arr) {
            sum += item;
            System.out.print(" " + item);
        }
        System.out.println("\n总：" + sum);
        return arr;
    }

    /**
     * 对随机分配后的结果进行乱序处理
     *
     * @param arr
     * @return
     */
    public static int[] disorder(int[] arr) {
        Random rd = new Random();
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            int index = rd.nextInt(arr.length);
            if (index != i) {
                temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }

    /**
     * 根据设定的参数，返回分配后的结果
     *
     * @param n
     * @param total
     * @param min
     * @param max
     * @return
     */
    public static int[] hb(int n, int total, int min, int max) {
        //先校验数据
        int[] arr = new int[n];
        if (n * min > total || n * max < total) {
            System.out.println("错误的金额上下限");
            return null;
        }
        for (int i = 0; i < n; i++) {//先根据最低金额分配
            arr[i] = min;//保底
        }
        int surplus = total - n * min;//计算余额
        int iter = 0;
        while (true) {//根据余额随机分配，直到余额全部分完
            iter++;
            surplus = fenpei(arr, min, max, surplus);
            if (surplus <= 0) {
                System.out.println("迭代次数：" + iter);
                break;
            }
        }
        return disorder(arr);
    }

    /**
     * 将余额平均分到数组中
     *
     * @param arr   待分配对象集合
     * @param min   最小金额
     * @param max   最大金额
     * @param total 总金额（余额）
     * @param f     true:正序分配，false：倒序分配
     * @return 余额
     */
    public static int fenpei(int[] arr, int min, int max, int total) {
        int surplus = total;
        if (surplus <= 0) return 0;
        Random rd = new Random();
        int right = max - min + 1;//计算可分配的金额上限
        for (int i = 0; i < arr.length; i++) {
            int r = rd.nextInt(right);//获取一个随机数
            int value = arr[i];
            if (value + r > max) {//若超出，则取最大值
                r = max - value;
            }
            if (surplus <= r) r = surplus;//余额不够时，直接取余额
            value = value + r;
            arr[i] = value;//分配金额
            surplus = surplus - r;//重新计算余额
            if (surplus <= 0) {
                break;
            }
        }
        return surplus;
    }
}
