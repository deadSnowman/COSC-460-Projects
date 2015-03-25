package edu.frostburg.cosc460.sethathomas.threadsockets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create a Client that requests it's ip address by sending it's host name to
 * the server
 *
 * @version 2014-10-3
 * @author Seth A. Thomas
 */
public class Client implements Runnable {

  private Socket client;
  private final String host;
  private final int port;
  private final String file;
  private final ArrayList<String> hosts;

  /**
   * Default constructor for Client
   *
   * @param host client host address
   * @param port Socket port
   * @param file file with hosts
   */
  public Client(String host, int port, String file) {
    this.host = host;
    this.port = port;
    this.file = file;
    hosts = new ArrayList();
  }

  /**
   * Initialize Client Handler (worker)
   */
  public void start() {
    System.out.println("Starting client...");
    try {

      // I/O
      BufferedReader hostsReader = new BufferedReader(new FileReader(file));
      String line;
      while ((line = hostsReader.readLine()) != null) {
	System.out.println();
	hosts.add(line);
      }

      client = new Socket(host, port);

      PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
      BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

      for (String s : hosts) {
	System.out.println("Client is sending " + s);
	pw.println(s);
      }

      while ((line = br.readLine()) != null) {
	System.out.println("Client is reading: " + line);
      }

      client.close();

    } catch (FileNotFoundException ex) {
      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("Unable to read file");
    } catch (UnknownHostException ex) {
      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    Client c = new Client("127.0.0.1", 6000, "hosts.txt");
    c.start();
  }

  @Override
  public void run() {
    start();
  }
}
