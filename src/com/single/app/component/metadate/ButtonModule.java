package com.single.app.component.metadate;


public class ButtonModule extends AbstractModelFrameWork{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String INITIALSTATE_ENABLED = "enabled";
	
	public final static String INITIALSTATE_DISABLED = "disabled";
	
	
	protected String name;
	protected String text;
	protected String tooltip;
	protected String onClick;
	protected double width;
	protected double height;
	protected String initialState;
	protected String enableDetect;
	protected float index;		//顺序

	
	public ButtonModule(String groupName, String name){
		super(groupName);
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String text){
		this.text = text;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getInitialState() {
		return initialState;
	}

	public void setInitialState(String initialState) {
		this.initialState = initialState;
	}

	public String getEnableDetect() {
		return enableDetect;
	}

	public void setEnableDetect(String enableDetect) {
		this.enableDetect = enableDetect;
	}
	
	/**
	 * 显示顺序
	 * @return
	 */
	public float getIndex() {
		return index;
	}
	
	/**
	 * 显示顺序
	 * @param index
	 */
	public void setIndex(float index) {
		this.index = index;
	}
	
	@Override
	public boolean needAssembly() {
		return false;
	}
}
