package com.util.otherutil;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

public class ExceptionUtils {
	public static String exceptionToString(Exception e) {
		CharArrayWriter caw = new CharArrayWriter();
		e.printStackTrace(new PrintWriter(caw));
		return caw.toString();
	}
}
