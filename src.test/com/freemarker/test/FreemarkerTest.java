package com.freemarker.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;

import com.single.commons.util.StringUtils;

public class FreemarkerTest {

	/*public static void main(String[] args) throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("D:/AppData/Workspace/space 001/Single/WebRoot"));
		Template template = cfg.getTemplate("test.ftl");
		
		Writer fout = new PrintWriter(System.out);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Saleson");
		
		template.process(map, fout);
	}*/
	
	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}
