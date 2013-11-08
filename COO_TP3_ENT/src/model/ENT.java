/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ENTController;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author hugo
 */
public class ENT extends Observable {
    private ArrayList<Group> groups;
    private ENTController ctrl;

    public ENT() {
        this.groups = new ArrayList<>();
    }

    /** Getters & Setters **/ 
    
    public void setCtrl(ENTController ctrl){
        this.ctrl = ctrl;
    }
     
    public void add(Group g) {
        groups.add(g);
        setChanged();
        notifyObservers("GROUP");
    }

    public void remove(Group g) {
        groups.remove(g);
        setChanged();
        notifyObservers("GROUP");
    }

    public Group getGroup(int index) {
        return groups.get(index);
    }
    
}
