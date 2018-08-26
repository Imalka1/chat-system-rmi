/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import observer.Observer;

/**
 *
 * @author Imalka Gunawardana
 */
public interface ChatController extends Remote {

    void setMessage(String message) throws RemoteException;

    String getMessage() throws RemoteException;

    ArrayList<String> getAllDevices() throws RemoteException;

    void addObserver(Observer observer, String device) throws RemoteException;

    void updateObservers() throws RemoteException;

    void updateDevices() throws RemoteException;

    void removeObserver(Observer observer, String device) throws RemoteException;

    void fileWrite() throws RemoteException;

    void fileRead() throws RemoteException;
}
