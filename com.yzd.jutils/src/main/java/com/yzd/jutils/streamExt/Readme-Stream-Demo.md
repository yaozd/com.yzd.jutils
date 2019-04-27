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
### 示例-02
```
    //利息与本金计算
    @Test
    public void interestAndCapital(){
        List<TbIncomeDetail>itemList=new ArrayList<>();
        itemList.add(getTbIncomeDetail(1,"10",1));
        itemList.add(getTbIncomeDetail(2,"10",1));
        itemList.add(getTbIncomeDetail(3,"10",1));
        itemList.add(getTbIncomeDetail(4,"10",1));
        itemList.add(getTbIncomeDetail(4,"100",2));
        //
        List<TbIncomeDetail>itemList4Periods=itemList.stream().filter(m->m.getIndFundType()==1).collect(Collectors.toList());
        for(TbIncomeDetail item4Periods:itemList4Periods){
            BigDecimal money4Interest=itemList.stream().filter(m->m.getIndPeriods()==item4Periods.getIndPeriods()&&m.getIndFundType()==1).findFirst().orElseGet(()->item4Zero()).getIndMoney();
            BigDecimal money4Capital=itemList.stream().filter(m->m.getIndPeriods()==item4Periods.getIndPeriods()&&m.getIndFundType()==2).findFirst().orElseGet(()->item4Zero()).getIndMoney();
            log.info("XX="+item4Periods.getIndPeriods()+"XX-VAL="+money4Interest+"XX-VAL="+money4Capital);
        }
        //
    }

    private TbIncomeDetail item4Zero() {
        TbIncomeDetail item4Zero=new TbIncomeDetail();
        item4Zero.setIndMoney(new BigDecimal("0"));
        return item4Zero;
    }

    private TbIncomeDetail getTbIncomeDetail(Integer indPeriods, String indMoney, Integer indFundType) {
        TbIncomeDetail item1=new TbIncomeDetail();
        item1.setIndBpId(1L);
        item1.setIndPeriods(indPeriods);
        item1.setIndMoney(new BigDecimal(indMoney));
        item1.setIndFundType(indFundType);
        return item1;
    }
```