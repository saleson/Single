package com.single.app.view.util;

import java.util.List;
import java.util.Map;

import com.single.app.view.View;

/**
 * 页面操作工具类.
 * 可以通过{@link com.single.app.view.util.handler.ViewUtilFactory}工厂类的getViewUtil方法获取去像.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public interface ViewUtil<V extends View> {

	/**
	 * 返回{@link com.single.app.view.View}对象
	 * @return
	 */
	public V getView();
	
	/**
	 * 赋于{@link com.single.app.view.View}对象
	 * @param view
	 */
	public void setView(V view);
	
	/**
	 * 返回页面视图中的表的操作工具类的数组.
	 * @return
	 */
	public <T extends ViewTableUtil> T[] geViewTableUtils();
	
	/**
	 * 返回指定名称的页面视图中的表的操作工具类.
	 * @param tableName
	 * @return
	 */
	public <T extends ViewTableUtil> T getViewTableUtil(String tableName);
	
	/**
	 * 返回查询条件
	 * @return
	 */
	public Map<String, String[]> getParameterMap();
	
	/**
	 * 设置查询条件
	 * @param paramMap
	 */
	public void setParameterMap(Map<String, String[]> paramMap);
	
	/**
	 * 返回从表/列表数据集,Map<String,String>
	 * @return 从表/列表数据集
	 */
	@SuppressWarnings("rawtypes")
	public List getRecordList();

	/**
	 * 设置从表/列表数据集为recordList
	 * @param recordList 表数据集List<Map<String,String>>
	 */
	@SuppressWarnings("rawtypes")
	public void setRecordList(List recordList);

	/**
	 * 返回主表数据Map<String,String>
	 * @return 主表数据Map<String,String>
	 */
	public <T> T getEntity();

	/**
	 * 设置回主表数据为entity
	 * @param entity 主表数据Map<String,String>
	 */
	public void setEntity(Object entity);
	
	/**
	 * 刷新数据的方法
	 */
	public void refresh();
	
	/**
	 * 新增一个单据.
	 * 该方法会调用{@link com.single.app.view.util.handler.ViewUtilAddHandler}的putInitialData方法赋于初始数据.
	 */
	public void addNew();
	
	/**
	 * 提交数据的方法
	 * 该方法会调用{@link com.single.app.view.util.handler.ViewUtilPostHandler}的post方法提交数据.
	 * @param data
	 */
	public void post(Object data);
	
	/**
	 * 
	 * @param data
	 */
	public void postSave(Object data);
	
	/**
	 * 该方法会将数据持久化.
	 * 具体操作由{@link com.single.app.view.util.handler.ViewUtilSaveHandler}实现.
	 */
	public void save();
	
	/**
	 * 删除数据.如果还有关联的数据未被删除,会抛出{@link AssociatedNotDeleteException}异常.
	 */
	public void delete() throws AssociatedNotDeleteException;
	
	/**
	 * 返回是否可以清除ViewUtil中的数据,默认true.
	 * @return
	 */
	public boolean wouldClear();
	
	/**
	 * 设置是否可以清除ViewUtil中的数据,默认true
	 * @param would
	 * @return
	 */
	public boolean wouldClear(boolean would);
	
	/**
	 * 清除ViewUtil中的数据.
	 */
	public void clear();
	
	/**
	 * 返回是否可以将这个ViewUtil从内存中销毁,默认true.
	 * @return
	 */
	public boolean wouldDestroy();
	
	/**
	 * 设置是否可以将这个ViewUtil从内存中销毁,默认true
	 * @param would
	 * @return
	 */
	public boolean wouldDestroy(boolean would);
	
	/**
	 * 将这个ViewUtil从内存中销毁.
	 */
	public void destroyed();
}
