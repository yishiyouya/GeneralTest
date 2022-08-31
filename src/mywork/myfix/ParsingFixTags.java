package mywork.myfix;

import java.io.File;

import mywork.myfix.util.BefParsingFixLogByTagUtil;

public class ParsingFixTags {
	
	private static String fixDir = "F://File//���ʵ��//����FIX tagֵ//����FIX��־";
	
	public static void main(String[] args) {
		batchAnalyFixTags(fixDir);
	}
	
	//��������Fix tags
	public static void batchAnalyFixTags(String dirPath) {
		File fixDir = new File(dirPath);
		String logPath = "";
		if(fixDir.isDirectory()){
			for(String fixName : fixDir.list()){
				logPath = dirPath + File.separator + fixName;
				BefParsingFixLogByTagUtil.readLog(logPath);
			}
		}
	}
}
