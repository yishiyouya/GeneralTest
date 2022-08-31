package mywork.myalgoserver.mytimer;

import java.util.Calendar;
import java.util.Date;


public class MyTimerTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String backupTime = "06:00:00";
        Date bakTime = getDatetime(backupTime);
        //启动备份历史表任务
        MyTaskManager.getInstance().addTask(new MySystemInitTask(), bakTime, MyTaskManager.TIME_DAY);
    }

    public static Date getDatetime(String time) {

        //check paras
        int hourPos = time.indexOf(":");
        int secondPos = time.indexOf(":", hourPos+1);

        if (hourPos < 0 || secondPos < 0) {
            throw new RuntimeException("unvalid time format");
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);

        //set hour
        int hour = Integer.parseInt(time.substring(0, hourPos));
        cal.set(Calendar.HOUR_OF_DAY, hour);

        //set minute
        int minute = Integer.parseInt(time.substring(hourPos + 1, secondPos));
        cal.set(Calendar.MINUTE, minute);

        //set second
        int second = Integer.parseInt(time.substring(secondPos + 1));
        cal.set(Calendar.SECOND, second);

        Date date = cal.getTime();

        return date;

    }
}
