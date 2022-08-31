package mywork.myfix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 * 1������FixGway_BLP-CMSE_20181106.log tagֵ
 * D���Tag
 * E��ʾ�ظ����ϣ���������Tag
 */
public class ParsingFixLogByTagProUtil {

	private static final Log parFixlog = LogFactory
			.getLog(ParsingFixLogByTagProUtil.class);
	private static final String fixDir = "F://File//���ʵ��//����FIX tagֵ//����FIX��־";
	private static final String separa = "";
	private static final String msgTypeD = "35=D";
	private static final String msgTypeE = "35=E";
	private static final String msgTypeEMulti = "35=E_MULTI";
	private static final String basketFlag = "73";

	// �� tag 35 D or E(73��ֵ > 1 ���) �ֱ�ͳ��tags���ϣ��ֱ�д��D/E�ļ�
	public static void main(String[] args) {
		mergeTags(fixDir);
	}

	// �ϲ�����fix log�������
	public static void mergeTags(String dirPath) {
		File fixDir = new File(dirPath);
		String logPath = "";
		if (fixDir.isDirectory()) {
			String[] fixArr = fixDir.list();
			if (null != fixArr && fixArr.length > 0) {
				Set<String> tempDSet = new LinkedHashSet<String>();
				Set<String> tempESet = new LinkedHashSet<String>();
				Set<String> tempESetMulti = new LinkedHashSet<String>();
				Map<String, Set<String>> finalMap = new HashMap<String, Set<String>>();
				for (String fixName : fixArr) {
					logPath = dirPath + File.separator + fixName;
					// ���������� distinct sum tags
					readLog(logPath, tempDSet, tempESet, tempESetMulti);
				}
				finalMap.put(msgTypeD, tempDSet);
				finalMap.put(msgTypeE, tempESet);
				finalMap.put(msgTypeEMulti, tempESetMulti);
				// ��������distinct tags ���
				saveFixTagReport(msgTypeD, finalMap.get(msgTypeD).toString(),
						fixArr);
				saveFixTagReport(msgTypeE, finalMap.get(msgTypeE).toString(),
						fixArr);
				saveFixTagReport(msgTypeE, finalMap.get(msgTypeEMulti)
						.toString(), fixArr);
				parFixlog.info("D final: " + finalMap.get(msgTypeD));
				parFixlog.info("E final: " + finalMap.get(msgTypeE)
						+ finalMap.get(msgTypeEMulti));
				finalMap.clear();
			}
		}
	}

	/**
	 * ��ȡ����Fix��־��ͳ��35=D��35=E�����tag���ϣ����ֱ�д���ļ�; �ϲ�����fix log������������ࣺ
	 * ��D/E����map,����setȥ��,������
	 */
	public static void readLog(String filePath, Set<String> tempDSet,
			Set<String> tempESet, Set<String> tempESetMulti) {
		BufferedReader bufReader = null;
		// �ֱ𱣴�35=D,35=E ����
		List<String> singleOrder = new ArrayList<String>();
		List<String> basketOrder = new ArrayList<String>();
		Set<String> tagsSet = new LinkedHashSet<String>();
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
			// ͳ�� 35=D
			countTagsByTypeD(singleOrder, tagsSet, tempDSet, tagsMap);

			// ͳ�� 35=E
			countTagsByTypeE(basketOrder, tagsSet, tempESet, tempESetMulti,
					tagsMap);
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
	}

	/**
	 * ͳ��35=D ����tags����
	 * @param sigalLines
	 * @param dTags
	 * @return
	 */
	public static Map<String, Set<String>> countTagsByTypeD(
			List<String> sigalLines, Set<String> dTags, Set<String> tempSet,
			Map<String, Set<String>> tagsMap) {
		List<String> tagList = new ArrayList<String>();
		// ���,����ͳ��
		dTags.clear();
		if (null != sigalLines && sigalLines.size() > 0) {
			tagList = handleDTag(sigalLines);
			for (String tag : tagList) {
				dTags.add(tag);
			}
		}
		tempSet.addAll(dTags);
		tagsMap.put(msgTypeD, dTags);
		tagList.clear();
		return tagsMap;
	}

