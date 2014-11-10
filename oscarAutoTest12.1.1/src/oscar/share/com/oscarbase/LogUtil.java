package com.oscarbase;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LogUtil {
	
	public static void appendLog(String tag, String message) throws IOException {
		String logfilePath = CaseConf.getInstance().getLogfilePath();
		String msg = tag+ " " + message;
		RandomAccessFile rf = new RandomAccessFile(logfilePath
				+ "\\AdminSuiteLog.txt", "rw");	
		rf.seek(rf.length());
		msg+="\r\n";
		rf.writeBytes(msg);
		rf.close();
	}
	
}
