# websocket
- [https://tools.ietf.org/html/rfc6455](https://tools.ietf.org/html/rfc6455)
## websocket 解码器-代码详解
- [Netty源码分析-Websocket之WebSocket08FrameDecoder](https://blog.csdn.net/nimasike/article/details/99230805)
## 参考

- [WebSocket协议理解-数据包格式解析](https://www.cnblogs.com/zhangmingda/p/12678630.html)-中文说明，推荐参考byArvin
- [WebSocket的Frame协议解析](https://blog.csdn.net/u011499747/article/details/83055845)
- [netty(4)高级篇-Websocket协议开发](https://blog.csdn.net/weixin_34232617/article/details/94631926)
- []()

## 掩码解码逻辑
- [WebSocket的Frame协议解析](https://blog.csdn.net/u011499747/article/details/83055845)
  - [详解XOR（异或）运算加密](https://www.cnblogs.com/shoshana-kong/p/11497494.html)  
  ```
    PS:如果mask=1，那么Masking-key存在，且都是4个字节（32位）
    //
    private static void unmask(byte[] maskingKey, ByteBuf frame) {
            byte[] maskedBytes = ByteBufUtil.getBytes(frame);
            int length = maskedBytes.length;
            //解码的结果
            byte[] unmaskedByte = new byte[length];
            for (int i = 0; i < length; ++i) {
                byte masking = maskingKey[i % 4];
                //XOR运算，还原原始值
                unmaskedByte[i] = (byte) (maskedBytes[i] ^ masking);
            }
            log.info(new String(unmaskedByte));
        }
    ```

