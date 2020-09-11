package com.yzd.jutils.algorithmExt.treeExt;

import com.yzd.jutils.fastjson.FastJsonUtil;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private Integer id;
    private Integer pid;
    private String name;
    private List<TreeNode> children;

    TreeNode(Integer id, Integer pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    //参考：递归输出树型
    //- [如何用 js 递归输出树型](https://www.cnblogs.com/yeminglong/p/4787533.html)
    public static void main(String[] args) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(new TreeNode(1, 0, "1"));
        list.add(new TreeNode(2, 0, "2"));
        list.add(new TreeNode(3, 2, "3"));
        list.add(new TreeNode(4, 3, "4"));
        list.add(new TreeNode(5, 4, "5"));
        list.add(new TreeNode(6, 5, "6"));
        list.add(new TreeNode(7, 1, "7"));

        List<TreeNode> treeList = new ArrayList<TreeNode>();
        //方法一、
        treeList = toTree(list, 0);
        System.out.println(FastJsonUtil.serialize(treeList));
    }

    private static List<TreeNode> toTree(List<TreeNode> data, Integer pid) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        List<TreeNode> temp = new ArrayList<TreeNode>();
        for (TreeNode item : data) {
            if (pid.equals(item.getPid())) {
                result.add(item);
                temp = toTree(data, item.getId());
                if (!temp.isEmpty()) {
                    item.setChildren(temp);
                }
            }
        }
        return result;
    }


}
