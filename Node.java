package com.graph;

public class Node {
    protected Node next;
    protected String data;
    public Node(String data){
        this.data=data;
    }
    public  void display(){
        System.out.print(data+" ");
    }
}