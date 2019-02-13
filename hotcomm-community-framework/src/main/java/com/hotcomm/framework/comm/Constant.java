package com.hotcomm.framework.comm;

public class Constant {
	
	public enum DevModel{
		DEV("test"),
		PROD("prod");

		private String val;

		DevModel(String val) {
			this.val = val;
		}

		public String getVal() {
			return val;
		}
	}
	
}
