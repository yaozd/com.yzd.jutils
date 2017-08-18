package com.yzd.jutils.javascriptExt;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Rhino使JavaScript应用程序更灵动
 * http://www.csdn.net/article/2012-01-20/311039
 * Created by zd.yao on 2017/8/18.
 */
public class _MainTest {
    public static void main(String[] args){
        javascript_rhino();
        //java内嵌的javascript的引擎执行速度比较慢-只是简单的测试可能会不准确
        //System.out.println(jsObjFunc());
        //String insurerXmlStr = FileUtil.read("/project.properties", "utf-8");
        /* for(int i=0;i<1000;i++){
            //rhino的javascript的引擎执行速度相对较快
            javascript_rhino();
        }*/
    }

    //rhino的javascript的引擎执行速度相对较快
    private static void javascript_rhino() {
        Context ctx= Context.enter();
        try{
            Scriptable scope=ctx.initStandardObjects();
            //先定义方法与变量--中间不任何参数所有都写在一个字符串
            //可以通过/**[注释内容]*/增加注释提高规则的可读性
            String jsStr="/**动态参数*/var x=1;/**入口方法*/ function a(){return x;}";
            Object result=ctx.evaluateString(scope, jsStr, null, 0,null);
            //然后调用执行方法名
            Object result2=ctx.evaluateString(scope, "a();", null, 0,null);
            System.out.println("result=" + Context.toString(result));
            System.out.println("result2="+Context.toString(result2));
        }finally {
            Context.exit();
        }
    }

    /**
     * 运行JS对象中的函数
     *java内嵌的javascript的引擎执行速度比较慢-只是简单的测试可能会不准确
     * @return
     */
    public static Object jsObjFunc() {
        String script =
                "var obj={run:function(){return 'run method : return:\"abc'+this.next('test')+'\"';},next:function(str){return ' 我来至next function '+str+')'}}";
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine scriptEngine = sem.getEngineByName("js");
        try {
            scriptEngine.eval(script);
            Object object = scriptEngine.get("obj");
            Invocable inv2 = (Invocable) scriptEngine;
            return (String) inv2.invokeMethod(object, "run");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
