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
import utils.BadArgumentException;
import utils.DuplicateItemException;
import utils.UnauthorisedException;

/**
 *
 * @author hugo
 */
public class StuffTest {

    private User bob;
    private User morane;
    private Group grp;
    private ENTController entCtrl;
    private ENT model;

    public StuffTest() {
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

    @Test(expected = UnauthorisedException.class)
    public void createCategoryFromNormalUser() throws UnauthorisedException, DuplicateItemException {
        entCtrl.createCategory("Enonce");
    }

    @Test(expected = DuplicateItemException.class)
    public void createDuplicateCategory() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        SuperUser su = new SuperUser("michel");
        entCtrl.connectUser(su);
        entCtrl.createCategory("Enonce");
        entCtrl.createCategory("Enonce");
    }

    @Test(expected = BadArgumentException.class)
    public void unknownCategory() throws BadArgumentException {
        model.getCategory("bob");
    }

    @Test
    public void createStuffs() throws BadArgumentException, UnauthorisedException, DuplicateItemException {
        SuperUser su = new SuperUser("michel");
        entCtrl.connectUser(su);
        entCtrl.createCategory("Enonce");
        entCtrl.createCategory("Correction");
        model.getCategory("Enonce");
        model.getCategory("Correction");
    }
}