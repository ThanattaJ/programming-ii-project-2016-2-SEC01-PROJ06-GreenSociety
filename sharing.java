public class Sharing {

    private Timer time = null; //เวลา
    private String timeDetail = null; //รายละเอียดเวลา
    private String type; //ชนิด
    private String equip; //อุปกรณ์
    private long cp; //ซีพี
    private long cpUse = 0; //ซีพีที่userใช้
    private int[] numBikeUser = {0, 0, 0, 0, 0}; //จำนวนที่ user ยืมแต่ละอุปกรณ์
    private static int numUtilityB = 100; //จำนวนจักนยาน ชนิด Utility ที่เหลืออยู่
    private static int numCruiserB = 100; //จำนวนจักนยาน ชนิด Cruiser ที่เหลืออยู่
    private static int numTouringB = 100; //จำนวนจักนยาน ชนิด Touring ที่เหลืออยู่
    private static int numHelmets = 100; //จำนวนหมวกที่เหลืออยู่
    private static int numKnee = 100; //จำนวนสนับเข่าที่เหลืออยู่
    private int statToBorrow[] = {0,0,0,0,0}; //เก็บสถิติการยืม

    public Sharing() { //constructors
        this.cp = 0;

    }

    public String showType() { //แสดงประเภทจักรยานที่ให้ยืม
        return "Type : \n"
                + "1.Utility Bike (100CP)\n Remaining : " + numUtilityB + "\n"
                + "2.Cruiser Bike (125CP)\n Remaining : " + numCruiserB + "\n"
                + "3.Touring Bike (150CP)\n Remaining : " + numTouringB + "\n"
                + "4.null";
    }

    public String showEquip() { //แสดงอุปกรณ์ที่ให้ปริการ
        return "Equipment : \n"
                + "1.Bicycle helmets (60CP)\n Remaining : " + numHelmets + "\n"
                + "2.Knee (80CP)\n Remaining : " + numKnee + "\n"
                + "3.null";
    }

    public void borrowType(int t, int count) { //ระบุจำนวนยืมจักรยานแต่ละชนิด
        switch (t) {
            case 1:
                numBikeUser[0] += count;
                numUtilityB -= count;
                break;
            case 2:
                numBikeUser[1] += count;
                numCruiserB -= count;
                ;
                break;
            case 3:
                numBikeUser[2] += count;
                numTouringB -= count;
                ;
                break;
            case 4:
                type += "null";
                break;
            default:
                type += "Error";
                break;
        }
    }

    public void borrowEquip(int e, int count) { //ระบุจำนวนยืมอุปกรณ์
        switch (e) {
            case 1:
                numBikeUser[3] += count;
                numHelmets -= count;
                ;
                break;
            case 2:
                numBikeUser[4] += count;
                numKnee -= count;
                ;
                break;
            case 3:
                equip += "null";
                break;
            default:
                equip += "Error";
                break;
        }
    }
 
    public void returnType(int numUB, int numCB, int numTB) { //คืนจักรยาน
        numUtilityB += numUB;
        numCruiserB += numCB;
        numTouringB += numTB;
        numBikeUser[0] = 0;
        numBikeUser[1] = 0;
        numBikeUser[2] = 0;
    }

    public void returnEquip(int numHel, int numKn) { //คืนอุปกรณ์
        numHelmets += numHel;
        numKnee += numKn;
        numBikeUser[3] = 0;
        numBikeUser[4] = 0;
    }

    public void edit(int num, int count) { //แก้ไขสิ่งของที่ยืมรวมถึงเวลา
        switch (num) {
            case 1:
                if (numBikeUser[0] > 0) {
                    numUtilityB += numBikeUser[0];
                    numBikeUser[0] = count;
                    numUtilityB -= count;
                } else {
                    numBikeUser[0] = count;
                    numUtilityB -= count;
                }
                break;

            case 2:
                if (numBikeUser[1] > 0) {
                    numCruiserB += numBikeUser[1];
                    numBikeUser[1] = count;
                    numCruiserB -= count;
                } else {
                    numBikeUser[1] = count;
                    numCruiserB -= count;
                }
                break;

            case 3:
                if (numBikeUser[2] > 0) {
                    numTouringB += numBikeUser[2];
                    numBikeUser[2] = count;
                    numTouringB -= count;
                } else {
                    numBikeUser[2] = count;
                    numTouringB -= count;
                }
                break;

            case 4:
                if (numBikeUser[3] > 0) {
                    numHelmets += numBikeUser[3];
                    numBikeUser[3] = count;
                    numHelmets -= count;
                } else {
                    numBikeUser[3] = count;
                    numHelmets -= count;
                }
                break;

            case 5:
                if (numBikeUser[4] > 0) {
                    numKnee += numBikeUser[4];
                    numBikeUser[4] = count;
                    numKnee -= count;
                } else {
                    numBikeUser[4] = count;
                    numKnee -= count;
                }
                break;

            default:
                System.out.println("Number Error, Please Enter Again.");
                break;
        }
    }

    public void countCpBorrow() { //คำนวนCp ที่จะต้องใช้แลกยืม
        int temp = 0;
        if (numBikeUser[0] > 0) {
            temp += numBikeUser[0] * 100;
        }
        if (numBikeUser[1] > 0) {
            temp += numBikeUser[1] * 125;
        }
        if (numBikeUser[2] > 0) {
            temp += numBikeUser[2] * 150;
        }

        if (numBikeUser[3] > 0) {
            temp += numBikeUser[3] * 60;
        }
        if (numBikeUser[4] > 0) {
            temp += numBikeUser[4] * 80;
        }
        cpUse = temp;
    }

    public void increseCp(long c) { //เพิ่มCp
        long nCp = c * 50;
        cp += nCp;
    }

    public void decreseCp(long c) { //ลดCp
        cp -= c;
    }

    public boolean checkCp(long c) { //check Cp ของ user กับ cp ทั้งหมดที่ต้องนำมาใช้แลกเพื่อยืม
        boolean temp = false;
        if (c > cp) {
            temp = false;
        } else if (c <= cp) {
            temp = true;
        }
        return temp;
    }

    public void startBorrow() throws InterruptedException { //เริ่มยืม
        decreseCp(cpUse);
        time.start();
        timeDetail = time.showDetail();
    }

    public void stopBorrow() { //หยุดยืม(นำของมาคืน)
        type = "";
        equip = "";
        time.stop();
        timeDetail = time.showDetail();
        returnType(numBikeUser[0], numBikeUser[1], numBikeUser[2]);
        returnEquip(numBikeUser[3], numBikeUser[4]);
    }

    public void enterTime(int userDate, int userMonth, int userYear, int userHr, int userMin, int userSec) { //ระบุวันเวลาที่จะคืน
        time = new Timer(userDate, userMonth, userYear, userHr, userMin, userSec);
        time.differentTime();
        timeDetail = time.showDetail();
    }

    public void increaseTime(int hr, int min, int sec) { //เพิ่มเวลาในการยืม
        time.increaseTime(hr, min, sec);
        timeDetail = time.showDetail();
    }

    public String allDetail() { //แสดงข้อมูลทั้งหมด
        countCpBorrow();
        boolean check = checkCp(cpUse);
        if (check == true) {
            type = "- Utility Bike : " + numBikeUser[0] + "\n";
            type += "- Cruiser Bike : " + numBikeUser[1] + "\n";
            type += "- Touring Bike : " + numBikeUser[2];
            equip = "- Bicycle helmets : " + numBikeUser[3] + "\n";
            equip += "- Knee : " + numBikeUser[4];

            return "Your CP : " + cp + "\n"
                    + "CP use : " + cpUse + "\n"
                    + "Borrow Detail : \n"
                    + "Bicycle Type : \n" + type + "\n"
                    + "Equipments : \n" + equip + "\n"
                    + "Time Detail : \n" + timeDetail;
        } else {

            return "Your CP : " + cp + "\n"
                    + "CP use : " + cpUse + "\n"
                    + "YOUR CP IS NOT ENOUGH. PlEASE SELECT AGAIN";
        }
    }

    public String getTimeDetail() { //แสดงข้อมูลวันทั้งวันยืมและคืและเวลาที่เหลือ
        return timeDetail;
    }

    public long getCp() { //แสดงค่า Cp
        return cp;
    }
    
    public String shoowStatBorror(){
        String b = "";
        String e = "";
        int max = 0;
        int temp = 0;
        
        for(int i = 0;i <statToBorrow.length-2;i++){
            if(max <= statToBorrow[i]){
                max = statToBorrow[i];
                temp = i;
                switch(temp){
                    case 0: b += "Utility Bike : " + max; break;
                    case 1: b += "Crusier Bike : " + max; break;
                    case 2: b += "Touring Bike : " + max; break;
                }
            }
        }
        
        max = 0;
        for(int i = 3;i <statToBorrow.length;i++){
            if(max <= statToBorrow[i]){
                max = statToBorrow[i];
                temp = i;
                switch(temp){
                    case 3: e += "Bicycle Helmets : " + max; break;
                    case 4: e += "Knee : " + max; break;
                }
            }
        }        
        return "The type of bicycle the most borrow of you is " + b +
                "\nThe equipment the most borrow of you is " + e;
    }
}
