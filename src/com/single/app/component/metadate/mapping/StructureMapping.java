package com.single.app.component.metadate.mapping;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.log4j.Logger;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.single.app.component.metadate.CssModule;
import com.single.app.component.metadate.ImageTextButton;
import com.single.app.component.metadate.MetadataModule;
import com.single.app.component.metadate.ModelFramework;
import com.single.app.component.metadate.ScriptModule;
import com.single.app.component.metadate.ViewModule;
import com.single.cache.BaseDiskCache;


public class StructureMapping {

	/**
	 * 分隔符.
	 */
	public static final String DELIMITER = "_";
	
	/**
	 * 分组名的前缀,或文件名的前缀.
	 */
	public static final String GRUOPNAME_KEY = "groupNames" + DELIMITER;
	
	/**
	 * ImageTextButton模型的前缀.
	 */
	public static final String IMAGETEXTBUTTON_PREFIX = "imagetextbutton";
	
	/**
	 * CssModule模型的前缀.
	 */
	public static final String CSSMODULE_PREFIX = "css";
	
	/**
	 * ScriptModule模型的前缀.
	 */
	public static final String SCRIPTMODULE_PREFIX = "script";
	
	/**
	 * MetadataModule模型的前缀.
	 */
	public static final String METADATAMODULE_PREFIX = "metamodule";
	
	/**
	 * ViewModule模型的前缀.
	 */
	public static final String VIEWMODULE_PREFIX = "viewmodule";
	
	/*ModelFramework : {
		ButtonModule,ImageTextButton,ContentModule,CssModule,ScriptModule,MetadataModule,ViewModule
	  }*/
	
	static Logger logger = Logger.getLogger(StructureMapping.class.getName());
	private static Object lock = new Object();
	private static StructureMapping mapping;
	private BaseDiskCache cache;
	
	
	private StructureMapping(){
		cache = new BaseDiskCache("structure", "/metadataStructure");
	}
	
	public static StructureMapping getInstance(){
		if (mapping == null) {
			synchronized (lock) {
				if (mapping == null) {
					mapping = new StructureMapping();
				}
			}
		}
		return mapping;
	}
	
	/**
	 * 缓存一组基础模型.如果基础模型的groupname不为空,会一起更新其它groupList的内容.
	 * @param mfList
	 */
	public void put(List<ModelFramework> mfList){
		for(int i=0;i<mfList.size();i++){
			ModelFramework mf = mfList.get(i);
			@SuppressWarnings("rawtypes")
			Class clazz = mf.getClass();
			String methodName = "put" + clazz.getSimpleName();
			//Method method = MethodUtils.getAccessibleMethod(this.getClass(), methodName, new Class[]{clazz});
			try {
				MethodUtils.invokeMethod(this, methodName, mf);
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error("指定缓存方法['" + methodName + "']执行失败,调用默认缓存方法['put(ModelFramework mf)']!");
				put(mf);
			}
			//method.invoke(this, new Object[]{mf});
		}
	}
	
	/**
	 * 缓存一个基础模型.
	 * @param mf
	 */
	public void put(ModelFramework mf){
		putModelFramework(mf);
	}
	
	/**
	 * 缓存一个基础模型.
	 * @param mf
	 */
	public void putModelFramework(ModelFramework mf){
		String cacheName = mf.needAssembly() ? mf.getGroupName() + DELIMITER + mf.getName() : mf.getName();
		putModelFramework(cacheName, mf);
	}
	
	public void putModelFramework(String cacheName, ModelFramework mf){
		String[] groups = {mf.getName(), mf.getGroupName()};
		putModelFramework(cacheName, mf, groups);
	}
	
	public void putModelFramework(String cacheName, ModelFramework mf, String[] groups){
		cache.put(cacheName, mf, groups);
	}
	
	/**
	 * 从缓存中返回指定的基础模型.
	 * @param name 模型的名称
	 * @param groupName 文件的名称或其它分组名.
	 * @return
	 * @throws NeedsRefreshException
	 */
	public ModelFramework getModelFramework(String name, String groupName) throws NeedsRefreshException{
		String cacheName = groupName + DELIMITER + name;
		return (ModelFramework) cache.get(cacheName);
	}
	
	public <T> T getModelFramework(String name, String groupName, Class<T> clazz) throws NeedsRefreshException{
		return cache.get(groupName + DELIMITER + name, clazz);
	}
	
	public <T> T getModelFramework(String cacheName, Class<T> clazz) throws NeedsRefreshException{
		return cache.get(cacheName, clazz);
	}
	
	@SuppressWarnings("rawtypes")
	public Set getModelFrameworkGroup(String... groups){
		return cache.getEnryByGroups(groups);
	}
	
	/**
	 * 删除被缓存的基础模型
	 * @param key
	 */
	public void remove(String name) {
		cache.remove(name);
	}

	/**
	 * 删除指定时间前的所有被缓存的基础模型
	 * @param date
	 */
	public void removeAll(Date date) {
		cache.removeAll(date);
	}
	
	/**
	 * 删除指定时间前的所有被缓存的基础模型
	 * @param date
	 */
	public void removeGroup(String group) {
		cache.removeGroup(group);
	}

