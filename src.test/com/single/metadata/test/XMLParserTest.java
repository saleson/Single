package com.single.metadata.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.xmlbeans.XmlException;

import com.single.app.component.metadate.ImageTextButton;
import com.single.app.component.metadate.ModelFramework;
import com.single.app.component.metadate.mapping.StructureMapping;
import com.single.app.component.metadate.parse.ModelFrameworkPersistenceListener;
import com.single.app.component.metadate.parse.ParserFactory;
import com.single.app.component.metadate.parse.ParsingListener;
import com.single.app.component.metadate.parse.ParsingProcessContext;
import com.single.app.component.metadate.parse.ViewBuildParsingListener;
import com.single.app.component.metadate.parse.XMLParser;
import com.single.xmlbeans.configuration.SingleConfigDocument;

public class XMLParserTest {
	
	public static void test1() throws XmlException, IOException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		XMLParser parser = ParserFactory.getParser(XMLParser.class, new Object[]{});
		parser.getParsingContext().addtListener(new ParsingListener() {
			
			@Override
			public void setDeprecated(boolean deprecated) {
				// TODO Auto-generated method stub
				System.out.println("ParsingListener.setDeprecated()");
			}
			
			@Override
			public boolean isDeprecated() {
				System.out.println("ParsingListener.isDeprecated()");
				return false;
			}
			
			@Override
			public void beforeParse(ParsingProcessContext context) {
				System.out.println(context);
				System.out.println("ParsingListener.beforeParse()");
			}
			
			@Override
			public void afterParsed() {
				// TODO Auto-generated method stub
				System.out.println("ParsingListener.afterParsed()");
			}

			@Override
			public void invoke(List<ModelFramework> mfList) {
				// TODO Auto-generated method stub
				
			}
		});
		File file = new File("");
		parser.parse(file);
		
		InputStream in = null;
		parser.parse(in);
		
		Reader reader = null;
		parser.parse(reader);
		
		SingleConfigDocument document = null;
		parser.parse(document);
	}
	
	public static void test2() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		XMLParser parser = ParserFactory.getParser(XMLParser.class, new Object[]{});
		parser.getParsingContext().addtListener(new ParsingListener() {
			
			@Override
			public void setDeprecated(boolean deprecated) {
				// TODO Auto-generated method stub
				System.out.println("ParsingListener.setDeprecated()");
			}
			
			@Override
			public boolean isDeprecated() {
				System.out.println("ParsingListener.isDeprecated()");
				return false;
			}
			
			@Override
			public void beforeParse(ParsingProcessContext context) {
				System.out.println(context);
				System.out.println("ParsingListener.beforeParse()");
			}
			
			@Override
			public void invoke(List<ModelFramework> mfList) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterParsed() {
				// TODO Auto-generated method stub
				
			}
		});
		
		for(int i=0;i<5;i++){
			Thread t = new TThread(parser);
			t.start();
			
		}
		
		for(int i=0;i<5;i++){
			Thread t = new MThread(parser);
			t.start();
			
		}
	}
	
	public static class TThread extends Thread{
			XMLParser parser;
			public TThread(XMLParser parser){
				this.parser = parser;
			}
			
			@Override
			public void run() {
				File file = new File("");
				try {
					parser.parse(file);
				} catch (XmlException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	public static class MThread extends Thread{
		XMLParser parser;
		public MThread(XMLParser parser){
			this.parser = parser;
		}
		
		@Override
		public void run() {
			System.out.println(parser.isParsing());
		}
}
	
	
	public static void test3(){
		File file = new File("D:/xmlbeanstest/generate xml/test5.xml");
		try {
			XMLParser parser = ParserFactory.getParser(XMLParser.class, new Object[0]);
			parser.addtListener(new ModelFrameworkPersistenceListener());
			parser.addtListener(new ViewBuildParsingListener());
			List<ModelFramework> list = parser.parse(file);
			System.out.println(list);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void test4(){
		File file = new File("D:/xmlbeanstest/generate xml/test5.xml");
		XMLParser parser = new XMLParser();
		ParsingProcessContext context = new ParsingProcessContext(parser);
		parser.setParsingContext(context);
		parser.addtListener(new ModelFrameworkPersistenceListener());
		parser.addtListener(new ViewBuildParsingListener());
		//context.setModelGroupName("testXMLFile");
		try {
			List<ModelFramework> list = parser.parse(file);
			System.out.println(list);
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		parser.finish();
	}
	
	
	public static void test5(){
		//String name = "test5.xml_bn_public_addnew";
		String group = "test5.xml";
		String name = "bn_public_addnew";
		ImageTextButton button = StructureMapping.getInstance().getImageTextButton(name+"_"+group);
		System.out.println(button.getName());
		StructureMapping.getInstance().removeGroup(group);
		button = StructureMapping.getInstance().getImageTextButton(name);
		System.out.println(button.getName());
	}
	
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, XmlException, IOException {
		test4();
	}
}
