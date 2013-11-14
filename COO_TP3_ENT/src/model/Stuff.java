/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hugo
 */
public abstract class Stuff {
    protected String name;
    
    /** parent folder ( null if root ) **/
    protected Folder parent;
    protected Relation relation;

    public Stuff(String name) {
        this.name = name;
    }

    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    @Override
    public String toString() {
        return "\n -- STUFFS -- \n " + "name=" + name  + ( (relation != null) ? " " + relation.getName()+ " " + relation.getStuff().getName() : "");
    }
    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }
}
