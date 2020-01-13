package com.yzd.jutils.httpExt.socketclientExt;

import com.sun.jndi.toolkit.url.Uri;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class SocketHttpClient {
    public static String get(String url) throws IOException {
        Uri uri = new Uri(url);
        int port = getPort(uri);
        String host = uri.getHost();
        BufferedWriter bw = null;
        BufferedReader br = null;
        Socket socket = null;
        if (host != null) {
            try {
                /**
                 *  socket编程一般步骤
                 *（1） 创建Socket；
                 *（2） 打开连接到Socket的输入/出流；
                 *（3） 按照一定的协议对Socket进行读/写操作；
                 *（4） 关闭Socket.
                 * 其中address、host和port分别是双向连接中另一方的IP地址、主机名和端 口号，
                 * stream指明socket是流socket还是数据报socket，localPort表示本地主机的端口号，
                 * localAddr和 bindAddr是本地机器的地址（ServerSocket的主机地址）
                 * */
                socket = new Socket(host, port);
                bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                /**
                 * HTTP1.1
                 * 它支持持续连接.
                 * 与之相对的
                 * HTTP1.0
                 * 当连接建立起来以后,浏览器发送一个请求,之后一个回应消息被送回来.然后TCP连接被释放.
                 * 所以发生了阻塞
                 * */
                bw.write("GET " + url + " HTTP/1.0\r\n");//HTTP/1.1会发生阻塞
                bw.write("HOST:" + host + "\r\n");
                //bw.write("Connection: Keep-Alive\r\n");//HTTP/1.1会发生阻塞
                bw.write("\r\n");//在行的结束符\r\n之前没有任何数据，代表http head输出给服务器端的数据结束并完成
                bw.flush();      //清空缓冲区
                br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
                String line = null;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null)//防止出现空指针异常
                        socket.close();//释放资源，防止内存泄漏
                    if (br != null)
                        br.close();
                    if (bw != null)
                        bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("流关闭失败");
                }
            }
        }
        return null;
    }

    private static int getPort(Uri uri) {
        if ("http".equals(uri.getScheme())) {
            return 80;
        }
        if ("https".equals(uri.getScheme())) {
            return 443;
        }
        return uri.getPort();
    }

    public static String get2(String url) {

        try {
            Uri uri = new Uri(url);
            int port = getPort(uri);
            String host = uri.getHost();
            Socket s = new Socket(host, port);
            OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
            StringBuffer sb = new StringBuffer();
            sb.append("GET " + host + " HTTP/1.1\r\n");
            sb.append("Host: " + uri.getHost() + "\r\n");
            sb.append("Connection: Keep-Alive\r\n");
            //注，这是关键的关键，忘了这里让我搞了半个小时。这里一定要一个回车换行，表示消息头完，不然服务器会等待
            sb.append("\r\n");
            osw.write(sb.toString());
            osw.flush();

            //--输出服务器传回的消息的头信息
            InputStream is = s.getInputStream();
            String line = null;
            int contentLength = 0;//服务器发送回来的消息长度
            // 读取所有服务器发送过来的请求参数头部信息
            do {
                line = readLine(is, 0);
                //如果有Content-Length消息头时取出
                if (line.startsWith("Content-Length")) {
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                }
                //打印请求部信息
                System.out.print(line);
                //如果遇到了一个单独的回车换行，则表示请求头结束
            } while (!line.equals("\r\n"));

            //--输消息的体
            System.out.print(readLine(is, contentLength));

            //关闭流
            is.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 这里我们自己模拟读取一行，因为如果使用API中的BufferedReader时，它是读取到一个回车换行后
     * 才返回，否则如果没有读取，则一直阻塞，直接服务器超时自动关闭为止，如果此时还使用BufferedReader
     * 来读时，因为读到最后一行时，最后一行后不会有回车换行符，所以就会等待。如果使用服务器发送回来的
     * 消息头里的Content-Length来截取消息体，这样就不会阻塞
     *
     * contentLe 参数 如果为0时，表示读头，读时我们还是一行一行的返回；如果不为0，表示读消息体，
     * 时我们根据消息体的长度来读完消息体后，客户端自动关闭流，这样不用先到服务器超时来关闭。
     */
    private static String readLine(InputStream is, int contentLe) throws IOException {
        ArrayList lineByteList = new ArrayList();
        byte readByte;
        int total = 0;
        if (contentLe != 0) {
            do {
                readByte = (byte) is.read();
                lineByteList.add(Byte.valueOf(readByte));
                total++;
            } while (total < contentLe);//消息体读还未读完
        } else {
            do {
                readByte = (byte) is.read();
                lineByteList.add(Byte.valueOf(readByte));
            } while (readByte != 10);
        }

        byte[] tmpByteArr = new byte[lineByteList.size()];
        for (int i = 0; i < lineByteList.size(); i++) {
            tmpByteArr[i] = ((Byte) lineByteList.get(i)).byteValue();
        }
        lineByteList.clear();

        return new String(tmpByteArr, "UTF-8");
    }

    public static void main(String[] args) throws IOException {

        String url = "http://www.baidu.com/";
        System.out.println(get(url));
        System.out.println("done");
    }


}
