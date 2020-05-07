### Java Collections Cheat Sheet
- [Java Collections Cheat Sheet](https://www.jrebel.com/blog/java-collections-cheat-sheet)
- [java-streams-cheat-sheet](https://www.jrebel.com/blog/java-streams-cheat-sheet)
- []()

### Arrays.asList（）vs Collections.singletonList（）
- [Arrays.asList（）vs Collections.singletonList（）](https://www.cnblogs.com/xingzc/p/9144375.html)
- [使用Collections.singletonList（）方法-不可变列表](http://www.leftso.com/blog/438.html)

```
HttpHeaders headers = new HttpHeaders();
headers.setAccept( Collections.singletonList( MediaType.APPLICATION_JSON ) )
//使用Collections.singletonList（）方法[不可变列表]
List<String> list = Collections.singletonList( "data" );  
//使用Arrays.asList（）方法
List<String> list = Arrays.asList( "data");
```
- [Java中快速创建不可变集合](http://www.sohu.com/a/293974041_120047080)
```
List<String> immutableList = Collections.unmodifiableList(list);
Set<String> immutableSet =Collections.unmodifiableSet(codeSet);
---------------
Collections可以怎么创建不可变集合，相关方法：
emptyList(): List<T>
emptySet(): Set<T>
emptyMap(): Map<K, V>
singletonList<T o): List<T>
singleton(T o): Set<T>
singletonMap(K key, V value): Map<K, V)
unmodifiableList(List<? extends T> list): List<T>
unmodifiableSet(Set<? extends T> s): Set<T>
unmodifiableMap(Map<? extends K, ? extends V> m): Map<K, V)
```