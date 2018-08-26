/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connector;

import controller.ChatController;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Imalka Gunawardana
 */
public class ServerConnector {
    
    private ChatController chatController;
    private static ServerConnector serverConnector;
    
    private ServerConnector() throws NotBoundException, MalformedURLException, RemoteException {
        try {
            Properties properties = new Properties();
            File file = new File("properties/ConnectionProp.properties");
            FileReader inputStream = new FileReader(file.getAbsolutePath());
            properties.load(inputStream);
            chatController = (ChatController) Naming.lookup("rmi://" + properties.getProperty("ip") + ":" + properties.getProperty("port") + "/ChatServer");
        } catch (IOException ex) {
            Logger.getLogger(ServerConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ServerConnector getConnector() throws NotBoundException, MalformedURLException, RemoteException {
        if (serverConnector == null) {
            serverConnector = new ServerConnector();
        }
        return serverConnector;
    }
    
    public ChatController getChatController() {
        return chatController;
    }
    
}
