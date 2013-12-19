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
    /**
     * parent folder ( null if root ) *
     */
    protected Folder parent;
    /**
     * relation *
     */
    protected Relation relation;
    /**
     * category *
     */
    protected Category cat;

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
        return "\n -- STUFFS -- \n " + "name=" + name + ((relation != null) ? " " + relation.getLibelle() + " " + relation.getRelatedStuff().getName() : "");
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public void setRelation(Stuff relatedStuff, RelationType rel, boolean isInverted) {
        this.relation = new Relation(relatedStuff, rel, isInverted);
    }

    public Category getCat() {
        return cat;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }
}
