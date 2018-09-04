package com.yzd.jutils.shutdownHook;

/**
 * Created by zd.yao on 2018/9/4.
 */
public class RTMServer extends Thread{


    //private MessageServer msg_server = null;
    //private ThriftServer thrift_server = null;


    public RTMServer()
    {
        this.setName("RTMServer");
    }

    public void start_server()
    {
        // 添加程序关闭监听线程
        Runtime.getRuntime().addShutdownHook(this);

        /*msg_server = new MessageServer();
        msg_server.start();

        thrift_server = new ThriftServer("ThriftServer");
        thrift_server.start();//该方法会阻塞*/
    }

    /*
     * 继承Thread
     * 用于在程序关闭时释放资源。
     * @see java.lang.Thread#run()
     */
    public void run()
    {
        System.out.println("shutdownHook close");
        System.out.println("监听获取程序退出事件（Linux、Windows、Java、C++）");
    }

    public static void main(String[] args) {
        //[监听获取程序退出事件（Linux、Windows、Java、C++）](https://my.oschina.net/u/2326085/blog/391352)
        // 初始化日志
        //LogUtil.init();

        RTMServer server = new RTMServer();
        server.start_server();

    }

}