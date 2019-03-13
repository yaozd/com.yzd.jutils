> [AcknowledgeMode选择](https://blog.csdn.net/weixin_38380858/article/details/84963944)
```
AcknowledgeMode.NONE模式
AcknowledgeMode.AUTO模式
AcknowledgeMode.MANUAL模式
-----
无ack模式（AcknowledgeMode.NONE）
server端行为
rabbitmq server默认推送的所有消息都已经消费成功，会不断地向消费端推送消息。
因为rabbitmq server认为推送的消息已被成功消费，所以推送出去的消息不会暂存在server端。
消息丢失的风险
当BlockingQueue<Runnable>堆满时（BlockingQueue<Delivery>一定会先满），server端推送消息会失败，然后断开connection。消费端从Socket读取Frame将会抛出SocketException，触发异常处理，shutdown掉connection和所有的channel，channel shutdown后WorkPool中的channel信息（包括channel inProgress,channel ready以及Map）全部清空，所以BlockingQueue<Runnable>中的数据会全部丢失。

此外，服务重启时也需对内存中未处理完的消息做必要的处理，以免丢失。

而在rabbitmq server，connection断掉后就没有消费者去消费这个queue，因此在server端会看到消息堆积的现象。

有ack模式（AcknowledgeMode.AUTO，AcknowledgeMode.MANUAL）
AcknowledgeMode.MANUAL模式需要人为地获取到channel之后调用方法向server发送ack（或消费失败时的nack）信息。

AcknowledgeMode.AUTO模式下，由spring-rabbit依据消息处理逻辑是否抛出异常自动发送ack（无异常）或nack（异常）到server端。

server端行为
rabbitmq server推送给每个channel的消息数量有限制，会保证每个channel没有收到ack的消息数量不会超过prefetchCount。
server端会暂存没有收到ack的消息，等消费端ack后才会丢掉；如果收到消费端的nack（消费失败的标识）或connection断开没收到反馈，会将消息放回到原队列头部。
这种模式不会丢消息，但效率较低，因为server端需要等收到消费端的答复之后才会继续推送消息，当然，推送消息和等待答复是异步的，可适当增大prefetchCount提高效率。

注意，有ack的模式下，需要考虑setDefaultRequeueRejected(false)，否则当消费消息抛出异常没有catch住时，这条消息会被rabbitmq放回到queue头部，再被推送过来，然后再抛异常再放回…死循环了。设置false的作用是抛异常时不放回，而是直接丢弃，所以可能需要对这条消息做处理，以免丢失。更详细的配置参考这里。
对比
无ack模式：效率高，存在丢失大量消息的风险。
有ack模式：效率低，不会丢消息。
```
