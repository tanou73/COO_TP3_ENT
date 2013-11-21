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
public class GroupTest {
    
    private ENTController entCtrl;
    private ENT model;
    public GroupTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        model = new ENT();
        this.entCtrl = new ENTController(model);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createGroup() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        User bob = new User("Bob");
        String groupName = "test";

        entCtrl.connectUser(bob);
        
        entCtrl.createGroup(groupName);
        
        Group grp = entCtrl.getUserGroup(groupName);
        
        assertTrue(model.getGroups().contains(grp));
    }
    
}