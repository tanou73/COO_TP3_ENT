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
    private RelationType rel;
    private boolean inverted;

    public Relation(Stuff stuff, RelationType rel, boolean isInverted) {
        this.relatedStuff = stuff;
        this.rel = rel;
        this.inverted = isInverted;
    }
    
    public String getLibelle(){
        return rel.getLibelle(inverted);
    }

    public Stuff getRelatedStuff() {
        return relatedStuff;
    }

    public void setRelatedStuff(Stuff stuff) {
        this.relatedStuff = stuff;
    }

    public RelationType getRel() {
        return rel;
    }

    public void setRel(RelationType rel) {
        this.rel = rel;
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean isInverted) {
        this.inverted = isInverted;
    }
}
