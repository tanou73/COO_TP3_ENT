/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hugo
 */
public class RelationType {

    private String name;
    private String invert;
    private Category catSource;
    private Category catDest;

    public RelationType(String name, String invert, Category catSource, Category catDest) {
        this.name = name;
        this.invert = invert;
        this.catSource = catSource;
        this.catDest = catDest;
    }

    public String getLibelle(boolean inverted) {
        if (inverted) {
            return this.invert;
        } else {
            return this.name;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvert() {
        return invert;
    }

    public void setInvert(String invert) {
        this.invert = invert;
    }

    public Category getCatSource() {
        return catSource;
    }

    public void setCatSource(Category catSource) {
        this.catSource = catSource;
    }

    public Category getCatDest() {
        return catDest;
    }

    public void setCatDest(Category catDest) {
        this.catDest = catDest;
    }

    public boolean isCompatible(Stuff source, Stuff destination) {
        return source.getCat() != null
                && destination.getCat() != null
                && source.getCat().getName().equals(this.catSource.getName())
                && destination.getCat().getName().equals(this.catDest.getName());
    }
}
