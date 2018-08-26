/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Imalka Gunawardana
 */
public interface Observer extends Remote {

    void update() throws RemoteException;
    
    void updateList() throws RemoteException;

    void remove(String device) throws RemoteException;
}
