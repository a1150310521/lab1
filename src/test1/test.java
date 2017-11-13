package test1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;

import org.junit.Test;

public class test {

	
	public static int j=0,t=0,i=0,f=0,n=0,g=0,txtlen=0;
	public static int [][] G=new int[txtlen][txtlen];
	 public static String[] txt=new String[100];
	 
	@Test
	public void testcalcShortestPath () throws Exception{
		String filename=new String("D:\\Users\\ASUS\\Desktop\\Lab\\file1.txt");
		 File file = new File(filename);//鏂囦欢浣嶇疆   D:\\JAVA\\2017_work\\work1\\file1.//
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
		Graph tt =new Graph();
		String word1="civilizations";
		String word2="to";
		String result =tt.calcShortestPath(G, word1,word2);
		assertEquals("no way" ,result);
		//fail("Not yet implemented");
	}

}
