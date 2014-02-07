package com.single.db.query;

import java.util.HashMap;
import java.util.Map;

import com.single.app.service.UnregisteredException;
import com.single.db.query.exceptions.DBDriverFailRegisteredException;
import com.single.db.query.parse.SQLBuilder;
import com.single.db.structure.DBStructure;
import com.single.db.structure.DataType;


public abstract class DBDriver {
	
	
	private static DBDriver driver;
	
	protected DBStructure dbStructure;
	protected Map<String, SQLBuilder> sqlBuilderConctext;
	
	public static void registerDBDriver(DBDriver driver) throws DBDriverFailRegisteredException{
		driver.init();
		DBDriver.driver = driver;
	}
	
	/**
	 * 如果没有先注册,该方法将会抛出{@link UnregisteredException}异常.
	 * @return
	 */
	public static DBDriver getRegisteredDBDriver(){
		if(driver==null){
			throw new UnregisteredException("程序数据库驱动");
		}
		return driver;
	}
	
	public void setDBStructure(DBStructure dbStructure){
		this.dbStructure = dbStructure;
	}
	
	public DBStructure getDBStructure(){
		return dbStructure;
	}
	
	public abstract void init() throws DBDriverFailRegisteredException;
	
	public abstract String getValueString(Object value, DataType dataType);
	
	public SQLBuilder findSQLBuilder(String classPath){
		SQLBuilder builder = null;
		try{
			builder = sqlBuilderConctext.get(classPath);
		}catch (NullPointerException e) {}
		if(builder==null){
			throw new UnregisteredException("SQLBuilder[" + classPath + "]");
		}
		return builder;
	}
	
	public void registerSQLBuilder(String classPath, SQLBuilder builder){
		if(sqlBuilderConctext==null){
			sqlBuilderConctext = new HashMap<String, SQLBuilder>();
		}
		sqlBuilderConctext.put(classPath, builder);
	}
}
