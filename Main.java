package com.graph;
import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
public class Main {
//1.4修改4
    public static File filename;
    public static int j=0,t=0,i=0,f=0,n=0,g=0,txtlen=0,che=0,chek=0,check=0,way=0;
    public static String[] txt=new String[100];//�ַ���
    public static String[] bgw=new String[10];
    public static int [][] G=new int[txtlen][txtlen];
    public static int [][] path=new int[txtlen][txtlen];
    public static int [][] signal=new int[txtlen][txtlen];
    public static String newsc="";
    public static String word1, word2, bword,text;
    public static void main(String[] args) throws Exception {
        System.out.println("请输入文本文件所在位置：");
        Scanner scan = new Scanner(System.in);
        String filename = scan.next();
        G = Graph.createDirectedGraph(filename);
        while(true){
            System.out.println("请选择所需要的功能：");
            System.out.println("1.查询桥接词。2.根据桥接词生成新文本。3.查询两个单词的最短路径。4.查询某一单词到其他所有单词的最短路径。5.随机游走。");
            System.out.println("按 # 结束");
            String chose=scan.next();
            if(chose.equals("1")==true){
                System.out.println("input word1 and word2");  //bridgewords
                Scanner sc = new Scanner(System.in);
                word1 = sc.next();
                word2 = sc.next();
                System.out.println(Graph.queryBridgeWords(G,word1, word2));
            }else if(chose.equals("2")==true){
                System.out.println("input a new sentence");
                String str;
                Scanner input = new Scanner(System.in);
                str = input.nextLine();
                str= Graph.generateNewText(G,str);
                System.out.println(str);
            }else if(chose.equals("3") == true){
                System.out.println("input two worlds");
                Scanner nsc = new Scanner(System.in);
                word1 = nsc.next();
                word2 = nsc.next();
                System.out.println(Graph.calcShortestPath(G,word1,word2));
            }else if(chose.equals("4")==true){
                System.out.println("input one worlds");
                Scanner nsc = new Scanner(System.in);
                word1 = nsc.next();
                Graph.calcShortestPath2(G,word1);
            }else if(chose.equals("5")==true){
                System.out.print("random walk:");
                text=Graph.randomWalk(G);
                System.out.println(text);
            }else if(chose.equals("#")==true){
                break;
            }
        }
    }
}