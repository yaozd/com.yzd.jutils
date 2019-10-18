##　排序算法
- [Java实现八大排序算法](https://www.cnblogs.com/morethink/p/8419151.html)
    - [https://github.com/morethink/algorithm/tree/master/src/main/java/algorithm/sort](https://github.com/morethink/algorithm/tree/master/src/main/java/algorithm/sort)
- [Java实现二分查找算法](https://www.cnblogs.com/morethink/p/8379475.html)
    - 二分查找法的缺陷-二分查找(Binary Search)
    ```
    二分查找法的O(log n)让它成为十分高效的算法。不过它的缺陷却也是那么明显的。就在它的限定之上：必须有序，我们很难保证我们的数组都是有序的。当然可以在构建数组的时候进行排序，可是又落到了第二个瓶颈上：它必须是数组。
    
    数组读取效率是O(1)，可是它的插入和删除某个元素的效率却是O(n)。因而导致构建有序数组变成低效的事情。
    
    解决这些缺陷问题更好的方法应该是使用二叉查找树了，最好自然是自平衡二叉查找树了，既能高效的（O(n log n)）构建有序元素集合，又能如同二分查找法一样快速（O(log n)）的搜寻目标数。
    ```