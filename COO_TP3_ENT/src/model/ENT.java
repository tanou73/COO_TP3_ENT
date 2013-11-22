/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Observable;
import utils.BadArgumentException;
import utils.UnauthorisedException;

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

    public void addRelationName(String name) {
        relationsNames.add(name);
    }

    public void removeRelationName(String name) {
        for (String relation : relationsNames) {
            if (relation.equals(name)) {
                relationsNames.remove(name);
            }
        }
    }

    /**
     * Getters & Setters *
     */
    public void addGroup(Group g) {
        groups.add(g);
        setChanged();
        notifyObservers("GROUP");
    }

    public void removeGroup(Group g) {
        groups.remove(g);
        setChanged();
        notifyObservers("GROUP");
    }
    /*
     public Group getGroup(int index) {
     return groups.get(index);
     }
     */

    public Group getGroup(String name) throws BadArgumentException {
        for (Group group : groups) {
            if (group.getName().equalsIgnoreCase(name)) {
                return group;
            }
        }
        throw new BadArgumentException("unknown group");
    }

    public User getConnectedUser() throws UnauthorisedException {
        if (connectedUser == null) {
            throw new UnauthorisedException("User not connected");
        }
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

    public boolean isAnExistingRelationName(String relationName) {
        for (String name : relationsNames) {
            if (name.equalsIgnoreCase(relationName)) {
                return true;
            }
        }
        return false;
    }
}
