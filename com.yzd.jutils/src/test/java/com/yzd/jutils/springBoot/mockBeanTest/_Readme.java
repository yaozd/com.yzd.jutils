package com.yzd.jutils.springBoot.mockBeanTest;


//参考：
// Springboot单元测试（MockBean||SpyBean）
// https://blog.csdn.net/maiyikai/article/details/78483423

//接口模拟时，最好使用：MockBean
//实现类模拟时，最好使用：SpyBean

//
//--如果使用的是MockBean 推荐使用 when->thenReturn
//--如果使用的是SpyBean 推荐使用 doReturn->when ,因为这样不执行方法体，直接返回结果
//
//对已经有实现的类不能使用MockBean,但可以对其接口类使用MockBean 例如：public class Sample1 implements ISample1Inf  ,所以不能再对Sample1使用“MockBean”的

//mockito-关于@Mock和@Spy的使用方法
//https://blog.csdn.net/hotdust/article/details/51416894
//参考：http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html
//
//当我们对@Mock的类（@Mock private OrderDao dao;）进行模拟方法时，会像下面这样去做：
//when(dao.getOrder()).thenReturn("returened by mock "); // 或者使用更为推荐的given方法
//但如果想对@Spy的类（@Spy private PriceService ps;）进行模拟方法时，需要像下面一样去做：
//doReturn("twotwo").when(ps).getPriceTwo();
//
//原因：
//使用@Mock生成的类，所有方法都不是真实的方法，而且返回值都是NULL。
//使用@Spy生成的类，所有方法都是真实方法，返回值都是和真实方法一样的。
//所以，你用when去设置模拟返回值时，它里面的方法（dao.getOrder()）会先执行一次。
//使用doReturn去设置的话，就不会产生上面的问题，因为有when来进行控制要模拟的方法，所以不会执行原来的方法。

/**
 * what is difference between @SpyBean @MockBean in mockito
 https://stackoverflow.com/questions/43999517/what-is-difference-between-spybean-mockbean-in-mockito

 * That mock object doesn't have any relation to the underlying production code.
 * It is an object that looks like being an object of class X. But none of the methods or fields that X has do "really" exist on that mocked thing.

 Whereas a spy wraps around an existing object of your class under test. Meaning: when you create a spy,
 you can decide if method calls going to the spy should be "intercepted" (then you are using the spy as if it would be a mock); or be "passed through" to the actual object the spy wraps around.
 */

//SpringBoot | 第十三章：测试相关（单元测试、性能测试）
//http://www.importnew.com/29579.html
//基于ContiPerf的性能测试
///**
// * 编写接口测试类
// * @author oKong
// *
// */
//@RunWith(SpringRunner.class)
////SpringBootTest 是springboot 用于测试的注解，可指定启动类或者测试环境等，这里直接默认。
//@SpringBootTest
//public class UnitTestServiceTest {
//
//    @Autowired
//    UnitTestService testService;
//
//    //引入 ContiPerf 进行性能测试
//    @Rule
//    public ContiPerfRule contiPerfRule = new ContiPerfRule();
//
//    @Test
//    //10个线程 执行10次
//    @PerfTest(invocations = 100,threads = 10)
//    public void test() {
//        String msg = "this is a test";
//        String result = testService.process(msg);
//        //断言 是否和预期一致
//        Assert.assertEquals(msg, result);
//    }
//}
