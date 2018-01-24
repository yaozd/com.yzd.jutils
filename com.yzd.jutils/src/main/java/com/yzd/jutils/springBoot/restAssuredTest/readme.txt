REST Assured：为REST/HTTP服务提供方便测试的Java DSL
http://hao.jobbole.com/rest-assured/
 //参考：
 //REST Assured：为REST/HTTP服务提供方便测试的Java DSL
 //http://hao.jobbole.com/rest-assured/
 //使用REST-Assured对API接口进行自动化测试
 //http://blog.csdn.net/u012050416/article/details/50674612
 //使用 Rest-Assured 测试 REST API
 //https://www.ibm.com/developerworks/cn/java/j-lo-rest-assured/
Java , REST Assured , 测试工具
本资源由 伯乐在线 - 连乐 整理
在Ruby和Groovy这样的动态语言中进行REST服务测试和校验一直都是非常困难的。REST Assured 让这些工作在Java中可以轻松完成。

rest_assured

示例

测试一个GET 请求方法，并校验Json或者XML响应。

get("/lotto").then().assertThat().body("lotto.lottoId", equalTo(5));
1
get("/lotto").then().assertThat().body("lotto.lottoId", equalTo(5));
获取和验证所有赢家的ids：


get("/lotto").then().assertThat().body("lotto.winners.winnerId", hasItems(23, 54));
1
get("/lotto").then().assertThat().body("lotto.winners.winnerId", hasItems(23, 54));
使用参数：


given().
    param("key1", "value1").
    param("key2", "value2").
when().
    post("/somewhere").
then().
    body(containsString("OK"));

given().
    param("key1", "value1").
    param("key2", "value2").
when().
    post("/somewhere").
then().
    body(containsString("OK"));
使用X-Path（仅限XML解析）：


given().
    parameters("firstName", "John", "lastName", "Doe").
when().
    post("/greetMe").
then().
    body(hasXPath("/greeting/firstName[text()='John']")).

given().
    parameters("firstName", "John", "lastName", "Doe").
when().
    post("/greetMe").
then().
    body(hasXPath("/greeting/firstName[text()='John']")).
需要验证？没问题，REST Assured提供了多种校验机制：


given().auth().basic(username, password).when().get("/secured").then().statusCode(200);
1
given().auth().basic(username, password).when().get("/secured").then().statusCode(200);
获取响应，并解析：


// Example with JsonPath
String json = get("/lotto").asString()
List winnderIds = from(json).get("lotto.winners.winnerId");

// Example with XmlPath
String xml = post("/shopping").andReturn().body().asString()
Node category = from(xml).get("shopping.category[0]");

// Example with JsonPath
String json = get("/lotto").asString()
List winnderIds = from(json).get("lotto.winners.winnerId");

// Example with XmlPath
String xml = post("/shopping").andReturn().body().asString()
Node category = from(xml).get("shopping.category[0]");
REST Assured 提供了对POST、GET、PUT、DELETE、OPTIONS、PATCH和HEAD的HTTP方法支持，还包括特殊校验，比如参数、headers、cookies和body等。

开发资源

官网
下载
使用指南
Javadoc
Rest Assured Javadoc
Rest Assured Mock Mvc Javadoc
XmlPath Javadoc
JsonPath Javadoc
发布说明
FAQ
技术支持

加入我们 Google group.

链接

修改日志
REST Assured在Ohloh
技术支持邮件