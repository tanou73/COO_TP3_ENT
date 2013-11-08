/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import javax.swing.JButton;
import model.Group;
import model.ENT;
import model.User;

/**
 *
 * @author hugo
 */
public class ENTController implements ActionListener{
    private ENT model;

    public ENTController(ENT model) {
        this.model = model;
    }
    
    public void subscribe(Observer view){
        model.addObserver(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton)e.getSource();
        
        if ( "add-group".equals(src.getName()) ){
            Group grp = new Group("test");
            grp.setCtrl(new GroupController());
            model.add(grp);
        }
        
        if ( "add-user".equals(src.getName()) ){
            // choose grp
            Group grp = model.getGroup(0);
           // grp.getCtrl().addUser(new User("toto"));
        }
    }
    
}
