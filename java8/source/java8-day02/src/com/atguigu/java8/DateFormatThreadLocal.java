package com.atguigu.java8;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatThreadLocal {
	
	private static final ThreadLocal<DateFormat> df = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HHH:mm:ss"));

	private static final ThreadLocal<DateFormat> sdf = new ThreadLocal<>();

	public static final Date convert(String source) throws ParseException{
		return df.get().parse(source);
	}

	public static final Date convert2(String source) throws ParseException{
		sdf.set(new SimpleDateFormat("yyyy-MM-dd HHH:mm:ss"));
		return sdf.get().parse(source);
	}
}
