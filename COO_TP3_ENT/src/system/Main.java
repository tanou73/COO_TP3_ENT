/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import controller.ENTController;
import java.util.ArrayList;
import model.Document;
import model.ENT;
import model.Folder;
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

        Folder documentFolder = entCtrl.createFolder(grp, null, "documents");

        Folder imagesFolder = entCtrl.createFolder(grp, documentFolder, "image");
        
        Document img = new Document(5, "patate", "bidon", "Monfichier.jpg");
        Document img2 = new Document(5, "iiii", "nnnn", "test.jpg");

        entCtrl.addStuff(imagesFolder, img);
        entCtrl.addStuff(imagesFolder, img2);

        ArrayList<String> categories = new ArrayList<>();
        categories.add("est la correction de");
        categories.add("a pour correction");
        
        entCtrl.addRelation(img, img2, categories.get(0));
        entCtrl.addRelation(img2, img, categories.get(1));
        
        System.out.println(grp);

        /* ENT ent = new ENT();
         ENTController entCtrl = new ENTController(ent);
        
         new IHM(ent,entCtrl);*/
    }
}
