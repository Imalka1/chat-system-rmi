/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subject;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import observer.Observer;

/**
 *
 * @author Imalka Gunawardana
 */
public class Observable implements Subject {

    private ArrayList<Observer> observerList;

    public Observable() {
        observerList = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observerList) {
            try {
                observer.update();
            } catch (RemoteException ex) {
                Logger.getLogger(Observable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void notifyDevices() {
        for (Observer observer : observerList) {
            try {
                observer.updateList();
            } catch (RemoteException ex) {
                Logger.getLogger(Observable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
