package com.yzd.jutils.springBoot.mockMvcTest;

/*
import com.yzd.h5.example.controller.Other01Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringBootStudentCacheApplicationTests {

    //参考：
    //Junit学习笔记之五：MockMVC
    //http://blog.csdn.net/xiao_xuwen/article/details/52890730
    //Spring Boot缓存实战 Redis
    //http://blog.csdn.net/xiaolyuh123/article/details/77184508

    @Test
    public void contextLoads() {
    }

    private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。

    //@Autowired
    //private WebApplicationContext wac; // 注入WebApplicationContext
    @Autowired
    private Other01Controller t; // 注入WebApplicationContext

//    @Autowired
//    private MockHttpSession session;// 注入模拟的http session
//
//    @Autowired
//    private MockHttpServletRequest request;// 注入模拟的http request\

    @Before // 在测试开始前初始化工作
    public void setup() {
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.mockMvc =MockMvcBuilders.standaloneSetup(t).build();
    }

    @Test
    public void testAble() throws Exception {
        for (int i = 0; i < 2; i++) {
            MvcResult result = mockMvc.perform(post("/other01/doSelectById").param("id", "2"))
                    .andExpect(status().isOk())// 模拟向testRest发送get请求
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;
                    // charset=UTF-8
                    .andReturn();// 返回执行请求的结果
            System.out.println(result.getResponse().getContentAsString());
        }
    }

}
*/
