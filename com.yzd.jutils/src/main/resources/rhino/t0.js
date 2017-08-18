/**请求对象数据结构*/
/**var requestObj={"code":"10001","year":20}*/
/**入口方法*/
function enterFun() {
    return compute(requestObj);
};
/**下面是实际的处理方法*/
function compute(obj){
    /**规则1：说明信息*/
    if(obj.code=="1001"&&obj.year==20)return 15;
    return 20;
}