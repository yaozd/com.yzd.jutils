## 原理-随机
```
int offset=ThreadLocalRandom.current().nextInt(totalWeight);
   for (Node node : nodeList) {
       offset=offset-node.getWeight();
       if(offset<1){
           return node;
       }
   }
```