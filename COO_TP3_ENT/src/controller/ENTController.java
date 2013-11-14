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
import model.Relation;
import model.Stuff;
import model.User;

/**
 *
 * @author hugo
 */
public class ENTController implements ActionListener {

    private ENT model;

    public ENTController(ENT model) {
        this.model = model;
    }

    public void subscribe(Observer view) {
        model.addObserver(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();

        if ("add-group".equals(src.getName())) {
            Group grp = new Group("test");
            model.add(grp);
        }

        if ("add-user".equals(src.getName())) {
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
            if (group.getName().equalsIgnoreCase(name)) {
                return group;
            }
        }

        return null;
    }

    public Folder createFolder(Group grp, Folder parent, String name) {
        Folder folder = new Folder(name);

        if (parent == null) {
            grp.getRootFolder().addStuff(folder);
        } else {
            parent.addStuff(folder);
        }
        return folder;

    }

    public void addStuff(Folder parentFolder, Stuff stuff) {
        if (parentFolder != null && stuff != null) {
            parentFolder.addStuff(stuff);
        }
    }
    
    public void addRelation(Stuff source,Stuff destination, String name){
        source.setRelation(new Relation(destination,name));
    }
}
