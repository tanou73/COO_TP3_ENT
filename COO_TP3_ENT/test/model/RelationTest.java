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

        this.bob = new SuperUser("Bob");
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
        docCorrection = new Document(15, "correction tp", "/lol/toto", "correctionTP.doc", catCorrection);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createRelation() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        String relationName = "est la correction de";
        entCtrl.connectUser(bob);
        entCtrl.createRelationType(relationName, "a pour correction", catCorrection, catEnnonce);
        assertTrue(model.isAnExistingRelationType(relationName));
    }

    @Test(expected = DuplicateItemException.class)
    public void createRelationTypeDuplication() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        String relationName = "est la correction de";
        entCtrl.connectUser(bob);
        entCtrl.createRelationType(relationName, "a pour correction", catCorrection, catEnnonce);
        entCtrl.createRelationType(relationName, "a une correction", catCorrection, catEnnonce);
    }

    @Test(expected = UnauthorisedException.class)
    public void createRelationTypeUnauthorized() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        String relationName = "est la correction de";
        entCtrl.connectUser(morane);
        entCtrl.createRelationType(relationName, "a pour correction", catCorrection, catEnnonce);
    }

    @Test
    public void removeRelationType() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        String relationName = "est la correction de";
        entCtrl.createRelationType(relationName, "a pour correction", catCorrection, catEnnonce);
        entCtrl.removeRelationType(relationName);
        assertFalse(model.isAnExistingRelationType(relationName));
    }

    @Test
    public void addRelation() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        String relationName = "est la correction de";
        String relationNameInvert = "a pour correction";
        entCtrl.connectUser(bob);
        entCtrl.createRelationType(relationName, relationNameInvert, catCorrection, catEnnonce);
        
        entCtrl.addRelation(docCorrection, docEnnonce, relationName);
        assertTrue(docCorrection.getRelation().getLibelle().equals(relationName));
        assertTrue(docCorrection.getRelation().getRelatedStuff().equals(docEnnonce));
        assertTrue(docEnnonce.getRelation().getLibelle().equals(relationNameInvert));
    }
}