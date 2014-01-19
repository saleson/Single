package com.single.app.component.metadate.parse;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.log4j.Logger;

import com.single.app.component.metadate.Field;
import com.single.app.component.metadate.ModelFramework;
import com.single.commons.util.StringUtils;

public class ParserUtil {

	private static Logger logger = Logger.getLogger(ParserUtil.class.getName());
	
	/**
	 * 返回字段的默认数据类型
	 * @return
	 */
	public static String defaultFieldDatatype(){
		return Field.DATATYPE_NUMBER;
	}
	
	
	/**
	 * 在模型解析器开始解析之前,被<code>ParsingProcessContext</code>添加进去时,调用该解析器中的监听器的beforeParse()方法.
	 * @param parser 模型解析器
	 */
	public static void invokeParsingListenerBeforeParse(ModelParser parser){
		ParsingProcessContext context = parser.getParsingContext();
		if(context!=null){
			List<ParsingListener> listeners = context.getListeners();
			for(int i=0;i<listeners.size();i++){
				ParsingListener listener = listeners.get(i);
				if(!listener.isDeprecated()){
					listener.beforeParse(context);
				}
			}
		}
	}
	
	/**
	 * 在模型解析器开始解析好之后执行<code>#ModelParser.finish()</code>方法时调用该解析器中的监听器的afterParsed()方法.
	 * @param parser 模型解析器
	 */
	public static void invokeParsingListenerAfterParsed(ModelParser parser){
		ParsingProcessContext context = parser.getParsingContext();
		if(context==null)
			return;
		List<ParsingListener> listeners = context.getListeners();
		for(int i=0;i<listeners.size();i++){
			ParsingListener listener = listeners.get(i);
			if(!listener.isDeprecated()){
				listener.afterParsed();
			}
		}
	}
	
	/**
	 * 在模型解析器执行<code>#ModelParser.parse()</code>方法之后调用该解析器中的监听器的invoke()方法.
	 * @param parser 模型解析器
	 * @param models 解析好后的模型框架
	 */
	public static void invokeParsingListenerInvoke(ModelParser parser, List<ModelFramework> models){
		ParsingProcessContext context = parser.getParsingContext();
		if(context==null)
			return;
		List<ParsingListener> listeners = context.getListeners();
		for(int i=0;i<listeners.size();i++){
			ParsingListener listener = listeners.get(i);
			if(!listener.isDeprecated()){
				listener.invoke(models);
			}
		}
	}
	
	
	
	/**
	 * 
	 * @param field
	 * @param items
	 * @return
	 */
	public static List<Map<String, String>> resolveFieldItems(Field field, String items){
		if(StringUtils.isNotEmpty(items) && items.startsWith("[{") && items.endsWith("}]")){
			String[] _items = items.replaceAll("^\\[|\\]$", "").split("\\},\\{");
			String keyC = "key:", nameC = "name:";
			for(int i=0;i<_items.length;i++){
				in:{
					String _item = _items[i].replaceAll("^\\{|\\}$", "").trim();
					int keyIdx = _item.indexOf(keyC);
					if(keyIdx==-1){
						logger.error(field.getClass().getName() + "[" + field.getName() + "] it's not find '" + keyC + "' from it's attribute name 'items' as '" + _item + "'");
						continue;
					}
					int nameIdx = _item.indexOf(nameC, keyIdx + keyC.length());
					if(nameIdx==-1){
						logger.error(field.getClass().getName() + "[" + field.getName() + "] it's not find '" + nameC + "' from it's attribute name 'items' as '" + _item + "'");
						continue;
					}
					Map<String, String> item = new CaseInsensitiveMap<String, String>();
					String[] keyNames = _item.split(", ");
					for(String keyName : keyNames){
						keyIdx = keyName.indexOf(":");
						if(keyIdx==-1){
							logger.error(field.getClass().getName() + "[" + field.getName() + "] it's exists a not a keyValue '"+keyName+"' --> from it's attribute name 'items' as '" + _item + "'" );
							break in;
						}
						String key = keyName.substring(0, keyIdx);
						String value = keyName.substring(keyIdx + 1).replaceAll("^'|'$", "");
						item.put(key, value);
					}
					field.addItem(item);
				}
			}
		}
		return field.getItems();
	}
}
