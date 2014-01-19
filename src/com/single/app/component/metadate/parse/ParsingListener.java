package com.single.app.component.metadate.parse;

import java.util.List;

import com.single.app.component.metadate.ModelFramework;

/**
 * 解析模型的监听器.
 * 在解析的过程中会调用该类型的beforeParse(),和afterParsed()方法.
 * beforeParse()方法是用来配置ParsingProcessContext对象中的一些属性,这些属性可以控制模型解析器.
 * afterParsed()方法是在解析好后调用,处理一些后续的操作.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public interface ParsingListener {
	/**
	 * 在模型解析器开始解析之前,被<code>ParsingProcessContext</code>添加进去时,调用该方法.
	 * @param context
	 */
	public void beforeParse(ParsingProcessContext context);
	
	/**
	 * 
	 * @param mfList
	 */
	public void invoke(List<ModelFramework> mfList);
	
	/**
	 * 
	 * @param model
	 */
	public void afterParsed();
	
	/**
	 * 设置是否过时,过时的监听器将不会被调用.
	 * @param deprecated
	 */
	public void setDeprecated(boolean deprecated);
	
	/**
	 * 返回该监听器是否过时,过时的监听器将不会被调用.
	 * @return
	 */
	public boolean isDeprecated();
}
