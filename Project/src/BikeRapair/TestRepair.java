package BikeRapair;


import History.History;
import Timer.Timer;
import java.sql.Timestamp;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author January
 */
public class TestRepair {
    public static void main (String [] args) throws InterruptedException{
//        Repair r = new Repair();
//
//        r.setHours(0);
//        r.setMinute(1);
//        r.setSecound(0);
//        
//        System.out.println(r.time());
//        r.sentDatabaseHistory();
        Scanner sc = new Scanner(System.in);
//        History h = new History();
//        
//        
        Repair rp = new Repair();
//        rp.setBike("Trek");
//        rp.setColor("Black and White");
//        rp.setWhyRepair("Broke chain");
////        rp.setBike("B02");
////        rp.setWhyRepair("Beake Broke");
////        
////
        System.out.print("Enter your bike: ");
        rp.setBike(sc.nextLine());
        System.out.print("Enter bike problem: ");
        rp.setProblem(sc.nextLine());
        System.out.print("Enter bike detail: ");
        rp.setDetail(sc.nextLine());
        System.out.print("Enter your time(HH:mm:ss): ");
        rp.setHours(sc.nextInt());
        rp.setMinute(sc.nextInt());
        rp.setSecound(sc.nextInt());
      
        System.out.println("---------------------------------------------------------------------------");
        System.out.print("Any service else?: ");
        rp.whatAlse(sc.next());
//        rp.getDetailRepair();
        rp.Status(true);
//        rp.time();
//        rp.connectDB();
//        rp.sentDatabaseHistory();
        System.out.print(rp);
        System.out.println(rp.time());
        System.out.println("-----------------------------------no-------------------");
        
        rp.increaseTimeRepair("Test","Increase",0, 30, 0);
        System.out.println("***************************************");
        System.out.println(rp.submitRepair());
        System.out.println("...."+rp.getTimeDetail());
//        System.out.println(c);
//        System.out.println(rp.getDetailRepair());
//        System.out.println(rp.submitRepair());
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                home guihome = new home();
//                guihome.setVisible(true);
//                guihome.connectRepairing(rp);
//            }
//        });
//        rp.sentDatabaseHistory();
    }
}
