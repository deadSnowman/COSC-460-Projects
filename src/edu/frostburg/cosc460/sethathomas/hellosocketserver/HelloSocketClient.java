package edu.frostburg.cosc460.sethathomas.hellosocketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create a Socket Client to send message to Socket Server
 * @version 2014-9-24
 * @author Seth A. Thomas
 */
public class HelloSocketClient implements Runnable {

  private Socket client;
  private String host;
  private int port;

  /**
   * Default constructor for HelloSocketClient
   * @param host client host address
   * @param port Socket port
   */
  public HelloSocketClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  /**
   * Initialize HelloSocketClient
   */
  public void start() {
    System.out.println("Starting client...");
    try {
      client = new Socket(host, port);

      PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
      BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

      String line;
      while ((line = br.readLine()) != null) {
        System.out.println("Client is reading: " + line);
      }

      client.close();

    } catch (UnknownHostException ex) {
      Logger.getLogger(HelloSocketClient.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(HelloSocketClient.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Main method
   * @param args 
   */
  public static void main(String[] args) {
    HelloSocketClient hsc = new HelloSocketClient("127.0.0.1", 6000);
    hsc.start();
  }

  @Override
  public void run() {
    start();
  }
}
