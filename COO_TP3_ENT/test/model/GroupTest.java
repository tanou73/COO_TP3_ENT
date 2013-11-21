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
    
    @Test(expected = UnauthorisedException.class)
    public void createGroupUnhautorised() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        String groupName = "test";
        
        entCtrl.createGroup(groupName);
    }

    @Test(expected = DuplicateItemException.class)
    public void createGroupDuplicate() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        User bob = new User("Bob");
        User morane = new User("Morane");
        String groupName = "test";

        entCtrl.connectUser(bob);
        entCtrl.createGroup(groupName);
        
        entCtrl.connectUser(morane);
        entCtrl.createGroup(groupName);
    }
    
    @Test(expected = BadArgumentException.class)
    public void createGroupBadArgument() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        User bob = new User("Bob");
        entCtrl.connectUser(bob);
        entCtrl.createGroup(null);
    }
    
    @Test
    public void joinGroup() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        User bob = new User("Bob");
        User morane = new User("Morane");
        String groupName = "test";

        entCtrl.connectUser(bob);
        
        entCtrl.createGroup(groupName);
        
        entCtrl.connectUser(morane);
        
        entCtrl.joinGroup(groupName);
        
        Group grp = entCtrl.getUserGroup(groupName);   
        assertTrue(grp.getUsers().contains(bob));
        assertTrue(grp.getUsers().contains(morane));
        
        assertTrue(bob.getUserGroups().contains(grp));
        assertTrue(morane.getUserGroups().contains(grp));
    }
    
    @Test
    public void quitGroup() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        User bob = new User("Bob");
        User morane = new User("Morane");
        String groupName = "test";

        entCtrl.connectUser(bob);
        
        entCtrl.createGroup(groupName);
        
        entCtrl.connectUser(morane);
        
        entCtrl.joinGroup(groupName);
        
        Group grp = entCtrl.getUserGroup(groupName);  
        
        entCtrl.quitGroup(groupName);        
        
        assertFalse(grp.getUsers().contains(morane));
        assertFalse(morane.getUserGroups().contains(grp));
    }
    
    @Test
    public void removeGroup() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        User bob = new User("Bob");
        User morane = new User("Morane");
        String groupName = "test";

        entCtrl.connectUser(bob);
        
        entCtrl.createGroup(groupName);
        
        entCtrl.connectUser(morane);
        
        entCtrl.joinGroup(groupName);
        
        Group grp = entCtrl.getUserGroup(groupName);  
        
        entCtrl.connectUser(bob);
        
        entCtrl.removeGroup(groupName);        
        
        assertFalse(bob.getUserGroups().contains(grp));
        assertFalse(morane.getUserGroups().contains(grp));
        assertFalse(model.getGroups().contains(grp));
    }
    
    @Test(expected = UnauthorisedException.class)
    public void removeGroupUnauthorised() throws UnauthorisedException, DuplicateItemException, BadArgumentException {
        User bob = new User("Bob");
        User morane = new User("Morane");
        String groupName = "test";

        entCtrl.connectUser(bob);
        
        entCtrl.createGroup(groupName);
        
        entCtrl.connectUser(morane);
        
        entCtrl.joinGroup(groupName);
        
        Group grp = entCtrl.getUserGroup(groupName);  
        
        entCtrl.removeGroup(groupName);
    }
}