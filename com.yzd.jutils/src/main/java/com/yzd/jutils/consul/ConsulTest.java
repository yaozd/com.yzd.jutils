package com.yzd.jutils.consul;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import com.orbitz.consul.model.health.Service;
import com.orbitz.consul.model.health.ServiceHealth;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/***
 *
 *
 * Created by yzd on 2018/9/5 13:10.
 */

public class ConsulTest {
    //<dependency>
    //    <groupId>com.orbitz.consul</groupId>
    //    <artifactId>consul-client</artifactId>
    //    <version>1.2.3</version>
    // </dependency>
    Consul client;

    /**
     * 初始化.
     */
    @Before
    public void init() {

        //client = Consul.builder().withHostAndPort(HostAndPort.fromParts("xx.xx.xx.xx", 8500)).build();
        client = Consul.builder().withHostAndPort(HostAndPort.fromParts("127.0.0.1", 8500)).build();
        //  catalogClient = client.catalogClient();
    }

    @Test
    public void queryAll() {
        Map<String, Service> services = client.agentClient().getServices();
        for (Map.Entry<String, Service> entry : services.entrySet()) {
            System.out.println("key:" + entry.getKey());
            System.out.println("value:" + entry.getValue().toString());
        }

    }


    @Test
    public void testDelete() {
        client.agentClient().deregister("etcd1");
    }

    /***
     * 最终确定的写法通过tcp方式来进行检查
     * //如果在服务注册的时候使用的是http方式，则getResponse中会带有大量的响应信息-output。使用传输的数据变大
     * //因此推荐使用TCP方式进行服务的检查
     * //目前的功能主要是consul作为prometheus的注册发现,jmx_exporter输出的信息比较多,所以推荐tcp注册方式
     * //https://github.com/prometheus/jmx_exporter
     */
    @Test
    public void testAdd_finall() {
        String serviceName = "prometheus-etcd";
        //serviceId相当记录的唯一标识，如果id相同则会覆盖。
        String serviceId = "etcd4";
        //// Check in with Consul (serviceId required only).
        //// Client will prepend "service:" for service level checks.
        //// Note that you need to continually check in before the TTL expires, otherwise your service's state will be marked as "critical".
        // ttl(10L)过期时间为10秒
        //Registration.RegCheck single = Registration.RegCheck.tcp("127.0.0.1:18080", 5).ttl(13L);
        //调整为ttl(13L);无ttl的方式
        //TCP+ Interval 的check 方式
        Registration.RegCheck single = Registration.RegCheck.tcp("127.0.0.1:18080", 5);
        Registration service = ImmutableRegistration.builder()
                .id(serviceId)
                .name(serviceName)
                .address("127.0.0.1")
                .port(18080)
                .check(single) // registers with a TTL of 3 seconds
                .tags(Collections.singletonList("tag1"))
                .meta(Collections.singletonMap("version", "1.0"))
                .build();
        client.agentClient().register(service);
    }


    @Test
    public void testAdd1() {
        String serviceName = "prometheus-etcd";
        //serviceId相当记录的唯一标识，如果id相同则会覆盖。
        String serviceId = "etcd";
        //Registration.RegCheck single = Registration.RegCheck.http("http://127.0.0.1:2379/metrics", 20);
        Registration.RegCheck single = Registration.RegCheck.http("http://127.0.0.1:18080/metrics", 20);//byArvin 不推荐使用HTTP方式
        //Registration.RegCheck single = Registration.RegCheck.tcp("127.0.0.1:18080", 20,3000).ttl(23L);//byArvin 推荐使用TCP方式
        Registration reg = ImmutableRegistration.builder()
                .check(single)
                .addTags("prometheus-target")
                .address("127.0.0.1")
                .port(18080)
                .name(serviceName)
                .id(serviceId)
                .build();
        client.agentClient().register(reg);
    }
    @Test
    public void testAdd2() {
        String serviceName = "prometheus-etcd";
        String serviceId = "etcd1";
        Registration.RegCheck single = Registration.RegCheck.http("http://127.0.0.1:18080/metrics", 20);
        Registration reg = ImmutableRegistration.builder()
                .check(single)
                .addTags("prometheus-target")
                .address("127.0.0.1")
                .port(18080)
                .name(serviceName)
                .id(serviceId)
                .build();
        client.agentClient().register(reg);
    }
    @Test
    public void testAdd3() {
        String serviceName = "prometheus-etcd";
        //serviceId相当记录的唯一标识，如果id相同则会覆盖。
        String serviceId = "etcd3";
        Registration.RegCheck single = Registration.RegCheck.http("http://127.0.0.1:18080/metrics", 20);
        Registration service = ImmutableRegistration.builder()
                .id(serviceId)
                .name(serviceName)
                .address("127.0.0.1")
                .port(18080)
                .check(single) // registers with a TTL of 3 seconds
                .tags(Collections.singletonList("tag1"))
                .meta(Collections.singletonMap("version", "1.0"))
                .build();
        client.agentClient().register(service);
    }
    @Test
    public void testAdd4() {
        String serviceName = "prometheus-etcd";
        //serviceId相当记录的唯一标识，如果id相同则会覆盖。
        String serviceId = "etcd4";
        //// Check in with Consul (serviceId required only).
        //// Client will prepend "service:" for service level checks.
        //// Note that you need to continually check in before the TTL expires, otherwise your service's state will be marked as "critical".
        // ttl(10L)过期时间为10秒
        //Registration.RegCheck single = Registration.RegCheck.tcp("127.0.0.1:18080", 5).ttl(13L);
        //调整为ttl(13L);无ttl的方式
        //TCP+ Interval 的check 方式
        Registration.RegCheck single = Registration.RegCheck.tcp("127.0.0.1:18080", 5);
        Registration service = ImmutableRegistration.builder()
                .id(serviceId)
                .name(serviceName)
                .address("127.0.0.1")
                .port(18080)
                .check(single) // registers with a TTL of 3 seconds
                .tags(Collections.singletonList("tag1"))
                .meta(Collections.singletonMap("version", "1.0"))
                .build();
        client.agentClient().register(service);
    }

    @Test
    public void testAdd5() throws NotRegisteredException {
        String serviceName = "prometheus-etcd";
        //serviceId相当记录的唯一标识，如果id相同则会覆盖。
        String serviceId = "etcd4";
        //client.agentClient().pass(serviceId);
    }
    /***
     * 寻找有效的健康服务
     */
    @Test
    public void findAvailableHealthyServices(){
        HealthClient healthClient = client.healthClient();
        // Discover only "passing" nodes
        //List<ServiceHealth> nodes = healthClient.getHealthyServiceInstances("prometheus-etcd").getResponse();
        //如果在服务注册的时候使用的是http方式，则getResponse中会带有大量的响应信息-output。使用传输的数据变大
        //因此推荐使用TCP方式进行服务的检查
        //目前的功能主要是consul作为prometheus的注册发现,jmx_exporter输出的信息比较多,所以推荐tcp注册方式
        //https://github.com/prometheus/jmx_exporter
        String serviceName = "prometheus-etcd";
        List<ServiceHealth> nodes = healthClient.getHealthyServiceInstances(serviceName).getResponse();
        for (ServiceHealth item:nodes) {
            System.out.println(item.getService());
            System.out.println(item);
        }
    }



}
