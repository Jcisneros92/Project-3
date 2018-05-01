package project3;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Arrays;

//import sun.misc.IOUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


public class Chan {
	  public static void main(String[] argv) throws Exception {
		  int port = Integer.parseInt(argv[1]);
	    Socket sock = new Socket(argv[0], port);
	    byte[] mybytearray = new byte[1024];

	    //Start rtt timer
	    long startTime = System.nanoTime();
	    
	    //Create output stream for server
	    PrintStream os = new PrintStream(sock.getOutputStream());

	    os.println("GET /"+ argv[2] +" HTTP/1.1");
	    os.println("Host: localhost:"+port);
	    os.println("");
	    os.flush();
	    
	    //Create inputstream for server
	    InputStream is = sock.getInputStream();

//Copy the incoming messages from InputStream to byte[] array
byte[] bytes = readFully(is);

//Print bytes array
System.out.println(new String(bytes, 0));

//Display server socket info
long elapsedTime = System.nanoTime() - startTime;
System.out.println("RTT: " +elapsedTime);
System.out.println("Connection Received From: "+argv[0]);
System.out.println("Server Port Number:"+port);
System.out.println("Peer Name: ("+ sock.getLocalAddress() + "," + sock.getPort()+")");
System.out.println("Protocol: HTTP");

	    sock.close();
	  }
	  
	  
	  //Receives inputstream and puts information into a byte[] array and returns it
	  public static byte[] readFully(InputStream input) throws IOException
	  {
		  //String CRLF = "\r\n";
	      byte[] buffer = new byte[1024];
	      int bytesRead;
	      ByteArrayOutputStream output = new ByteArrayOutputStream();
	      while ((bytesRead = input.read(buffer)) != -1)
	      {
	          output.write(buffer, 0, bytesRead);
	      }
	      return output.toByteArray();
	  }
	  
	}
