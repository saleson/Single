package com.single.app.component.metadate;

/**
 * CSS的内容模型.
 * @author Saleson
 *
 */
public class CssModule extends ContentModule{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final String CSS_TYPE = "text/css";
	
	
	private String type;
	
	public CssModule(String groupName, String name) {
		super(groupName, name);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	
	@Override
	public CssModule clone() {
		return (CssModule) super.clone();
	}
}
