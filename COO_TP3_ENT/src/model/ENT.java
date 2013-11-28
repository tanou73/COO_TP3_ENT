/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;
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
    private ArrayList<Category> categories;

    public ENT() {
        this.groups = new ArrayList<>();
        this.relationTypes = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public void addRelationType(RelationType rel) {
        relationTypes.add(rel);
    }

    public void removeRelationType(String name) {        
        for (Iterator<RelationType> it = relationTypes.iterator(); it.hasNext();) {
            RelationType rel = it.next();
            if (rel.getName().equals(name)) {
                it.remove();
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

    public boolean isAnExistingRelationType(String relationName) {
        for (RelationType relType : relationTypes) {
            if (relType.getName().equalsIgnoreCase(relationName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnExistingCategory(String name) {
        for (Category cat : categories) {
            if (cat.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean addCategory(Category cat) {
        return categories.add(cat);
    }

    public void removeCategory(String name) {
        for (Category cat : categories) {
            if (cat.getName().equalsIgnoreCase(name)) {
                categories.remove(cat);
            }
        }
    }
    
    public Category getCategory(String name) throws BadArgumentException{
        for (Category cat : categories) {
            if (cat.getName().equalsIgnoreCase(name)) {
                return cat;
            }
        }
        throw new BadArgumentException("unknown category");
    }
}
