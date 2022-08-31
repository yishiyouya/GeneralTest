
// Copyright (c) 2000 cicc
package mywork.myalgoserver;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * ����ʵ���ַ�������/ʱ�����Ժ�������ʾ������/ʱ��֮���ת��.
 */
public class MyTradeDate {
	private static char seperator = '-';
	private static long adjustTime = 0;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	public static SimpleDateFormat sdf_day = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat sdf_t = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat sdf_minute = new SimpleDateFormat("HHmm");
	public static SimpleDateFormat sdf_hours = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // ��¼ʱ����Ҫ֪��ʱ����
	/**
	 * ȱʡ������
	 */
	public MyTradeDate() {
	}

	/**
	 * ָ���·ݵ��������
	 * @param year ���
	 * @param month �·�[1--12]
	 */
	public static int getDaysOfMonth(int year, int month) {

		Calendar c = Calendar.getInstance();
		c.set(year, month, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.get(Calendar.DAY_OF_MONTH);

	}

	/**
	 * ��þ�ȷ�����ӵ�ʱ�䣬����yyyy-mm-dd hh:mm
	 * @datetime yyyymmddhhmm ���� yyyymmddhhmmss
	 */
	public static String getDatetime1(long datetime) {
		String temp = Long.toString(datetime);
		if (temp.trim().length() == 12) {
			temp += "00";
		}
		String time = getDateTime(Long.parseLong(temp));
		time = time.substring(0, time.length() - 3);
		return time;
	}

	/**
	 * �Ѹ�ʽΪ yyyy/mm/dd �� yyyy-mm-dd ������ת�����������ڡ�
	 * ���߰Ѹ�ʽΪ yyyy/mm/dd hh:mm:ss �� yyyy-mm-dd hh:mm:ss ������ʱ��ת�����������ڡ�
	 * @param strDate ��ʾһ�������ַ�������ʽΪ yyyy/mm/dd �� yyyy-mm-dd ��
	 * @return ���ظ�ʽΪ yyyymmddhhmmss ���������ں�ʱ�䡣
	 */
	public static long getMillis(String strDate) {
		if (strDate == null
			|| (strDate != null && strDate.trim().equals(""))
			|| (strDate != null && strDate.trim().equals("-1")))
			return -1;
		String sDate = strDate;
		String sTime = null;
		String sep = " ";
		StringTokenizer st = null;
		long millis = 0;
		if (strDate.indexOf(":") > 0) {
			st = new StringTokenizer(strDate, sep);
			sDate = st.nextToken();
			sTime = st.nextToken();
		}
		sDate = getFormatDate(sDate);
		sTime = getFormatTime(sTime);

		//������ת��Ϊyyyymmdd��ʽ
		if (sDate != null && !sDate.equals(""))
			sDate = sDate.substring(0, 4) + sDate.substring(5, 7) + sDate.substring(8);
		else
			sDate = "00000000";
		//��ʱ��ת��Ϊhhmmss��ʽ
		if (sTime != null)
			sTime = sTime.substring(0, 2) + sTime.substring(3, 5) + sTime.substring(6);
		else
			sTime = "000000";
		String strDateTime = sDate + sTime;

		return Long.parseLong(strDateTime);
	}

	/**
	 * ����������ת���ɸ�ʽΪ yyyy-mm-dd �����ڡ�
	 * @param millis �������ڡ������磺20011011��
	 * @return ���ظ�ʽΪ yyyy-mm-dd���ַ����ں�ʱ�䡣
	 */
	public static String getDate(long millis) {
		if (millis <= 0)
			return "";

		String strDate = String.valueOf(millis);
		int len = strDate.length();
		if (len < 4)
			return "";
		else if (len == 4)
			strDate += "0101";
		else if (len == 6)
			strDate += "01";
		len = strDate.length();
		//����������ת����yyyymmdd��ʽ
		if (len > 8)
			strDate = strDate.substring(0, 8);
		else
			strDate = strDate.substring(0);

		strDate =
			strDate.substring(0, 4) + "-" + strDate.substring(4, 6) + "-" + strDate.substring(6);
		return strDate;
	}

	/**
	 * ����������ת���ɸ�ʽΪ yyyymmdd �����ڡ�
	 * @param millis �������ڡ������磺20011011��
	 * @return ���ظ�ʽΪ yyyymmdd���ַ����ں�ʱ�䡣
	 */
	public static String getDate1(long millis) {
		if (millis <= 0)
			return "";

		String strDate = String.valueOf(millis);
		int len = strDate.length();
		if (len < 4)
			return "";
		else if (len == 4)
			strDate += "0101";
		else if (len == 6)
			strDate += "01";
		len = strDate.length();
		//����������ת����yyyymmdd��ʽ
		if (len > 8)
			strDate = strDate.substring(0, 8);
		else
			strDate = strDate.substring(0);

		strDate = strDate.substring(0, 4) + strDate.substring(4, 6) + strDate.substring(6);
		return strDate;
	}

	/**
	 * ����������ת����һ�����ں�ʱ����ַ�������ʽΪ��yyyy-mm-dd hh:mm:ss��
	 * @param millis �������ڡ������磺20011011��
	 * @return ���ظ�ʽΪ yyyy-mm-dd hh:mm:ss ���ַ����ں�ʱ�䡣
	 */
	public static String getDateTime(long millis) {
		if (millis <= 0)
			return "";

		String strDate = String.valueOf(millis);
		int len = strDate.length();
		if (len == 4)
			strDate += "0101";
		else if (len == 6)
			strDate += "01";
		len = strDate.length();
		//��������λ��Ҫ����14λ
		for (int i = 0; i < 14 - len; i++) {
			strDate += "0";
		} //end for

		strDate =
			strDate.substring(0, 4)
				+ "-"
				+ strDate.substring(4, 6)
				+ "-"
				+ strDate.substring(6, 8)
				+ " "
				+ strDate.substring(8, 10)
				+ ":"
				+ strDate.substring(10, 12)
				+ ":"
				+ strDate.substring(12);
		return strDate;
	}

	/**
	 * ���ص�ǰ���ڵ��ַ���ʽ����ʽΪ��yyyy-mm-dd
	 */
	public static String getToday() {
		Calendar rightNow = getCalendar();
		int year = rightNow.get(Calendar.YEAR);
		int month = rightNow.get(Calendar.MONTH) + 1;
		int day = rightNow.get(Calendar.DAY_OF_MONTH);
		return year
			+ String.valueOf(seperator)
			+ (month < 10 ? "0" + month : month + "")
			+ String.valueOf(seperator)
			+ (day < 10 ? "0" + day : day + "");
	}

	/**
	 * ���������������,��1999�꿪ʼ����ǰ
	 * @return
	 */
	public static String[] getAllowYears() {
		int currentYear = getYear();
		String[] s = new String[currentYear - 1999 + 1];
		for (int y = 1999, i = 0; y <= currentYear; i++, y++)
			s[i] = Integer.toString(y);
		return s;
	}

	/**
	 * ���ص�ǰ���
	 */
	public static int getYear() {
		Calendar rightNow = getCalendar();
		return rightNow.get(Calendar.YEAR);
	}

	/**
	 * ���ص�ǰ�·�
	 */
	public static int getMonth() {
		Calendar rightNow = getCalendar();
		return rightNow.get(Calendar.MONTH) + 1;
	}

	/**
	 * ���ص�ǰ����
	 */
	public static int getDay() {
		Calendar rightNow = getCalendar();
		return rightNow.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * �������ڼ�
	 */
	public static int getWeekDay() {
		Calendar rightNow = getCalendar();
		return rightNow.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * ����ָ�����ڵ����
	 * @param millis ��������
	 */
	public static int getYear(long millis) {
		String strDate = String.valueOf(millis);
		String year = strDate.substring(0, 4);
		return Integer.parseInt(year);
	}

	/**
	 * ����ָ�����ڵ��·�
	 * @param millis ��������
	 */
	public static int getMonth(long millis) {
		String strDate = String.valueOf(millis);
		String month = strDate.substring(4, 6);
		return Integer.parseInt(month);
	}

	/**
	 * ����ָ�����������·��е�����
	 * @param millis ��������
	 */
	public static int getDay(long millis) {
		if (millis <= 0)
			return -1;
		String strDate = String.valueOf(millis);
		String day = strDate.substring(6, 8);
		return Integer.parseInt(day);
	}

	/**
	 * ���ص�ǰ���ڵĺ���������ȷ��Ϊ���룩
	 */
	public static long getCurrentSecond() {
		long currTime = currentTimeMillis();
		//    currTime = currTime / 1000 * 1000;
		return currTime;
	}

	/**
	 * �ж���������֮����������
	 * @param millis1 ��������ʾ�ĵ�һ��ʱ�䡣
	 * @param millis2 ��������ʾ�ĵڶ���ʱ�䡣
	 * @return ��������ʱ������������
	 */
	public static int getIntervalDays(long millis1, long millis2) {
		long beginMillis1 = getOneDayBeginMillis(millis1);
		long beginMillis2 = getOneDayBeginMillis(millis2);
		long realMillis1 = getDateMillis(beginMillis1);
		long realMillis2 = getDateMillis(beginMillis2);
		return Math.round((realMillis1 - realMillis2) / 86400000);
	}

	/**
	 * ����һ����׼ʱ���һ�����׼ʱ������������
	 * �õ�һ����ǰ������ʱ��
	 * @param baseMillis ���������͵Ļ�׼ʱ��
	 * @param days ���׼ʱ����������
	 */
	public static long getAdvancedMillis(long baseMillis, int days) {
		//ȡ�������ĺ�����
		long realMillis = getDateMillis(baseMillis);
		realMillis += days * 86400000l;
		return getLongDateTime(realMillis);
	} //end getAdvancedMillis(long baseMillis, int days)

	/**
	 * ����һ����׼ʱ���һ�����׼ʱ������������
	 * �õ�һ����ǰ������ʱ��
	 * @param baseDate �ַ��������͵Ļ�׼ʱ��
	 * @param days ���׼ʱ����������
	 */
	public static long getAdvancedMillis(String baseDate, int days) {
		//ȡ�������ĺ�����
		long realMillis = getDateMillis(baseDate);
		realMillis += days * 86400000l;
		return getLongDateTime(realMillis);
	} //end getAdvancedMillis(long baseMillis, int days)

	/**
	 * ȡ��һ�����ʼ��������
	 * @param millis ��������
	 */
	public static long getOneDayBeginMillis(long millis) {
		if (millis <= 0)
			return millis;
		String strDate = String.valueOf(millis);
		if (strDate.length() < 8)
			return -1;
		strDate = strDate.substring(0, 8) + "000000";
		return Long.parseLong(strDate);
	}

	/**
	 * ȡ��һ�����ֹ��������
	 * @param millis ��������
	 */
	public static long getOneDayEndMillis(long millis) {
		if (millis <= 0)
			return millis;
		String strDate = String.valueOf(millis);
		if (strDate.length() < 8)
			return -1;
		strDate = strDate.substring(0, 8) + "235959";
		return Long.parseLong(strDate);
	}

	/**
	 * ȡ��һ�����ʼ��������
	 * @param strDate �����ַ�������ʽΪ��yyyy-mm-dd �� yyyy/mm/dd
	 */
	public static long getOneDayBeginMillis(String strDate) {
		if (strDate == null || (strDate != null && strDate.trim().equals("")))
			return -1;
		int len = strDate.length();
		if (len < 4)
			return -1;
		else if (len == 4) //yyyy
			strDate = strDate + "0101000000";
		else if (len <= 7) //yyyy-mm
			strDate = strDate.substring(0, 4) + strDate.substring(5, 7) + "01000000";
		else
			strDate =
				strDate.substring(0, 4)
					+ strDate.substring(5, 7)
					+ strDate.substring(8, 10)
					+ "000000";
		return Long.parseLong(strDate);
	}

	/**
	 * ȡ��һ�����ֹ��������
	 * @param strDate �����ַ�������ʽΪ��yyyy-mm-dd �� yyyy/mm/dd
	 */
	public static long getOneDayEndMillis(String strDate) {
		if (strDate == null || (strDate != null && strDate.trim().equals("")))
			return -1;
		int len = strDate.length();
		if (len < 4)
			return -1;
		else if (len == 4) //yyyy
			strDate = strDate + "1231235959";
		else
			strDate =
				strDate.substring(0, 4)
					+ strDate.substring(5, 7)
					+ strDate.substring(8, 10)
					+ "235959";
		return Long.parseLong(strDate);
	}

	/**
	 * ���Ǳ�׼���ڸ�ʽת��Ϊ��׼���ڸ�ʽ����׼���ڸ�ʽ��yyyy-mm-dd��
	 */
	public static String getFormatDate(String strDate) {
		String sep = null;
		if (strDate.indexOf("-") > 0)
			sep = "-";
		else if (strDate.indexOf("/") > 0)
			sep = "/";

		StringTokenizer st = new StringTokenizer(strDate, sep);
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
		if (year == null || month == null || day == null)
			return "";

		if (year.length() == 2) {
			//������>=70������Ϊ�� 19xx
			if (year.compareTo("70") >= 0)
				year = "19" + year;
			//������<70������Ϊ�� 20xx
			else
				year = "20" + year;
		}
		if (month.length() == 1)
			month = "0" + month;
		if (day.length() == 1)
			day = "0" + day;

		try {
			if (Integer.parseInt(month) > 12)
				throw new Exception("�·ݲ��ܴ���12��");

			if (Integer.parseInt(day) > 31)
				throw new Exception("���ڲ��ܴ���31��");

			return year + "-" + month + "-" + day;
		} catch (Exception e) {
			return "";
		}
	} //end getFormatDate(String strDate)

	/**
	 * ���Ǳ�׼ʱ���ʽת��Ϊ��׼ʱ���ʽ����׼ʱ���ʽ��hh:mm:ss��
	 */
	public static String getFormatTime(String strTime) {
		if (strTime == null)
			return null;

		String sep = ":";
		StringTokenizer st = new StringTokenizer(strTime, sep);
		try {
			String hour = st.nextToken();
			String minute = st.nextToken();
			String second = st.nextToken();

			if (hour.length() == 1)
				hour = "0" + hour;
			if (minute.length() == 1)
				minute = "0" + minute;
			if (second.length() == 1)
				second = "0" + second;

			if (Integer.parseInt(hour) > 24)
				throw new Exception("Сʱ���ܴ���24��");

			if (Integer.parseInt(minute) > 60)
				throw new Exception("���Ӳ��ܴ���60��");

			if (Integer.parseInt(second) > 60)
				throw new Exception("���Ӳ��ܴ���60��");

			return hour + ":" + minute + ":" + second;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * �ж�ĳ���Ƿ�Ϊ����
	 *
	 * @param  year �꣨���Σ�
	 * @return ture����ʾδ���ꣻfalse��ʾ��������
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �ж�ĳ���Ƿ�Ϊ����
	 *
	 * @param  year �꣨���Σ�
	 * @return ture����ʾδ���ꣻfalse��ʾ��������
	 */
	public static boolean isRunNian(int year) {
		if ((year % 4 == 00) && (year % 100 != 0) || (year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ����ĳ��ĳ���µ�����
	 *
	 * @param year ���
	 * @param month �·�
	 */
	public static int getDaySumOfMonth(int year, int month) {
		int days = 0;
		switch (month) {
			case 1 :
				days = 31;
				break;
			case 2 :
				if (isLeapYear(year))
					days = 29;
				else
					days = 28;
				break;
			case 3 :
				days = 31;
				break;
			case 4 :
				days = 30;
				break;
			case 5 :
				days = 31;
				break;
			case 6 :
				days = 30;
				break;
			case 7 :
				days = 31;
				break;
			case 8 :
				days = 31;
				break;
			case 9 :
				days = 30;
				break;
			case 10 :
				days = 31;
				break;
			case 11 :
				days = 30;
				break;
			case 12 :
				days = 31;
				break;
		}
		return days;
	}

	/**
	 * ȡ��ǰ��ϵͳʱ�䣨����Sysconfig.tradeDate��
	 */
	public static long currentTimeMillis() {
		/*
		TransactionManager transMgr = null;
		try{
		  transMgr = new TransactionManager(PoolType.SPEC_POOL);
		
		  DBTableSQL dbTableSQL = new DBTableSQL(transMgr);
		  String sql = "SELECT (SYSDATE-TO_DATE('19700101','yyyymmdd'))*24*60*60*1000 FROM dual";
		  dbTableSQL.executeQuery(sql);
		  dbTableSQL.next();
		  long currTime = dbTableSQL.getLong(1);
		
		  transMgr.`();
		  return getLongDateTime(currTime);
		}
		catch(AppException e){
		  if (transMgr != null) transMgr.cancelTransaction();
		}
		catch(SQLException e){
		  if (transMgr != null) transMgr.cancelTransaction();
		}
		catch(Exception e){
		  if (transMgr != null) transMgr.cancelTransaction();
		} //end catch
		return getLongDateTime(System.currentTimeMillis() + 8*60*60*1000); //GMT+8:00
		*/
		//    return getLongDateTime(System.currentTimeMillis() + 8*60*60*1000 + adjustTime); //GMT+8:00
		//ȡϵͳ����
		//long currentSystemTime =
		//getLongDateTime(System.currentTimeMillis() + 8*60*60*1000 + adjustTime); //GMT+8:00
		long currentSystemTime = getLongDateTime(System.currentTimeMillis() + adjustTime);
		//GMT+8:00
		//ȡϵͳ��������
		long tradeDate = 0;
		//���ϵͳ��������>ϵͳ���ڣ���ȡϵͳ�������ڣ�����ȡϵͳ����
		return tradeDate > getOneDayEndMillis(currentSystemTime) ? tradeDate : currentSystemTime;
	} //end currentTimeMillis()

	/**
	 * ȡ��ǰ��ϵͳʱ��
	 */
	public static long currentSystemTimeMillis() {
		//long currentSystemTime =
		//  getLongDateTime(System.currentTimeMillis() + 8*60*60*1000 + adjustTime); //GMT+8:00
		long currentSystemTime = getLongDateTime(System.currentTimeMillis() + adjustTime);
		//GMT+8:00
		return currentSystemTime;
	} //end currentTimeMillis()

	/**
	 * ȡ��ǰʱ����ǰ��֮��seconds��ĳ�������������  
	 * @param seconds ��ǰ��֮�������
	 * @return  
	 */
	public static long getNextSystemTimeMillis(int seconds) {
		long currentSystemTime = System.currentTimeMillis() + adjustTime;
		long currentTime = getLongDateTime(currentSystemTime + seconds * 1000);
		return currentTime;
	}

	/**
	 * ��ָ����ʱ����ǰ���˺�
	 * @param datetime1 yyyymmddhhmmss���ַ�������ʱ��
	 * @param field Calendar��ʱ�䳣����Calendar.YEAR��Calendar.MONTH��Calendar.DATE��
	 * Calendar.HOUR_OF_DAY��Calendar.MINUTE��Calendar.SECOND
	 * 
	 * @param seconds ��ǰ����˵�����
	 * 
	 * @return ���ظ�ʽΪ yyyymmddhhmmss ��Long������ʱ��
	 */
	public static long calculateTimeMillis(long datetime1, int field, int number) {

		String datetime = Long.toString(datetime1);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(datetime.substring(0, 4)));
		calendar.set(Calendar.MONTH, Integer.parseInt(datetime.substring(4, 6)) - 1);
		calendar.set(Calendar.DATE, Integer.parseInt(datetime.substring(6, 8)));
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(datetime.substring(8, 10)));
		calendar.set(Calendar.MINUTE, Integer.parseInt(datetime.substring(10, 12)));
		calendar.set(Calendar.SECOND, Integer.parseInt(datetime.substring(12, 14)));
		calendar.add(field, number);
		long currentTime = getLongDateTime(calendar.getTime().getTime());
		return currentTime;
	}


	public static long getLocalAdjustTime() {
		return adjustTime;
	}

	public static long getLocalCurrentTimeMillis() {
		return getLongDateTime(System.currentTimeMillis());
		//return getLongDateTime(System.currentTimeMillis() + 8*60*60*1000); //GMT+8:00
	} //end currentTimeMillis()

	/**
	 * �Ѻ�����ת�����������ں�ʱ�䡣
	 * @param millis ����1970��1��1����ҹ0��ĺ�������
	 * @return ���ظ�ʽΪ yyyymmddhhmmss ���ַ����ں�ʱ�䡣
	 */
	public static long getLongDateTime(long millis) {
		if (millis <= 0)
			return -1;
		//    if (millis == 0) return 19700101000000l;
		//   Calendar rightNow = Calendar.getInstance();
		Calendar rightNow = getCalendar();
		rightNow.setTime(new java.util.Date(millis));
		int year = rightNow.get(Calendar.YEAR);
		int month = rightNow.get(Calendar.MONTH) + 1;
		int day = rightNow.get(Calendar.DAY_OF_MONTH);
		int hour = rightNow.get(Calendar.HOUR_OF_DAY);
		int minute = rightNow.get(Calendar.MINUTE);
		int second = rightNow.get(Calendar.SECOND);
		String strDateTime =
			year
				+ (month < 10 ? "0" + month : month + "")
				+ (day < 10 ? "0" + day : day + "")
				+ (hour < 10 ? "0" + hour : hour + "")
				+ (minute < 10 ? "0" + minute : minute + "")
				+ (second < 10 ? "0" + second : second + "");
		return Long.parseLong(strDateTime);
	} //end getLongDateTime(long millis)

	/**
	 * �Ѹ�ʽΪ yyyy/mm/dd �� yyyy-mm-dd ������ת�����������ڡ�
	 * ���߰Ѹ�ʽΪ yyyy/mm/dd hh:mm:ss �� yyyy-mm-dd hh:mm:ss ������ʱ��ת�����������ڡ�
	 * @param sDate ��ʾһ�������ַ�������ʽΪ yyyy/mm/dd �� yyyy-mm-dd ��
	 * @return ���ظ�ʽΪ yyyymmddhhmmss �������������ں�ʱ�䡣
	 */
	public static long getLongDateTime(String sDate) {
		return getMillis(sDate);
	}

	/**
	 * ���ر��µ�һ������ڡ�
	 * @param strDate �ַ������ڣ���ʽ��yyyy-mm-dd��
	 * @return ���ظ�ʽ��yyyy-mm-dd
	 */
	public static String getFirstDateOfMonth(String strDate) {
		return strDate.substring(0, 8) + "01";
	} //end getFirstDateOfMonth(String strDate)

	/**
	 * ���ر������һ������ڣ����ظ�ʽ��yyyy-mm-dd��
	 * @param strDate �ַ������ڣ���ʽ��yyyy-mm-dd��
	 * @return ���ظ�ʽ��yyyy-mm-dd
	 */
	public static String getLastDateOfMonth(String strDate) {
		long millis = getMillis(strDate);
		int year = getYear(millis);
		int month = getMonth(millis);
		int day = getDaySumOfMonth(year, month);
		return strDate.substring(0, 8) + day;
	} //end getLastDateOfMonth(String strDate)

	/**
	 * �������µ�һ������ڡ�
	 * @param strDate �ַ������ڣ���ʽ��yyyy-mm-dd��
	 * @return ���ظ�ʽ��yyyy-mm-dd
	 */
	public static String getFirstDateOfLastMonth(String strDate) {
		long millis = getMillis(strDate);
		int year = getYear(millis);
		int month = getMonth(millis);
		int lastMonth = month - 1;
		if (lastMonth == 0) {
			lastMonth = 12;
			year = year - 1;
		}
		return String.valueOf(year)
			+ "-"
			+ (lastMonth > 9 ? String.valueOf(lastMonth) : "0" + lastMonth)
			+ "-01";
	} //end getFirstDateOfLastMonth(strDate)

	/**
	 * �����������һ������ڡ�
	 * @param strDate �ַ������ڣ���ʽ��yyyy-mm-dd��
	 * @return ���ظ�ʽ��yyyy-mm-dd
	 */
	public static String getLastDateOfLastMonth(String strDate) {
		long millis = getMillis(strDate);
		int year = getYear(millis);
		int month = getMonth(millis);
		//���������·�
		month = month - 1;
		if (month == 0) {
			month = 12;
			year = year - 1;
		}
		int day = getDaySumOfMonth(year, month);
		return ""
			+ year
			+ "-"
			+ ((month > 9 ? String.valueOf(month) : "0" + month))
			+ "-"
			+ String.valueOf(day);
	} //end getLastDateOfLastMonth(strDate)

	/**
	 * ����һ����׼ʱ���һ�����׼ʱ������������
	 * �õ�һ����ǰ������ʱ��
	 * @param baseDate �ַ��������͵Ļ�׼ʱ��
	 * @param days ���׼ʱ��������������/����
	 * @param �����ַ������ڣ���ʽΪ��yyyy-mm-dd��
	 */
	public static String getAdvancedDate(String baseDate, int days) {
		//ȡ�������ĺ�����
		long realMillis = getDateMillis(baseDate);
		realMillis += days * 86400000l;
		return getDate(getLongDateTime(realMillis));
	} //end getAdvancedDate(long baseMillis, int days)

	/**
	 * ���SysConfig�������TradeDateʱ����Ϊtoday
	 * @param
	 * @return long
	 */
	public static long getTradeDateMillis() {
		/*
		TransactionManager transMgr = null;
		long dbTradeDate = -1;
		try {
		    transMgr = new TransactionManager(PoolType.SPEC_POOL);
		
		    DBTableSQL dbTableSQL = new DBTableSQL(transMgr);
		    String sql = "SELECT TRADEDATE FROM SYSCONFIG";
		    dbTableSQL.executeQuery(sql);
		    dbTableSQL.next();
		    dbTradeDate = dbTableSQL.getLong(1);
		    transMgr.endTransaction();
		} catch (AppException e) {
		    if (transMgr != null)
		        transMgr.cancelTransaction();
		} catch (SQLException e) {
		    if (transMgr != null)
		        transMgr.cancelTransaction();
		} catch (Exception e) {
		    if (transMgr != null)
		        transMgr.cancelTransaction();
		} //end catch
		return dbTradeDate;
		*/
		//��ʱ�Ķ����Ҳ���������Ӧ���׳��쳣
			return -1;
	} //end getTradeDateMillis

	/**
	 * �ж���������֮����������
	 * @param millis1 ��������ʾ�ĵ�һ��ʱ�䡣
	 * @param millis2 ��������ʾ�ĵڶ���ʱ�䡣
	 * @return ��������ʱ������������
	 */
	public static long getIntevalSeconds(long millis1, long millis2) {
		long realMillis1 = getDateMillis(millis1);
		long realMillis2 = getDateMillis(millis2);
		return Math.round((realMillis1 - realMillis2) / 1000);
	}

	/**
	 * �ж���������֮����������
	 * Ҫ������ʱ�����ͬһ����
	 */
	public static long getIntevalSecondsForTDay(long offerStopTime, long offerStartTime) {

		long LstopTime = offerStopTime - offerStopTime / 1000000 * 1000000;
		// change to HHMISS

		long LstartTime = offerStartTime - offerStartTime / 1000000 * 1000000;
		//change to HHMISS

		return (LstopTime / 10000 - LstartTime / 10000) * 60 * 60
			+ ((LstopTime / 100 - LstopTime / 10000 * 100)
				- (LstartTime / 100 - LstartTime / 10000 * 100))
				* 60
			+ LstopTime
			- LstopTime / 100 * 100
			- (LstartTime - LstartTime / 100 * 100);
	}

	/**
	 * ���������ڣ�yyyymmddhhmmss��ת���ɺ�������
	 */
	public static long getDateMillis(long millis) {
		return getDateMillis(getDateTime(millis));
	} //end getDateMillis(long millis)


    
	/**************************** private methods ****************************/
	private static Calendar getCalendar() {
		return Calendar.getInstance();
		//return Calendar.getInstance(TimeZone.getTimeZone("GMT+0:00"));
	}

	/**
	 * �Ѹ�ʽΪ yyyy/mm/dd �� yyyy-mm-dd ������ת���ɺ�������
	 * ���߰Ѹ�ʽΪ yyyy/mm/dd hh:mm:ss �� yyyy-mm-dd hh:mm:ss ������ʱ��ת���ɺ�������
	 * @param strDate ��ʾһ�������ַ�������ʽΪ yyyy/mm/dd �� yyyy-mm-dd ��
	 * @return ���ؾ���1970��1��1����ҹ0��ĺ�������
	 */
	private static long getDateMillis(String strDate) {
		if (strDate == null || (strDate != null && strDate.trim().equals("")))
			return -1;
		String sDate = strDate;
		String sTime = null;
		String sep = " ";
		StringTokenizer st = null;
		long millis = 0;
		if (strDate.indexOf(":") > 0) {
			st = new StringTokenizer(strDate, sep);
			sDate = st.nextToken();
			sTime = st.nextToken();
		}
		if (sTime != null) {
			st = new StringTokenizer(sTime, ":");
			int hour = Integer.parseInt(st.nextToken());
			int minute = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			millis = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
		}
		try {
			sDate = getFormatDate(sDate);
			sDate = sDate.toString().replace('/', seperator);
			if (sDate.equals("1970-01-01"))
				millis += 0;
			else
				//ԭ��Ҫ��8Сʱ(����28800000)�����ڸ�Ϊ����
				millis = java.sql.Date.valueOf(sDate).getTime() + millis;
			return millis;
		} catch (Throwable e) {
		}
		return -1;
	}

	/**
	 * ����ָ�����ڵ��ꡢ�·�
	 * @param millis ��������
	 */
	public static int getMonthMillis(long millis) {
		String strDate = String.valueOf(millis);
		if (strDate.length() <= 6)
			return Integer.parseInt(strDate);
		String month = strDate.substring(0, 6);
		return Integer.parseInt(month);
	}

	/**
	 *�õ��Ȼ�׼ʱ�����󼸸��µ�ʱ�䣬���monthΪ��������õ�һ���Ȼ�׼ʱ����ǰ�����µ�ʱ��
	 * @param baseTime
	 * @param month
	 * @return
	 */
    public static long getNextMonthTime(long baseTime, int month) {
        long baseTimeYear = baseTime / 1000000 / 10000; //�õ����
        long baseTimeMongth = baseTime / 100000000 - baseTimeYear * 100;
        long baseTimeDaytime =
            baseTime - baseTimeYear * 1000000 * 10000 - baseTimeMongth * 100000000;

        baseTimeYear = baseTimeYear + (baseTimeMongth + month) / 12;
        baseTimeMongth = (baseTimeMongth + month) - (baseTimeMongth + month) / 12 * 12;
        if (baseTimeMongth <= 0) {
            baseTimeYear = baseTimeYear - 1;
            baseTimeMongth = baseTimeMongth + 12;
        }
        long newTime =
            baseTimeYear * 1000000 * 10000 + baseTimeMongth * 100000000 + baseTimeDaytime;
        return newTime;

    }

    /**
     * ���ʱ��ĳ����͸�ʽ
     * @param time ʱ���ַ���(hh:mm:ss)
     * @return hhmmss
     */
    public static long getTimeMilli(String time) {
        int hourPos = time.indexOf(":");
        int secondPos = time.indexOf(":", hourPos + 1);

        if (hourPos < 0 || secondPos < 0) {
            return 0;
        }
        String hour = time.substring(0, hourPos);
        String minute = time.substring(hourPos + 1, secondPos);
        String second = time.substring(secondPos + 1);
        return Long.parseLong(hour + minute + second);
    }

    /**
     * ����MQ����������������ʱ���ַ�������ʽyyyymmdd-hh:mm:ss��
     * ת��Ϊ�������ں�ʱ�䣨��ʽyyyymmddhhmmss��
     * @param time ʱ���ַ�����yyyymmdd-hh:mm:ss��
     * @return yyyymmddhhmmss
     */
    public static long convertTime(String time) {
        time = time.replace('-', ' ');
        time = time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6, 8) + time.substring(8);
        return getMillis(time);
    }
    
    /**
     * ���������ں�ʱ�䣨��ʽyyyymmddhhmmss��ת��Ϊ
     * ��MQ����������������ʱ���ַ�������ʽyyyymmdd-hh:mm:ss��
     * @param time ����ʱ���ַ�����yyyymmddhhmmss��
     * @return yyyymmdd-hh:mm:ss
     */
    public static String convertTimeToMQ(String time) {
        if(time.length() != 14) {
            return null;
        }
        return time.substring(0, 8) + "-" + time.substring(8, 10) + ":" + time.substring(10, 12) + ":" + time.substring(12, 14);
    }
    
    /**
     *ȡָ�����ڵ���һ��     YYYYMMDDhhmmss
     * @param baseTime
     * @return YYYYMMDDhhmmss
     */
    public static long getNextDayTime(long baseTime) {
        long baseTimeYear = baseTime / 1000000 / 10000; //�õ����
        long baseTimeMonth = baseTime / 100000000 - baseTimeYear * 100;//�·�
        long baseTimeDay = baseTime / 1000000 - baseTimeYear * 10000 - baseTimeMonth * 100;//����
        long baseTimeTime =
            baseTime
                - baseTimeYear
                * 1000000
                * 10000
                - baseTimeMonth
                * 100000000
                - baseTimeDay
                * 1000000;//ʱ��

        int days = getMonthDays((int) baseTimeYear, (int) baseTimeMonth);
        baseTimeDay++;
        if (baseTimeDay > days) {
            baseTimeYear = baseTimeYear + (baseTimeMonth + 1) / 12;
            baseTimeMonth = (baseTimeMonth + 1) - (baseTimeMonth + 1) / 12 * 12;
            if (baseTimeMonth == 0) {
                baseTimeYear--;
                baseTimeMonth = 12;
            }
            baseTimeDay = 1;
        }

        long newTime =
            baseTimeYear
                * 1000000
                * 10000
                + baseTimeMonth
                * 100000000
                + baseTimeDay
                * 1000000
                + baseTimeTime;
        return newTime;
    }
    
    /**
     * ����һ���·ݵ�����
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        int day = 30;
        switch (month) {
            case 1 :
                day = 31;
            break;
            case 2 :
                if (isLeapYear(year))
                    day = 29;
                else
                    day = 28;
            break;
            case 3 :
                day = 31;
            break;
            case 5 :
                day = 31;
            break;
            case 7 :
                day = 31;
            break;
            case 8 :
                day = 31;
            break;
            case 10 :
                day = 31;
            break;
            case 12 :
                day = 31;
            break;
            default :
                day = 30;
            break;
        }
        return day;
    }

    /**
     * ��ȡ���ʱ����ַ���
     * ���ظ�ʽ:yyyy-mm-dd hh:mm:ss.fffffffff
     */
    public static String getCurrentTimeMilliSeconds(long time) {
        return new Timestamp(time).toString();
    }
    
    /**
     * ��long��ʱ��ת����׼ʱ���ʽ
     */
	public static String formatTime(long time) {
	    
	    String sTime = String.valueOf(time);
	    if(sTime.length()<5){
	        return sTime;
	    }
	    if(sTime.length()==5){
	        sTime = "0"+sTime;
	    }
		return sTime.substring(0, 2) 
				+ ":" + sTime.substring(2, 4) 
				+ ":"+ sTime.substring(4, 6);

	}
	
	/**
	 * ��ȡ���к����ʱ���ʽ
	 * @return
	 */
    public static long getLongDateTimeSSS() {
        return Long.parseLong(sdf.format(new Date()));
    }
    
    /**
     * �ж�ʱ���Ƿ��ǽ��죬ʱ�䴮��ʽyyyyDDmmXXX
     * @param dateTime
     * @return
     */
    public static boolean isTodayDate(String dateTime){
        if(dateTime == null || dateTime.length() < 8)
            return false;
        return String.valueOf(currentSystemTimeMillis()).substring(0, 8).equals(dateTime.substring(0, 8));
    }
    
    
    /**
     * ���ݵ�ǰ���ӻ�ȡ��һ���ӵķ�����
     * @param currentMinute yyyyMMddHHmmss
     * @return HHmm
     * @throws ParseException 
     */
    public static int getNextMinute(long currentMinute,int minuteCnt) throws ParseException {
        String time = sdf_t.format(new Date(sdf_t.parse(String.valueOf(currentMinute)).getTime()+minuteCnt*60*1000));
        return Integer.parseInt(time.substring(8, 12));
        
    }
    
    
    /**
     * ���ݵ�ǰ���ӻ�ȡ�����ɷ��ӵķ�����
     * @param currentMinute yyyyMMddHHmmss
     * @return HHmm
     * @throws ParseException 
     */
    public static long getLastSeveralMinute(long currentMinute, int minuteCnt)
        throws ParseException {
        
        String currentMinuteStr = String.valueOf(currentMinute);
        if(currentMinuteStr.length() < 4){
            currentMinuteStr = "0" + currentMinuteStr;
        }
        String time =
                sdf_minute.format(
                new Date(
                    sdf_minute.parse(currentMinuteStr).getTime() - minuteCnt * 60 * 1000));
        return Long.parseLong(time);

    }

	/**
	 * ���ݴ���Ĳ�������ȡ��ǰ��������һ����
	 * @param occurTime yyyyMMddHHmmss
	 * @param cnt ��Ҫ����������ǰ���ݲ�Ҫ����60
	 * @return yyyyMMddHHmmss
	 */

	public static long getNextMinuteMillisecond (long occurTime, int cnt) {
		String currentOccurTimeStr = String.valueOf(calculateTimeMillis(occurTime, Calendar.MINUTE, cnt));

		currentOccurTimeStr = currentOccurTimeStr.substring(0, 12) + "00";

    	return Long.parseLong(currentOccurTimeStr);
	}
    
	/**
	 * �Ѻ���ת��mm:ss
	 * @param milli
	 * @return
	 */
	public static String getFmtTime(long milli){
	    long mm = milli/1000/60;
	    long ss = (milli-mm*1000*60)/1000;
	    return mm > 0 ? (mm+":"+ss) : ""+ss;
	}

	/**
     * �Ѻ�����ת�����������ں�ʱ�䡣
     * @param millis ����1970��1��1����ҹ0��ĺ�������
     * @return ���ظ�ʽΪ yyyymmddhhmmssSSS ���ַ����ں�ʱ�䡣
     */
    public static long getLongDateMilliTime(long millis) {
        if (millis <= 0)
            return -1;
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new java.util.Date(millis));
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH) + 1;
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minute = rightNow.get(Calendar.MINUTE);
        int second = rightNow.get(Calendar.SECOND);
        int milli = rightNow.get(Calendar.MILLISECOND);
        String strDateTime =
                year
                + (month < 10 ? "0" + month : month + "")
                + (day < 10 ? "0" + day : day + "")
                + (hour < 10 ? "0" + hour : hour + "")
                + (minute < 10 ? "0" + minute : minute + "")
                + (second < 10 ? "0" + second : second + "")
                + milli + "";
        return Long.parseLong(strDateTime);
    } //end getLongDateMilliTime(long millis)
    
} //class TradeDate
