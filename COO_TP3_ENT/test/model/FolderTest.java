/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ENTController;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.BadArgumentException;
import utils.DuplicateItemException;
import utils.UnauthorisedException;

/**
 *
 * @author hugo
 */
public class FolderTest {
    private User bob;
    private User morane;
    private Group grp;
    private ENTController entCtrl;
    private ENT model;
    
    
    public FolderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws BadArgumentException, UnauthorisedException, DuplicateItemException {
        model = new ENT();
        this.entCtrl = new ENTController(model);
        
        this.bob = new User("Bob");
        this.morane = new User("Morane");
        String grpName = "test";
                
        entCtrl.connectUser(bob);
        
        entCtrl.createGroup(grpName);
        
        this.grp = entCtrl.getConnectedUserGroup(grpName);
    }
    
    @After
    public void tearDown() {
    }
   
    @Test
    public void createFolder() throws DuplicateItemException {
        entCtrl.createFolder(grp, null, "documents", null);
        
        Folder doc = (Folder)entCtrl.getStuff(grp, null, "documents");
        
        assertTrue(doc != null);
        
        entCtrl.createFolder(grp, doc, "images", null);
        
        assertTrue(entCtrl.getStuff(grp, doc, "images") != null);
    }
    
    @Test(expected = DuplicateItemException.class)
    public void createFolderDuplicate() throws DuplicateItemException {
        entCtrl.createFolder(grp, null, "documents", null);
        
        entCtrl.createFolder(grp, null, "documents", null);
    }
    
    @Test
    public void removeFolder() throws Exception {
        // Creer folder document
        entCtrl.createFolder(grp, null, "documents", null);
        Folder fold = (Folder)entCtrl.getStuff(grp, null, "documents");
        // Creer un document "TOTO" dans le folder
        Document doc = new Document(15, "un doc", "/toto/lol.jpg", "toto.jpg");        
        entCtrl.addStuff(fold, doc);
        // Creer un folder dans le folder documents
        entCtrl.createFolder(grp, fold, "images", null);
        Folder img = (Folder)entCtrl.getStuff(grp, fold, "images");        
        // Creer un service dans folder images
        Service serv = new Service("un service");
        entCtrl.addStuff(img, serv);
        
        entCtrl.removeStuff(entCtrl.getRootFolder(grp), "documents");
        
        assertNull(entCtrl.getStuff(grp, entCtrl.getRootFolder(grp), "documents"));
    }
    
}