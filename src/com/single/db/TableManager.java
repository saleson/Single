package com.single.db;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Model;
import com.single.jfianl.dbmodle.BM;

public class TableManager {


	@SuppressWarnings("rawtypes")
	private static Map<String, TableInfo<? extends Model>> tableMapping = new HashMap<String, TableInfo<? extends Model>>();
	
	static{
		tableMapping.put("bm", new TableInfo<BM>("bm", "deptid", "s_bm", new BM()));
	}
	
	@SuppressWarnings("rawtypes")
	public static TableInfo<? extends Model> getTableInfo(String tableName){
		return tableMapping.get(tableName);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void configActiveRecordMapping(ActiveRecordPlugin arp){
		Iterator<TableInfo<? extends Model>> iter = tableMapping.values().iterator();
		while(iter.hasNext()){
			TableInfo<? extends Model> info = iter.next();
			arp.addMapping(info.getTableName(), info.getPrimaryKey(), 
					(Class<? extends Model<?>>) info.getModelClass().getClass());
		}
	}
}
