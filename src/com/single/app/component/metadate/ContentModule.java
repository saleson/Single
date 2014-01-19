package com.single.app.component.metadate;

/**
 * 记录大量文字内容的模型.比如css, javascript, sql
 * @author Saleson
 *
 */
public abstract class ContentModule extends AbstractModelFrameWork{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected StringBuffer content;
	protected String name;
	
	
	public ContentModule(String groupName, String name){
		super(groupName);
		this.name = name;
		content = new StringBuffer();
	}
	
	public String getContent(){
		return content.toString();
	}
	
	public void appendLine(String line){
		content.append(line).append("\n");
	}
	
	public void appendAll(String content){
		this.content.append(content);
	}
	
	public void clearContent(){
		content = new StringBuffer();
	}
	
	public String getName() { 
		return name;
	}
	
	@Override
	protected Object clone() {
		ContentModule c = null;
		try {
			c = (ContentModule) super.clone();
			c.content = new StringBuffer(this.content);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public boolean needAssembly() {
		return false;
	}
}
