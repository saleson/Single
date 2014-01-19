package com.single.app.component.metadate;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import org.apache.commons.collections4.ListUtils;

import com.single.commons.util.StringUtils;

/**
 * 
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public class MetadataModule extends AbstractModelFrameWork{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected String name;
	protected String title;
	protected Vector<Field> displayFields;
	protected Field primaryField;
	protected Vector<WhereField> whereFields;
	
	public MetadataModule(String groupName, String name){
		super(groupName);
		this.name = name;
		displayFields = new Vector<Field>();
		whereFields = new Vector<WhereField>();
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 返回只读的显示字段集合.
	 * @return
	 */
	public List<Field> getDisplayFields() {
		return ListUtils.unmodifiableList(displayFields);
	}
	
	/**
	 * 返回允许修改的显示字段集合.
	 * @return
	 */
	public List<Field> getAllowsMmodifiableDisplayFields() {
		return displayFields;
	}
	
	/**
	 * 设置显示字段集合.
	 * @param displayFields
	 */
	protected void setDisplayFields(List<Field> displayFields) {
		this.displayFields = new Vector<Field>(displayFields);
	}
	
	/**
	 * 添加显示字段.
	 * 如果字段名在显示字段集合中已存在,将不能添加进去,并返回false.
	 * @param field
	 * @return
	 */
	public boolean addDisplayField(Field field){
		if(field!=null && StringUtils.isNotEmpty(field.getName()) && !containsDisplayField(field)){
			displayFields.add(field);
		}
		return false;
	}
	
	/**
	 * 删除指定显示字段.
	 * @param field
	 * @return
	 */
	public boolean removeDisplayField(Field field){
		return removeField(displayFields, field);
	}
	
	/**
	 * 通过指定的字段名删除显示字段.
	 * @param fieldName
	 * @return
	 */
	public boolean removeDisplayField(String fieldName){
		return removeField(displayFields, fieldName);
	}
	
	/**
	 * 返回只读的查询字段集合.
	 * @return
	 */
	public List<WhereField> getWhereFields() {
		return ListUtils.unmodifiableList(whereFields);
	}
	
	/**
	 * 返回允许修改的显示字段集合.
	 * @return
	 */
	public List<WhereField> getAllowsMmodifiableWhereFields() {
		return whereFields;
	}
	
	/**
	 * 设置显示字段集合.
	 * @param whereFields
	 */
	protected void setWhereFields(List<WhereField> whereFields) {
		this.whereFields = new Vector<WhereField>(whereFields);
	}
	
	/**
	 * 添加查询字段.
	 * 如果字段名在查询字段集合中已存在,将不能添加进去,并返回false.
	 * @param field
	 * @return
	 */
	public boolean addWhereField(WhereField field){
		if(field!=null && StringUtils.isNotEmpty(field.getName()) && !containsWhereField(field)){
			whereFields.add(field);
		}
		return false;
	}
	
	/**
	 * 删除指定的查询字段.
	 * @param field
	 * @return
	 */
	public boolean removeWhereField(WhereField field){
		return removeField(whereFields, field);
	}
	
	/**
	 * 通过指定的字段名删除查询字段.
	 * @param fieldName
	 * @return
	 */
	public boolean removeWhereField(String fieldName){
		return removeField(whereFields, fieldName);
	}
	
	/**
	 * 
	 */
	public void sortDisplayFields(){
		bubbleSortField(displayFields);
	}
	
	/**
	 * 
	 */
	public void sortWhereFields(){
		bubbleSortField(whereFields);
	}
	
	/**
	 * 
	 * @param files
	 */
	public void bubbleSortField(List<? extends Field> fields){
		Collections.sort(fields, new Comparator<Field>() {
			public int compare(Field f1, Field f2) {
				float idx1 = f1.getIndex(), idx2 = f2.getIndex();
				return idx1 < idx2 ? 1 : idx1 > idx2 ? -1 : 0;
			}
		});
	}
	
	
	/**
	 * 是否已包含指定的字段.
	 * @param field
	 * @return
	 */
	public boolean containsDisplayField(Field field){
		return containsField(displayFields, field);
	}
	
	/**
	 * 是否已包含指定的字段名称.
	 * @param fieldName
	 * @return
	 */
	public boolean containsDisplayField(String fieldName){
		return containsField(displayFields, fieldName);
	}
	
	/**
	 * 是否已包含指定的查询字段.
	 * @param field
	 * @return
	 */
	public boolean containsWhereField(WhereField field){
		return containsField(whereFields, field);
	}
	
	/**
	 * 是否已包含指定的查询字段名称.
	 * @param fieldName
	 * @return
	 */
	public boolean containsWhereField(String fieldName){
		return containsField(whereFields, fieldName);
	}
	
	/**
	 * 在给定的字段集合中,删除指定的字段名称,如果删除掉,返回true.
	 * @param fields
	 * @param fieldName
	 * @return
	 */
	private <T extends Field> boolean removeField(List<T> fields, String fieldName){
		int index = indexOfField(fields, fieldName);
		if(index != -1){
			return fields.remove(index) != null;
		}
		return false;
	}
	
	/**
	 * 在给定的字段集合中,删除指定的字段,如果删除掉,返回true.
	 * @param fields
	 * @param field
	 * @return
	 */
	private <T extends Field> boolean removeField(List<T> fields, T field){
		int index = indexOfField(fields, field);
		if(index != -1){
			return fields.remove(index) != null;
		}
		return false;
	}
	
	/**
	 * 在给定的字段集合中,是否已包含指定的字段.
	 * @param fields
	 * @param field
	 * @return
	 */
	protected <T extends Field> boolean containsField(List<T> fields, T field){
		return indexOfField(fields, field) != -1;
	}
	
	
	/**
	 * 在给定的字段集合中,是否已包含指定的字段名称.
	 * @param fields
	 * @param fieldName
	 * @return
	 */
	protected <T extends Field> boolean containsField(List<T> fields, String fieldName){
		return indexOfField(fields, fieldName) != -1;
	}
	
	/**
	 * 在给定的字段集合中,是否已包含指定的字段,如果有,返回该字段在集合中的下标.
	 * @param fields
	 * @param field
	 * @return
	 */
	protected <T extends Field> int indexOfField(List<T> fields, T field){
		if(field!=null){
			for(int i=0;i<fields.size();i++){
				Field _field = fields.get(i);
				if(_field==field){
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * 在给定的字段集合中,是否已包含指定的字段名称.如果有,返回该字段名称在集合中的下标.
	 * @param fields
	 * @param field
	 * @return
	 */
	protected <T extends Field> int indexOfField(List<T> fields, String fieldName){
		if(StringUtils.isNotEmpty(fieldName)){
			for(int i=0;i<fields.size();i++){
				Field field = fields.get(i);
				if(fieldName.equals(field.getName())){
					return i;
				}
			}
		}
		return -1;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected MetadataModule clone() {
		MetadataModule metadata = null;
		try {
			metadata = (MetadataModule) super.clone();
			metadata.displayFields = (Vector<Field>) this.displayFields.clone();
			metadata.whereFields = (Vector<WhereField>) this.whereFields.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return metadata;
	}


	/**
	 * 设置主键
	 * @param field
	 */
	public void setPrimaryField(Field field){
		if(containsField(displayFields, field)){
			if(primaryField!=null){
				primaryField.setPrimary(false);
			}
			primaryField = field;
		}
	}
	
	/**
	 * 返回主键
	 * @return
	 */
	public Field getPrimaryField(){
		return primaryField;
	}

	@Override
	public boolean needAssembly() {
		return true;
	}
}
