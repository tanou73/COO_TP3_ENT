/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author maxime
 */
public class Relation {

    private Stuff relatedStuff;
    private String name;
    private Relation invert;

    public Relation(Stuff stuff, String name) {
        this.relatedStuff = stuff;
        this.name = name;
    }

    public Stuff getRelatedStuff() {
        return relatedStuff;
    }

    public void setRelatedStuff(Stuff stuff) {
        this.relatedStuff = stuff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Relation getInvert() {
        return invert;
    }

    public void setInvert(Relation invert) {
        this.invert = invert;
    }
}
