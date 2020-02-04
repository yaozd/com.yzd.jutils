package com.yzd.jutils.httpExt;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author: yaozhendong
 * @create: 2019-12-02 10:20
 **/

public class UriUtil {
    private UriUtil() {
    }

    public static URI create(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getHost(URI uri) {

        return uri.getHost();
    }

    public static int getPort(URI uri) {
        if (uri.getPort() != -1) {
            return uri.getPort();
        }
        if (Protocol.http.name.equals(uri.getScheme())) {
            return 80;
        }
        if (Protocol.https.name.equals(uri.getScheme())) {
            return 443;
        }
        throw new RuntimeException("port= -1");
    }

    enum Protocol {
        http("http"),
        https("https");

        private final String name;

        Protocol(String name) {
            this.name = name;
        }
    }
}
