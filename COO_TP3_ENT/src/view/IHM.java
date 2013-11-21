/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ENTController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.ENT;

/**
 *
 * @author hugo
 */
public class IHM extends JFrame implements Observer{
    private JPanel controlPanel;
    private ENTController entCtrl;
    private ENT model;

    public IHM(ENT model, ENTController entCtrl) {
        
        super("ENT");
        
        this.model = model;
        this.entCtrl = entCtrl;
        this.entCtrl.subscribe(this);
        
        initComponent();
        
        this.getContentPane().setLayout(new BorderLayout());
        
        this.getContentPane().add(controlPanel, BorderLayout.NORTH);
        controlPanel.setVisible(true);
        
        this.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initComponent() {
        GridLayout layout = new GridLayout(0,2);   
        controlPanel = new JPanel(layout);     
        
        JButton butAddGroup = new JButton("Add a group");
        JButton butAddUser = new JButton("Add a user");
        JButton butAddDoc = new JButton("Add a doc");
        JButton butAddRepo = new JButton("Add a repo");
        
        controlPanel.add(butAddGroup);
        controlPanel.add(butAddUser);
        controlPanel.add(butAddDoc);
        controlPanel.add(butAddRepo);
        
        butAddGroup.setName("add-group");
        butAddUser.setName("add-user");
        butAddDoc.setName("add-doc");
        butAddRepo.setName("add-repo");
        
        butAddGroup.setVisible(true);
        butAddUser.setVisible(true);
        butAddDoc.setVisible(true);
        butAddRepo.setVisible(true);
        
     //   butAddGroup.addActionListener(entCtrl);
    }

    @Override
    public void update(Observable o, Object arg) {
       System.out.println("notify: "+ (String)arg);
    }
}
