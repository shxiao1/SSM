package com.cjd.ssm.utils;

import java.security.SecureRandom;
import java.util.UUID;



public class IdGen{

	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	


}
