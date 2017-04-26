/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Register;

import Timer.Timer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author January
 */
public class TestRegister {
    public static void main(String[] args){
        Register rg = new Register();
//        rg.connectDBforCheckEmail("january@hotmail.com");
        System.out.println(rg.connectDBforCheckEmail("january@hotmail.com"));
        System.out.println("-----------------------------------------------");
        System.out.println(rg.connectDBforCheckId("59130500042"));
        
    }        
}
