//用于写javascript脚本

var currPage=getCurrPage();

if(currPage<=6){
    parent_job.setVariable("CURR_PAGE",currPage+1);
    true;
}
if(currPage>6){
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