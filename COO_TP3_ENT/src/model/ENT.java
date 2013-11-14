/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author hugo
 */
public class ENT extends Observable {

    private ArrayList<Group> groups;
    private User connectedUser;
    private ArrayList<String> relationsNames;

    public ENT() {
        this.groups = new ArrayList<>();
        this.relationsNames = new ArrayList<>();
    }

    /**
     * Getters & Setters *
     */
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

    public Group getGroup(String name) {
        for (Group group : groups) {
            if (group.getName().equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<String> getRelationsNames() {
        return relationsNames;
    }

    public void setRelationsNames(ArrayList<String> relationsNames) {
        this.relationsNames = relationsNames;
    }
    
    
}