	/**
	 * ͳ��E tags:����73��ֵ��=1/>1�ֱ�����б�ȥ�أ�������dTags
	 * @param sigalLines
	 * @param dTags
	 * @return
	 */
	public static Map<String, Set<String>> countTagsByTypeE(
			List<String> sigalLines, Set<String> dTags, Set<String> tempSet,
			Set<String> tempESetMulti, Map<String, Set<String>> tagsMap) {
		Set<String> dSingleTags = new LinkedHashSet<String>();
		Set<String> dBasketTags = new LinkedHashSet<String>();
		// 73 > 1?
		List<String> tagList = new ArrayList<String>();
		List<String> tagBasketList = new ArrayList<String>();
		// ���,����ͳ��
		dTags.clear();
		if (null != sigalLines && sigalLines.size() > 0) {
			List<List<String>> handledEList = handleETag(sigalLines);
			tagList = handledEList.get(0);
			tagBasketList = handledEList.get(1);

			if (null != tagList && tagList.size() > 0) {
				dSingleTags.add("\r\n35=E isn't group: ");
				for (String tag : tagList) {
					dSingleTags.add(tag);
				}
				dTags.addAll(dSingleTags);
			}

			if (null != tagBasketList && tagBasketList.size() > 0) {
				dBasketTags.add("\r\n35=E is group: ");
				for (String tag : tagBasketList) {
					dBasketTags.add(tag);
				}
				dTags.addAll(dBasketTags);
			}
		}
		tempSet.addAll(dSingleTags);
		tempESetMulti.addAll(dBasketTags);
		tagsMap.put(msgTypeE, dSingleTags);
		tagsMap.put(msgTypeEMulti, dBasketTags);
		if (null != tagList) {
			tagList.clear();
		}
		return tagsMap;
	}

	/**
	 *  �ָ�ȡtag
	 * @param fixLines
	 * @return
	 */
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
				// ��ȡtag
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

	/**
	 * 35=E tag����
	 * @param fixLines
	 * @return
	 */
	public static List<List<String>> handleETag(List<String> fixLines) {
		List<List<String>> eTagList = new ArrayList<List<String>>();
		List<String> tagList = new ArrayList<String>();
		// ����
		List<String> tagBasketList = new ArrayList<String>();
		String tagPre = "";
		String tagAft = "";
		StringTokenizer ste = null;
		for (String fixLine : fixLines) {
			ste = new StringTokenizer(fixLine, separa);
			int count = ste.countTokens();
			for (int i = 0; i < count; i++) {
				tagPre = ste.nextToken();
				// ��ȡtag
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

	/** 
	 * ����������
	 * @param msgType
	 * @param msgContent
	 * @param fixArr
	 */
	public static void saveFixTagReport(String msgType, String msgContent,
			String[] fixArr) {
		String fileName = fixDir.substring(fixDir.lastIndexOf("//") + 2,
				fixDir.length())
				+ "_" + msgType+ "_" + fixArr[0] + "--" + fixArr[fixArr.length - 1];
		File file = new File("E://" + fileName + ".txt");
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

	/**
	 *  35=E �������Ƚ�73��ֵ > 1 �ж��Ƿ����Ӷ���
	 * @param oriOrder
	 * @return
	 */
	public static boolean isBasketFixOrder(String oriOrder) {
		if (oriOrder.contains("=")) {
			String[] multiFlag = oriOrder.split(basketFlag + "=");
			String baVal = "";
			if (multiFlag[1].contains("")) {
				baVal = multiFlag[1].substring(0, multiFlag[1].indexOf(""));
			} else {
				for (char c : multiFlag[1].toCharArray()) {
					if (c < 57 && c > 48) {
						baVal += c;
					} else {
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

	/**
	 *  is integer
	 * @param maybeNum
	 * @return
	 */
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
