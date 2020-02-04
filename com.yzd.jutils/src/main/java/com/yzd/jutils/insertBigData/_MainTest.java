package com.yzd.jutils.insertBigData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class _MainTest {

    //=====================================================
    // JAVA向Mysql插入亿级别数据---测评
    // https://blog.csdn.net/q6834850/article/details/73726707
    // 方案一
    // 利用mybatis插入 一万 条数据耗时：28613，即*28.6秒*
    // 方案二
    // 采用JDBC直接处理 平均每 21.2 秒插入 一万 数据
    // 方案三
    // 采用JDBC批处理 平均每 2.1 秒插入 十万 条数据 -byArvin-推荐使用-2018-03-24
    // 示例代码如下：
    //=====================================================
    private long begin = 33112001;//起始id
    private long end = begin + 100000;//每次循环插入的数据量
    //private long end = begin+1000;//每次循环插入的数据量
    private String url = "jdbc:mysql://192.168.3.30:3306/bigdata?useServerPrepStmts=false&rewriteBatchedStatements=true&useUnicode=true&amp;characterEncoding=UTF-8";
    private String user = "canal";
    private String password = "canal";

    //当前插入数据为：一百万条
    @org.junit.Test
    public void insertBigData() {
        //定义连接、statement对象
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            //加载jdbc驱动
            Class.forName("com.mysql.jdbc.Driver");
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //必须关闭自动提交,否则有时不能插入数据-byArvin-2018-03-24
            //将自动提交关闭
            //conn.setAutoCommit(false);
            //编写sql
            String sql = "INSERT INTO person VALUES (?,?,?,?,?,?,?)";
            //预编译sql
            pstm = conn.prepareStatement(sql);
            //开始总计时
            long bTime1 = System.currentTimeMillis();

            //循环10次，每次十万数据，一共1000万
            for (int i = 0; i < 10; i++) {

                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                while (begin < end) {
                    //赋值
                    pstm.setLong(1, begin);
                    pstm.setString(2, RandomValue.getChineseName());
                    pstm.setString(3, RandomValue.name_sex);
                    pstm.setInt(4, RandomValue.getNum(1, 100));
                    pstm.setString(5, RandomValue.getEmail(4, 15));
                    pstm.setString(6, RandomValue.getTel());
                    pstm.setString(7, RandomValue.getRoad());
                    //添加到同一个批处理中
                    pstm.addBatch();
                    begin++;
                }
                //执行批处理
                pstm.executeBatch();
//                //提交事务
//                conn.commit();
                //边界值自增10W
                end += 100000;
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入10W条数据耗时：" + (eTime - bTime));
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入100W数据共耗时：" + (eTime1 - bTime1));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
