package com.single.app.component.metadate.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class ParsingProcessContext {
	
	private Logger log;
	private ModelParser parser;
	private boolean isFactoryGenerated = false;
	private String modelGroupName;
	private List<ParsingListener> listeners;
	private Map<String, Object> attributes;

	@SuppressWarnings("unused")
	private ParsingProcessContext(){
		this(null);
	}
	
	public ParsingProcessContext(ModelParser parser){
		this.parser = parser;
		this.listeners = new ArrayList<ParsingListener>();
		this.attributes = new HashMap<String, Object>();
	}
	
	/**
	 * 
	 * @param isFactoryGenerated
	 */
	protected void isFactoryGenerated(boolean isFactoryGenerated) {
		this.isFactoryGenerated = isFactoryGenerated;
	}
	
	/**
	 * 判断模型解析器是否由工厂<code>ParserFactory</code>生成.
	 * @return
	 */
	public boolean isFactoryGenerated(){
		return isFactoryGenerated;
	}
	
	public void setLogger(Logger logger){
		this.log = logger;
	}
	
	public Logger getLogger(){
		return log;
	}
	
	void setParser(ModelParser parser){
		this.parser = parser;
	}
	
	public ModelParser getParser() {
		return parser;
	}

	/**
	 * 返回正在解析的内容的名称.
	 * @return
	 */
	public String getModelGroupName() {
		return modelGroupName;
	}

	/**
	 * 设置即将解析的内容的名称,这将会用于显示或记录到缓存中.
	 * 如果即将解析的是文件,建议设置文件的名称;当模型修改后,可以直接修改对应的文件.
	 * @param parsingContentName
	 */
	public void setModelGroupName(String modelGroupName) {
		this.modelGroupName = modelGroupName;
	}

	public List<ParsingListener> getListeners() {
		return listeners;
	}

	public void setListeners(List<ParsingListener> listeners) {
		this.listeners = listeners;
		for(ParsingListener listener : listeners){
			listener.beforeParse(this);
		}
	}
	
	public void addtListener(ParsingListener listener){
		listeners.add(listener);
		listener.beforeParse(this);
	}
	
	public void addAttribute(String attributeName, Object attributeValue){
		attributes.put(attributeName, attributeValue);
	}
	
	public Object removeAttribute(String attributeName){
		return attributes.remove(attributeName);
	}
	
	public boolean containAttribute(String attributeName){
		return attributes.containsKey(attributeName);
	}
	
	public boolean containAttributeValue(Object attributeValue){
		return attributes.containsValue(attributeValue);
	}
}
