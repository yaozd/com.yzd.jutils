package com.yzd.jutils.manifestExt;

import com.yzd.jutils.print.PrintUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * Created by zd.yao on 2017/12/7.
 */
public class _MainTest {
    @Test
    public void example() {
/*        <!-- 时间戳插件 start -->
        <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <configuration>
        <archive>
        <manifestEntries>
        <Timestamp>${maven.build.timestamp}</Timestamp>
        </manifestEntries>
        </archive>
        </configuration>
        </plugin>
        <!-- 时间戳插件 end -->*/
        //主要用途在于dubbo发版时间与发版的版本号
        String packageTimestamp = getTimestamp();
        packageTimestamp = addPackageTimestamp(packageTimestamp);
        PrintUtil.outLn("项目打包时间截--timestamp:" + packageTimestamp);
    }

    //增加dubbo打包的版本号
    private static String addPackageTimestamp(String packageTimestamp) {
        if (StringUtils.isBlank(packageTimestamp)) {
            packageTimestamp = "没有找到【当前非Maven打包后运行】";
        } else {
            System.setProperty("dubbo.package.timestamp", packageTimestamp);
        }
        return packageTimestamp;
    }

    private static String getTimestamp() {
        URLClassLoader cl = (URLClassLoader) new MyResources().getClass().getClassLoader();
        URL url = cl.findResource("META-INF/MANIFEST.MF");
        if (url == null) {
            return null;
        }
        InputStream is = null;
        try {
            is = url.openStream();
            Manifest manifest = new Manifest(is);
            Attributes attr = manifest.getMainAttributes();
            String value = attr.getValue("Timestamp");
            if (StringUtils.isBlank(value)) {
                return null;
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            return new SimpleDateFormat("yyMMddHHmmss").format(df.parse(value));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class MyResources {
    }
}
