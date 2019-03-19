> 极光推送参考
- [极光推送Springboot微服务搭建教程](https://blog.csdn.net/x541211190/article/details/81123829)
```
/**
 * 极光推送服务
 * 
 * @author 葫芦娃
 *
 */
@Service("jpushService")
public class JpushService {
    private static final Logger LOG = LoggerFactory.getLogger(JpushService.class);
    @Resource
    JpushConfig jpushConfig;// 注入配置信息

    /**
     * 发送自定义推送，由APP端拦截信息后再决定是否创建通知(目前APP用此种方式)
     * 
     * @param title
     *            App通知栏标题
     * @param content
     *            App通知栏内容（为了单行显示全，尽量保持在22个汉字以下）
     * @param extrasMap
     *            额外推送信息（不会显示在通知栏，传递数据用）
     * @param alias
     *            别名数组，设定哪些用户手机能接收信息（为空则所有用户都推送）
     * @return PushResult
     */
    public PushResult sendCustomPush(String title, String content, Map<String, String> extrasMap, String... alias) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.valueOf(jpushConfig.getLiveTime()));
        // 使用NativeHttpClient网络客户端，连接网络的方式，不提供回调函数
        JPushClient jpushClient = new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppkey(), null,
                clientConfig);
        // 设置为消息推送方式为仅推送消息，不创建通知栏提醒
        PushPayload payload = buildCustomPushPayload(title, content, extrasMap, alias);
        PushResult result = null;
        try {
            result = jpushClient.sendPush(payload);
            LOG.info("极光推送结果 - " + result+",接收推送的别名列表:" + String.join(",", alias));
        } catch (APIConnectionException e) {
            LOG.error("极光推送连接错误，请稍后重试 ", e);
            LOG.error("Sendno: " + payload.getSendno());
        } catch (APIRequestException e) {
            LOG.error("极光服务器响应出错，请修复！ ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.info("以下存在不能识别的别名: " + String.join(",", alias));
            LOG.error("Sendno: " + payload.getSendno());
        }
        return result;
    }

    /**
     * 原生方式推送
     * 
     * @param title
     *            App通知栏标题
     * @param content
     *            App通知栏内容（为了单行显示全，尽量保持在22个汉字以下）
     * @param extrasMap
     *            额外推送信息（不会显示在通知栏，传递数据用）
     * @param alias
     *            别名数组，设定哪些用户手机能接收信息（为空则所有用户都推送）
     * 
     */
    public PushResult sendPush(String title, String content, Map<String, String> extrasMap, String... alias) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.valueOf(jpushConfig.getLiveTime()));
        // 使用NativeHttpClient网络客户端，连接网络的方式，不提供回调函数
        JPushClient jpushClient = new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppkey(), null,
                clientConfig);
        // 设置推送方式
        PushPayload payload = buildPushPayload(title, content, extrasMap, alias);
        PushResult result = null;
        try {
            result = jpushClient.sendPush(payload);
            LOG.info("极光推送结果 - " + result);
        } catch (APIConnectionException e) {
            LOG.error("极光推送连接错误，请稍后重试 ", e);
            LOG.error("Sendno: " + payload.getSendno());
        } catch (APIRequestException e) {
            LOG.error("极光服务器响应出错，请修复！ ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.info("以下存在不能识别别名: " + alias);
            LOG.error("Sendno: " + payload.getSendno());
        }
        return result;
    }

    /**
     * 异步请求推送方式
     * 
     * @param title
     *            通知栏标题
     * @param content
     *            通知栏内容（为了单行显示全，尽量保持在22个汉字以下）
     * @param extrasMap
     *            额外推送信息（不会显示在通知栏，传递数据用）
     * @param alias
     *            需接收的用户别名数组（为空则所有用户都推送）
     * 
     * @see 使用NettyHttpClient,异步接口发送请求，通过回调函数可以获取推送成功与否情况
     */
    public void sendPushWithCallback(String title, String content, Map<String, String> extrasMap, String... alias) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.valueOf(jpushConfig.getLiveTime()));
        String host = (String) clientConfig.get(ClientConfig.PUSH_HOST_NAME);
        NettyHttpClient client = new NettyHttpClient(
                ServiceHelper.getBasicAuthorization(jpushConfig.getAppkey(), jpushConfig.getMasterSecret()), null,
                clientConfig);
        try {
            URI uri = new URI(host + clientConfig.get(ClientConfig.PUSH_PATH));
            PushPayload payload = buildPushPayload(title, content, extrasMap, alias);
            client.sendRequest(HttpMethod.POST, payload.toString(), uri, new NettyHttpClient.BaseCallback() {
                @Override
                public void onSucceed(ResponseWrapper responseWrapper) {
                    if (200 == responseWrapper.responseCode) {
                        LOG.info("极光推送成功");
                    } else {
                        LOG.info("极光推送失败，返回结果: " + responseWrapper.responseContent);
                    }
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            // 需要手动关闭Netty请求进程,否则会一直保留
            client.close();
        }

    }

    /**
     * 构建Android和IOS的推送通知对象
     * 
     * @return PushPayload
     */
    private PushPayload buildPushPayload(String title, String content, Map<String, String> extrasMap, String... alias) {
        if (extrasMap == null || extrasMap.isEmpty()) {
            extrasMap = new HashMap<String, String>();
        }
        // 批量删除数组中空元素
        String[] newAlias = removeArrayEmptyElement(alias);
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
                // 别名为空，全员推送；别名不为空，按别名推送
                .setAudience((null == newAlias || newAlias.length == 0) ? Audience.all() : Audience.alias(alias))
                .setNotification(Notification.newBuilder().setAlert(content)
                        .addPlatformNotification(
                                AndroidNotification.newBuilder().setTitle(title).addExtras(extrasMap).build())
                        .addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtras(extrasMap).build())
                        .build())
                .build();
    }

    /**
     * 构建Android和IOS的自定义消息的推送通知对象
     * 
     * @return PushPayload
     */
    private PushPayload buildCustomPushPayload(String title, String content, Map<String, String> extrasMap,
            String... alias) {
        // 批量删除数组中空元素
        String[] newAlias = removeArrayEmptyElement(alias);
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
                .setAudience((null == newAlias || newAlias.length == 0) ? Audience.all() : Audience.alias(alias))
                .setMessage(Message.newBuilder().setTitle(title).setMsgContent(content).addExtras(extrasMap).build())
                .build();
    }

    /**
     * 查询记录推送成功条数（暂未使用）
     * 
     * @param msg_id
     *            在推送返回结果PushResult中保存
     */
    public void countPush(String msg_id) {
        JPushClient jpushClient = new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppkey());
        try {
            ReceivedsResult result = jpushClient.getReportReceiveds(msg_id);
            Received received = result.received_list.get(0);
            LOG.debug("Android接受信息:" + received.android_received + "\n IOS端接受信息:" + received.ios_apns_sent);
            LOG.debug("极光推送返回结果 - " + result);
        } catch (APIConnectionException e) {
            LOG.error("极光推送连接错误，请稍后重试", e);
        } catch (APIRequestException e) {
            LOG.error("检查错误，并修复推送请求", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     * 删除别名中的空元素（需删除如：null,""," "）
     * 
     * @param strArray
     * @return String[]
     */
    private String[] removeArrayEmptyElement(String... strArray) {
        if (null == strArray || strArray.length == 0) {
            return null;
        }
        List<String> tempList = Arrays.asList(strArray);
        List<String> strList = new ArrayList<String>();
        Iterator<String> iterator = tempList.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            // 消除空格后再做比较
            if (null != str && !"".equals(str.trim())) {
                strList.add(str);
            }
        }
        // 若仅输入"",则会将数组长度置为0
        String[] newStrArray = strList.toArray(new String[strList.size()]);
        return newStrArray;
    }
}
--------------------- 
作者：大脑补丁 
来源：CSDN 
原文：https://blog.csdn.net/x541211190/article/details/81123829 
版权声明：本文为博主原创文章，转载请附上博文链接！
```