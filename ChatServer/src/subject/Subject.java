/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subject;

import observer.Observer;

/**
 *
 * @author Imalka Gunawardana
 */
public interface Subject {

    void addObserver(Observer observer);

    void deleteObserver(Observer observer);

    void notifyObserver();

    void notifyDevices();
}
