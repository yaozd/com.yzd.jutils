package com.yzd.jutils.springBoot.mockBeanTest;

import com.yzd.jutils.springBoot.mockBeanTest.service.ISample1Inf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpyBean_Test {
    @SpyBean
    private ISample1Inf spyTest;
    /**
     * test3-test4比较
     * 正常执行顺序
     *  coming.........
     *  coming.........g
     *  coming.........h
     *  result data is d
     *  d
     * 使用Mockito.when(spyTest.g(false)).thenReturn("test");方式
     * g(boolean)方法会在调用主方法gg(boolean)前执行，不管执行的结果是啥，都会按照mock的数据“test”返回
     *
     * 使用Mockito.doReturn("test").when(spyTest).g(false);方式
     * g(boolean)方法不会被执行，当时会将它的返回值赋值为：“test”。
     * 在主方法gg(boolean)执行到g(boolean)时，不执行g(boolean)方法体，直接将值“test'”返回。后续方法照常执行
     */
    @Test
    public void test(){

        //Mockito.when(spyTest.gg(false)).thenReturn("false");
        //Mockito.when(spyTest.gg(true)).thenReturn("111111111true");
        //--如果使用的是SpyBean 推荐使用 doReturn->when ,因为这样不执行方法体，直接返回结果
        Mockito.doReturn("doReturn=111111111true").when(spyTest).gg(true);//

        System.err.println(spyTest.gg(true));
    }
    //==============================
    /*@MockBean
    private ISimpleTest mockBeanTest;
    @Test
    public void test_mockBeanTest(){
        //Mockito.when(mockBeanTest.gg(false)).thenReturn("false");
        //Mockito.when(mockBeanTest.gg(true)).thenReturn("111111111true");
        //doReturn 只能用于存在的类，不能用于接口类
        Mockito.doReturn("doReturn=111111111true").when(mockBeanTest).gg(true);

        System.err.println(mockBeanTest.gg(true));
    }*/

}

