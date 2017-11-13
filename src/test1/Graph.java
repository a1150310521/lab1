package test1;
import java.util.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileReader;
import java.lang.String;
import java.util.Arrays;


public class Graph {
	public static int j=0,t=0,i=0,f=0,n=0,g=0,txtlen=0,che=0,chek=0,check=0,way=0;
    public static String[] txt=new String[100];//锟街凤拷锟斤拷
    public static String[] bgw=new String[10];//锟街凤拷锟斤拷
    public static int [][] G=new int[txtlen][txtlen];
    public static int [][] path=new int[txtlen][txtlen];
    public static int [][] signal=new int[txtlen][txtlen];
    public static String newsc="",bword="",word1,word2;

    public static int[][] createDirectedGraph (String filename)throws Exception  {
        File file = new File(filename);//鏂囦欢浣嶇疆   D:\\JAVA\\2017_work\\work1\\file1.txt//
        FileReader reader = new FileReader(file);
        int fileLen = (int) file.length();//鏂囦欢鍐呭闀垮害
        int j=0,t=0,i=0,f=0,n=0,g=0;//txtLen 涓烘渶缁堝瓧绗︿覆闀垮害鍗冲崟璇嶄釜鏁?
        char[] chars = new char[fileLen];
        reader.read(chars);//鎸夊瓧绗﹁杩涙暟缁?
        for( i=0;i<fileLen;i++){
            if ((chars[i]<='z')&&(chars[i]>='a')){   //瑙勮寖鍐呭
                continue;
            }else if((chars[i]<='Z')&&(chars[i]>='A')){
                chars[i]=(char)(chars[i]+32);
            }
            else {
                chars[i] =' ';
            }
        }
        for(t=0;t<fileLen;t++){    //娑堥櫎杩囧鐨勭┖鏍?
            if((t==fileLen-1)&&(chars[t]==' ')) {
                fileLen--;
            }
            else if((chars[t]==' ')&&(chars[t+1]==' ')){
                for(i=t+1;i<fileLen;i++){
                    if(chars[i]==' ') { //璁＄畻澶氫綑绌烘牸涓暟
                        n++;
                    }else{
                        break;
                    }
                }
                f=0;
                while(f<n){   //娑堥櫎鎿嶄綔
                    for(j=t+1;j<fileLen-1;j++){
                        chars[j]=chars[j+1];
                    }
                    f++;
                    fileLen--;
                }
                n=0;
            }else{
                continue;
            }
        }
        for(i=0;i<fileLen;i++){ //璁＄畻鍗曡瘝闀垮害
            if(chars[i]==' '){
                n++;
            }
        }
        txtlen=n+1;
        j=0;
        for(i=0;i<fileLen;i++){ //鐢熸垚鍗曡瘝
            if(chars[i]!=' ') {
                if (i == 0) {
                    txt[j] =String.valueOf(chars[i]);
                } else {
                    txt[j]+= String.valueOf(chars[i]);
                }
            }else {
                j++;
                i++;
                txt[j] =String.valueOf(chars[i]);
            }
        }
        if(txt[txtlen-1].equals(" ")){
            txtlen--;
        }
        //System.out.println(txtlen);
        LinkList linkList=new LinkList();
        for(i=txtlen;i>0;i--){
            linkList.addhead(txt[i-1]);
        }
        linkList.displayList();
        int [][] G=new int[txtlen][txtlen];
        for(i=0;i<txtlen-1;i++){                               //鏋勯€犲浘G
            n=0;
            g=0;
            for(j=0;j<i;j++){
                f=0;
                if((txt[j].equals(txt[i]))==true){            //鍒ゆ柇绗竴涓瘝鏄惁鏈夐噸澶?
                    g++;                                      //闃叉閲嶅鍖归厤
                    if(g>1){
                        break;
                    }
                    for(t=0;t<i+1;t++){
                        if((txt[t].equals(txt[i+1]))==true){ //鍒ゆ柇绗簩涓瘝鏄惁鏈夐噸澶?
                            f++;
                            if(f>1){
                                break;
                            }
                            G[j][t]++;
                            n=1;

                        }else{
                            continue;
                        }
                    }
                    if(n==1){
                        continue;
                    }else{
                        G[j][i+1]++;
                        n=1;
                        break;
                    }

                }else{
                    continue;
                }
            }
            if(n==1){
                continue;
            }else{                                      //绗竴璇嶆湭閲嶅
                f=0;
                for(j=0;j<i+1;j++){
                    if((txt[j].equals(txt[i+1])==true)){ //鍒ゆ柇绗簩娆℃槸鍚﹂噸澶?
                        G[i][j]++;
                        f=1;
                        break;
                    }else{
                        continue;
                    }
                }
                if(f==1){
                    continue;
                }else{
                    G[i][i+1]++;
                }
            }
        }
        for(i=0;i<txtlen;i++){
            for(j=0;j<txtlen;j++){
                System.out.print(G[i][j]);
            }
            System.out.println("  "+txt[i]);
        }
        start(G);
        return G;
    }
    public static String queryBridgeWords2(int G[][],String word1, String word2) {
        che=0;
        bword="";
        for(i=0;i<txtlen-2;i++) {
            if((word1.equals(txt[i])==true)&&(word2.equals(txt[i+2])==true)) {
                bgw[che]=txt[i+1];
                bword=bword+" "+txt[i+1];
                che++;
            }
        }
        if(bword.equals("")==true){
            return "No bridge words from word1 to word2!" ;
        }else{
            check=1;
            return "The bridge words from word1 to word2 are: "+bword ;
        }
    }
    public static String queryBridgeWords(int G[][],String word1, String word2) {
        int che=0;
        String bword="";
        int txtlen=17;
        int chek=0;
        int check=0;
        String[]txt=new String[]{"to","explore","strange", "new", "worlds","to","seek","out","new","life","and","and","new","worlds","and","new","civilizations" };
        for(i=0;i<txtlen;i++) {
            if(word1.equals(txt[i])==true) {
                check=2;
            }
            if(word2.equals(txt[i])==true) {
                chek=1;
            }
        }
        if(check+chek<2) {
            return "No word1 or word2 in the graph";
        }
        else {
            for (i = 0; i < txtlen - 2; i++) {
                if ((word1.equals(txt[i]) == true) && (word2.equals(txt[i + 2]) == true)) {
                    //bgw[che] = txt[i + 1];
                    bword = bword + " " + txt[i + 1];
                    che++;
                }
            }
            if (bword.equals("") == true) {
                return "No bridge words from word1 to word2!";
            } else {
                check = 1;
                return "The bridge words from word1 to word2 are: " + bword;
            }
        }
    }
    public static String generateNewText(int G[][],String str) {
        String[] wordlist=str.split(" ");
        Random random=new Random();
        newsc="";
        for (j = 0; j < wordlist.length - 1; j++) {
            check=0;
            newsc = newsc + wordlist[j] + " ";
            queryBridgeWords2(G, wordlist[j], wordlist[j + 1]);
            if(check==1){
                n = random.nextInt(che);
                newsc = newsc + bgw[n] + " ";
            }
        }
        newsc = newsc + wordlist[wordlist.length - 1];
        return newsc;
    }
    public static String calcShortestPath(int G[][],String word1, String word2) {
        int k, v, w = 0;
        int check=0;
        int chek = 0;
        int txtlen=17;
        String[]txt=new String[]{"to","explore","strange", "new", "worlds","to","seek","out","new","life","and","and","new","worlds","and","new","civilizations" };
        int[][] D = new int[17][17];
        path = new int[17][17];
        String newsc = "";
        for (i = 0; i < txtlen; i++) {
            for (j = 0; j < txtlen; j++) {
                if (G[i][j] == 0) {
                    D[i][j] = 999;  //鏀惧ぇ绛変簬0鐨勭偣
                    path[i][j] = 0;
                } else {
                    D[i][j] = 1;
                    path[i][j] = j;
                }
            }
        }
        for (k = 0; k < txtlen; k++) {
            for (v = 0; v < txtlen; v++) {
                for (w = 0; w < txtlen; w++) {
                    if (D[v][w] > D[v][k] + D[k][w]) {
                        D[v][w] = D[v][k] + D[k][w];//璺緞鐩村皠缁忚繃涓嬫爣涓簁鐨勯《鐐?
                        path[v][w] = path[v][k];
                    }
                }
            }
        }
        for (i = 0; i < txtlen; i++) {
            for (j = 0; j < txtlen; j++) {
                if (i == j) {
                    D[i][j] = 0;
                    path[i][j] = 99;
                }
            }
        }
        for (i = 0; i < txtlen; i++) {
            if (word1.equals(txt[i]) == true) {
                check = 1;
            }
            if (word2.equals(txt[i]) == true) {
                chek = 1;
            }
        }
        if (check + chek < 2) {
            return "No word1 or word2 in the graph";
        } else {
            for (i = 0; i < txtlen; i++) {
                if (word1.equals(txt[i]) == true)
                    break;
            }
            for (j = 0; j < txtlen; j++) {
                if (word2.equals(txt[j]) == true)
                    break;
            }
            way = D[i][j];
            newsc = txt[i];
            while (true) {
                if (path[i][j] == 99) {
                    break;
                } else if (path[i][j] != 99) {
                    newsc = newsc + "->" + txt[path[i][j]];
                    i = path[i][j];
                }
            }
            if (way == 999) {
                return "no way";
            } else {
                /*GraghViz gv = new GraghViz();
                gv.addln(gv.start_graph());
                for (i = 0; i < txtlen - 1; i++) {
                    for (j = 0; j < txtlen; j++) {
                        if (G[i][j] != 0) {
                            gv.addln(txt[i] + "->" + txt[j] + "[color=greenyellow]");
                        }
                    }
                }
                gv.addln(newsc);  //锟斤拷锟铰凤拷锟?
                gv.addln(gv.end_graph());
                System.out.println(gv.getDotSource());
                String type = "png";
                File out = new File("C:\\Users\\gyd\\Desktop\\pic\\鏈€鐭矾寰勭敓鎴愬浘." + type);
                gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);*/
                return "the ShortestPath is: " + way + " " + newsc;
            }
        }
    }
    public static void calcShortestPath2(int G[][],String word1) {     //浠绘剰涓€鐐瑰埌鍏朵粬鎵€鏈夌偣鐨勬渶鐭矾寰?
        int k, v, w = 0;
        int[][] D = new int[txtlen][txtlen];
        path = new int[txtlen][txtlen];
        newsc = "";
        for (i = 0; i < txtlen; i++) {
            for (j = 0; j < txtlen; j++) {
                if (G[i][j] == 0) {
                    D[i][j] = 999;  //鏀惧ぇ绛変簬0鐨勭偣
                    path[i][j] = 0;
                } else {
                    D[i][j] = 1;
                    path[i][j] = j;
                }
            }
        }
        for (k = 0; k < txtlen; k++) {
            for (v = 0; v < txtlen; v++) {
                for (w = 0; w < txtlen; w++) {
                    if (D[v][w] > D[v][k] + D[k][w]) {
                        D[v][w] = D[v][k] + D[k][w];//璺緞鐩村皠缁忚繃涓嬫爣涓簁鐨勯《鐐?
                        path[v][w] = path[v][k];
                    }
                }
            }
        }
        for (i = 0; i < txtlen; i++) {
            for (j = 0; j < txtlen; j++) {
                if (i == j) {
                    D[i][j] = 0;
                    path[i][j] = 99;
                }
            }
        }


        for(i=0;i<txtlen;i++) {
            if(word1.equals(txt[i])==true) {
                check=1;
                break;
            }
        }
        if(check==0) {
            System.out.println("No "+word1+" in the graph");
        }else {
            for(i=0;i<txtlen;i++) {
                if (word1.equals(txt[i])==true) {
                    break;
                }
            }
            for(t=0;t<txtlen;t++) {
                word2=txt[t];
                for (i = 0; i < txtlen; i++) {
                    if (word1.equals(txt[i]) == true)
                        break;
                }
                for (j = 0; j < txtlen; j++) {
                    if (word2.equals(txt[j]) == true)
                        break;
                }
                way = D[i][j];
                newsc = txt[i];
                while (true) {
                    if (path[i][j] == 99) {
                        break;
                    } else if (path[i][j] != 99) {
                        newsc = newsc + "->" + txt[path[i][j]];
                        i = path[i][j];
                    }
                }
                if(word1.equals(word2)){
                    System.out.println(word1+" to "+word2+":"+"the ShortestPath is: 0");
                }else {
                    System.out.print(word1+" to "+word2+":");
                    if(way==999) {
                        System.out.println("there is no way between "+word1+" to "+word2);
                    }
                    else {
                        System.out.println("the ShortestPath is: "+way+" "+newsc);
                    }
                }
            }
        }
    }
    public static String randomWalk(int G[][]) {
        check=0;
        newsc="";
        signal=new int[txtlen][txtlen];
        for(i=0;i<txtlen;i++) {
            for(j=0;j<txtlen;j++) {
                signal[i][j]=0;
            }
        }
        Random random=new Random();
        i=random.nextInt(txtlen);
        for(t=0;t<i;t++) {  //濡傛灉鍗曡瘝閲嶅锛屼粠绗竴娆″嚭鐜颁綅缃捣
            if(txt[i].equals(txt[t])==true) {
                i=t;
                break;
            }
        }
        newsc=txt[i];
        while(true) {
            check=0;
            for(j=0;j<txtlen;j++) {  //鍒ゆ柇绗琲涓瘝鏈夋病鏈夐偦鎺ョ殑璇?
                if(G[i][j]!=0) {
                    check=1;
                }
            }
            if(check==1) {          //濡傛灉鏈夊氨鍦ㄨ繖浜涢偦鎺ヨ瘝涓殢鏈洪€夋嫨
                while(true) {
                    j=random.nextInt(txtlen);
                    if(G[i][j]!=0) {
                        break;
                    }
                }
                if(signal[i][j]!=1) {     //鍒ゆ柇閲嶅杈?
                    newsc=newsc+"->"+txt[j];
                    signal[i][j]=1;
                    i=j;
                }else {
                    newsc=newsc+"->"+txt[j];
                    //return newsc;
                    break;
                }
            }
            else{   //鏃犻偦鎺ヨ瘝
                break;
            }
        }
        newsc=newsc+";";
        GraghViz gv = new GraghViz();
        gv.addln(gv.start_graph());
        gv.addln(newsc);
        gv.addln(gv.end_graph());
        System.out.println(gv.getDotSource());
        String type = "png";
        File out = new File("C:\\Users\\gyd\\Desktop\\pic\\闅忔満娓歌蛋." + type);
        gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
        return newsc;
    }

    public static void start(int G[][])
    {
        GraghViz gv = new GraghViz();
        gv.addln(gv.start_graph());
        for(i=0;i<txtlen-1;i++) {
            for(j=0;j<txtlen;j++) {
                if(G[i][j]!=0) {
                    gv.addln(txt[i]+"->"+txt[j]+ "[color=greenyellow]");
                }
            }
        }
        gv.addln(gv.end_graph());
        System.out.println(gv.getDotSource());
        String type = "png";
        File out = new File("C:\\Users\\gyd\\Desktop\\pic\\灞曠ず鏈夊悜鍥?" + type);
        gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
    }



}
