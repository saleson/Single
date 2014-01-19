package com.single.metadata.test;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.xml.namespace.QName;

import org.apache.xmlbeans.CDataBookmark;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlCursor.XmlBookmark;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.schema.SchemaTypeLoaderImpl;
import org.junit.Test;
import org.xml.sax.XMLReader;

import com.single.xmlbeans.configuration.ButtonMappingType;
import com.single.xmlbeans.configuration.DataType;
import com.single.xmlbeans.configuration.FieldType;
import com.single.xmlbeans.configuration.MetadataMappingType;
import com.single.xmlbeans.configuration.MetadataMappingType.Metadata;
import com.single.xmlbeans.configuration.ScriptMappingType;
import com.single.xmlbeans.configuration.ScriptType;
import com.single.xmlbeans.configuration.SingleConfigDocument;
import com.single.xmlbeans.configuration.SingleConfigDocument.SingleConfig;
import com.single.xmlbeans.configuration.WhereFieldType;

public class GenerateXMLTest {

	@Test
	public void generateXML() throws IOException{
		System.out.println("generateXML=================");
		SingleConfigDocument document = SingleConfigDocument.Factory.newInstance();
		SingleConfig config = document.addNewSingleConfig();
		ButtonMappingType butMap = config.addNewButtonMapping();
		com.single.xmlbeans.configuration.ImageTextButtonType but = butMap.addNewImageTextButton();
		but.setName("bn_public_addnew");
		//but.setInitialState(ImageTextButton.InitialState.ENABLE);
		but.setText("新增");
		but.setOnClick("function addNew()");
		but.setTooltip("新增单据");
		ScriptMappingType scriptMap = config.addNewScriptMapping();
		ScriptType script = scriptMap.addNewScript();
		script.setName("test1");
		script.setType("text/javascript");
		XmlCursor c = script.newCursor();
		c.setTextValue("function addNew(){#{\\n}\n\n\n\talert(\"addNew\");\n}");
		c.toFirstContentToken();
		c.setBookmark(CDataBookmark.CDATA_BOOKMARK);
		c.toNextToken();
		
		MetadataMappingType metaMap = config.addNewMetadataMapping();
		Metadata meta = metaMap.addNewMetadata();
		meta.setName("md_test_one_list");
		meta.setTitle("测试页面主表");
		FieldType field = null;
		for(int i=0;i<10;i++){
			field = meta.addNewField();
			field.setName("listTestField" + i);
			field.setCaption("列表测试字段" + i);
			field.setDatatype(DataType.NUMBER);
			field.setTipTitle("列表测试字段" + i + "tipTitle");
			field.setFormat("0.00");
		}
		
		File file = new File("D:/xmlbeanstest/generate xml/test1.xml");
		XmlOptions options = new XmlOptions();
		options.setCharacterEncoding("UTF-8");
		options.setSavePrettyPrint();
		options.setSavePrettyPrintIndent(4);
		options.setSaveCDataLengthThreshold( 1 );
        options.setUseCDataBookmarks();
        System.out.println(script.xmlText( options ) );
		document.save(file, options);
	}
	
	@Test
	public static void resolveXML(){
		System.out.println("resolveXML=================");
		try{
			File file = new File("D:/xmlbeanstest/generate xml/test2.xml");
			SingleConfigDocument document = SingleConfigDocument.Factory.parse(file);
			ScriptType script = document.getSingleConfig().getScriptMapping().getScriptArray(0);
			XmlCursor c = script.newCursor();
			c.setTextValue(c.getTextValue());
			c.toFirstContentToken();
			c.setBookmark(CDataBookmark.CDATA_BOOKMARK);
	        //c.toNextToken();
	        //XmlBookmark bm = c.getBookmark(CDataBookmark.CDATA_BOOKMARK.getKey());
	        System.out.println(c.getTextValue().trim());
	        c.toNextToken();
			System.out.println(script.getName());
			XmlOptions opts = new XmlOptions();
			opts.setCharacterEncoding("UTF-8");
			opts.setSavePrettyPrint();
			opts.setSavePrettyPrintIndent(2);
			opts.setSaveCDataLengthThreshold( 1 );
			opts.setUseCDataBookmarks();
			opts.setLoadStripWhitespace();
			//opts.setSaveSyntheticDocumentElement(new QName("script"));
			System.out.println(script.xmlText( opts ) );
			System.out.println(script.getName() );
			document.save(new File("D:/xmlbeanstest/generate xml/test3.xml"), opts);
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	@Test
	public void resolveXML2(){
		System.out.println("resolveXML2=================");
		try{
			File file = new File("D:/xmlbeanstest/generate xml/test4.xml");
			SingleConfigDocument document = SingleConfigDocument.Factory.parse(file);
			MetadataMappingType script = document.getSingleConfig().getMetadataMapping();
			Metadata[] metadatas = script.getMetadataArray();
			for(Metadata metadata : metadatas){
				WhereFieldType where = metadata.getWhereFieldArray(0);
				System.out.println(where.getOpersign().toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	
	public void test1() throws XmlException{
		XmlObject x = XmlObject.Factory.parse( "<BankRate>        </BankRate>" );
        XmlCursor c = x.newCursor();
        c.toFirstContentToken();
        c.setTextValue("fds");
        c.toNextToken();
        c.setBookmark(CDataBookmark.CDATA_BOOKMARK);
        c.getTextValue();
        XmlOptions options = new XmlOptions();
        options.setSaveCDataLengthThreshold( 1 );
        options.setUseCDataBookmarks();
        System.out.print( x.xmlText( options ) );
	}
	
	private static void test2(){
		try {
			String txt = "<script name=\"jkd\" type=\"text/javascript\"><![CDATA[ function d(){}]]></script>";
			XmlOptions opts = new XmlOptions();
			opts.setCharacterEncoding("UTF-8");
			opts.setSavePrettyPrint();
			opts.setSavePrettyPrintIndent(2);
			opts.setSaveCDataLengthThreshold( 1 );
			opts.setUseCDataBookmarks();
			opts.setSaveNamespacesFirst();
			opts.setLoadUseDefaultResolver();
			ScriptType script = ScriptType.Factory.parse(txt, opts);
			System.out.println(script.getName());
			System.out.println(script.getType());
			System.out.println(script.newCursor().getAttributeText(new QName("@type")));
		} catch (XmlException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws XmlException {
		//test2();
		//System.out.println(DataType.NUMBER.intValue());
		
		resolveXML();
	}
}
