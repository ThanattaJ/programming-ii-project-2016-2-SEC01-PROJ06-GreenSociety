/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package History;

import java.sql.Timestamp;
import Timer.Timer;
/**
 *
 * @author January
 */
public class TestHistory {
    public static void main(String[] args) {
        History h = new History();
        Timer time = new Timer();
//        Timestamp borrowDate = new Timestamp(time.getNowTime().getTime());
//        h.HistoryByAdmin("B01",borrowDate,"Repair");
        System.out.println(h.statGreensociety());
        h.showBorrowUser(1111);
    }
    
}
