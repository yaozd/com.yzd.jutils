## Spring Boot Controller层测试
- [Spring Boot Controller层测试](https://blog.csdn.net/linsongbin1/article/details/100591097)

### *junit*
```
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.1.1</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.1.1</version>
    <scope>test</scope>
</dependency>

```
###　eg:HelloControllerTest
```
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.MOCK,classes = TestApplication.class)
@AutoConfigureMockMvc
public class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("测试controller post方法")
    void helloCreate() throws Exception {
        HelloCreateReq req = new HelloCreateReq();
        req.setMsg("test hello create");
        req.setUserId(123);
        MvcResult mvcResult = mockMvc.
                perform(
                        post("/hello/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(req)))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
```