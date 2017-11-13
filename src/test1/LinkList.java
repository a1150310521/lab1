package test1;

public class LinkList {
	public  Node head;
    private String loc;
    public LinkList(){
        this.head=null;
    }
    public void addhead (String data){
        Node node=new Node(data);
        node.next=head;
        node.data=data;
        head=node;
    }
    public Node deletehead(){
        Node tempNode=head;
        head=tempNode.next;
        return tempNode;
    }
    public void creatLink(String data){
        Node temp=head;
        while(head.next!=null){
            temp=temp.next;
        }
        temp.data=data;
    }
    public void displayList(){
        Node current =head;
        while(current!=null){
            current.display();
            current=current.next;
        }
        System.out.println();
    }


}
