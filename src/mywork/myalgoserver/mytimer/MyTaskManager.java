package mywork.myalgoserver.mytimer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * task manager
 * 
 * @author GuiPei
 * 2009-5-25
 */
public class MyTaskManager {

	public final static long TIME_SECOND = 1000;

	public final static long TIME_MINUTE = 60 * TIME_SECOND;

	public final static long TIME_HOUR = 60 * TIME_MINUTE;

	public final static long TIME_DAY = 24 * TIME_HOUR;

	private Timer timer = new Timer();

	private MyTaskManager() {

		//		
		//		LogBackupTask logBakTask = new LogBackupTask();
		//		
		//		timer.schedule(logBakTask, new Date(), DAY);

	}

	private static MyTaskManager instance = new MyTaskManager();

	public static MyTaskManager getInstance() {

		return instance;

	}

	/**
	 * 
	 * @param task
	 * @param date
	 * @param period
	 */
	public void addTask(TimerTask task, Date date, long period) {

		timer.scheduleAtFixedRate(task, date, period);

	}

}
