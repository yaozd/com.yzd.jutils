package com.yzd.jutils.jsonExt.jsoniterExt;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import com.yzd.jutils.person.Person;
import org.junit.Test;

import java.io.IOException;

public class TestDemo {
    //参考：
    //json iterator
    //http://jsoniter.com/java-features.html#deserialize
    //jsoniter-高性能的json解析器
    //http://www.bubuko.com/infodetail-1937041.html
    //如何评价jsoniter自称是最快的 JSON 解析器
    //https://www.zhihu.com/question/53774273
    @Test
    public void t1() throws IOException {
        JsonIterator iter = JsonIterator.parse("[0,1,2,3]");
        int[] val = iter.read(int[].class);
        System.out.println(val[3]);
    }
    @Test
    public void t2() throws IOException{
        Person person=new Person(1,"2","3",4);
        String jsonStr= JsonStream.serialize(person);
        Person jsonObj= JsonIterator.deserialize(jsonStr, Person.class);
    }
    @Test
    public void t3() throws IOException{
        Person person=new Person(1,"2","3",4);
        String jsonStr= JsoniterHelper.serialize(person);
        Person jsonObj= JsoniterHelper.deserialize(jsonStr, Person.class);
    }
}
