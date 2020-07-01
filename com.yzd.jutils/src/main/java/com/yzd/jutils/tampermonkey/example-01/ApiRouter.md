```
// ==UserScript==
// @name         正则指标提取
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       You
// @match        http://127.0.0.1:9311/
// @grant        none
// @require https://cdn.bootcss.com/jquery/3.4.1/jquery.js
// @require https://cdn.bootcss.com/axios/0.19.0/axios.js
// ==/UserScript==

(function() {
    const $ = window.$;
    const pattern = /hyperspace_connections{service=[^\r\n]+/;
    function check(){
        $.get("http://127.0.0.1:9311/",function(data,status){
           console.log("Metrics:"+data.match(pattern));
        });
    }
    window.setInterval(check,2000);
})();
```