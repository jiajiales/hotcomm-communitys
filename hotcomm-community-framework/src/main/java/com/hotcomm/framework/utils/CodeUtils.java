package com.hotcomm.framework.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeUtils {
	
	private static final String HASHALGORITHMNAME="MD5";
	
	final static Base64.Decoder decoder = Base64.getDecoder();
	final static Base64.Encoder encoder = Base64.getEncoder();
	
    public static String md5(String source) {
    	StringBuffer sb = new StringBuffer(32);
    	try {
    		MessageDigest md 	= MessageDigest.getInstance("MD5");
    		byte[] array 		= md.digest(source.getBytes("utf-8"));
    		for (int i = 0; i < array.length; i++) {
    			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
    		}
    	} catch (Exception e) {
    		log.error("Can not encode the string '" + source + "' to MD5!", e);
    		return null;
    	}
    	return sb.toString();
    }
	
	public static String md5EncodeData(String inputData) {
		try {
			MessageDigest md5 = MessageDigest.getInstance(HASHALGORITHMNAME);
			return encoder.encodeToString(md5.digest(inputData.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException|UnsupportedEncodingException e) {
			log.error(inputData, e);
		} 
		return null;
	}
	
	public boolean checkpassword(String newpasswd,String oldpasswd){
		return md5EncodeData(newpasswd).equals(oldpasswd);
	}
	
    public static String base64Decode(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
           
            return  new String(decoder.decode(inputData.getBytes()));
        } catch (Exception e) {
            log.error(inputData, e);
        }
        return null;
    }

    /**
     * 对给定的字符串进行base64加密操作
     */
    public static String base64Encode(String inputData) {
        try {
            if (null == inputData) 
                return null;
            return encoder.encodeToString(inputData.getBytes());
        } catch (Exception e) {
        	log.error(inputData, e);
        }
        return null;
    }
    
    public static void main(String[] args) {
	System.out.println(md5("123456"));
    }
	
}
