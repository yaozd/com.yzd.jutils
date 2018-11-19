
#### [SpringBoot配置(配置文件占位符)](https://blog.csdn.net/baidu_36216018/article/details/79597923)
#### [详解Spring Boot加载properties和yml配置文件](https://www.jb51.net/article/111063.htm)
#### [SpringBoot配置(@PropertySource @ImportResource @Bean 注解)](https://blog.csdn.net/baidu_36216018/article/details/79595516)
#### [SpringBoot四种读取properties文件的方式](https://blog.csdn.net/thc1987/article/details/78789426)

```
使用@Value获取配置文件
//启动时加入参数 -Dcms.static.path=E:\opc_workspace\opc\conf
@Value("${cms.static.path}")
private String cmsStaticPath;

```

```
前言
在项目开发中经常会用到配置文件，配置文件的存在解决了很大一份重复的工作。今天就分享四种在Springboot中获取配置文件的方式。

注：前三种测试配置文件为springboot默认的application.properties文件

#######################方式一#########################
com.zyd.type3=Springboot - @ConfigurationProperties
com.zyd.title3=使用@ConfigurationProperties获取配置文件
#map
com.zyd.login[username]=zhangdeshuai
com.zyd.login[password]=zhenshuai
com.zyd.login[callback]=http://www.flyat.cc
#list
com.zyd.urls[0]=http://ztool.cc
com.zyd.urls[1]=http://ztool.cc/format/js
com.zyd.urls[2]=http://ztool.cc/str2image
com.zyd.urls[3]=http://ztool.cc/json2Entity
com.zyd.urls[4]=http://ztool.cc/ua

#######################方式二#########################
com.zyd.type=Springboot - @Value
com.zyd.title=使用@Value获取配置文件

#######################方式三#########################
com.zyd.type2=Springboot - Environment
com.zyd.title2=使用Environment获取配置文件

一、@ConfigurationProperties方式
自定义配置类：PropertiesConfig.java

package com.zyd.property.config;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 对应上方配置文件中的第一段配置
 * @author <a href="mailto:yadong.zhang0415@gmail.com">yadong.zhang</a>
 * @date 2017年6月1日 下午4:34:18 
 * @version V1.0
 * @since JDK ： 1.7
 */
@Component
@ConfigurationProperties(prefix = "com.zyd")
// PropertySource默认取application.properties
// @PropertySource(value = "config.properties")
public class PropertiesConfig {

    public String type3;
    public String title3;
    public Map<String, String> login = new HashMap<String, String>();
    public List<String> urls = new ArrayList<>();

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getTitle3() {
        try {
            return new String(title3.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    public Map<String, String> getLogin() {
        return login;
    }

    public void setLogin(Map<String, String> login) {
        this.login = login;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

} 

程序启动类：Applaction.java

package com.zyd.property;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zyd.property.config.PropertiesConfig;

/**
 * @author <a href="mailto:yadong.zhang0415@gmail.com">yadong.zhang</a>
 * @date 2017年6月1日 下午3:49:30 
 * @version V1.0
 * @since JDK ： 1.7
 */
@SpringBootApplication
@RestController
public class Applaction {

    @Autowired
    private PropertiesConfig propertiesConfig;

    /**
     * 
     * 第一种方式：使用`@ConfigurationProperties`注解将配置文件属性注入到配置对象类中
     * 
     * @author zyd
     * @throws UnsupportedEncodingException
     * @since JDK 1.7
     */
    @RequestMapping("/config")
    public Map<String, Object> configurationProperties() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", propertiesConfig.getType3());
        map.put("title", propertiesConfig.getTitle3());
        map.put("login", propertiesConfig.getLogin());
        map.put("urls", propertiesConfig.getUrls());
        return map;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(Applaction.class);
        application.run(args);
    }
}

访问结果：

{"title":"使用@ConfigurationProperties获取配置文件","urls":["http://ztool.cc","http://ztool.cc/format/js","http://ztool.cc/str2image","http://ztool.cc/json2Entity","http://ztool.cc/ua"],"login":{"username":"zhangdeshuai","callback":"http://www.flyat.cc","password":"zhenshuai"},"type":"Springboot - @ConfigurationProperties"}
1
二、使用@Value注解方式
程序启动类：Applaction.java


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:yadong.zhang0415@gmail.com">yadong.zhang</a>
 * @date 2017年6月1日 下午3:49:30 
 * @version V1.0
 * @since JDK ： 1.7
 */
@SpringBootApplication
@RestController
public class Applaction {

    @Value("${com.zyd.type}")
    private String type;

    @Value("${com.zyd.title}")
    private String title;

    /**
     * 
     * 第二种方式：使用`@Value("${propertyName}")`注解
     * 
     * @author zyd
     * @throws UnsupportedEncodingException
     * @since JDK 1.7
     */
    @RequestMapping("/value")
    public Map<String, Object> value() throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        // *.properties文件中的中文默认以ISO-8859-1方式编码，因此需要对中文内容进行重新编码
        map.put("title", new String(title.getBytes("ISO-8859-1"), "UTF-8"));
        return map;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(Applaction.class);
        application.run(args);
    }
}

访问结果：

{"title":"使用@Value获取配置文件","type":"Springboot - @Value"}
1
三、使用Environment
程序启动类：Applaction.java

package com.zyd.property;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:yadong.zhang0415@gmail.com">yadong.zhang</a>
 * @date 2017年6月1日 下午3:49:30
 * @version V1.0
 * @since JDK ： 1.7
 */
@SpringBootApplication
@RestController
public class Applaction {

    @Autowired
    private Environment env;

    /**
     * 
     * 第三种方式：使用`Environment`
     * 
     * @author zyd
     * @throws UnsupportedEncodingException
     * @since JDK 1.7
     */
    @RequestMapping("/env")
    public Map<String, Object> env() throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", env.getProperty("com.zyd.type2"));
        map.put("title", new String(env.getProperty("com.zyd.title2").getBytes("ISO-8859-1"), "UTF-8"));
        return map;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(Applaction.class);
        application.run(args);
    }
}

访问结果：

{"title":"使用Environment获取配置文件","type":"Springboot - Environment"}
1
四、使用PropertiesLoaderUtils
app-config.properties

#### 通过注册监听器(`Listeners`) + `PropertiesLoaderUtils`的方式
com.zyd.type=Springboot - Listeners
com.zyd.title=使用Listeners + PropertiesLoaderUtils获取配置文件
com.zyd.name=zyd
com.zyd.address=Beijing
com.zyd.company=in

PropertiesListener.java 用来初始化加载配置文件

package com.zyd.property.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import com.zyd.property.config.PropertiesListenerConfig;

/**
 * 配置文件监听器，用来加载自定义配置文件
 * 
 * @author <a href="mailto:yadong.zhang0415@gmail.com">yadong.zhang</a>
 * @date 2017年6月1日 下午3:38:25 
 * @version V1.0
 * @since JDK ： 1.7
 */
public class PropertiesListener implements ApplicationListener<ApplicationStartedEvent> {

    private String propertyFileName;

    public PropertiesListener(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        PropertiesListenerConfig.loadAllProperties(propertyFileName);
    }
}

PropertiesListenerConfig.java 加载配置文件内容

package com.zyd.property.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 第四种方式：PropertiesLoaderUtils
 * 
 * @author <a href="mailto:yadong.zhang0415@gmail.com">yadong.zhang</a>
 * @date 2017年6月1日 下午3:32:37
 * @version V1.0
 * @since JDK ： 1.7
 */
public class PropertiesListenerConfig {
    public static Map<String, String> propertiesMap = new HashMap<>();

    private static void processProperties(Properties props) throws BeansException {
        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            try {
                // PropertiesLoaderUtils的默认编码是ISO-8859-1,在这里转码一下
                propertiesMap.put(keyStr, new String(props.getProperty(keyStr).getBytes("ISO-8859-1"), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (java.lang.Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadAllProperties(String propertyFileName) {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
            processProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String name) {
        return propertiesMap.get(name).toString();
    }

    public static Map<String, String> getAllProperty() {
        return propertiesMap;
    }
}

Applaction.java 启动类

package com.zyd.property;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zyd.property.config.PropertiesListenerConfig;
import com.zyd.property.listener.PropertiesListener;

/**
 * @author <a href="mailto:yadong.zhang0415@gmail.com">yadong.zhang</a>
 * @date 2017年6月1日 下午3:49:30 
 * @version V1.0
 * @since JDK ： 1.7
 */
@SpringBootApplication
@RestController
public class Applaction {
    /**
     * 
     * 第四种方式：通过注册监听器(`Listeners`) + `PropertiesLoaderUtils`的方式
     * 
     * @author zyd
     * @throws UnsupportedEncodingException
     * @since JDK 1.7
     */
    @RequestMapping("/listener")
    public Map<String, Object> listener() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(PropertiesListenerConfig.getAllProperty());
        return map;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(Applaction.class);
        // 第四种方式：注册监听器
        application.addListeners(new PropertiesListener("app-config.properties"));
        application.run(args);
    }
}

访问结果：

{"com.zyd.name":"zyd","com.zyd.address":"Beijing","com.zyd.title":"使用Listeners + PropertiesLoaderUtils获取配置文件","com.zyd.type":"Springboot - Listeners","com.zyd.company":"in"}
1
作者： 慕冬雪 
```