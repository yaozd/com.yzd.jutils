package com.yzd.jutils.springBoot.mockBeanTest;


import com.yzd.jutils.springBoot.mockBeanTest.service.ISample1Inf;
import com.yzd.jutils.springBoot.mockBeanTest.service.Sample1;
import com.yzd.jutils.springBoot.mockBeanTest.service.Sample2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MockBean_Test {

    // 报错：public class Sample1 implements ISample1Inf  ,所以不能再对Sample1使用“MockBean”的
    // org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'sample1' is expected to be of type 'com.yzd.jutils.springBoot.mockBeanTest.service.Sample1' but was actually of type 'com.yzd.jutils.springBoot.mockBeanTest.service.ISample1Inf
    //@MockBean
    //private Sample1 test1;
    @MockBean
    private Sample2 test1;
    @Test
    public void test(){
        //--如果使用的是MockBean 推荐使用 when->thenReturn
        //Mockito.when(test1.gg(false)).thenReturn("false");
        //Mockito.when(test1.gg(true)).thenReturn("111111111true");
        //Mockito.when(test1.gg(Mockito.anyBoolean())).thenReturn("111111111true");
        //Mockito.doReturn("doReturn=111111111true").when(test1).gg(true);
        //在没有对应的类型的情况下，输出结果为：null
        System.err.println(test1.gg(false));
    }
    //==============================
    @MockBean
    private ISample1Inf mockBeanTest;
    @Test
    public void test_mockBeanTest(){
        Mockito.when(mockBeanTest.gg(false)).thenReturn("false");
        //Mockito.when(mockBeanTest.gg(true)).thenReturn("111111111true");
        //doReturn 只能用于存在的类，不能用于接口类
        //Mockito.doReturn("doReturn=111111111true").when(mockBeanTest).gg(true);--如果使用的是MockBean 推荐使用 when->thenReturn
        //Mockito.anyBoolean() 最好是使用具体的类型
        Mockito.doReturn("doReturn=111111111true").when(mockBeanTest).gg(Mockito.anyBoolean());

        System.err.println(mockBeanTest.gg(true));
    }

}
