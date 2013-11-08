/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import model.Group;

/**
 *
 * @author hugo
 */
public class GroupController implements ActionListener {
    private Group model;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton)e.getSource();
    }
    
}
