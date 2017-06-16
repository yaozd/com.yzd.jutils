package com.yzd.jutils.guava.listExt;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.util.*;

/**
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
