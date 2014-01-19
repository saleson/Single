package com.single.commons.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * 将特定的字符按顺序转换成指定的字符串.
	 * 
	 * @param src 未组转之前的字符串
	 * @param sep 特定字符，用于被替换成特定的值
	 * @param values 用于替换字符的值
	 * @return 组装后SQL语句
	 */
	public static String combine(String src, char sep, String... values) {
		StringBuilder buf = new StringBuilder(src.length() + values.length * 16);
		int num = 0;
		for (int i = 0, n = src.length(); i < n; i++) {
			if (src.charAt(i) == sep) {
				if (num < values.length) {
					buf.append(values[num]);
					num++;
				} else {
					buf.append(src.substring(i));
					break;
				}
			} else {
				buf.append(src.charAt(i));
			}
		}
		return buf.toString();
	}
	
	
	
	public static String replace(String text, Map<String, String> map){
    	Pattern pat = Pattern.compile("\\$\\{([\\w\\.\\-_]*)\\}");
		Matcher mat = pat.matcher(text);
		String returnText = text;
		while(mat.find()){
			String name = mat.group(1);
			if(map.containsKey(name)){
				returnText = returnText.replace(mat.group(), map.get(name));
			}
		}
		return returnText;
    }
}
