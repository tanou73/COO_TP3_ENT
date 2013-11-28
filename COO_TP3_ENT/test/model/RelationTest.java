/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ENTController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.BadArgumentException;
import utils.DuplicateItemException;
import utils.UnauthorisedException;

/**
 *
 * @author hugo
 */
public class RelationTest {
    private User bob;
    private User morane;
    private Group grp;
    private ENTController entCtrl;
    private ENT model;
    private Document docEnnonce;
    private Document docCorrection;
    private Category catEnnonce;
    private Category catCorrection;
    
    
    public RelationTest() {
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
        
        entCtrl.createCategory("ennonce");
        entCtrl.createCategory("correction");
        
        catEnnonce = entCtrl.getCategory("ennonce");
        catCorrection = entCtrl.getCategory("correction");
        
        docEnnonce = new Document(15, "ennonce tp", "/lol/toto", "ennonceTP.doc", catEnnonce);
        docEnnonce = new Document(15, "correction tp", "/lol/toto", "correctionTP.doc", catCorrection);        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createRelation() throws UnauthorisedException, DuplicateItemException {
        String relationName = "est la correction de";
        entCtrl.createRelationType(relationName, "a pour correction", catCorrection, catEnnonce);
        assertTrue(model.isAnExistingCategory(relationName));
    }
    
    @Test
    public void removeRelation() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        String relationName = "est la correction de";
        entCtrl.createRelationType(relationName, "a pour correction", catCorrection, catEnnonce);
        entCtrl.removeRelationType(relationName);
        assertFalse(model.isAnExistingCategory(relationName));
    }
}