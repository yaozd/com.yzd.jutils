package com.yzd.jutils.streamExt.t1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    private String id;
    private String pid;
    private String name;
    private int level;
    private List<Node> sub = new ArrayList<>();

    public Node(String id, String pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }
}
