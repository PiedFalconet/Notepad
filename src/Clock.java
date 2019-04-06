import java.util.Calendar;
import java.util.GregorianCalendar;

public class Clock extends Thread{  
  
    public void run() {  
        while (true) {  
            GregorianCalendar time = new GregorianCalendar();  
            int hour = time.get(Calendar.HOUR_OF_DAY);  
            int min = time.get(Calendar.MINUTE);  
            int second = time.get(Calendar.SECOND);  
            Notepad.time.setText("\tTime:" + hour + ":" + min + ":" + second+" ");  
            try {  
                Thread.sleep(1000);  
            } catch (InterruptedException exception) {  
            }  
  
        }  
    }  
}  