package com.yzd.jutils.groovyExt;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * Created by zd.yao on 2017/12/11.
 */
public class groovyUtil2 {
    public static void main(String[] args) {

// TODO Auto-generated method stub

        Binding binding = new Binding();

        binding.setVariable("var", 5);

        GroovyShell gs = new GroovyShell(binding);
        int i=0;
        while (true){
            Object value = gs.evaluate("abc=123;return var*10");//执行groovyshell脚本
           // System.out.println(value.equals(50));
            if(i>1000)break;
            i++;
        }





        System.out.println(binding.getVariable("abc").equals(123));

    }
}
