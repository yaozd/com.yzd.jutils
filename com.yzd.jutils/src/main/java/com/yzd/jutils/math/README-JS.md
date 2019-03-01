- [js 阿拉伯数字转化为中文数字-js版本](https://blog.csdn.net/lavendersue/article/details/81066091)
```
function toChinesNum(num){
    let changeNum = ['零', '一', '二', '三', '四', '五', '六', '七', '八', '九']; //changeNum[0] = "零"
    let unit = ["", "十", "百", "千", "万"];
    num = parseInt(num);
    let getWan = (temp) => {
    let strArr = temp.toString().split("").reverse();
    let newNum = "";
    for (var i = 0; i < strArr.length; i++) {
      newNum = (i == 0 && strArr[i] == 0 ? "" : (i > 0 && strArr[i] == 0 && strArr[i - 1] == 0 ? "" : changeNum[strArr[i]] + (strArr[i] == 0 ? unit[0] : unit[i]))) + newNum;
    }
     return newNum;
   }
   let overWan = Math.floor(num / 10000);
   let noWan = num % 10000;
   if (noWan.toString().length < 4) noWan = "0" + noWan;
   return overWan ? getWan(overWan) + "万" + getWan(noWan) : getWan(num);
}
```
```
function getWan(temp){
    var changeNum = ['零', '一', '二', '三', '四', '五', '六', '七', '八', '九']; //changeNum[0] = "零"
    var unit = ["", "十", "百", "千", "万"];
    var strArr = temp.toString().split("").reverse();
    var newNum = "";
    for (var i = 0; i < strArr.length; i++) {
      newNum = (i == 0 && strArr[i] == 0 ? "" : (i > 0 && strArr[i] == 0 && strArr[i - 1] == 0 ? "" : changeNum[strArr[i]] + (strArr[i] == 0 ? unit[0] : unit[i]))) + newNum;
    }
    return newNum;
}
function toChinesNum(num){
    num = parseInt(num);
    var overWan = Math.floor(num / 10000);
    var noWan = num % 10000;
    if (noWan.toString().length < 4) noWan = "0" + noWan;
    return overWan ? getWan(overWan) + "万" + getWan(noWan) : getWan(num);

}
```