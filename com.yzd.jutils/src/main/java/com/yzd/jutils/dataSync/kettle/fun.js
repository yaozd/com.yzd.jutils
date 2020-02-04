//用于写javascript脚本

var currPage = getCurrPage();
var startRow = getStartRow(currPage);
if (currPage <= 6) {
    parent_job.setVariable("CURR_PAGE", currPage + 1);
    parent_job.setVariable("START_ROW", startRow);
    true;
}
if (currPage > 6) {
    false;
}

//
function getCurrPage() {
    var currPage = parent_job.getVariable("CURR_PAGE");
    if (currPage == null || currPage == undefined || currPage == "") {
        return 1;
    }
    return parseInt(currPage);
}

function getStartRow(currPage) {
    var pageSize = 100;
    var startRow = currPage * pageSize;
    return startRow;
}

//==============================================================
//最多循环1000次，避免出现死循环
var currPage = getCurrPage();
var startRow = getStartRow(currPage);
if (currPage <= 1000) {
    parent_job.setVariable("CURR_PAGE", currPage + 1);
    parent_job.setVariable("START_ROW", startRow);
    true;
}
if (currPage > 1000) {
    false;
}

//
function getCurrPage() {
    var currPage = parent_job.getVariable("CURR_PAGE");
    if (currPage == null || currPage == undefined || currPage == "") {
        return 1;
    }
    return parseInt(currPage);
}

function getStartRow(currPage) {
    var pageSize = parent_job.getVariable("PAGE_SIZE");
    //var pageSize=100;
    var startRow = currPage * pageSize;
    return startRow;
}