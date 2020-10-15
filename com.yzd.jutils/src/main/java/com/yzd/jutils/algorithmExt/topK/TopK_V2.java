package com.yzd.jutils.algorithmExt.topK;

/**
 * [面试手撕代码常见TopK问题（Java小顶堆实现）]
 * (https://blog.csdn.net/qq_30242987/article/details/104800716) 推荐参考
 * @Author: yaozh
 * @Description:
 */
public class TopK_V2 {
    public static void main(String[] args) {
        int k = 5;
        int[] data = {23, 333, 5, 8, 28, 7, 9, 2, 4, 3, 1, 6};
        //举例，如获取top5
        int[] top5 = topK(data, k);
        //1.先从原始数据中取出topK的前k个数据建立小顶堆
        for (int i = 0; i < k; i++) {
            System.out.println(top5[i]);
        }
        //

    }//main结束

    //2.建堆，剩下的N-K个数据来一个排序一个
    //3.从data数组中获取最大的k个数
    public static int[] topK(int[] data, int k) {
        int[] topk = new int[k];
        for (int i = 0; i < k; i++) {
            topk[i] = data[i];
        }
        //转换成小顶堆
        MinHeap heap = new MinHeap(topk);
        //从k开始，即剩下的数，进行遍历并加入到小顶堆
        for (int i = k; i < data.length; i++) {
            int root = heap.getRoot();
            //当当前数据大于堆中根节点，即最小的数，说明在范围之内，先保留
            if (data[i] > root) {
                heap.setRoot(data[i]);
            }
        }//for循环结束
        return topk;
    }//topk方法结束
} //TopK类结束

//构建小顶堆的类
class MinHeap {
    //堆的存储结构，数组保存
    private int[] data;

    //将数组数据建立小顶堆
    public MinHeap(int[] data) {
        this.data = data;
        bulidHeap();
    }

    //具体建立最小堆
    private void bulidHeap() {
        for (int i = (data.length) / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    //调整堆
    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        //存在左节点且左节点小于根节点的值
        if (l < data.length && data[l] < data[i]) {
            smallest = l;
        }
        //存在右节点且右节点小于以上节点的较小值
        if (r < data.length && data[r] < data[smallest]) {
            smallest = l;
        }
        if (i == smallest) {
            return;
        }

        //交换根节点和左右节点中最小的那个值，把根节点的值替换
        swap(i, smallest);
        //由于替换影响，需要对子树进行再次排序，直到；
        heapify(smallest);
    }

    //获取右节点的数组下标
    private int right(int i) {
        return (i + 1) << 1;
    }

    //获取左节点的数组下标
    private int left(int i) {
        return ((i + 1) << 1) - 1;
    }

    //交换元素位置
    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    //获取堆中的最小元素，根元素
    public int getRoot() {
        return data[0];
    }

    //替换根元素，并重新heapify
    public void setRoot(int root) {
        data[0] = root;
        heapify(0);
    }
}