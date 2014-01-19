package com.single.app.component.metadate;

/**
 * 编辑字段
 * @author Saleson
 *
 */
public class EditField extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public EditField(String name, String caption) {
		super(name, caption);
	}
	
	protected boolean editable;		//是否编辑
	protected boolean coderule;	//是否是编号字段

	
	/**
	 * 是否编辑
	 * @return
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * 是否编辑
	 * @param editable
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	/**
	 * 字段是否为单据的编号字段,如果是,返回true;
	 * @return
	 */
	public boolean isCodeRule() {
		return coderule;
	}

	/**
	 * 设置字段为单据编号字段.
	 * @param coderule
	 */
	public void setCodeRule(boolean coderule) {
		this.coderule = coderule;
	}
	
	
	@Override
	protected EditField clone() {
		return (EditField) super.clone();
	}
}
