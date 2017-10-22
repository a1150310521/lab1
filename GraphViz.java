package com.graph;
//1.4ÐÞ¸Ä2
//GraphViz.java - a simple API to call dot from Java programs
/*$Id$*/
/*
******************************************************************************
*                                                                            *
*              (c) Copyright 2003 Laszlo Szathmary                           *
*                                                                            *
* This program is free software; you can redistribute it and/or modify it    *
* under the terms of the GNU Lesser General Public License as published by   *
* the Free Software Foundation; either version 2.1 of the License, or        *
* (at your option) any later version.                                        *
*                                                                            *
* This program is distributed in the hope that it will be useful, but        *
* WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY *
* or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public    *
* License for more details.                                                  *
*                                                                            *
* You should have received a copy of the GNU Lesser General Public License   *
* along with this program; if not, write to the Free Software Foundation,    *
* Inc., 675 Mass Ave, Cambridge, MA 02139, USA.                              *
*                                                                            *
******************************************************************************
*/
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
/**
* <dl>.
* <dt>Purpose: GraphViz Java API
* <dd>
*
* <dt>Description:
* <dd> With this Java class you can simply call dot
*      from your Java programs
* <dt>Example usage:
* <dd>
* <pre>
*    GraphViz gv = new GraphViz();
*    gv.addln(gv.start_graph());
*    gv.addln("A -> B;");
*    gv.addln("A -> C;");
*    gv.addln(gv.end_graph());
*    System.out.println(gv.getDotSource());
*
*    String type = "gif";
*    File out = new File("out." + type);   // out.gif in this example
*    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
* </pre>
* </dd>
*
* </dl>
*
* @version v0.4, 2011/02/05 (February)
* -- Patch of Keheliya Gallaba is added. Now you
* can specify the type of the output file:
*  gif, dot, fig, pdf, ps, svg, png, etc.
* @version v0.3, 2010/11/29 (November) -- Windows support + ability
* to read the graph from a text file
* @version v0.2, 2010/07/22 (July) -- bug fix
* @version v0.1, 2003/12/04 (December) -- first release
* @author  Laszlo Szathmary
* (<a href="jabba.laci@gmail.com">jabba.laci@gmail.com</a>)
*/
public class GraphViz {
/**
 * The dir. where temporary files will be created.
 */
//private static String TEMP_DIR = "/tmp"; // Linux
private static String tempDIR = "E:\\graphviz\\temp";
/**
 * Where is your dot program located? It will be called externally.
 */
// private static String DOT = "/usr/bin/dot"; // Linux
private static String dotpath = "E:\\graphviz\\bin\\dot.exe";
/**
 * The source of the graph written in dot language.
 */
private StringBuilder graph = new StringBuilder();
/**
 * Constructor: creates a new GraphViz object that will contain
 * a graph.
 */
public GraphViz() {
	//contructor
}

/**
 * Returns the graph's source description in dot language.
 * @return Source of the graph in dot language.
 */
public String getDotSource() {
   return graph.toString();
}
/**
 * Adds a string to the graph's source (without newline).
 * @param line line
 */
public void add(final String line) {
   graph.append(line);
}
/**
 * Adds a string to the graph's source (with newline).
 * @param line line
 */
public void addln(final String line) {
   graph.append(line);
   graph.append('\n');
}
/**
 * Adds a newline to the graph's source.
 */
public void addln() {
   graph.append('\n');
}
/**
 * Returns the graph as an image in binary format.
 * @param dotsource Source of the graph to be drawn.
 * @param type Type of the output image to be produced,
 *  e.g.: gif, dot, fig, pdf, ps, svg, png.
 * @return A byte array containing the image of the graph.
 */
public byte[] getGraph(final String dotsource, final String type) {
   File dot;
   byte[] imgstream = null;

   try {
      dot = writeDotSourceToFile(dotsource);
      if (dot != null) {
         imgstream = getimgstream(dot, type);
         if (!dot.delete()) {
            System.out.println("Warning: "
             + dot.getAbsolutePath() + " could not be deleted!");
         }
         return imgstream;
      }
      return null;
   } catch (java.io.IOException ioe) {
      return null;
   }
}
/**
 * Writes the graph's image in a file.
 * @param img   A byte array containing the image of the graph.
 * @param file  Name of the file to where we want to write.
 * @return Success: 1, Failure: -1
 */
public int writeGraphToFile(final byte[] img, final String file) {
   final File toFile = new File(file);
   return writeGraphToFile(img, toFile);
}
/**
 * Writes the graph's image in a file.
 * @param imgbytes   A byte array containing the image of the graph.
 * @param toFile    A File object to where we want to write.
 * @return Success: 1, Failure: -1
 */
public int writeGraphToFile(final byte[] imgbytes, final File toFile) {
   try {
      final FileOutputStream fos = new FileOutputStream(toFile);
      fos.write(imgbytes);
      fos.close();
   } catch (java.io.IOException ioe) {
       ioe.printStackTrace();
       return -1;
   }
   return 1;
}
/**
 * It will call the external dot program, and return the image in
 * binary format.
 * @param dot Source of the graph (in dot language).
 * @param type Type of the output image to be produced,
 *  e.g.: gif, dot, fig, pdf, ps, svg, png.
 * @return The image of the graph in .gif format.
 */
private byte[] getimgstream(final File dot, final String type) {
   File img;
   byte[] imgstream = null;
try {
      img = File.createTempFile("graph_", "." + type,
             new File(GraphViz.tempDIR));
      final Runtime runtime = Runtime.getRuntime();

      // patch by Mike Chenault
      final String[] args = {dotpath, "-T" + type, dot.getAbsolutePath(),
            "-o", img.getAbsolutePath()};
      final Process process = runtime.exec(args);

      process.waitFor();
      final FileInputStream inputstream = new FileInputStream(img.getAbsolutePath());
      imgstream = new byte[inputstream.available()];
      inputstream.read(imgstream);
      // Close it if we need to
      if (inputstream != null) {
          inputstream.close();
      }
      if (!img.delete()) {
         System.out.println("Warning: "
         +
         img.getAbsolutePath() + " could not be deleted!");
      }
   } catch (java.io.IOException ioe) {
      System.out.println("Error:    in I/O processing of tempfile in dir "
            + GraphViz.tempDIR + "\n");
      System.out.println("       or in calling external command");
      ioe.printStackTrace();
   } catch (java.lang.InterruptedException ie) {
      System.out.println(
           "Error: the execution of the external program was interrupted"
      );
      ie.printStackTrace();
   }
return imgstream;   }
/**
 * Writes the source of the graph in a file, and returns the written file
 * as a File object.
 * @param str Source of the graph (in dot language).
 * @throws java.io.IOException exception
 * @return The file (as a File object) that contains the source of the graph.
 */
public File writeDotSourceToFile(final String str) throws java.io.IOException {
   File temp;
   try {
      temp = File.createTempFile("graph_", ".dot.tmp",
          new File(GraphViz.tempDIR));
      final FileWriter fout = new FileWriter(temp);
      fout.write(str);
      fout.close();
   } catch (java.io.IOException e) {
      System.out.println(
            "Error: I/O error while writing the dot source to temp file!"
      );
      return null;
   }
   return temp;
}
/**
 * Returns a string that is used to start a graph.
 * @return A string to open a graph.
 */
public String startgraph() {
   return "digraph G {";
}
/**
 * Returns a string that is used to end a graph.
 * @return A string to close a graph.
 */
public String endgraph() {
   return "}";
}
/**
 * Read a DOT graph from a text file.
 *
 * @param input Input text file containing the DOT graph
 * source.
 */
public void readSource(final String input) {
 final StringBuilder sbuilder = new StringBuilder();
 try {
  final FileInputStream fis = new FileInputStream(input);
  final DataInputStream dis = new DataInputStream(fis);
  final BufferedReader breader = new BufferedReader(new InputStreamReader(dis));
  String line = breader.readLine();
  while (line != null) {
   sbuilder.append(line);
   line = breader.readLine();
  }
  dis.close();
  breader.close();
 } catch (java.io.IOException e) {
  System.out.println("Error: " + e.getMessage());
 }
 this.graph = sbuilder;
}
 
} // end of class GraphViz
