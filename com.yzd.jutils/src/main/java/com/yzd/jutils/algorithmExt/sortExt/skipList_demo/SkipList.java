package com.yzd.jutils.algorithmExt.sortExt.skipList_demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiYang
 * @Date: 2020/3/6 23:57
 * @Description: 跳表的实现（增加、删除和查找，查找是O(logN)的时间复杂度）
 */
public class SkipList {

    /**
     * 跳表的结点类
     */
    private static class Node {

        //跳表结点的数据
        private Integer data;

        //跳表结点的左结点
        private Node left;

        //跳表结点的右结点
        private Node right;

        //跳表结点的下级结点
        private Node down;

        /**
         * 无参构造函数
         */
        public Node() {

        }

        /**
         * 全参数构造函数
         * @param data 结点值
         * @param left 左结点
         * @param right 右结点
         * @param down 下级结点
         */
        public Node(Integer data, Node left, Node right, Node down) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.down = down;
        }

        /**
         * 重写toString为展示数据
         * @return
         */
        @Override
        public String toString() {
            //如果有数据
            if (this.data != null) {

                //就展示数据
                return String.valueOf(this.data);

                //如果没有数据
            } else {

                //就直接返回null表示
                return "null";
            }
        }

        /**
         * 打印跳表结点自身和右边的链表数据
         */
        public void printRightList() {
            //把本结点赋值为当前结点
            Node current = this;

            //遍历当前结点的右结点
            while (current.getRight() != null){

                //打印结点数据，并用 - 隔开
                System.out.print(current.getRight().getData() + " - ");

                //获取下一个右结点
                current = current.getRight();
            }
        }

        /**
         * 下面是 Getter 和 Setter 方法
         */
        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getDown() {
            return down;
        }

