package com.single.app.view.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.single.app.component.metadate.EditField;
import com.single.app.component.metadate.Field;
import com.single.app.component.metadate.ViewModule;
import com.single.app.component.metadate.ViewStructure;
import com.single.app.service.UnregisteredException;
import com.single.app.view.View;

public class ViewBuildHandler {

	
	static Logger logger = Logger.getLogger(ViewBuildHandler.class.getName());
	private static Map<String, ViewBuilder<? extends View>> builderManager = new HashMap<String, ViewBuilder<? extends View>>();
	
	static{
		builderManager.put(ViewStructure.List.name(), new ListViewBuilder());
		builderManager.put(ViewStructure.MasterSlave.name(), new MasterSlaveViewBuilder());
	}
	
	public static ViewBuilder<? extends View> getViewBuilder(String key){
		return builderManager.get(key);
	}
	
	public static void registerViewBuilder(String key, ViewBuilder<? extends View> builder){
		builderManager.put(key, builder);
	}
	
	public static void buildView(ViewModule viewModule){
		if(viewModule==null){
			return;
		}
		ViewBuilder<? extends View> builder = getViewBuilder(viewModule.getViewStructure().name());
		if(builder==null){
			UnregisteredException exp = new UnregisteredException(viewModule.getViewStructure().name() + "类型的视图构建器");
			logger.error(exp.getMessage());
			throw exp;
		}
		View view = builder.build(viewModule);
		if(view!=null){
			ViewManager.getManager().putView(view);
		}
	}
	
	
	
	public static void putAttributeToView(View view, ViewModule viewModule){
		Iterator<Map.Entry<String, String>> iter = viewModule.getAttributes().entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<String, String> entry = iter.next();
			view.setAttribute(entry.getKey(), entry.getValue());
		}
	}
	
	
	/**
	 * 查找单据的编号字段.
	 * @param fields
	 * @return
	 */
	public static Field findCoderuleField(Collection<Field> fields){
		if(fields!=null){
			Iterator<Field> iter = fields.iterator();
			while(iter.hasNext()){
				Field field = iter.next();
				if(field instanceof EditField && ((EditField)field).isCodeRule()){
					return field;
				}
			}
		}
		return null;
	}
}
