public class Notification {
    
    public String notiNews(String nameNews,String content){ //แจ้งเตือนข่าวใหม่
        return nameNews + " : \n" + content;
    }
    
    public boolean notiTime(int hr,int min,int sec){  //แจ้งเตือนเวลา
        if(hr==0 && min == 10 && sec ==0){
              return true;
          }
          return false;
    }
        
    public String notiRepairFinish(){ //แจ้งเตือนซ่อมเสร็จ
        return "---Notification---\nRepairs completed,You can pick up it. From this day onwards.";
    }
    
    public String notiRepairIncreseTime(String oldDate,String detail,String newDate){
        return "---Notification---\n>>Old Date :\n"+oldDate  + "\n>>Detail :\n" + detail + "\n>>New Date :\n" + newDate;
    }
}
