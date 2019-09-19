### Mybatis的示例
- hyperspace-2019-09-18-0942-[hyperspace-console-mybatis示例模板].zip-2019-09-18-0943（最新byArvin）
- SpringBoot整合Mybatis完整详细版-[SpringBoot-1.5.17.RELEASE版本-包含API代码模板]-2019-01-19-1149.zip(推荐参考byArvin-2019-01-21-1029)
- Jebao-Mybatis-Generator-示例四-[独立代码生成无包含依赖包].zip
- Jebao-Mybatis-Generator-示例三-[独立代码生成包含依赖包].zip
- ngnix-upsync-deploy-示例二-[代码生成无依赖].zip
- springboot-mybatis-demo-示例一-[实现数据库连接].zip
```
下载地址：
百度云-》软件开发java-》J-M-Mybatis的示例
```


### [如何优雅的使用mybatis](https://www.cnblogs.com/ityouknow/p/6037431.html)
```
2、application.properties 添加相关配置
mybatis.type-aliases-package=com.neo.entity

spring.datasource.driverClassName = com.mysql.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=utf-8
spring.datasource.username = root
spring.datasource.password = root
springboot会自动加载spring.datasource.*相关配置，数据源就会自动注入到sqlSessionFactory中，sqlSessionFactory会自动注入到Mapper中，对了你一切都不用管了，直接拿起来使用就行了。

在启动类中添加对mapper包扫描@MapperScan

@SpringBootApplication
@MapperScan("com.neo.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
或者直接在Mapper类上面添加注解@Mapper,建议使用上面那种，不然每个mapper加个注解也挺麻烦的

3、开发Mapper
第三步是最关键的一块，sql生产都在这里

public interface UserMapper {

    @Select("SELECT * FROM users")
    @Results({
        @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
        @Result(property = "nickName", column = "nick_name")
    })
    List<UserEntity> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
        @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
        @Result(property = "nickName", column = "nick_name")
    })
    UserEntity getOne(Long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(UserEntity user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(UserEntity user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);

}
为了更接近生产我特地将user_sex、nick_name两个属性在数据库加了下划线和实体类属性名不一致，另外user_sex使用了枚举

@Select 是查询类的注解，所有的查询均使用这个
@Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
@Insert 插入数据库使用，直接传入实体类会自动解析属性到对应的值
@Update 负责修改，也可以直接传入对象
@delete 负责删除
了解更多属性参考这里

注意，使用#符号和$符号的不同：

// This example creates a prepared statement, something like select * from teacher where name = ?;
@Select("Select * from teacher where name = #{name}")
Teacher selectTeachForGivenName(@Param("name") String name);

// This example creates n inlined statement, something like select * from teacher where name = 'someName';
@Select("Select * from teacher where name = '${name}'")
Teacher selectTeachForGivenName(@Param("name") String name);
4、使用
上面三步就基本完成了相关dao层开发，使用的时候当作普通的类注入进入就可以了

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper UserMapper;

    @Test
    public void testInsert() throws Exception {
        UserMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
        UserMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
        UserMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));

        Assert.assertEquals(3, UserMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<UserEntity> users = UserMapper.getAll();
        System.out.println(users.toString());
    }

    @Test
    public void testUpdate() throws Exception {
        UserEntity user = UserMapper.getOne(3l);
        System.out.println(user.toString());
        user.setNickName("neo");
        UserMapper.update(user);
        Assert.assertTrue(("neo".equals(UserMapper.getOne(3l).getNickName())));
    }
}
源码中controler层有完整的增删改查，这里就不贴了
源码在这里spring-boot-mybatis-annotation

```
#### mybatis不能找到对应的mapper，错误示例：dataReport与datareport是不相同的
```
mybatis 一定要保持mapper与xml的路径一致。
错误示例：dataReport与datareport是不相同的
com.jebao.jebaodb.dao.mapper.dataReport.JinGuanTongMapper.java
com\jebao\jebaodb\dao\mapper\datareport\JinGuanTongMapper.xml

```
