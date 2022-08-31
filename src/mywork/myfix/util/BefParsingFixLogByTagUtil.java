package mywork.myfix.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 * 1、解析FixGway_BLP-CMSE_20181106.log tag值
 * D最多Tag
 * E表示重复集合，并提出最多Tag
 */
public class BefParsingFixLogByTagUtil {

	private static Log parFixlog = LogFactory.getLog(BefParsingFixLogByTagUtil.class);
	private static String logPath = "F://File//编程实践//解析FIX tag值//FixGway_BLP-CMSE_20181106_test_simple.log";
	private static String separa = "";
	private static String msgTypeD = "35=D";
	private static String msgTypeE = "35=E";
	private static String basketFlag = "73";

	public static void main(String[] args) {
		readLog(logPath);
	}

	// 读取分析Fix日志，统计35=D，35=E的最多tag集合，并分别写入文件
	public static Set<String> readLog(String filePath) {
		BufferedReader bufReader = null;
		// 分别保存35=D,35=E 订单
		List<String> singleOrder = new ArrayList<String>();
		List<String> basketOrder = new ArrayList<String>();
		Set<String> tagsSet = new HashSet<String>();
		Map<String, Set<String>> tagsMap = new HashMap<String, Set<String>>();
		try {
			bufReader = new BufferedReader(new FileReader(new File(filePath)));
			String logLine = "";
			while ((logLine = bufReader.readLine()) != null) {
				if (logLine.contains(msgTypeD)) {
					singleOrder.add(logLine);
				}
				if (logLine.contains(msgTypeE)) {
					basketOrder.add(logLine);
				}
			}
			// 统计 35=D
			countTagsByTypeD(singleOrder, tagsSet);
			tagsMap.put(msgTypeD, tagsSet);
			parFixlog.info("D final: " + tagsSet);
			// 统计 35=E
			countTagsByTypeE(basketOrder, tagsSet);
			tagsMap.put(msgTypeD, tagsSet);
			parFixlog.info("E final: " + tagsSet);
		} catch (Exception e) {
			e.printStackTrace();
			parFixlog.error(e.getMessage());
		} finally {
			try {
				if (bufReader != null) {
					bufReader.close();
				}
				singleOrder.clear();
				basketOrder.clear();
				tagsSet.clear();
			} catch (Exception e) {
				parFixlog.error(e.getMessage());
			}
		}
		return tagsSet;
	}

	/**
	 * 统计35=D 包含tags并集
	 * 
	 * @param sigalLines
	 * @param dTags
	 * @return
	 */
	public static void countTagsByTypeD(List<String> sigalLines,
			Set<String> dTags) {
		List<String> tagList = new ArrayList<String>();
		// 清空,后续统计
		dTags.clear();
		if (null != sigalLines && sigalLines.size() > 0) {
			tagList = handleDTag(sigalLines);
			for (String tag : tagList) {
				dTags.add(tag);
			}
		}
		// 保存分析结果
		saveFixTagReport(msgTypeD, dTags.toString());
		tagList.clear();
	}

	/**
	 * 统计E tags:根据73后值，=1/>1分别加入列表去重，最后加入dTags
	 * 
	 * @param sigalLines
	 * @param dTags
	 * @return
	 */
	public static void countTagsByTypeE(List<String> sigalLines,
			Set<String> dTags) {
		Set<String> dSingleTags = new HashSet<String>();
		Set<String> dBasketTags = new HashSet<String>();
		// 73 > 1?
		List<String> tagList = new ArrayList<String>();
		List<String> tagBasketList = new ArrayList<String>();
		// 清空,后续统计
		dTags.clear();
		if (null != sigalLines && sigalLines.size() > 0) {
			List<List<String>> handledEList = handleETag(sigalLines);
			tagList = handledEList.get(0);
			tagBasketList = handledEList.get(1);

			if (null != tagList && tagList.size() > 0) {
				for (String tag : tagList) {
					dSingleTags.add(tag);
				}
				dTags.add("\r\n35=E isn't group: " + dSingleTags.toString());
			}

			if (null != tagBasketList && tagBasketList.size() > 0) {
				for (String tag : tagBasketList) {
					dBasketTags.add(tag);
				}
				dTags.add("\r\n35=E is group: " + dBasketTags.toString());
			}
		}
		// 保存分析结果
		saveFixTagReport(msgTypeE, dTags.toString());
		tagList.clear();
	}

