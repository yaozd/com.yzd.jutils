package com.yzd.jutils.javascriptExt;

import com.yzd.jutils.fastjson.FastJsonUtil;
import com.yzd.jutils.fileExt.FileUtil;
import com.yzd.jutils.javascriptExt.obj.Product;
import com.yzd.jutils.print.PrintUtil;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 *
 * Created by zd.yao on 2017/8/18.
 */
public class _MainTest2 {
    public static void main(String[] args) {
        Product product = new Product();
        product.setCode("1001");
        product.setYear(30);
        String requestJsonStr = FastJsonUtil.serialize(product);
        //初始请求参数，必须要加换行符
        String requestObjStr = String.format("var requestObj=%s;\r\n", requestJsonStr);
        //初始处理方法
        String functionStr = FileUtil.read("/rhino/t0.js", "UTF-8");
        //
        StringBuilder sb = new StringBuilder();
        sb.append(requestObjStr);
        sb.append(functionStr);
        PrintUtil.outLn(sb.toString());
        //
        Context ctx = Context.enter();
        try {
            Scriptable scope = ctx.initStandardObjects();
            //先定义方法与变量--中间不任何参数所有都写在一个字符串
            //可以通过/**[注释内容]*/增加注释提高规则的可读性
            //String jsStr="/**动态参数*/var x=1;/**入口方法*/ function enterFun(){return x;}";
            String jsStr = sb.toString();
            ctx.evaluateString(scope, jsStr, null, 0, null);
            //然后调用执行方法名
            Object result2 = ctx.evaluateString(scope, "enterFun();", null, 0, null);
            System.out.println("result2=" + Context.toString(result2));
        } finally {
            Context.exit();
        }
    }
}
