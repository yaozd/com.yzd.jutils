package com.yzd.jutils.springBoot.restAssuredTest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.yzd.jutils.print.PrintUtil;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RestfullApiTest {

    //参考：
    //rest-assured接口测试框架中文文档--官方
    //https://github.com/RookieTester/rest-assured-doc
    //REST Assured：为REST/HTTP服务提供方便测试的Java DSL
    //http://hao.jobbole.com/rest-assured/
    //使用REST-Assured对API接口进行自动化测试
    //http://blog.csdn.net/u012050416/article/details/50674612
    //使用 Rest-Assured 测试 REST API
    //https://www.ibm.com/developerworks/cn/java/j-lo-rest-assured/

    @Before
    public void before() {
        RestAssured.baseURI = "http://localhost:8080/";
        //RestAssured.port = 80;
    }

    @Test
    public void t1() {
        given().
                param("x", "y").
                expect().
                statusCode(400).
                body("lotto.lottoId", equalTo(6)).
                when().
                get("/lotto");
        given().param("x", "y").and().header("z", "w").when().get("/something").then().assertThat().statusCode(200).and().body("x.y", equalTo("z"));
    }

    @Test
    //URL为http://api.douban.com/v2/book/1220562
    //判断Json中的返回信息title
    public void testGetBook() {
        //given().auth().basic("username", "password").when().get("/secured").then().statusCode(200);

        //
        ValidatableResponse resp = get("/other01/doSelectById").then();
        //比对内容
        get("/other01/doSelectById").then().body(notNullValue());
        //打印响应内容
        String body = get("/other01/doSelectById").andReturn().body().asString();
        PrintUtil.outLn(body);
        //
        Response responseOptions = get("/other01/doSelectById").thenReturn();
        responseOptions.body();
        responseOptions.andReturn().body().asString();
        //
        given().cookie("name", "xx").when().get("/xxx").then().body(notNullValue());
        given().header("name", "xx").when().get("/xxx").then().body(notNullValue());
        given().contentType("application/json").when().get("/xxx").then().body(notNullValue());
    }
}
