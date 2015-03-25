package edu.frostburg.cosc460.sethathomas.hellosocketserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create a Socket Server to respond to Socket Client
 * @version 2014-9-24
 * @author Seth A. Thomas
 */
public class HelloSocketServer implements Runnable {

  private Socket server;
  private ServerSocket s;
  private int port;

  /**
   * Default Constructor for HelloSocketServer
   * @param port ServerSocket port used by HelloSocketServer
   */
  public HelloSocketServer(int port) {
    this.port = port;
  }

  /**
   * Initialize HelloSocketServer
   */
  public void start() {
    System.out.println("Starting server...");
    try {
      s = new ServerSocket(port);
      while (true) {
        server = s.accept();
        PrintWriter pw = new PrintWriter(server.getOutputStream(), true);
        System.out.println("Server is sending: Hello World!");
        pw.println("Hello World!");

        server.close();
      }
    } catch (IOException ex) {
      Logger.getLogger(HelloSocketServer.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Main method
   * @param args 
   */
  public static void main(String[] args) {
    ExecutorService es = Executors.newCachedThreadPool();
    es.submit(new HelloSocketServer(6000));
    es.submit(new HelloSocketClient("127.0.0.1", 6000));

    es.shutdown();
  }

  @Override
  public void run() {
    start();
  }
}
