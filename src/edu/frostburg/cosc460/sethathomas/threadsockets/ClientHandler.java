/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc460.sethathomas.threadsockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A client worker for the server to use
 *
 * @version 2014-10-3
 * @author seth
 */
public class ClientHandler implements Runnable {

  private final Socket client;

  public ClientHandler(Socket client) {
    this.client = client;
  }

  @Override
  public void run() {
    try {
      String ip;
      BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
      PrintWriter pw = new PrintWriter(client.getOutputStream(), true);

      String line;
      while ((line = br.readLine()) != null) {
        InetAddress hAddr = InetAddress.getByName(line);
        ip = hAddr.getHostAddress();
        pw.println("IP address: " + ip);
      }

      client.close();

    } catch (IOException ex) {
      Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
