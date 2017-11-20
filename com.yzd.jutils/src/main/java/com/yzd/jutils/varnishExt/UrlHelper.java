package com.yzd.jutils.varnishExt;

import com.yzd.jutils.print.PrintUtil;
import com.yzd.jutils.regex.RegExUtil;
import org.junit.Test;

/**
 * Created by zd.yao on 2017/11/20.
 */
public class UrlHelper {
    @Test
    public void createMd5URL(){
        String url="http://testhxb.shanxinbaoxian.com/static/js/manifest.cb9b82712ce843bf8520.js";
        int index=url.lastIndexOf(".js");
        PrintUtil.outLn(index);
        PrintUtil.outLn(url.length());


    }
    @Test
    public void regexMd5URL(){
        String url="http://testhxb.shanxinbaoxian.com/static/js/manifest.cb9b82712ce843bf8520.js";
        String pattern="(/[^/]+\\.)[^\\./]+\\.(js|css)$";
        boolean isOk= RegExUtil.ereg(pattern,url);
        //http://testhxb.shanxinbaoxian.com/static/js/manifest.js
        String newUrl= RegExUtil.ereg_replace(pattern,"$1$2",url);
        PrintUtil.outLn(newUrl);
    }
    @Test
    public void varnishMd5URL(){
        //varnish的后台缓存正则
        //正则：\.(js|css)\?[\w]{32}$
        //目前js/css的url格式
        //http://localhost:7840/content/script/application/index.js?ef7ad35938eaa0d212200412ad57192b
        //http://localhost:7840/content/script/application/index.css?ef7ad35938eaa0d212200412ad57192b
    }

}
