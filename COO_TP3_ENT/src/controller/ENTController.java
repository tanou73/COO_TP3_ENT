/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import model.Group;
import model.ENT;
import model.Folder;
import model.Relation;
import model.Stuff;
import model.SuperUser;
import model.User;
import utils.DuplicateItemException;
import utils.UnauthorisedException;

/**
 *
 * @author hugo
 */
public class ENTController {

    private ENT model;

    public ENTController(ENT model) {
        this.model = model;
    }

    public void subscribe(Observer view) {
        model.addObserver(view);
    }

    public void connectUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        model.setConnectedUser(user);
    }

    public void disconnectUser() {
        model.setConnectedUser(null);
    }

    public void createGroup(String name) throws UnauthorisedException, DuplicateItemException {
        if (name == null) {
            throw new IllegalArgumentException("Name is null");
        }
        for (Group group : model.getGroups()) {
            if (group.getName().equals(name)) {
                throw new DuplicateItemException("group: " + name);
            }
        }
        Group grp = new Group(name);
        joinGroup(name);
        model.addGroup(grp);
    }

    public void joinGroup(String grpName) throws UnauthorisedException {

        model.getGroup(grpName).addUser(model.getConnectedUser());
    }

    public ArrayList<Group> getUserGroups() throws UnauthorisedException {
        return model.getConnectedUser().getUserGroups();
    }

    public Group getUserGroup(String name) throws UnauthorisedException {
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
        try {
            if (parent == null) {
                grp.getRootFolder().addStuff(folder);
            } else {
                parent.addStuff(folder);
            }
            return folder;
        } catch (DuplicateItemException ex) {
            Logger.getLogger(ENTController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void addStuff(Folder parentFolder, Stuff stuff) {
        if (parentFolder != null && stuff != null) {
            try {
                parentFolder.addStuff(stuff);
            } catch (DuplicateItemException ex) {
                Logger.getLogger(ENTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new IllegalArgumentException();
        }

    }

    public void addRelation(Stuff source, Stuff destination, String name) {
        source.setRelation(new Relation(destination, name));
    }

    public void createRelationName(String name) throws UnauthorisedException {
        if (model.getConnectedUser() instanceof SuperUser) {
            model.addRelationName(name);
        } else {
            throw new UnauthorisedException("You must be super user");
        }
    }

    public void removeRelationName(String name) throws UnauthorisedException {
        if (model.getConnectedUser() instanceof SuperUser) {
            model.removeRelationName(name);
        } else {
            throw new UnauthorisedException("You must be super user");
        }
    }
}
