package com.single.app.component.metadate;

/**
 * 查询字段
 * @author Saleson
 *
 */
public class WhereField extends Field{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 查询符:全like查询符号(like '%sk%') */
	public static final String QUERYOPERSIGN_LIKE = "like";
	/** 查询符:左like查询符号(like 'sk%') */
	public static final String QUERYOPERSIGN_LEFTLIKE = "left like";
	/** 查询符:右like查询符号(like '%sk') */
	public static final String QUERYOPERSIGN_RIGTHLIKE = "right like";
	/** 查询符:等于查询符号 */
	public static final String QUERYOPERSIGN_EQUAL = "=";
	/** 查询符:小于查询符号 */
	public static final String QUERYOPERSIGN_LESSTHAN = "<";
	/** 查询符:大于查询符号 */
	public static final String QUERYOPERSIGN_GREATERTHAN = ">";
	/** 查询符:小于并等于查询符号 */
	public static final String QUERYOPERSIGN_LESSTHANEQUAL = "<=";
	/** 查询符:大于并等于查询符号 */
	public static final String QUERYOPERSIGN_GREATERTHANEQUAL = ">=";
	/** 查询符:IN查询符号(in ('1','2') */
	public static final String QUERYOPERSIGN_IN = "in";
	/** 查询符:NOT IN 查询符号(not in ('1','2')) */
	public static final String QUERYOPERSIGN_NOTIN = "not in";
	/** 查询符:在 ... 之间 的 查询符号,类似于 */
	public static final String QUERYOPERSIGN_BETWEEN = "between";
	
	
	
	protected String field;				//查询的字段名称,name属性将会用于页面接收数据.
	protected String opersign;			//查询符
	
	
	
	public WhereField(String name, String caption) {
		super(name, caption);
	}
	
	/**
	 * 查询的字段名称,name属性将会用于页面接收数据.
	 * @return
	 */
	public String getField() {
		return field;
	}
	
	/**
	 * 查询的字段名称,name属性将会用于页面接收数据.
	 * @param field
	 */
	public void setField(String field) {
		this.field = field;
	}
	
	
	public String getOpersign() {
		return opersign;
	}
	
	
	public void setOpersign(String opersign) {
		this.opersign = opersign;
	}		

	@Override
	protected WhereField clone() {
		return (WhereField) super.clone();
	}
	
}
