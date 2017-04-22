/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;


/**
 *
 * @author January
 */
public class TestSupport {
    public static void main (String[] args){
        Support sp = new Support();
        sp.searchSupport("j");
        System.out.println(sp.contact());
        System.out.println(sp.getOutput());
    }
    
}
