## 对于 Netty ByteBuf 的零拷贝(Zero Copy) 的理解
- [对于 Netty ByteBuf 的零拷贝(Zero Copy) 的理解](https://www.cnblogs.com/xys1228/p/6088805.html)
    - 通过 CompositeByteBuf 实现零拷贝
    - 通过 wrap 操作实现零拷贝
    - 通过 slice 操作实现零拷贝
    - 通过 FileRegion 实现零拷贝
    
## 示例
```
1.
传统的做法是将此 byte 数组拷贝到 ByteBuf 中, 即:
byte[] bytes = ...
ByteBuf byteBuf = Unpooled.buffer();
byteBuf.writeBytes(bytes);
2.
byte[] bytes = {(byte) 1};
PS:
ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
通过 Unpooled.wrappedBuffer 方法来将 bytes 包装成为一个 UnpooledHeapByteBuf 对象,
而在包装的过程中, 是不会有拷贝操作的. 即最后我们生成的生成的 ByteBuf 对象是和 bytes 数组共用了同一个存储空间,
对 bytes 的修改也会反映到 ByteBuf 对象中
```