/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bike;

/**
 *
 * @author ThanJa
 */
public class SelectMemberNameException extends Exception {

    public SelectMemberNameException() {
        super("Don't have Member's name.");
    }

    public SelectMemberNameException(String message) {
        super(message);
    }
    
}
