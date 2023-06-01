//client
package udpclient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;

public class Udpclient {

 
    public static void main(String[] args) throws Exception {
        DatagramSocket cs= new DatagramSocket();
        InetAddress ip=(InetAddress) InetAddress.getByName("172.17.251.158");
        byte[] data=new byte[1024];
        String yourmsg="hello From Short Hair F";
        data=yourmsg.getBytes();
          DatagramPacket pkt = new DatagramPacket(data, data.length,ip,5000);
          cs.send(pkt);
          cs.close();
    }
    
}


package udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author deeewaaann
 */
public class Udpserver {

  
    public static void main(String[] args) throws Exception {
        DatagramSocket ss= new DatagramSocket(5000);
        byte[] data = new byte[1024];
        
        DatagramPacket packet = new DatagramPacket(data, data.length);
        ss.receive(packet);
        InetAddress ip= packet.getAddress();
        String st = new String(data, 0,data.length);
        System.out.println(st);
    }
    
}




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author deeewaaann
 */
public class Client {
  public static void main(String[] args) throws IOException {
      try (Socket s = new Socket("172.17.251.158",5000)) {
          PrintWriter pw=new PrintWriter(s.getOutputStream());
          pw.println("ShortHairF Dewan");
          pw.flush();
          
          InputStreamReader sr=new InputStreamReader(s.getInputStream());
          BufferedReader br=new BufferedReader(sr);
          String data=br.readLine();
          System.out.println(data);
      }
        
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author deeewaaann
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
         ServerSocket ss = new ServerSocket(5000);
       while(true){
       
        Socket s=ss.accept();
        System.out.println("Connected: "+s.getInetAddress());
        
        InputStreamReader sr=new InputStreamReader(s.getInputStream());
        BufferedReader br=new BufferedReader(sr);
        String data=br.readLine();
        
        PrintWriter pw=new PrintWriter(s.getOutputStream());
       pw.println("Data from server to client");
       pw.flush();
        
        s.close();
       }
        
    }
    
}
