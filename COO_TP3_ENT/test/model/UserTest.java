package model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import controller.ENTController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import exception.BadArgumentException;
import exception.UnauthorisedException;

/**
 *
 * @author hugo
 */
public class UserTest {

    private ENTController entCtrl;
    private ENT model;

    public UserTest() {
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
    public void connection() throws UnauthorisedException, BadArgumentException {
        User bob = new User("Bob");
        User morane = new User("Morane");

        entCtrl.connectUser(bob);
        assertEquals(model.getConnectedUser(), bob);

        entCtrl.connectUser(morane);
        assertEquals(model.getConnectedUser(), morane);
    }

    @Test(expected = UnauthorisedException.class)
    public void disconnection() throws UnauthorisedException, BadArgumentException {
        User bob = new User("Bob");

        entCtrl.connectUser(bob);
        assertEquals(model.getConnectedUser(), bob);

        entCtrl.disconnectUser();
        model.getConnectedUser();
    }
}