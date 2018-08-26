/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import connector.ServerConnector;
import controller.ChatController;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import view.ChatBoxView;

/**
 *
 * @author Imalka Gunawardana
 */
public class ChatBox extends UnicastRemoteObject implements Observer {

    private ChatController chatController;

    private ChatBoxView chatBoxView;

    public ChatBox(String text) throws RemoteException {
        try {
            chatController = ServerConnector.getConnector().getChatController();
            chatBoxView = new ChatBoxView(this,text);
            chatController.addObserver(this, text);
        } catch (NotBoundException ex) {
            Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() throws RemoteException {
        chatBoxView.updateMessage();
    }

    @Override
    public void remove(String device) throws RemoteException {
        chatController.removeObserver(this, device);
    }

    @Override
    public void updateList() throws RemoteException {
        chatBoxView.updateList();
    }
}
