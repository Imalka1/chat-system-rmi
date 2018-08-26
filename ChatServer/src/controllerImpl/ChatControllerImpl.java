/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerImpl;

import controller.ChatController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import observer.Observer;
import subject.Observable;
import subject.Subject;

/**
 *
 * @author Imalka Gunawardana
 */
public class ChatControllerImpl extends UnicastRemoteObject implements ChatController {

    private String message = "";
    private ArrayList<String> devices = new ArrayList<>();
    private Subject observable = new Observable();

    public ChatControllerImpl() throws RemoteException {

    }

    @Override
    public void setMessage(String message) throws RemoteException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:a");
        this.message = message + " / " + dateFormat.format(new Date()) + ")" + System.lineSeparator();
        fileWrite();
        updateObservers();
    }

    @Override
    public String getMessage() throws RemoteException {
        fileRead();
        return message;
    }

    @Override
    public ArrayList<String> getAllDevices() throws RemoteException {
        return devices;
    }

    @Override
    public void addObserver(Observer observer,String device) throws RemoteException {
        observable.addObserver(observer);
        devices.add(device);
        updateDevices();
    }

    @Override
    public void updateObservers() throws RemoteException {
        observable.notifyObserver();
    }

    @Override
    public void removeObserver(Observer observer,String device) throws RemoteException {
        observable.deleteObserver(observer);
        devices.remove(device);
        updateDevices();
    }

    @Override
    public void updateDevices() throws RemoteException {
        observable.notifyDevices();
    }

    @Override
    public void fileWrite() throws RemoteException {
        try {
            File f = new File("files/Chat.txt");
            FileWriter fileWriter = new FileWriter(f.getAbsolutePath(), true);
            fileWriter.write(message);
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(ChatControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void fileRead() throws RemoteException {
        try {
            File f = new File("files/Chat.txt");
            FileReader fileReader = new FileReader(f.getAbsolutePath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            message = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                message += line + "\n";
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChatControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
