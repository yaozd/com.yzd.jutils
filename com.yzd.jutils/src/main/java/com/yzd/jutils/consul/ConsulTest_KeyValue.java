package com.yzd.jutils.consul;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.yzd.jutils.fastjson.FastJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * 参考：
 * consul 配置---K/V存储及ACL
 * https://www.2cto.com/kf/201608/543144.html
 * consul key/value
 * https://www.cnblogs.com/java-zhao/p/5381892.html
 * 附1 consul常用命令+常用选项
 * https://www.cnblogs.com/java-zhao/p/5378876.html
 * Consul客户端（orbitz 与ecwid）API介绍
 * https://blog.csdn.net/u012129558/article/details/84029365
 */
@Slf4j
public class ConsulTest_KeyValue {

    /**
     * 使用场景：
     * Nginx+upstram+consul
     * KEY/VALUE+SERVICE服务发现
     */
    Consul client;

    /**
     * 初始化.
     */
    @Before
    public void init() {

        //client = Consul.builder().withHostAndPort(HostAndPort.fromParts("xx.xx.xx.xx", 8500)).build();
        client = Consul.builder().withHostAndPort(HostAndPort.fromParts("192.168.1.238", 8500)).build();
        //  catalogClient = client.catalogClient();
    }

    /**
     * KEY的组成：统一前缀（upstreams）/服务名（test2）/IP地址（192.168.1.239）：端口（2225）
     */
    private static final String key="/upstreams/test2/192.168.1.239:2225";

    /**
     * 保存
     */
    @Test
    public void testPut() {
        KeyValueClient kvClient = client.keyValueClient();
        kvClient.putValue(key, FastJsonUtil.serialize("value"));
        String value = kvClient.getValueAsString(key).get();
        log.info(value);
    }

    /**
     * 读取
     */
    @Test
    public void testGet() {
        KeyValueClient kvClient = client.keyValueClient();
        String value = kvClient.getValueAsString(key).get();
        log.info(value);
    }

    /**
     * 删除
     */
    @Test
    public void testDelete() {
        KeyValueClient kvClient = client.keyValueClient();
        kvClient.deleteKey(key);
        log.info("delete");
    }
}
