/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ENTController;
import exception.BadArgumentException;
import exception.DuplicateItemException;
import exception.UnauthorisedException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hugo
 */
public class CloneTest {

    private ENTController entCtrl;
    private ENT model;
    private Group grp;

    public CloneTest() {
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
        User bob = new User("Bob");
        String groupName = "test";

        entCtrl.connectUser(bob);
        entCtrl.createGroup(groupName);
        grp = entCtrl.getConnectedUserGroup(groupName);


    }

    @After
    public void tearDown() {
    }

    @Test
    public void cloneGroup() throws Exception {

        Document doc = new Document(5, "", "", "doc");
        Document photo = new Document(5, "", "", "jpg");
        Service service = new Service("service");
        Folder images = entCtrl.createFolder(grp, grp.getRootFolder(), "images", null);
        Folder vacances = entCtrl.createFolder(grp, images, "vacances", null);
        entCtrl.addStuff(vacances, photo);
        entCtrl.addStuff(entCtrl.getRootFolder(grp), doc);
        entCtrl.addStuff(entCtrl.getRootFolder(grp), service);

        Group copiedGroup = new Group(grp);

        //only folders
        if (!onlyHaveFoldersAsChildren(copiedGroup.getRootFolder())) {
            fail("Only folders should be copied");
        }

        //copied folder 1
        Folder copiedImageFolder = (Folder) entCtrl.getStuff(grp, copiedGroup.getRootFolder(), "images");
        assertNotNull(copiedImageFolder);

        //copied folder 2
        Folder copiedVacancesFolder = (Folder) entCtrl.getStuff(grp, copiedImageFolder, "vacances");
        assertNotNull(copiedVacancesFolder);
    }

    private boolean onlyHaveFoldersAsChildren(Folder folder) {
        for (Stuff stuff : folder.getChilds()) {
            if (stuff instanceof Folder == false) {
                return false;
            } else {
                return onlyHaveFoldersAsChildren((Folder) stuff);
            }
        }
        return true;
    }
}
