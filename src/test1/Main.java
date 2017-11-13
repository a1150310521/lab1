package test1;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.lang.Exception;


public class Main {

    public static File filename;
    public static int j=0,t=0,i=0,f=0,n=0,g=0,txtlen=0,che=0,chek=0,check=0,way=0;
    public static String[] txt=new String[100];//锟街凤拷锟斤拷
    public static String[] bgw=new String[10];
    public static int [][] G=new int[txtlen][txtlen];
    public static int [][] path=new int[txtlen][txtlen];
    public static int [][] signal=new int[txtlen][txtlen];
    public static String newsc="";
    public static String word1, word2, bword,text;
    public static void main(String[] args) throws Exception {
        System.out.println("请输入文本所在的位置");
        Scanner scan = new Scanner(System.in);
        String filename = scan.next();
        File filee = new File(filename);//鏂囦欢浣嶇疆  D:\Users\ASUS\Desktop\Lab\file1.txt    seek to explore new and exciting
        Graph graph = new Graph();
        G=Graph.createDirectedGraph(filename);
        while(true){
            System.out.println("选择功能");
            System.out.println("1桥接词2新文本3最短路径4单单词最短路径5随机游走");
            System.out.println("#结束");
            String chose=scan.next();
            if(chose.equals("1")==true){
                System.out.println("input word1 and word2");  //bridgewords
                Scanner sc = new Scanner(System.in);
                word1 = sc.next();
                word2 = sc.next();
                System.out.println(graph.queryBridgeWords(G,word1, word2));
            }else if(chose.equals("2")==true){
                System.out.println("input a new sentence");
                String str;
                String text="";
                Scanner input = new Scanner(System.in);
                str = input.nextLine();
                str=graph.generateNewText(G,str);
                System.out.println(str);
            }else if(chose.equals("3")==true){
                System.out.println("input two worlds");
                Scanner nsc=new Scanner(System.in);
                word1 = nsc.next();
                word2 = nsc.next();
                System.out.println(graph.calcShortestPath(G,word1,word2));
            }else if(chose.equals("4")==true){
                System.out.println("input one worlds");
                Scanner nsc=new Scanner(System.in);
                word1 = nsc.next();
                graph.calcShortestPath2(G,word1);
            }else if(chose.equals("5")==true){
                System.out.print("random walk:");
                text=graph.randomWalk(G);
                System.out.println(text);
            }else if(chose.equals("#")==true){
                break;
            }
        }


    }


}

