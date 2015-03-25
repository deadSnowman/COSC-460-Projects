package edu.frostburg.cosc460.sethathomas.threadsockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create a Socket NameServer to respond to Socket Client
 * @version 2014-10-3
 * @author Seth A. Thomas
 */
public class NameServer implements Runnable {

  private final int port;
  private ServerSocket s;
  private ExecutorService pool;
  private boolean isRunning = false;

  /**
   * Default Constructor for NameServer
   * @param port ServerSocket port used by the name server
   */
  public NameServer(int port) {
    this.port = port;
  }

  public void start() {
    System.out.println("Starting server...");
    isRunning = true;
    pool = Executors.newCachedThreadPool();
    try {
      s = new ServerSocket(port);
      ClientHandler worker;

      while (true) {
        Socket client = s.accept();
        worker = new ClientHandler(client);
        pool.execute(worker);
      }
    } catch (IOException ex) {
      Logger.getLogger(NameServer.class.getName()).log(Level.SEVERE, null, ex);
      pool.shutdown();
      isRunning = false;
    }
  }

  /**
   * Main method
   * @param args 
   */
  public static void main(String[] args) {
    NameServer ns = new NameServer(6000);
    ns.start();
  }

  @Override
  public void run() {
    start();
  }
}
