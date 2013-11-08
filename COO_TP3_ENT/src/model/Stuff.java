/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hugo
 */
public class Stuff {
    protected String name;
    
    /** parent folder ( null if root ) **/
    protected Repository parent;

    public Stuff(String name) {
        this.name = name;
    }

    public Repository getParent() {
        return parent;
    }

    public void setParent(Repository parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "\n -- STUFFS -- \n " + "name=" + name ;
    }
}