        public void setDown(Node down) {
            this.down = down;
        }

    }

    /**
     * 跳表的最大层数
     * 如果想要完全发挥Integer的logN查询，可以
     * 将这个值设置为32
     */
    private static int SKIP_LIST_MAX_LEVEL = 16;

    /**
     * 跳表每一层的头结点列表
     */
    private List<Node> headList;

    //当SkipList类实例创建的时候，初始化headList
    {
        //将头结点列表，声明为固定容量（跳表最大层数）的列表
        headList = new ArrayList<>(SKIP_LIST_MAX_LEVEL);

        //每一层都加入空数据的头结点
        for (int i = 0; i < SKIP_LIST_MAX_LEVEL; i++) {
            headList.add(new Node());
        }

        //上一层将下一层设置为下结点
        for (int i = 1; i < SKIP_LIST_MAX_LEVEL; i++) {
            headList.get(i).setDown(headList.get(i - 1));
        }
    }

    /**
     * 获得新加节点的层数
     * @return 随机层数
     */
    private int getLevel() {
        //最底层下标为0，这一层包含所有数据
        int level = 0;

        //每当中标二分之一的概率
        while (Math.random() < 0.5) {

            //层数累加（看能连续中多少个二分之一的概率，也就是多少层）
            level ++;
        }

        //返回当前数据的最高层数，如果超过了最大层，就返回最大层
        return level >= SKIP_LIST_MAX_LEVEL? SKIP_LIST_MAX_LEVEL - 1 : level;
    }

    /**
     * 跳表的元素添加操作
     * @param data 需要添加的数据
     */
    public boolean add(int data) {
        //先获取需要插入的层数
        int level = getLevel();

        //获取最顶层的头结点
        Node topLevelHead = headList.get(level);

        //新结点（位于上部）
        Node upNode = null;

        //新结点（位于下部）
        Node downNode = null;

        //从顶层的头结点，一直往右下角方向找
        while (true) {

            //插入数据的新实例结点
            Node newNode = new Node();

            //设置结点数据为插入的数据
            newNode.setData(data);

            //标记为下部新结点
            downNode = newNode;

            //从本层头结点一直往右找，直到找到空，或者右边结点数据比插入数据大的
            while (topLevelHead.getRight() != null && topLevelHead.getRight().getData() < data) {

                //获得那个比插入数据刚刚小一点的节点
                topLevelHead = topLevelHead.getRight();
            }

            //刚刚小一点的节点的右边的数据（刚刚大于或等于插入数据）
            Node right = topLevelHead.getRight();

            //如果右边的数据存在，且是等于插入数据
            if (right != null && right.getData() == data) {

                //表示数据已存在，不重复加入，返回操作失败
                return false;
            }

            //如果topLevelHead刚好比插入数据小，且
            //right刚好比插入数据大

            //刚刚小的，设置插入结点为右结点
            topLevelHead.setRight(newNode);

            //插入结点，设置刚刚小的为左节点
            newNode.setLeft(topLevelHead);

            //如果有刚刚大的右结点
            if (right != null) {

                //插入节点，设置刚刚大的为右结点
                newNode.setRight(right);

                //刚刚大的，设置插入节点为左节点
                right.setLeft(newNode);
            }

            //如果标记的上部结点存在
            if (upNode != null) {

                //将新结点，也就是下部结点，设置为上部结点的下结点
                upNode.setDown(downNode);
            }

            //如果还有下一层
            if (topLevelHead.getDown() != null) {

                //将刚刚小的结点，用下一层的等值结点替代
                //然后重复这个过程，往右找到合适的地方插入新结点
                topLevelHead = topLevelHead.getDown();

                //更新位于上部的标记结点
                upNode = newNode;

                //如果已经是最底层了（最底层的结点，
                //都没有下结点 ），结束
            } else {

                //跳出循环
                break;
            }
        }

        //如果没有找到等值的结点，就会插入
        //并走到这里，返回插入成功
        return true;
    }

    /**
     * 跳表的元素删除操作
     * @param data 待删除的元素
     */
    public boolean delete(int data) {
        //先找到最顶的相同结点
        Node topNode = find(data);

        //如果遍历完链表所有层都没找到
        if (topNode == null) {

            //返回删除失败
            return false;
        }

        //定义当前头结点，初始化为最顶层找到的待删除数据的等值结点
        Node currentTopNode = topNode;

        //当还没找到最底层
        while (currentTopNode != null) {

            //如果找到当前层的删除数据等值结点无右节点
            if (currentTopNode.getRight() == null) {

                //获取等值结点的左节点
                Node left = currentTopNode.getLeft();

                //等值结点左节点设置右节点为空
                //也就相当于删除了这个等值结点
                left.setRight(null);

                //如果找到当前层的删除数据等值结点有右节点
            } else {

                //找到等值结点的左节点
                Node left = currentTopNode.getLeft();

                //找到等值结点的右节点
                Node right = currentTopNode.getRight();

                //然后让等值结点两边的结点牵手，跨过等值结点牵手
                //中间的等值结点就被删除了

                //左节点设置右节点为右节点
                left.setRight(right);

                //右节点设置左节点为左节点
                right.setLeft(left);

            }

            //继续往下一层找等值结点，重复上述删除的过程
            currentTopNode = currentTopNode.getDown();
        }

        //如果走到这里，就证明找到了删除元素，并删除成功了
        return true;
    }

    /**
     * 跳表元素的查找
     * @param data 要查找的数据
     * @return
     */
    public Node find(int data) {
        //找到顶层的头结点
        Node topLevelHead = headList.get(SKIP_LIST_MAX_LEVEL - 1);

        //当前节点初始化为顶层头结点
        Node current = topLevelHead;

        //顶部节点
        Node topNode = null;

        //寻找最顶部相同元素
        while (true) {

            //如果到了最底层都没找到
            if (current == null) {

                //那就不存在，返回空
                return null;
            }

            //一直找比查询值刚刚小的节点
            while (current.getRight() != null && current.getRight().getData() < data) {

                //将刚刚小的节点，作为当前节点
                current = current.getRight();
            }

            //如果是头结点，或者找到了末尾
            if (current.getRight() == null) {

                //当前节点垂直到下一层，接着往右找
                current = current.getDown();

                //如果刚刚小的结点的右节点的值不等于查询的值
            } else if (current.getRight().getData() != data) {

                //当前节点垂直到下一层，接着往右找
                current = current.getDown();

                //最后就是等于
            } else {

                //也就是找到了最顶的等价结点
                topNode = current.getRight();

                //结束查找
                break;
            }
        }

        //返回最顶的结点（所有层结点都是一样的）
        return topNode;
    }

    /**
     * 打印跳表
     */
    public void printSkipList() {
        //从底层到顶层打印
        for (int i = 0; i < SKIP_LIST_MAX_LEVEL; i++) {
            System.out.print("第" + i + "层：");
            headList.get(i).printRightList();
            System.out.println();
        }
    }


    /**
     * 跳表的测试方法
     * 算法：用Java实现跳表(SkipList)，让查找的时间复杂度媲美红黑树
     * https://blog.csdn.net/weixin_39448458/article/details/104707932
     * @param args
     */
    public static void main(String[] args) {

        //新建跳表的实例
        SkipList skipList = new SkipList();

        //乱序加入一些数据
        skipList.add(3);
        skipList.add(7);
        skipList.add(5);
        skipList.add(4);
        skipList.add(2);
        skipList.add(6);
        skipList.add(8);
        skipList.add(9);
        skipList.add(0);
        skipList.add(1);

        //打印加入数据后的跳表
        skipList.printSkipList();

        System.out.println("==================================");

        //删除一些存在的值，和不存在的值
        System.out.println("删除3：" + skipList.delete(3));
        System.out.println("删除5：" + skipList.delete(5));
        System.out.println("删除7：" + skipList.delete(7));
        System.out.println("删除15：" + skipList.delete(15));

        System.out.println("==================================");

        //打印删除后的跳表
        skipList.printSkipList();

        System.out.println("==================================");

        //查找一些存在的值，和不存在的值
        System.out.println("寻找6：" + skipList.find(6));
        System.out.println("寻找8：" + skipList.find(8));
        System.out.println("寻找3：" + skipList.find(3));
    }

}
