/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;
import javax.swing.JButton;
import model.Group;
import model.ENT;
import model.Folder;
import model.User;

/**
 *
 * @author hugo
 */
public class ENTController implements ActionListener{
    private ENT model;

    public ENTController(ENT model) {
        this.model = model;
    }
    
    public void subscribe(Observer view){
        model.addObserver(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton)e.getSource();
        
        if ( "add-group".equals(src.getName()) ){
            Group grp = new Group("test");
            model.add(grp);
        }
        
        if ( "add-user".equals(src.getName()) ){
            // choose grp
            Group grp = model.getGroup(0);
           // grp.getCtrl().addUser(new User("toto"));
        }
    }

    public void createGroup(String name) {
        Group grp = new Group(name);
        grp.addUser(model.getConnectedUser());
        model.add(grp);
    }

    public void connectUser(User user) {
        model.setConnectedUser(user);
    }

    public void joinGroup(String grpName) {
        model.getGroup(grpName).addUser(model.getConnectedUser());
    }
    
    public ArrayList<Group> getUserGroups() {
        return model.getConnectedUser().getUserGroups();
    }
    
    public Group getUserGroup(String name) {
         ArrayList<Group> grps = model.getConnectedUser().getUserGroups();
         
         for (Group group : grps) {
            if ( group.getName().equalsIgnoreCase(name) )
                return group;
         }
         
         return null;
    }

    public void createFolder(Group grp, String chemin, String name ) {
        Folder folder = new Folder(name);
        
        if (chemin == null)
            grp.addStuff(folder);
        else{
            String[] folders = chemin.split("/");
            
            Folder root = (Folder)grp.getStuff(folders[0]);
            
            if (root != null) {
                // On boucle sur les fils pr arriver au bout du chemin
            }
        }
    }
    
}
