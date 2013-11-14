/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author maxime
 */
public class Relation {
    
    private Stuff stuff;
    private String name;

    public Relation(Stuff stuff, String name) {
        this.stuff = stuff;
        this.name = name;
    }

    public Stuff getStuff() {
        return stuff;
    }

    public void setStuff(Stuff stuff) {
        this.stuff = stuff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
