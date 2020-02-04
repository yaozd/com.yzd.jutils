package com.yzd.jutils.feignExt;

import com.yzd.jutils.fastjson.FastJsonUtil;
import feign.*;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.junit.Test;

/**
 * Created by zd.yao on 2018/9/13.
 */
public class _FeignTest {

    //- [Feign真正正确的使用方法](https://www.jianshu.com/p/3d597e9d2d67)
    //- [Feign基础教程](https://blog.csdn.net/u010862794/article/details/73649616)
    //- [feign官网示例详解](http://p.codekk.com/detail/Android/OpenFeign/feign)
    @Test
    public void t1() {
        Wikipedia wikipedia = Feign.builder()
                .target(Wikipedia.class, "https://en.wikipedia.org");
        String result = wikipedia.search("1");
        System.out.println(result);
    }

    public static interface Wikipedia {

        @RequestLine("GET /w/api.php?action=query&continue=&generator=search&prop=info&format=json&gsrsearch={search}")
        String search(@Param("search") String search);
    }

    //===========================================================
    @Test
    public void t2() {
        LoginClient client = Feign.builder()
                .encoder(new GsonEncoder())
                .logLevel(Logger.Level.FULL)
                .target(LoginClient.class, "http://www.jebao.net/");

        String result = client.login(new Credentials("denominator", "secret"), "1");
        System.out.println(result);
    }

    interface LoginClient {
        @RequestLine("POST /")
        String login(Credentials creds, @Param("search") String search);
    }

    static class Credentials {
        final String user_name;
        final String password;

        Credentials(String user_name, String password) {
            this.user_name = user_name;
            this.password = password;
        }
    }

    //===========================================================
    //@PostMapping("getBaseToken")
    //public String getBaseToken(@RequestBody GetBaseTokenForm form,String sign){return null;}
    //===========================================================
    @Test
    public void t3() {
        TokenClient client = Feign.builder()
                .encoder(new GsonEncoder())
                .logLevel(Logger.Level.FULL)
                .target(TokenClient.class, "http://localhost:8890");
        GetBaseTokenForm form = new GetBaseTokenForm("1");
        String json = FastJsonUtil.serialize(form);
        System.out.println(FastJsonUtil.serialize(form));
        String result = client.getBaseToken(form, "1");
        System.out.println(result);
    }

    interface TokenClient {
        @Headers("Content-Type: application/json")
        @RequestLine("POST /api/token/getBaseToken?sign={sign}")
        String getBaseToken(GetBaseTokenForm form, @Param("sign") String sign);
    }

    static class GetBaseTokenForm {
        public GetBaseTokenForm(String appId) {
            this.appId = appId;
        }

        private String appId;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }
    }

    //===========================================================
    @Test
    public void t4() {
        //options方法指定连接超时时长及响应超时时长，retryer方法指定重试策略,target方法绑定接口与服务端地址。返回类型为绑定的接口类型。
        RemoteService service = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(RemoteService.class, "http://127.0.0.1:8085");
    }

    public interface RemoteService {

        @Headers({"Content-Type: application/json", "Accept: application/json"})
        @RequestLine("POST /users/list")
        User getOwner(User user);
    }

    class User {
        private String id;
    }
}
