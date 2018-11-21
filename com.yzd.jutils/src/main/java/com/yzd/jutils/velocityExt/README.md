
### 模板全局变量$filePath = $UrlHelperTool.getStaticFilePath()与${filePath}

```
head.html

#set($filePath = $UrlHelperTool.getStaticFilePath())
<meta name="keywords" content="金额宝,金额,额宝,jebao,jeb,jinebao,投资,金融,高息,理财,理财产品,稳健" />
<meta name="description" content="金额宝(www.jebao.net)--专注一线城市房产抵押的借贷网络信息中介服务平台，借款标的全部以优质房产做抵押，资金存管在恒丰银行，通过公安部颁发的三级等保认证，为投资人和高速成长的中小企业搭建一个安全、透明、可靠的网络借贷信息中介服务平台。" />
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no,email=no">
<link rel="stylesheet" type="text/css" href="${filePath}/css/lib/base.css">
<script src="${filePath}/js/lib/jquery.js"></script>

```

### AJAX的全局请求地址变量

```
<script>if(common){common.apiOrigin = "$UrlHelperTool.getApiOrigin()";}</script>
----------------------------------
(function () {
    //页面公共对象
    var common = {
        apiOrigin: "",
        //获取url参数
        getUrlParam: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            else return null;
        },
        //显示文本内容
        toText: function (obj) {
            if (obj == null) {
                return '';
            }
            return obj.toString();
        },
        //将json日期格式化显示 数字转日期
        formatJsonDate: function (str, format) {
            if (str == null || str.length == 0) {
                return '';
            }
            str = str.replace(/T/g, " ").replace(/-/g, "/").substr(0, 19);
            var d = new Date(str);
            format = format || 'yyyy-MM-dd';
            return d.toFormatString(format);
        }
    };

    window.common = common;
})(jQuery);
----------------------------------
$(document).ajaxSend(function (event, jqxhr, settings) {
        if (settings.url.indexOf("/api") === 0) {
            settings.url = common.apiOrigin + settings.url;
            settings.xhrFields = {
                withCredentials: true
            };
            settings.crossDomain = true;
        }
    });

```
