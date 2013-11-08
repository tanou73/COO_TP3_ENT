/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import controller.ENTController;
import model.ENT;
import model.Group;
import model.User;

/**
 *
 * @author hugo
 */
public class Main {
    public static void main(String[] args) {
        
        User bob = new User("bob");
        User laurane = new User("laurane");
        
        ENT ent = new ENT();
        ENTController entCtrl = new ENTController(ent);
        
        entCtrl.connectUser(bob);
        
        entCtrl.createGroup("Papyslave");
        
        entCtrl.connectUser(laurane);
        
        entCtrl.joinGroup("Papyslave");
        
        entCtrl.connectUser(bob);
        
        Group grp = entCtrl.getUserGroup("Papyslave");
        
        entCtrl.createFolder(grp, "documents", null);
        
        entCtrl.createFolder(grp, "documents", "image");
        
       /* ENT ent = new ENT();
        ENTController entCtrl = new ENTController(ent);
        
        new IHM(ent,entCtrl);*/
    }
}
