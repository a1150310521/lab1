package com.graph;
//1.4ÐÞ¸Ä3
public class LinkList {
    public  LinkNode head;
    private String loc;
    public LinkList(){
        this.head=null;
    }
    public void addhead (String data){
        LinkNode node=new LinkNode(data);
        node.next=head;
        node.data=data;
        head=node;
    }
    public LinkNode deletehead(){
        LinkNode tempNode=head;
        head=tempNode.next;
        return tempNode;
    }
    public void creatLink(String data){
        LinkNode temp=head;
        while(head.next!=null){
            temp=temp.next;
        }
        temp.data=data;
    }
    public void displayList(){
        LinkNode current =head;
        while(current!=null){
            current.display();
            current=current.next;
        }
        System.out.println();
    }

}
