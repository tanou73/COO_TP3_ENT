/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Group;
import model.ENT;
import model.Folder;
import model.Stuff;
import model.SuperUser;
import model.User;
import utils.BadArgumentException;
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

    public void connectUser(User user) throws BadArgumentException {
        if (user == null) {
            throw new BadArgumentException("user is null");
        }
        model.setConnectedUser(user);
    }

    public void disconnectUser() {
        model.setConnectedUser(null);
    }

    public void createGroup(String name) throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        if (name == null) {
            throw new BadArgumentException("Name is null");
        }
        if (Group.findGroupByName(model.getGroups(), name) != null) {
            throw new DuplicateItemException("group: " + name);
        }
        Group groupe = new Group(name, model.getConnectedUser());
        model.addGroup(groupe);
        joinGroup(name);
    }

    public void joinGroup(String grpName) throws UnauthorisedException, BadArgumentException {
        model.getGroup(grpName).addUser(model.getConnectedUser());
    }

    public void quitGroup(String grpName) throws BadArgumentException, UnauthorisedException {
        model.getGroup(grpName).removeUser(model.getConnectedUser());
    }

    public void removeGroup(String name) throws BadArgumentException, UnauthorisedException {
        Group group = model.getGroup(name);
        if (!group.getOwner().equals(model.getConnectedUser())) {
            throw new UnauthorisedException(("Group doesn't belong to you, dear"));
        }
        for (User user : group.getUsers()) {
            user.removeGroup(group);
        }
        model.removeGroup(group);
    }

    public ArrayList<Group> getUserGroups() throws UnauthorisedException {
        return model.getConnectedUser().getUserGroups();
    }

    public Group getConnectedUserGroup(String name) throws UnauthorisedException, BadArgumentException {
        ArrayList<Group> grps = model.getConnectedUser().getUserGroups();
        Group group = Group.findGroupByName(grps, name);
        if (group == null) {
            throw new BadArgumentException("group not found");
        } else {
            return group;
        }
    }

    public Folder createFolder(Group grp, Folder parent, String name) throws DuplicateItemException {
        Folder folder = new Folder(name);
        if (parent == null) {
            grp.getRootFolder().addStuff(folder);
        } else {
            parent.addStuff(folder);
        }
        return folder;
    }
    
    public Folder getRootFolder(Group grp) throws BadArgumentException {
        if (grp != null)
            return grp.getRootFolder();
        else
            throw new BadArgumentException("group is null");
    }
    
    public void removeStuff(Folder parent, String name) throws BadArgumentException {
        if (parent == null)
            throw new BadArgumentException("folder parent is null");
        
        Stuff target = parent.getChild(name);        
        if (target == null)
            throw new BadArgumentException("stuff name is null");
        
        parent.removeChild(target);
        
        if (target instanceof Folder){
            ((Folder)target).removeAllChildren();
        }
    }

    public Stuff getStuff(Group grp, Folder parent, String name) {
        if (parent == null) {
            return grp.getRootFolder().getChild(name);
        } else {
            return parent.getChild(name);
        }
    }

    public void addStuff(Folder parentFolder, Stuff stuff) throws BadArgumentException {
        if (parentFolder != null && stuff != null) {
            try {
                parentFolder.addStuff(stuff);
            } catch (DuplicateItemException ex) {
                Logger.getLogger(ENTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new BadArgumentException();
        }

    }

    public void addRelation(Stuff source, Stuff destination, String relationName) throws BadArgumentException {
        // Check args
        if (source == null || destination == null || relationName == null) {
            throw new BadArgumentException("Arguments cant be null");
        }
        
        // Check that the relation name exists
        boolean validRelationname = false;
        for (String name : model.getRelationsNames()) {
            if (name.equalsIgnoreCase(relationName)) {
                validRelationname = true;
                break;
            }
        }
        
        // Add relation
        if (validRelationname) {
            source.setRelation(destination, relationName);
        } else {
            throw new BadArgumentException("relation name not found");
        }
    }

    public void createRelationName(String relationName, String invertName) throws UnauthorisedException, DuplicateItemException {
        if (model.getConnectedUser() instanceof SuperUser) {
            if (model.isAnExistingRelationName(relationName)) {
                throw new DuplicateItemException("Relation name already used");
            }
            model.addRelationName(relationName, invertName);
        } else {
            throw new UnauthorisedException("You must be super user");
        }
    }

    public void removeRelationName(String name) throws UnauthorisedException, BadArgumentException {
        if (model.getConnectedUser() instanceof SuperUser) {
            if (model.isAnExistingRelationName(name)) {
                model.removeRelationName(name);
            } else {
                throw new BadArgumentException("relation name not found");
            }
        } else {
            throw new UnauthorisedException("You must be super user");
        }
    }
}
