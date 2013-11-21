/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author maxime
 */
public class UnauthorisedException extends Exception{
    
    public UnauthorisedException(String message){
        super("Unauthorised :" + message );
    }
    
}
