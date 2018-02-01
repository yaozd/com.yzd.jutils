package com.yzd.jutils.httpExt;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;


import java.net.URI;

//参考：ranong项目总结-HttpClient-HttpDelete支持HttpEntity
//http://blog.csdn.net/minicto/article/details/55103739

/**
 * 依赖jar包
 import org.apache.http.annotation.NotThreadSafe;
 <dependency>
 <groupId>org.apache.httpcomponents</groupId>
 <artifactId>httpcore</artifactId>
 <version>4.4.3</version>
 </dependency>
 */
@NotThreadSafe
public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";
    @Override
    public String getMethod() { return METHOD_NAME; }

    public HttpDeleteWithBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }
    public HttpDeleteWithBody(final URI uri) {
        super();
        setURI(uri);
    }
    public HttpDeleteWithBody() { super(); }
}
