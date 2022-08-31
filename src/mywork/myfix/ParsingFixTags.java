package mywork.myfix;

import java.io.File;

import mywork.myfix.util.BefParsingFixLogByTagUtil;

public class ParsingFixTags {
	
	private static String fixDir = "F://File//编程实践//解析FIX tag值//精城FIX日志";
	
	public static void main(String[] args) {
		batchAnalyFixTags(fixDir);
	}
	
	//批量分析Fix tags
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