	// 分隔取tag
	public static List<String> handleDTag(List<String> fixLines) {
		List<String> tagList = new ArrayList<String>();
		String tagPre = "";
		String tagAft = "";
		StringTokenizer std = null;
		for (String fixLine : fixLines) {
			std = new StringTokenizer(fixLine, separa);
			int count = std.countTokens();
			for (int i = 0; i < count; i++) {
				tagPre = std.nextToken();
				// 获取tag
				if (tagPre.contains("=")) {
					tagAft = tagPre.substring(0, tagPre.lastIndexOf("="));
					if (!isInteger(tagAft)) {
						tagAft = "";
					}
				}
				if (!tagAft.isEmpty()) {
					tagList.add(tagAft);
				}
			}
		}
		return tagList;
	}

	// 35=E tag解析
	public static List<List<String>> handleETag(List<String> fixLines) {
		List<List<String>> eTagList = new ArrayList<List<String>>();
		List<String> tagList = new ArrayList<String>();
		// 保存
		List<String> tagBasketList = new ArrayList<String>();
		String tagPre = "";
		String tagAft = "";
		StringTokenizer ste = null;
		for (String fixLine : fixLines) {
			ste = new StringTokenizer(fixLine, separa);
			int count = ste.countTokens();
			for (int i = 0; i < count; i++) {
				tagPre = ste.nextToken();
				// 获取tag
				if (tagPre.contains("=")) {
					tagAft = tagPre.substring(0, tagPre.lastIndexOf("="));
					if (!isInteger(tagAft)) {
						tagAft = "";
					}
					if (!tagAft.isEmpty()) {
						if (isBasketFixOrder(fixLine)) {
							tagBasketList.add(tagAft);
						} else {
							tagList.add(tagAft);
						}
					}
				}
			}
		}
		eTagList.add(tagList);
		eTagList.add(tagBasketList);
		return eTagList;
	}

	// 保存分析结果
	public static void saveFixTagReport(String msgType, String msgContent) {
		File file = new File("E://Com_FixGway_BLP-CMSE_20181106_" + msgType
				+ ".txt");
		if (!file.exists()) {
			file.setWritable(true);
		}
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			bufferedWriter.write("\r\n"
					+ msgType
					+ ":\r\n"
					+ msgContent.replaceAll("\\],", "").replaceAll("\\[", "")
							.replaceAll("\\]", ""));
			bufferedWriter.flush();
		} catch (IOException e) {
			parFixlog.error(e.getMessage());
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					parFixlog.error(e.getMessage());
				}
			}
		}
	}

	// 35=E 订单，比较73后值 > 1 判断是否篮子订单
	public static boolean isBasketFixOrder(String oriOrder) {
		if (oriOrder.contains("=")) {
			String[] multiFlag = oriOrder.split(basketFlag + "=");
			String baVal = "";
			if(multiFlag[1].contains("")){
				baVal = multiFlag[1].substring(0, multiFlag[1].indexOf(""));
			}else{
				for(char c : multiFlag[1].toCharArray()){
					if(c < 57 && c > 48){
						baVal += c;
					}else{
						break;
					}
				}
			}
			if (oriOrder.contains(basketFlag) && isInteger(baVal)
					&& Integer.valueOf(baVal) > 1) {
				return true;
			}
		}
		return false;
	}

	// is integer
	public static boolean isInteger(String maybeNum) {
		for (int i = 0; i < maybeNum.length(); i++) {
			int chr = maybeNum.charAt(i);
			if (chr < 48 || chr > 57) {
				return false;
			}
		}
		return true;
	}
}
