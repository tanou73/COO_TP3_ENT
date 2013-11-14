/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author hugo
 */
public class Folder extends Stuff {
    /** childs **/
    protected ArrayList<Stuff> childs;

    public Folder(String name) {
        super(name);
        this.childs = new ArrayList<>();
    }
    
    /** add some stuff in the repo **/
    public void addStuff(Stuff stuff) {
        this.childs.add(stuff);
        stuff.setParent(this);
    }
    
    /** remove some stuff !! 
     * CAUTION --> must remove BEFORE adding to a new repo (cause the remove methode set parent to null)
     **/
    public void removeStuff(Stuff stuff) {
        this.childs.remove(stuff);
        stuff.setParent(null);
    }
    
    /** Getters & Setters  **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Stuff> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Stuff> childs) {
        this.childs = childs;
    }

    
    
    @Override
    public String toString() {
        String ret = super.toString() + "\n -> Repository{";
       
        if (super.getParent() != null)
            ret += "parent=" + super.getParent().getName(); 
        else
            ret += "parent=root"; 
        
        ret += "childs : \n";        
        for (Stuff stuff : childs) {
            ret += stuff.toString() + "\n";
        }
        
        return ret + "\n\n";
    }
    
}