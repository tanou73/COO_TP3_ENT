/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import controller.ENTController;
import model.ENT;
import view.IHM;

/**
 *
 * @author hugo
 */
public class Main {
    public static void main(String[] args) {
        /*User henry = new User("Henry");
        User robert = new User("Robert");
        
        Group iut = new Group("IUT");
        
        iut.addUser(robert);
        iut.addUser(henry);
        
        Repository doc = new Repository("Documents");
        Repository img = new Repository("Image");
        
        Document cv = new Document(4, "mon cv", "cv.pdf", "MyCV.pdf");
        Document cover = new Document(10, "my cover letter", "cover.pdf", "MyCover.pdf");
        Document avatar = new Document(500, "my avatar", "avatar.jpg", "MyAvatar.jpg");
        
        img.addStuff(avatar);
        doc.addStuff(img);
        
        iut.addStuff(doc);
        iut.addStuff(cv);
        iut.addStuff(cover);
        
        System.out.println(iut.toString());*/      
        
        ENT ent = new ENT();
        ENTController entCtrl = new ENTController(ent);
        
        new IHM(ent,entCtrl);
    }
}
