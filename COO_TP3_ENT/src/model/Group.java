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
public class Group {

    /**
     * users in the group *
     */
    protected ArrayList<User> users;
    /**
     * list of objects contained into the group *
     */
    protected Folder rootFolder;
    /**
     * name of the group *
     */
    protected String name;
    /**
     * owner of the group *
     */
    protected User owner;

    /**
     * Constructor *
     */
    public Group(String name, User owner) {
        this.name = name;
        this.owner = owner;
        users = new ArrayList<>();
        rootFolder = new Folder(name);

    }

    /**
     * add a group *
     */
    public void addUser(User userToAdd) {
        this.users.add(userToAdd);
        userToAdd.addGroup(this);
    }

    /**
     * remove a group *
     */
    public void removeUser(User userToRemove) {
        this.users.remove(userToRemove);
        userToRemove.removeGroup(this);
    }

    /**
     * Getters & Setters *
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public Folder getRootFolder() {
        return rootFolder;
    }

    public void setRootFolder(Folder rootFolder) {
        this.rootFolder = rootFolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        String toReturn = "\n -- Group " + name + " -- \n";

        toReturn += "users : \n";
        for (User user : users) {
            toReturn += user.toString() + "\n";
        }

        toReturn += "stuffs : \n" + rootFolder.toString();

        return toReturn + " \n\n ";
    }

    public static Group findGroupByName(ArrayList<Group> groups, String name) {
        for (Group group : groups) {
            if (group.getName().equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }
}
