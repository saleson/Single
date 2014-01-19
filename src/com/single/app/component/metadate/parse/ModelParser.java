package com.single.app.component.metadate.parse;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import com.single.app.component.metadate.ModelFramework;

/**
 * 模型解析器.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public interface ModelParser {
	
	/**
	 * 设置是否在解析过程中.
	 * 用于多线程控制.
	 * @param isParsing
	 */
	public void isParsing(boolean isParsing);
	
	/**
	 * 返回是否在解析过程中.
	 * 用于多线程控制.
	 * @return
	 */
	public boolean isParsing();
	
	/**
	 * 
	 * @param xmlFile
	 * @return
	 * @throws Exception
	 */
	public List<ModelFramework> parse(File xmlFile) throws Exception;
	
	/**
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public List<ModelFramework> parse(InputStream in) throws Exception;
	
	/**
	 * 
	 * @param reader
	 * @return
	 * @throws Exception
	 */
	public List<ModelFramework> parse(Reader reader) throws Exception;
	
	/**
	 * 设置解析的上下文内容,该对象可能对解析的过程进行控制.
	 * @param context
	 */
	public void setParsingContext(ParsingProcessContext context);
	
	/**
	 * 返回解析的上下文内容,该对象可能对解析的过程进行控制.
	 * @return
	 */
	public ParsingProcessContext getParsingContext();
	
	/**
	 * 结束
	 */
	public void finish();
}
