package com.single.app.component.metadate;

public class DisplayField extends Field{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DisplayField(String name, String caption) {
		super(name, caption);
	}
	
	@Override
	protected DisplayField clone() {
		// TODO Auto-generated method stub
		return (DisplayField) super.clone();
	}
}
