package com.yzd.jutils.guava.listExt;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.util.*;

/**
 * 参考：
 *  Guava学习笔记：Guava新增集合类型-Multimap
 *  http://www.cnblogs.com/peida/p/3180129.html
 * Multimap的实现

 　　Multimap提供了丰富的实现，所以你可以用它来替代程序里的Map<K, Collection<V>>，具体的实现如下：
 　　Implementation            Keys 的行为类似       　　　Values的行为类似
 　　ArrayListMultimap         HashMap                   　　ArrayList
 　　HashMultimap               HashMap                  　　 HashSet
 　　LinkedListMultimap        LinkedHashMap*              LinkedList*
 　　LinkedHashMultimap      LinkedHashMap                LinkedHashSet
 　　TreeMultimap                TreeMap                          TreeSet
 　　ImmutableListMultimap  ImmutableMap                 ImmutableList
 　　ImmutableSetMultimap  ImmutableMap                 ImmutableSet


 以上这些实现，除了immutable的实现都支持null的键和值。
 　　1.LinkedListMultimap.entries()能维持迭代时的顺序。

 　　2.LinkedHashMultimap维持插入的顺序，以及键的插入顺序。
 　　要注意并不是所有的实现都正真实现了Map<K, Collection<V>>！（尤其是有些Multimap的实现为了最小话开销，使用了自定义的hash table）
 * Created by zd.yao on 2017/6/16.
 */
public class ListTest {
    public static void main(String[] args){
        //
        List<ShortcutMapForm> shortcutMapFormList=Lists.newArrayList();;
        List<Shortcut> shortcutList= Lists.newArrayList();
        Multimap<Integer, Shortcut> shortcutMultimap =listToMultimap(shortcutList);
        for(Map.Entry<Integer, Collection<Shortcut>> e : shortcutMultimap.asMap().entrySet()){
            List<Shortcut> itemList=Lists.newArrayList(e.getValue());
            Collections.sort(itemList, new Comparator<Shortcut>() {
                public int compare(Shortcut arg0, Shortcut arg1) {
                    return arg1.getId().compareTo(arg0.getId());
                }
            });
            List<ShortcutForm> shortcutFormList=Lists.newArrayList();
            itemList.forEach(item->shortcutFormList.add(ShortcutForm.toForm(item)));
            ShortcutMapForm shortcutMapForm=new ShortcutMapForm();
            shortcutMapForm.setColorId(e.getKey());
            shortcutMapForm.setShortcutFormList(shortcutFormList);
            shortcutMapFormList.add(shortcutMapForm);
        }
        Collections.sort(shortcutMapFormList,new Comparator<ShortcutMapForm>(){
            @Override
            public int compare(ShortcutMapForm o1, ShortcutMapForm o2) {
                return o1.getColorId().compareTo(o2.getColorId());
            }
        } );
    }
    public static Multimap<Integer, Shortcut> listToMultimap(List<Shortcut> list) {
        return Multimaps.index(list, keyFunction());
    }
    public static Function<Shortcut, Integer> keyFunction() {
        return new Function<Shortcut, Integer>() {
            @Override
            public Integer apply(Shortcut input) {
                return input.getColor();
            }
        };
    }
}
