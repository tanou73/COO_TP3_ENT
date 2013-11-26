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
    private ArrayList<RelationType> relationTypes;

    public ENT() {
        this.groups = new ArrayList<>();
        this.relationTypes = new ArrayList<>();
    }

    public void addRelationName(String name, String invert) {
        relationTypes.add(new RelationType(name, invert));
    }

    public void removeRelationName(String name) {
        for (RelationType relType : relationTypes) {
            if (relType.getName().equals(name)) {
                relationTypes.remove(relType);
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

    public ArrayList<RelationType> getRelationTypes() {
        return relationTypes;
    }

    public void setRelationTypes(ArrayList<RelationType> relationTypes) {
        this.relationTypes = relationTypes;
    }

    public boolean isAnExistingRelationName(String relationName) {
        for (RelationType relType : relationTypes) {
            if (relType.getName().equalsIgnoreCase(relationName)) {
                return true;
            }
        }
        return false;
    }
}
