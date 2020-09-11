package com.yzd.jutils.algorithmExt.treeExt.t2;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yaozh
 * @Description:
 */
@Getter
@Setter
public class ThreeNode {
    private Integer id;
    private Integer parentId;
    private Integer childNodeSize;
    private List<ThreeNode> childNode = new ArrayList<>();
    private String data;
    public ThreeNode(Integer id,String data,Integer parentId){
        this.id=id;
        this.data=data;
        this.parentId=parentId;
    }
}
