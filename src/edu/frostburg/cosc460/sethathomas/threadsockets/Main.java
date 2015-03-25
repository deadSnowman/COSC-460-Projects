/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc460.sethathomas.threadsockets;

/**
 * Main class used to instantiate a client and server
 *
 * @version 2014-10-3
 * @author seth
 */
public class Main {

  public Main() {
    // Empty constructor
  }

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {

    // I am so sorry...
    try {
      //Server
      if (args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("server")) {
        NameServer ns = new NameServer(6000);
        ns.start();
      } // Client
      else if (args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("client")) {
        
        if (args[1].equalsIgnoreCase("f") && args[2] != null) {
          Client c = new Client("127.0.0.1", 6000, args[2]);
          c.start();
        }
        
      } // Usage
      else if (args[0].equalsIgnoreCase("h") || args[0].equalsIgnoreCase("help")) {
        usage();
      } else {
        usage();
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      usage();
    } catch (Exception e) {
      System.out.println("Could not connect to server.  Is there one running?");
    }

  }

  /**
   * Display usage
   */
  public static void usage() {
    System.out.println("usage: java -jar sethathomas460Projects.jar [s or server]");
    System.out.println("                                            [c or client] [f hosts_file]");
    System.out.println("                                            [h or help]");
  }
}
