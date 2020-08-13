```
// ==UserScript==
// @name         Kibana Helper
// @version      0.1
// @description  Link the pages each other
// @author       Nix
// @include      http://ulp.kibana.demo.com/*
// @grant        none
// @require https://code.jquery.com/jquery-2.1.4.min.js
// ==/UserScript==

(function() {
  'use strict';

  const currentUrl = window.location.href;
  if (!/^http:\/\/ulp.kibana.demo.com\/app\/kibana#\/dashboard\/.+$/igm.test(currentUrl)) {
    return
  }

  const $ = window.$

  /**
   * 检查运行状态
   * 每秒检查一次, 直到 worker 返回 true
   * @param worker [Function] 状态检查通过后的回调函数
   * @returns boolean
   */
  function checkStatus(worker) {
    const result = worker()
    if (!result) {
      window.$running = setTimeout(checkStatus, 2000, worker)
    }
  }
  /**
  * change event
  */
  function handlePanelClick() {
    checkStatus(backendStatisticsWorker)
  }
    /**
    *
    */
   function UrlParamHash(url) {
       var params = [],
           h;
       var hash = url.slice(url.indexOf("?") + 1).split('&');
       //console.log(hash);
       for (var i = 0; i < hash.length; i++) {
           h = hash[i].split("="); //
           params[h[0]] = h[1];
           //console.log(h);
       }
       return params;
   }
   /**
   * 获取参数
   *
   */
   function getUrlParam(name){
       var params = UrlParamHash(window.location.href);
       return params[name];
   }
  /**
   * 后端服务耗时统计
   *  一个 worker 函数
   * @returns boolean
   */
  function backendStatisticsWorker() {
    //const titleSet = $('div[data-test-subj="dashboardPanelHeading-[APIGateway(apiroute)]后端服务耗时统计"]')
    //const dataSet = titleSet.next('div#embeddedPanel')
    //const resultSet = dataSet.find('tbody[data-test-subj="paginated-table-body"]')
    //const rowSet = resultSet.children('tr')
    const titleSet = $('div[data-title="[APIGateway(apiroute)]后端服务耗时统计"]')
    const dataSet = titleSet.find('table')
    const resultSet = dataSet.find('tbody')[1]
    const rowSet=$(resultSet).children('tr');
    for (var i = 0; i < rowSet.length; i++) {
      const row = $(rowSet[i])
      const columnSet = row.children('td')
      const domain = $(columnSet[1]).text().trim()
      const path = $(columnSet[2]).text().trim();
      const timeParam=getUrlParam("_g");
      const queryUrl="http://ulp.kibana.demo.com/app/kibana#/discover?_g=#time#&_a=(columns:!(_source),index:cef94120-c9c6-11e9-a9e2-45125a6654d4,interval:auto,query:(language:kuery,query:'%20requestHost.keyword:%22#ServiceName#%22%20and%20%09uri_path.keyword:%22#ServiceMethod#%22'),sort:!('@timestamp',desc))";
      const url = queryUrl.replace(new RegExp("#time#","gm"),timeParam).replace(new RegExp("#ServiceName#","gm"),domain).replace(new RegExp("#ServiceMethod#","gm"),path);
      console.log(url);

      $(columnSet[2]).find('span:first').replaceWith(`<span ng-non-bindable><a href="${url}" target="_blank">${path}</a></span>`)
    }
     $('body').off('click', 'div[data-test-subj="dashboardPanel"]', handlePanelClick)
     $('body').on('click', 'div[data-test-subj="dashboardPanel"]', handlePanelClick)
    console.log(resultSet.length)
    return resultSet.length > 0
  }
  // main entrypoint
  checkStatus(backendStatisticsWorker)
})();
```