package com.single.db.structure.test;

import com.single.xmlbeans.configuration.SingleConfigDocument;
import com.single.xmlbeans.configuration.impl.SingleConfigDocumentImpl;

public class XMLConfigurationTest {

	
	public static void test1(){
		SingleConfigDocument doc = SingleConfigDocument.Factory.newInstance();
		doc.getSingleConfig();
	}
	
	public static void main(String[] args) {
		
	}
}
