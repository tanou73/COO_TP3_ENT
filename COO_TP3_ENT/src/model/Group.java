/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.GroupController;
import java.util.ArrayList;

/**
 *
 * @author hugo
 */
public class Group {
    /** users in the group **/
    protected ArrayList<User> users;
    /** list of objects contained into the group **/
    protected ArrayList<Stuff> stuffs;
    /** name of the group **/ 
    protected String name;

    private GroupController ctrl;
    
    /** Constructor **/
    public Group(String name) {
        this.name = name;
        users = new ArrayList<>();
        stuffs = new ArrayList<>();
    }

    /** add a group **/
    public void addUser(User userToAdd) {
        this.users.add(userToAdd);
        userToAdd.addGroup(this);
    }
    
    /** remove a group **/
    public void removeUser(User userToRemove) {
        this.users.remove(userToRemove);
        userToRemove.removeGroup(this);
    }
    
    /** add a group **/
    public void addStuff(Stuff stuffToAdd) {
        this.stuffs.add(stuffToAdd);
    }
    
    /** remove a group **/
    public void removeStuff(Stuff stuffToRemove) {
        this.stuffs.remove(stuffToRemove);
    }
    
    /** Getters & Setters **/
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setCtrl(GroupController ctrl) {
        this.ctrl = ctrl;
    }
    
    public GroupController getCtrl(){
        return ctrl;
    }

    @Override
    public String toString() {
        String toReturn = "\n -- Group "+ name +" -- \n";
        
        toReturn += "users : \n";        
        for (User user : users) {
            toReturn += user.toString() + "\n";
        }
        
        toReturn += "stuffs : \n";        
        for (Stuff stuff : stuffs) {
            toReturn += stuff.toString() + "\n";
        }
        
        return toReturn + " \n\n ";
    }
    
}