	/**
	 * 删除所有被缓存的基础模型
	 */
	public void removeAll() {
		cache.removeAll();
	}
	
	/**
	 * 返回一组驻留在一个特定组的基础模型.
	 * @param groups
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<ModelFramework> getByGroups(String... groups){
		return cache.getEnryByGroups(groups);
	}
	
	public void putImageTextButton(ImageTextButton button){
		//putScriptModule(button.getName() + DELIMITER + button.getGroupName(), button);
		putScriptModule(button.getName(), button);
	}
	
	public void putScriptModule(String buttonName, ImageTextButton button){
		putModelFramework(IMAGETEXTBUTTON_PREFIX + DELIMITER + buttonName, button);
	}
	
	public ImageTextButton getImageTextButton(String buttonName, String group){
		return getImageTextButton(buttonName + DELIMITER + group);
	}
	
	public ImageTextButton getImageTextButton(String buttonName){
		try {
			return getModelFramework(IMAGETEXTBUTTON_PREFIX + DELIMITER + buttonName, ImageTextButton.class);
		} catch (NeedsRefreshException e) {
			logger.error("getImageTextButton('" + buttonName + "')", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Set<ImageTextButton> getImageTextButtonGroup(String... group){
		return getModelFrameworkGroup(group);
	}
	
	public void putScriptModule(ScriptModule script){
		putScriptModule(script.getName() + DELIMITER + script.getGroupName(), script);
	}
	
	public void putScriptModule(String scriptName, ScriptModule script){
		putModelFramework(SCRIPTMODULE_PREFIX + DELIMITER + scriptName, script);
	}
	
	public ScriptModule getScriptModule(String scriptName, String group){
		return getScriptModule(scriptName + DELIMITER + group);
	}
	
	public ScriptModule getScriptModule(String scriptName){
		try {
			return getModelFramework(SCRIPTMODULE_PREFIX + DELIMITER + scriptName, ScriptModule.class);
		} catch (NeedsRefreshException e) {
			logger.error("getScriptModule('" + scriptName + "')", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Set<ScriptModule> getScriptModuleGroup(String... group){
		return getModelFrameworkGroup(group);
	}
	
	
	public void putCssModule(CssModule css){
		putCssModule(css.getName() + DELIMITER + css.getGroupName(), css);
	}
	
	public void putCssModule(String cssName, CssModule css){
		putModelFramework(CSSMODULE_PREFIX + DELIMITER + cssName, css);
	}
	
	public CssModule getCssModule(String cssName, String group){
		return getCssModule(cssName + DELIMITER + group);
	}
	
	public CssModule getCssModule(String cssName){
		try {
			return getModelFramework(CSSMODULE_PREFIX + DELIMITER + cssName, CssModule.class);
		} catch (NeedsRefreshException e) {
			logger.error("getCssModule('" + cssName + "')", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Set<CssModule> getCssModuleGroup(String... group){
		return getModelFrameworkGroup(group);
	}
	
	
	public void putViewModule(ViewModule view){
		putViewModule(view.getName() + DELIMITER + view.getGroupName(), view);
	}
	
	public void putViewModule(String viewName, ViewModule view){
		putModelFramework(VIEWMODULE_PREFIX + DELIMITER + viewName, view);
	}
	
	public void putViewModule(String viewName, ViewModule view, String[] groups){
		putModelFramework(VIEWMODULE_PREFIX + DELIMITER + viewName, view, groups);
	}
	
	
	public ViewModule getViewModule(String viewName, String group){
		return getViewModule(viewName + DELIMITER + group);
	}
	
	public ViewModule getViewModule(String viewName){
		try {
			return getModelFramework(VIEWMODULE_PREFIX + DELIMITER + viewName, ViewModule.class);
		} catch (NeedsRefreshException e) {
			logger.error("getViewModule('" + viewName + "')", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Set<ViewModule> getViewModuleGroup(String... group){
		return getModelFrameworkGroup(group);
	}
	
	public void putMetadataModule(MetadataModule metadata){
		putModelFramework(metadata.getName() + DELIMITER + metadata.getGroupName(), metadata);
	}
	
	public void putMetadataModule(String metadataName, MetadataModule metadata){
		putModelFramework(METADATAMODULE_PREFIX + DELIMITER + metadataName, metadata);
	}
	
	public void putMetadataModule(String metadataName, MetadataModule metadata, String[] groups){
		putModelFramework(METADATAMODULE_PREFIX + DELIMITER + metadataName, metadata, groups);
	}
	
	public MetadataModule getMetadataModule(String metadataName, String group){
		return getMetadataModule(metadataName + DELIMITER + group);
	}
	
	public MetadataModule getMetadataModule(String metadataName){
		try {
			return getModelFramework(METADATAMODULE_PREFIX + DELIMITER + metadataName, MetadataModule.class);
		} catch (NeedsRefreshException e) {
			logger.error("getMetadataModule('" + metadataName + "')", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Set<MetadataModule> getMetadataModuleGroup(String... group){
		return getModelFrameworkGroup(group);
	}
	
}
