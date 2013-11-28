/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hugo
 */
public class Document extends Stuff {

    /**
     * document's size *
     */
    private long size;
    /**
     * description *
     */
    private String desc;
    /**
     * link to the file *
     */
    private String link;

    public Document(long size, String desc, String link, String name, Category category) {
        super(name);
        super.setCat(cat);
        this.size = size;
        this.desc = desc;
        this.link = link;
    }

    public Document(long size, String desc, String link, String name) {
        super(name);
        this.size = size;
        this.desc = desc;
        this.link = link;
    }

    /**
     * Getters & Setters *
     */
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + " \n -> Document{" + "size=" + size + ", desc=" + desc + ", link=" + link + '}' + "\n\n";
    }
}
