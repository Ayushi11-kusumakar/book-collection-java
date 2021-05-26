package com.book.collection.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	public static String MyHostIP = "";
	public static String MyHostName = "";

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static void getMyHostIP() {

		try {
			InetAddress addr = InetAddress.getLocalHost();
			MyHostIP = addr.getHostAddress();
			MyHostName = addr.getHostName();

		} catch (UnknownHostException ex) {
			System.out.println("Exception: " + ex);

		}
	}

}
