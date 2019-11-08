## 流式组件fluent-hc使用：
- [Apache下基于HttpClient的流式组件fluent-hc](https://blog.csdn.net/tengxing007/article/details/99826391)-推荐byArvin
- [HttpClient4.2 Fluent API学习](https://blog.csdn.net/vector_yi/article/details/24298629)
- [小技巧：利用HC fluent API 优雅地使用Apache HttpClient](https://blog.csdn.net/flysqrlboy/article/details/88706049)
- []()

## [fluent-hc连接池的大小](https://blog.csdn.net/tengxing007/article/details/99826391)
```
Executor执行器的CLIENT对象是从连接池中获取的HttpClient对象。采用HTTP连接池技术降低了频繁建立HTTP连接的时间开销，减少了TCP连接建立和释放时socket通信服务器端资源的浪费，同时支持更高的并发量。
Registry<ConnectionSocketFactory> sfr = RegistryBuilder.create().register("http",     PlainConnectionSocketFactory.getSocketFactory()).register("https", ssl != null ? ssl :  SSLConnectionSocketFactory.getSocketFactory()).build();
        CONNMGR = new PoolingHttpClientConnectionManager(sfr);
        //将每个路由的默认最大连接数设置为100
        CONNMGR.setDefaultMaxPerRoute(100);
        //设置最大连接数200个
        CONNMGR.setMaxTotal(200);
        CONNMGR.setValidateAfterInactivity(1000);
        CLIENT = HttpClientBuilder.create().setConnectionManager(CONNMGR).build();
————————————————
```

## HttpClient连接池复用
- [HttpClient连接池](https://jianpage.com/2019/06/05/httpclientspool/);
- [不想自己实现的话可以使用现成的带连接池的工具包，比如：fluent-hc](https://jianpage.com/2019/06/05/httpclientspool/);
- [Chapter 5. Fluent API-Response handling](https://hc.apache.org/httpcomponents-client-ga/tutorial/html/fluent.html)
    ```
    fluent facade API通常免除了用户处理连接管理和资源释放的麻烦。
    不过，在大多数情况下，这样做的代价是必须在内存中缓冲响应消息的内容强
    烈建议使用ResponseHandler进行HTTP响应处理，以避免在内存中缓冲内容。
    ```
    ```
    Document result = Request.Get("http://somehost/content")
            .execute().handleResponse(new ResponseHandler<Document>() {
    
        public Document handleResponse(final HttpResponse response) throws IOException {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                throw new HttpResponseException(
                        statusLine.getStatusCode(),
                        statusLine.getReasonPhrase());
            }
            if (entity == null) {
                throw new ClientProtocolException("Response contains no content");
            }
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
                ContentType contentType = ContentType.getOrDefault(entity);
                if (!contentType.equals(ContentType.APPLICATION_XML)) {
                    throw new ClientProtocolException("Unexpected content type:" +
                        contentType);
                }
                String charset = contentType.getCharset();
                if (charset == null) {
                    charset = HTTP.DEFAULT_CONTENT_CHARSET;
                }
                return docBuilder.parse(entity.getContent(), charset);
            } catch (ParserConfigurationException ex) {
                throw new IllegalStateException(ex);
            } catch (SAXException ex) {
                throw new ClientProtocolException("Malformed XML document", ex);
            }
        }
    
        });
    ```
## Http超时时间
- [Http超时时间之SocketTimeout](https://www.cnblogs.com/number7/p/9514040.html)
    1. connectTimeOut：指建立连接的超时时间，比较容易理解
    2. connectionRequestTimeOut：指从连接池获取到连接的超时时间，如果是非连接池的话，该参数暂时没有发现有什么用处
    3. socketTimeOut：指客户端和服务进行数据交互的时间，是指两者之间如果两个数据包之间的时间大于该时间则认为超时，而不是整个交互的整体时间，比如如果设置1秒超时，如果每隔0.8秒传输一次数据，传输10次，总共8秒，这样是不超时的。而如果任意两个数据包之间的时间超过了1秒，则超时。
    4. validateAfterInactivity ms之内没有校验,则校验entry。校验不通过则关闭并释放,继续从连接池中获取entry