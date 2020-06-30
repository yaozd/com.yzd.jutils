package com.yzd.jutils.streamExt.t1;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * by 王伟
 */
public class TreeUtil {
    public static List<Node> buildTree(List<Node> nodes) {
        Map<String, List<Node>> sub = nodes.stream().filter(node -> StringUtils.isNotBlank(node.getPid()) && hasParent(nodes, node.getPid())).collect(Collectors.groupingBy(node -> node.getPid()));
        // 设置各节点层数-通过查找父节点集合数量计算 todo 是否可以换种方式计算各节点所在层级
        nodes.forEach(node -> {
            List<Node> parentList = new ArrayList<>();
            int size = findParentList(parentList, nodes, node.getPid()).size();
            node.setLevel(size + 1);
        });

        nodes.forEach(node -> node.setSub(sub.get(node.getId()) == null ? new ArrayList<>() : sub.get(node.getId())));
        return nodes.stream().filter(node -> StringUtils.isBlank(node.getPid()) || !hasParent(nodes, node.getPid())).collect(Collectors.toList());
    }

    /**
     * 父节点ID不为空的情况下集合中是否存在该父节点
     * @param allList
     * @param parentId
     * @return
     */
    private static boolean hasParent(List<Node> allList, String parentId){
        Node node = allList.stream().filter(p -> p.getId().equals(parentId)).findAny().orElse(null);
        return node == null ? false : true;
    }

    /**
     * 查找父节点集合
     * @param parentList
     * @param allList
     * @param parentId
     * @return
     */
    private static List<Node> findParentList(List<Node> parentList, List<Node> allList, String parentId) {
        allList.stream().filter(p -> p.getId().equals(parentId))
                .forEach(o -> {
                    parentList.add(o);
                    findParentList(parentList, allList, o.getPid());
                });
        return parentList;
    }

    public static void main(String[] args) {
        Node dennis = new Node("1", "", "dennis");
        Node daughter = new Node("3", "1", "daughter");
        Node calm = new Node("2", "", "calm");
        Node grandson = new Node("4", "3", "grandson");
        Node son = new Node("5", "2", "son");
        Node grandsonc = new Node("6", "4", "grandsonc");

        System.out.println("------------------包含顶层节点情况---------------------------");
        List<Node> nodes = Lists.newArrayList(grandson, daughter, son, grandsonc,dennis,calm);
        List<Node> tree = TreeUtil.buildTree(nodes);
        System.out.println(JSON.toJSONString(tree));
/*---------------------------------------------------------------------------------------------*/
        System.out.println("------------------没有顶层节点情况---------------------------");
        List<Node> nodes2 = Lists.newArrayList(grandson, daughter, son, grandsonc);
        List<Node> tree2 = TreeUtil.buildTree(nodes2);
        System.out.println(JSON.toJSONString(tree2));

    }
}
