/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author maxime
 */
public class DuplicateItemException extends Exception {

    public DuplicateItemException(String message) {
        super("Duplicated element : " + message);
    }
}
