package bike;
import java.util.Scanner;
public class TestSh {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sharing sh = new Sharing();
//        String loop = "";
//        System.out.print("Please enter Count of Can : ");
//        int c = sc.nextInt();
//        sh.increseCp(c);
//        System.out.println("Your CP : " + sh.getCp());
//        
//        do{
//            System.out.println(sh.showType());
//            System.out.print("Please,Enter numer select type bicyle. : ");
//            int t = sc.nextInt();
//            while(t != 4){
//                System.out.print("Please enter count wants to borrow : ");
//                int count = sc.nextInt();
//                try{
//                    sh.borrowTypeStep(t,count);
//                    break;
//                }catch(ItemAmountException iae){
//                    System.out.println(iae.getMessage());
//                }
//            }
//            if(t == 4){
//                break;
//            }
//            sc.nextLine();
//            System.out.print("If you want to borrow again. Please enter y or n : ");
//            loop = sc.nextLine();
//        }while(loop.equalsIgnoreCase("y"));
//        
//        do{
//            System.out.println(sh.showEquip());
//            System.out.print("Please,Enter numer select equipment. : ");
//            int e = sc.nextInt();
//            while(e != 3){
//                System.out.print("Please enter count wants to borrow : ");
//                int count = sc.nextInt();
//                try{
//                    sh.borrowEquipStep(e, count);
//                    break;
//                }catch(ItemAmountException iae){
//                    System.out.println(iae.getMessage());
//                }
//            }
//            if(e == 3){
//                sc.nextLine();
//                break;
//            }
//            sc.nextLine();
//            System.out.print("If you want to borrow again. Please enter y or n : ");
//            loop = sc.nextLine();
//        }while(loop.equalsIgnoreCase("y"));
//        sh.enterTime(30,3,2017,13,29,0);
//        System.out.println("----------------------------------------------------------\n");
//        System.out.println(sh.allDetail());
//        
//        do{
//            int num = 0;
//            
//            System.out.print("Do you want to edit,Please enter y or n : ");
//            loop = sc.nextLine();
//            if(loop.equalsIgnoreCase("y")){
//                System.out.print("Do you want to edit -\n"
//                        + "1.Utility Bike\n"
//                        + "2.Cruiser Bike\n"
//                        + "3.Touring Bike\n"
//                        + "4.Bicycle helmets\n"
//                        + "5.Knee\n"
//                        + "6.Time\n"
//                        + "\nPlease Enter number 1-6 : ");
//                num = sc.nextInt();
//                if (num == 6) {
//                System.out.print("Please enter Date : ");
//                int d = sc.nextInt();
//                System.out.print("Please enter Mont 1-12 : ");
//                int mm = sc.nextInt();
//                System.out.print("Please enter Year(A.D.) : ");
//                int y = sc.nextInt();
//                System.out.print("Please enter Hour : ");
//                int h = sc.nextInt();
//                System.out.print("Please enter Minutes : ");
//                int m = sc.nextInt();
//                System.out.print("Please enter Secound : ");
//                int s = sc.nextInt();
//                
//                sh.enterTime(d, mm, y, h, m, s);
//            }else{
//                while(0 < num && 6 > num){
//                    System.out.print("Please enter edit count : ");
//                    int count = sc.nextInt();
//                    try {
//                        sh.editStep(num, count);
//                        break;
//                    } catch (ItemAmountException iae) {
//                        System.out.println(iae.getMessage());
//                    }
//                }
//            }
//            }else{
//                break;
//            }
//            sc.nextLine();
//            System.out.print("Do you want to edit,Please enter y or n : ");
//            loop = sc.nextLine();  
//        }while(loop.equalsIgnoreCase("y"));
//        System.out.println("----------------------------------------------------------\n");
//        int submit;
//        System.out.println(sh.allDetail());
//        System.out.print("Do you want to submit?\n 1.Submit\n 2.Cancle\nPlease Enter Number : ");
//        submit = sc.nextInt();
//        while(submit != 1 && submit != 2){
//            System.out.print("Do you want to submit?\n 1.Submit\n 2.Cancle\nPlease Enter Number : ");
//            submit = sc.nextInt();
//        }
//        try{
//            sh.submitTrans(submit);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("----------------------------------------------------------\n");
//        sh.stopBorrow();

//        int[] tmp = sh.getAvailableItem();
//        for(int i : tmp){
//            System.out.println(i);
//        }
            
          sh.enterTime(9,4,2017,14,30,0);
          try{
            sh.startBorrow();
          }catch(InterruptedException e){
              System.out.println(e.getMessage());
          }
          System.out.println("Continue");
          sh.increaseTime(1,20, 0);
    }
}
