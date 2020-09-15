## Minio直接下载URL插件

```
// ==UserScript==
// @name         Minio download url
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  Minio直接下载URL插件,使用场景：Minio设置永久下载链接
// @author       You
// @match        http://127.0.0.1:6050/minio/api-router/9fb89970/
// @grant        none
// @require https://code.jquery.com/jquery-2.1.4.min.js
// ==/UserScript==

(function() {
    'use strict';

    // Your code here...
    const $ = window.$
    const url="http://127.0.0.1:6050";
    const path=window.location.pathname.replace("/minio/","/");
    const downloadUrl=url+path;
    $(document).on('click', '.fesl-item-name',function(){
        alert(downloadUrl+$(this).text());
    });


})();
```