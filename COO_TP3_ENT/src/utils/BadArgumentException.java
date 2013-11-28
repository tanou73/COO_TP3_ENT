/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author maxime
 */
public class BadArgumentException extends Exception {

    public BadArgumentException(String message) {
        super("Bad argument : " + message);
    }

    public BadArgumentException() {
        super();
    }
}
