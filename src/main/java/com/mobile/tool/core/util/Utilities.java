package com.mobile.tool.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

	public static Date convertStringToDate(String date, String format) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.parse(date);
	}
}
