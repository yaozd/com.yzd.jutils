## HTT2-错误码
----
| name          |code           |desc           |
| ------------- | ------------- | ------------- |
|NO_ERROR  |0   |没有错误    |
|PROTOCOL_ERROR  | 1  |协议错误    |
|INTERNAL_ERROR  |2   |内部错误    |
|FLOW_CONTROL_ERROR  | 3  |流控制错误    |
|SETTINGS_TIMEOUT  | 4  |设置超时    |
|STREAM_CLOSED  | 5  |流关闭    |
|FRAME_SIZE_ERROR  | 6  |帧大小错误    |
|REFUSED_STREAM  | 7  |拒绝流    |
|CANCEL  | 8  | 取消   |
|COMPRESSION_ERROR  | 9  | 压缩错误   |
|CONNECT_ERROR  |10   | 连接错误  |
|ENHANCE_YOUR_CALM  | 11  | 增强   |
|INADEQUATE_SECURITY  |12   |不够安全    |
|HTTP_1_1_REQUIRED  |13   |HTTP_1_1要求    |

## GRPC-错误码
----
| name          |code           |desc           |
| ------------- | ------------- | ------------- |
|OK  |0   |没有错误    |
|CANCELLED  |1   |取消    |
|UNKNOWN  |2   |未知的    |
|INVALID_ARGUMENT  |3   |无效的参数    |
|DEADLINE_EXCEEDED  |4   |期限超过-超时    |
|NOT_FOUND  |5   |没有找到    |
|ALREADY_EXISTS  |6   |已经存在    |
|PERMISSION_DENIED  |7   |没有权限    |
|RESOURCE_EXHAUSTED  |8   |资源耗尽    |
|FAILED_PRECONDITION  |9   |失败的前提    |
|ABORTED  |10   |中止    |
|OUT_OF_RANGE  |11   |溢出    |
|UNIMPLEMENTED  |12   |未实现    |
|INTERNAL  |13   |内部    |
|UNAVAILABLE  |14   |不可用    |
|DATA_LOSS  |15   |数据丢失    |
|UNAUTHENTICATED  |16   |未经身份验证的    |

## [grpc成功和异常状态码](https://blog.csdn.net/qq_14945847/article/details/102466693)

```
0：Ok：返回成功
1：Canceled：操作已取消
2：Unknown：未知错误。如果从另一个地址空间接收到的状态值属 于在该地址空间中未知的错误空间，则可以返回此错误的示例。 没有返回足够的错误信息的API引发的错误也可能会转换为此错误
3：InvalidArgument：表示客户端指定了无效的参数。 请注意，这与FailedPrecondition不同。 它表示无论系统状态如何（例如格式错误的文件名）都有问题的参数
4：DeadlineExceeded：意味着操作在完成之前过期。 对于更改系统状态的操作，即使操作成功完成，也可能会返回此错误。 例如，服务器的成功响应可能会延迟足够的时间以使截止日期到期
5：NotFound：表示找不到某个请求的实体（例如文件或目录）
6：AlreadyExists：表示尝试创建实体失败，因为已经存在
7：PermissionDenied：表示调用者没有执行指定操作的权限。它不能用于因耗尽某些资源而引起的拒绝（使用ResourceExhausted代替这些错误）。如果调用者无法识别，则不能使用它（使用Unauthenticated代替这些错误）
8：ResourceExhausted：表示某些资源已耗尽，可能是每个用户的配额，或者整个文件系统空间不足
9：FailedPrecondition：表示操作被拒绝，因为系统不处于操作执行所需的状态。
10：Aborted：表示操作被中止，通常是由于并发问题（如序列器检查失败，事务异常终止等）造成的。请参阅上面的试金石测试以确定FailedPrecondition，Aborted和Unavailable之间的差异
11：OutOfRange：表示操作尝试超过有效范围。
12：Unimplemented：该方法未实现
13：Internal： 意味着底层系统预期的一些不变量已被打破。 如果你看到其中的一个错误，那么事情就会非常糟糕
14：Unavailable：内部Grpc服务不可用，请求不到
15：DataLoss：指示不可恢复的数据丢失或损坏
16：Unauthenticated：表示请求没有有效的操作认证凭证

```

## Grpc问题分析
> grpc调试可以开启dubug模式即可！
- Received unexpected EOS on DATA frame from server
```
grpc 同步阻塞模式下 DATA帧设置为is_end_stream=true
PS:
EOS（end-of-stream） 会在最后的 DATA frame 里面带上了 END_STREAM 这个 flag。用来表示 stream 不会在发送任何数据，可以关闭了
```
- INTERNAL: Encountered end-of-stream mid-frame
```
DATA帧上传输的数据不完整导致！
```