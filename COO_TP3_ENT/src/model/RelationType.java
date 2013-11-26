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

    public RelationType(String name, String invert) {
        this.name = name;
        this.invert = invert;
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
}
