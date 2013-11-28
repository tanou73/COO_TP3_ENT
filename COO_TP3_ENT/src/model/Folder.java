/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;
import utils.DuplicateItemException;

/**
 *
 * @author hugo
 */
public class Folder extends Stuff {

    /**
     * childs *
     */
    protected ArrayList<Stuff> childs;

    public Folder(String name, Category cat) {
        super(name);
        super.setCat(cat);
        this.childs = new ArrayList<>();
    }

    public Folder(String name) {
        super(name);
        this.childs = new ArrayList<>();
    }

    /**
     * add some stuff in the repo *
     */
    public void addStuff(Stuff stuffToAdd) throws DuplicateItemException {
        for (Stuff stuff : childs) {
            if (stuff.getName().equalsIgnoreCase(stuffToAdd.getName())) {
                throw new DuplicateItemException(stuffToAdd.getName());
            }
        }
        this.childs.add(stuffToAdd);
        stuffToAdd.setParent(this);
    }

    /**
     * remove some stuff !! CAUTION --> must remove BEFORE adding to a new repo
     * (cause the remove methode set parent to null)
     *
     */
    public void removeChild(Stuff child) {
        this.childs.remove(child);
    }

    public synchronized void removeAllChildren() {
        for (Iterator<Stuff> it = childs.iterator(); it.hasNext();) {
            Stuff stuff = it.next();

            if (stuff instanceof Folder) {
                ((Folder) stuff).removeAllChildren();
                if (stuff.getRelation() != null) {
                    stuff.setRelation(null);
                    stuff.getRelation().getRelatedStuff().setRelation(null);
                }
                it.remove();
            }
        }
    }

    public Stuff getChild(String name) {
        for (Stuff stuff : childs) {
            if (stuff.getName().equalsIgnoreCase(name)) {
                return stuff;
            }
        }
        return null;
    }

    public void removeStuff(String name) {
        for (Stuff stuff : childs) {
            if (stuff.getName().equalsIgnoreCase(name)) {
                childs.remove(stuff);
            }
        }
    }

    /**
     * Getters & Setters *
     */
    public ArrayList<Stuff> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Stuff> childs) {
        this.childs = childs;
    }

    @Override
    public String toString() {
        String ret = super.toString() + "\n -> Repository{";

        if (super.getParent() != null) {
            ret += "parent=" + super.getParent().getName();
        } else {
            ret += "parent=root";
        }

        ret += "childs : \n";
        for (Stuff stuff : childs) {
            ret += stuff.toString() + "\n";
        }

        return ret + "\n\n";
    }
}