/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author hugo
 */
public class User {

    /**
     * user's groups *
     */
    protected ArrayList<Group> userGroups;
    protected String name;

    /**
     * Constructor *
     */
    public User(String name) {
        this.name = name;
        this.userGroups = new ArrayList<Group>();
    }

    /**
     * add a group *
     */
    public void addGroup(Group grpToAdd) {
        this.userGroups.add(grpToAdd);
    }

    /**
     * remove a group *
     */
    public void removeGroup(Group grpToRemove) {
        this.userGroups.remove(grpToRemove);
    }

    /**
     * Getters & setters *
     */
    public ArrayList<Group> getUserGroups() {
        return userGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String toRet = "\n -- USER -- \n";

        toRet += "groups : \n";
        for (Group group : userGroups) {
            toRet += group.getName() + "\n";
        }

        return toRet + "name=" + name + " \n\n ";
    }
}
