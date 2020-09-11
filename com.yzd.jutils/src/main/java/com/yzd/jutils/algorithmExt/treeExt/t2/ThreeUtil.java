package com.yzd.jutils.algorithmExt.treeExt.t2;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于递归算法，树形结构下的业务数据场景，封装解决方法
 * https://mp.weixin.qq.com/s/KSTHZ3YAe5HxBoM-CbFe9A
 * @Author: yaozh
 * @Description:
 */
public class ThreeUtil {
    /**
     * 递归创建树形结构
     */
    private static List<ThreeNode> getTree(List<ThreeNode> nodeList, Integer parentId) {
        List<ThreeNode> threeNodeList = new ArrayList<>() ;
        for (ThreeNode entity : nodeList) {
            Integer nodeId = entity.getId() ;
            Integer nodeParentId = entity.getParentId() ;
            if (parentId.intValue() == nodeParentId.intValue()) {
                List<ThreeNode> childList = getTree(nodeList,nodeId) ;
                if (childList != null && childList.size()>0){
                    entity.setChildNode(childList);
                    entity.setChildNodeSize(childList.size());
                }
                threeNodeList.add(entity) ;
            }
        }
        return threeNodeList ;
    }

    /**
     * 获取指定子节点
     */
    private static List<ThreeNode> getChildTree (Integer id,List<ThreeNode> nodeList){
        List<ThreeNode> resultList = new ArrayList<>();
        for (ThreeNode entity : nodeList) {
            if (entity.getParentId().intValue() == id) {
                List<ThreeNode> childList = getChildTree(entity.getId(),nodeList) ;
                entity.setChildNode(childList);
                entity.setChildNodeSize(childList.size());
                resultList.add(entity) ;
            }
        }
        return resultList ;
    }

    /**
     * 遍历树形结构
     */
    private static transient List<Integer> treeIdList = new ArrayList<>() ;
    private static List<Integer> getTreeInfo (List<ThreeNode> treeList){
        for (ThreeNode entity : treeList) {
            if (entity.getChildNodeSize()!=null && entity.getChildNodeSize()>0){
                getTreeInfo(entity.getChildNode());
            }
            treeIdList.add(entity.getId());
        }
        return treeIdList ;
    }

    /**
     * 判断节是否是叶子节点
     */
    private static boolean hasChildNode (Integer id,List<ThreeNode> nodeList){
        for (ThreeNode entity:nodeList){
            if (entity.getParentId().intValue() == id){
                return true ;
            }
        }
        return false ;
    }

    public static void main(String[] args) {
        List<ThreeNode> threeNodeList = new ArrayList<>() ;
        threeNodeList.add(new ThreeNode(1,"节点A",0)) ;
        threeNodeList.add(new ThreeNode(2,"节点B",1)) ;
        threeNodeList.add(new ThreeNode(3,"节点C",1)) ;
        threeNodeList.add(new ThreeNode(4,"节点D",1)) ;
        threeNodeList.add(new ThreeNode(5,"节点E",2)) ;
        threeNodeList.add(new ThreeNode(6,"节点F",2)) ;
        // 测试1
        List<ThreeNode> getTree = getTree(threeNodeList,0) ;
        System.out.println(getTree);
        // 测试2
        // List<ThreeNode> getChildTree = getChildTree(2,threeNodeList) ;
        // System.out.println(getChildTree);
        // 测试3
        List<Integer> treeIdList = getTreeInfo(getTree) ;
        System.out.println(treeIdList);
        // 测试4
        System.out.println(hasChildNode(2,threeNodeList)) ;
    }
}