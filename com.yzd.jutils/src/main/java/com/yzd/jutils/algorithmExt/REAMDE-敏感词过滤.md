# 对敏感词、广告词过滤功能
## 推荐代码
- 快速敏感词过滤 -速度快但功能不完善 。目前考虑效率第一，暂时推荐此代码byArvin
    - [https://github.com/elulis/sensitive-words](https://github.com/elulis/sensitive-words) 
    - 性能概述
    ```
    使用60MB大小的小说测试，单核性能超过50M字符每秒（i7 2.3GHz）。
    
    敏感词 14553 条
    待过滤文本共 599254 行，30613005 字符。
    过滤耗时 0.535 秒， 速度为 57220.6字符/毫秒
    其中 39691 行有替换
    优化方式
    主要的优化目标是速度，从以下方面优化：
    
    敏感词都是2个字以上的，
    对于句子中的一个位置，用2个字符的hash在稀疏的hash桶中查找，如果查不到说明一定不是敏感词，则继续下一个位置。
    2个字符（2x16位），可以预先组合为1个int（32位）的mix，即使hash命中，如果mix不同则跳过。
    StringPointer，在不生成新实例的情况下计算任意位置2个字符的hash和mix
    StringPointer，尽量减少实例生成和char数组的拷贝
    ```
- []()
- [Java实现DFA算法对敏感词、广告词过滤功能](https://blog.csdn.net/fengshizty/article/details/52373005)
    - [https://github.com/andyzty/sensitivewd-filter](https://github.com/andyzty/sensitivewd-filter)
- [Aho-Corasick算法的Java实现与分析](https://blog.csdn.net/xiewenbo/article/details/53324621)
    > 用双数组Trie树表达AC自动机，就能集合两者的优点，得到一种近乎完美的数据结构
    - [https://github.com/hankcs/AhoCorasickDoubleArrayTrie](https://github.com/hankcs/AhoCorasickDoubleArrayTrie)  代码实现  

## 相关算法
> 文本过滤+字符过滤+敏感词过滤+关键词过滤+脏词过滤+多字符串匹配+Aho-Corasick 算法+Trie树+DFA filter
- 双数组trie树
    - [双数组trie树](https://baike.baidu.com/item/%E5%8F%8C%E6%95%B0%E7%BB%84trie%E6%A0%91/12508646)
    - [https://github.com/dingyaguang117/DoubleArrayTrie](https://github.com/dingyaguang117/DoubleArrayTrie) 代码实现
    - [https://github.com/KimShen/double-array-trie](https://github.com/KimShen/double-array-trie)
    - [https://github.com/ottsion/DoubleArrayTrie](https://github.com/ottsion/DoubleArrayTrie)
- Aho-Corasick算法简称AC算法 
    > 通过将模式串预处理为确定有限状态自动机，扫描文本一遍就能结束。其复杂度为O(n)，即与模式串的数量和长度无关。
    - [Aho-Corasick算法的Java实现与分析](https://blog.csdn.net/xiewenbo/article/details/53324621)
    - [AweiWordFilter是基于自动机和trie树算法,实现的敏感词过滤](https://github.com/zvv/AweiWordFilter)  代码实现  
    - [https://github.com/hankcs/AhoCorasickDoubleArrayTrie](https://github.com/hankcs/AhoCorasickDoubleArrayTrie)  代码实现  
    - [https://github.com/hankcs/aho-corasick](https://github.com/hankcs/aho-corasick)
- PATRICIA Trie
    - []()
- 


## 关注点
- [https://github.com/andyzty/sensitivewd-filter](https://github.com/andyzty/sensitivewd-filter)
```
其中resources资源目录中：
stopwd.txt ：停顿词，匹配时间直接过滤。
wd.txt：敏感词库。
完成了对敏感词、广告词的过滤，而且效率较好
具体实现：
 1、匹配大小写过滤
 2、匹配全角半角过滤
 3、匹配过滤停顿词过滤。
 4、敏感词重复词过滤。

例如：
支持如下类型类型过滤检测：
 fuck 全小写
 FuCk 大小写
 ｆｕｃｋ全角半角
 f!!!u&c ###k 停顿词
 fffuuuucccckkk 重复词
```