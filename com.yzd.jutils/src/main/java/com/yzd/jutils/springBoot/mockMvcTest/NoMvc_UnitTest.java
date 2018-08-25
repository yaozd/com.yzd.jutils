package com.yzd.jutils.springBoot.mockMvcTest;

/**
 * Created by zd.yao on 2018/8/25.
 */
public class NoMvc_UnitTest {

    //单元测试如果当前测试方法，是非web测试，则可以关闭web环境：SpringBootTest.WebEnvironment.NONE

    /***
     * @RunWith(SpringRunner.class)
    //@SpringBootTest
    //@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    //单元测试如果当前测试方法，是非web测试，则可以关闭web环境：SpringBootTest.WebEnvironment.NONE
     @SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE, classes = Application.class)
     public class ParseTool_UnitTest {

     @Autowired
     CrawlerServiceImpl_iShadow crawlerServiceImplIShadow;

     @Test
     public void parseLink_Test() throws Exception {
     String linkTxt="";
     crawlerServiceImplIShadow.parseLink(linkTxt);
     }
     @Test
     public void parseURL() throws IOException, NotFoundException {
     String url = "https://free.yitianjianss.com/img/qrcode_image/293/c797e96ed6969ab4bc24726104fe12ea.png";
     crawlerServiceImplIShadow.parseURL(url);
     }
     }

     */
}
