package com.single.db.query.parse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import com.single.db.query.Join;
import com.single.db.query.QueryTable;

public class JoinContext {

	private QueryTable table;
	private Map<Join, JoinContext> joinMap;
	
	public JoinContext(QueryTable table){
		this.table = table;
		this.joinMap = new HashMap<Join, JoinContext>();
	}
	
	public void addJoin(Join join, JoinContext context){
		joinMap.put(join, context);
	}
	
	public Iterator<Entry<Join, JoinContext>> getIteratorEntrys(){
		return joinMap.entrySet().iterator();
	}
	
	public void removeJoin(Join join){
		joinMap.remove(join);
	}
	
	public JoinContext getJoinContext(Join join){
		return joinMap.get(join);
	}
	
	
	public QueryTable getTable(){
		return table;
	}
}
