### 示例-01
```
//通过接口类型进行分组
//interfaceType--groupBy
Map<Integer, List<TbIfcertPushLog>> map4PushLog2InterfaceType = itemList4PushLog2NoBalance.stream().collect(Collectors.groupingBy(TbIfcertPushLog::getInterfaceType));
//对应接口类型的发时间
for (Map.Entry<Integer, List<TbIfcertPushLog>> entry : map4PushLog2InterfaceType.entrySet()) {
    String interfaceType=entry.getKey().toString();
    List<TbIfcertPushLog> listPushLog=entry.getValue();
    //SendDate--distinct
    List<String> sendDateList4PushLog = itemList4PushLog2NoBalance.stream().map(TbIfcertPushLog::getSendDate).distinct().collect(Collectors.toList());
    for (String sendDate:sendDateList4PushLog) {
        BatchList_DZ_IFCERT param=new BatchList_DZ_IFCERT();
        param.setInfType(interfaceType);
        param.setPutType("");
        param.setPageNum("");
        param.setSentDate(sendDate);
        BatchListReturn_DZ_IFCERT result4BatchList=ifcertClient4BalanceInf.invokeBatchList(param);

    }
}
```