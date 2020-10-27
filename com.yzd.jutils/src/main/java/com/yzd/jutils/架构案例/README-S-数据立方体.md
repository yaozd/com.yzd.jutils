## 数据立方体
- [数据立方体（Cube）](https://www.cnblogs.com/sthinker/p/5965271.html)

## 立方体算法思路
- [Apache Kylin的快速数据立方体算法——概述](https://www.infoq.cn/article/apache-kylin-algorithm)
  ```
  快速 Cube 算法（Fast Cubing）是麒麟团队对新算法的一个统称，它还被称作“逐段”(By Segment) 或“逐块”(By Split) 算法。
  
  该算法的主要思想是，对 Mapper 所分配的数据块，将它计算成一个完整的小 Cube 段（包含所有 Cuboid）；每个 Mapper 将计算完的 Cube 段输出给 Reducer 做合并，生成大 Cube，也就是最终结果；
  ```
- []()
- []()