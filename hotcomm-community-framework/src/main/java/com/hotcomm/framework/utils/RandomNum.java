package com.hotcomm.framework.utils;

import java.util.Random;

public class RandomNum {
	//获取某个范围的随机数
	public static int getNum(Integer max, Integer min) {
		Random random = new Random();
		int s =random.nextInt(max - min + 1) + min;
		return s;
	}
}
