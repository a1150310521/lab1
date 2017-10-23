package com.graph;

public class LinkNode {
    protected LinkNode next;
    protected String data;
    public LinkNode(String data){
        this.data=data;
    }
    public  void display(){
        System.out.print(data+" ");
    }
}