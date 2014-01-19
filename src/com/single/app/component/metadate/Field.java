package com.single.app.component.metadate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.single.commons.util.StringUtils;

/**
 * 字段的基础属性.
 * @author Saleson
 *
 */
public abstract class Field implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 字段数据类型:字符 */
//	public static final String DATATYPE_VARCHAR = "varchar";
	/** 字段数据类型:数字 */
	public static final String DATATYPE_NUMBER = "number";
	/** 字段数据类型:日期 */
	public static final String DATATYPE_DATE = "date";
	/** 字段数据类型:大数据 */
	public static final String DATATYPE_MEMO = "memo";
	/** 字段数据类型:大数据 */
	public static final String DATATYPE_BLOB = "blob";
	/** 字段数据类型:大数据 */
	public static final String DATATYPE_CLOB = "clob";
	
	/** 字段输入/显示类型:文本框*/
	public static final String INPUTTYPE_TEXT = "text";
	/** 字段输入/显示类型:单选框 */
	public static final String INPUTTYPE_RADIO = "radio";
	/** 字段输入/显示类型:文复选框*/
	public static final String INPUTTYPE_CHECKBOX = "checkbox";
	/** 字段输入/显示类型:隐藏域 */
	public static final String INPUTTYPE_HIDDEN = "hidden";
	/** 字段输入/显示类型:单选下拉框 */
	public static final String INPUTTYPE_SELECT = "select";
	/** 字段输入/显示类型:多选下拉框 */
	public static final String INPUTTYPE_MULTISELECT = "multiselect";
	/** 字段输入/显示类型:可编辑的单选下拉框 */
	public static final String INPUTTYPE_COMBOX = "combox";
	/** 字段输入/显示类型:通过LOOKUP可传递参数到数据库中查询的输入框 */
	public static final String INPUTTYPE_LOOKUPBOX = "lookupbox";
	/** 字段输入/显示类型:密码框 */
	public static final String INPUTTYPE_PASSWORD = "password";
	/** 字段输入/显示类型:输入日期的 */
	public static final String INPUTTYPE_DATE = "date";
	/** 字段输入/显示类型:输入时间的 */
	public static final String INPUTTYPE_TIME = "time";
	/** 字段输入/显示类型:输入日期及时间的 */
	public static final String INPUTTYPE_DATETIME = "datetime";
	/** 字段输入/显示类型:图片 */
	public static final String INPUTTYPE_IMAGE = "image";
	/** 字段输入/显示类型:文本域 */
	public static final String INPUTTYPE_TEXTAREA = "textarea";
	
	
	protected String name;		//字段名称
	protected String caption;	//字段显示标题
	protected String tipTitle;	//鼠标悬停在标题处是显示的提示
	protected String datatype;	//数据类型,可参考以Field.DATATYPE*开头的常量.
	protected String format;	//字段的格式化
	protected String type;		//字段的类型 ,可参考以Field.INPUTTYPE*开头的常量.
	protected ArrayList<Map<String, String>> items = new ArrayList<Map<String,String>>();	//当字段的值写items[i][key]相同时,字段显示的在页面上的内容就是items[i][name]
	protected boolean display;	//是否显示
	protected float index;		//顺序
	protected boolean primary;	//是否为主键
	
	
	public Field(String name, String caption){
		this.name = name;
		this.caption = caption;
	}
	
	/**
	 * 字段名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 字段名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 字段显示标题
	 * @return
	 */
	public String getCaption() {
		return caption;
	}
	
	/**
	 * 字段显示标题
	 * @param caption
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	/**
	 * 鼠标悬停在标题处是显示的提示
	 * @return
	 */
	public String getTipTitle() {
		return tipTitle;
	}
	
	/**
	 * 鼠标悬停在标题处是显示的提示
	 * @param tipTitle
	 */
	public void setTipTitle(String tipTitle) {
		this.tipTitle = tipTitle;
	}
	
	/**
	 * 数据类型,可参考以Field.DATATYPE*开头的常量.
	 * @return
	 */
	public String getDatatype() {
		return datatype;
	}
	
	/**
	 * 数据类型,可参考以Field.DATATYPE*开头的常量.
	 * @param datatype
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	
	/**
	 * 字段的格式化
	 * @return
	 */
	public String getFormat() {
		return format;
	}
	
	/**
	 * 字段的格式化
	 * @param format
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
	/**
	 * 字段的类型 ,可参考以Field.INPUTTYPE*开头的常量.
	 * @return
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 字段的类型 ,可参考以Field.INPUTTYPE*开头的常量.
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 当字段的值写items[i][0]相同时,字段显示的在页面上的内容就是items[i][1]
	 * @return
	 */
	public List<Map<String, String>> getItems() {
		return items;
	}
	
	/**
	 * 当字段的值写items[i][0]相同时,字段显示的在页面上的内容就是items[i][1]
	 * @param items
	 */
	protected void setItems(ArrayList<Map<String, String>> items) {
		this.items = items;
	}
	
	/**
	 * 添加一个item
	 * @param item
	 */
	public void addItem(Map<String, String> item){
		items.add(item);
	}
	
	/**
	 * 将item.key等于指定的值(key)的item删除掉.
	 * @param key
	 * @return
	 */
	public Map<String, String> removeItem(String key){
		if(StringUtils.isNotEmpty(key)){
			for(Iterator<Map<String, String>> iter = items.iterator();iter.hasNext();){
				Map<String, String> item = iter.next();
				if(key.equals(item.get("key"))){
					iter.remove();
					return item;
				}
			}
		}
		return null;
	}
	
	/**
	 * 是否显示
	 * @return
	 */
	public boolean isDisplay() {
		return display;
	}
	
	/**
	 * 是否显示
	 * @param display
	 */
	public void setDisplay(boolean display) {
		this.display = display;
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
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected Field clone() {
		Field field = null;
		try {
			field = (Field) super.clone();
			field.items =  (ArrayList<Map<String, String>>) items.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return field;
		
	}

	/**
	 * 字段是否为主键,如果是,返回true;
	 * @return
	 */
	public boolean isPrimary() {
		return primary;
	}

	/**
	 * 设置字段是否为主键.
	 * @param primary
	 */
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	
	
}
