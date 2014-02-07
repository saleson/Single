package com.single.db.query;

/***********************************************************************
 * Module:  From.java
 * Author:  Administrator
 * Purpose: Defines the Class From
 ***********************************************************************/

import java.util.*;

import com.single.db.query.parse.FromSQLBuilder;
import com.single.db.query.parse.JoinContext;

public class From {
	private QueryTable masterTable;
	private Collection<QueryTable> tables;
	private Collection<Join> joins;
	private JoinContext joinContext;

	public From(QueryTable masterTable){
		this.masterTable = masterTable;
	}
	
	public QueryTable getMasterTable(){
		return masterTable;
	}
	
	public Collection<Join> getJoin() {
		if (joins == null)
			joins = new HashSet<Join>();
		return joins;
	}

	public Iterator<Join> getIteratorJoin() {
		if (joins == null)
			joins = new HashSet<Join>();
		return joins.iterator();
	}

	public void setJoins(Collection<Join> newJoins) {
		removeAllJoin();
		for (Iterator<Join> iter = newJoins.iterator(); iter.hasNext();)
			addJoin(iter.next());
	}

	public void addJoin(Join newJoin) {
		if (newJoin == null)
			return;
		if (this.joins == null)
			this.joins = new HashSet<Join>();
		if (!this.joins.contains(newJoin)){
			this.joins.add(newJoin);
			addTable(newJoin.getReferenceTable());
		}
	}

	public void removeJoin(Join oldJoin) {
		if (oldJoin == null)
			return;
		if (this.joins != null)
			if (this.joins.contains(oldJoin)){
				this.joins.remove(oldJoin);
				removeTable(oldJoin.getReferenceTable());
			}
	}

	public void removeAllJoin() {
		if (joins != null)
			joins.clear();
		removeAllTables();
	}
	
	public Collection<QueryTable> getQueryTables(){
		return tables;
	}
	
	public Iterator<QueryTable> getIteratorTable() {
		if (tables == null)
			tables = new HashSet<QueryTable>();
		return tables.iterator();
	}

	public void setTables(Collection<QueryTable> newJoins) {
		removeAllTables();
		for (Iterator<QueryTable> iter = newJoins.iterator(); iter.hasNext();)
			addTable(iter.next());
	}

	public void addTable(QueryTable newTable) {
		if (newTable == null)
			return;
		if (this.tables == null)
			this.tables = new HashSet<QueryTable>();
		if (!this.tables.contains(newTable))
			this.tables.add(newTable);
	}

	public void removeTable(QueryTable oldTable) {
		if (oldTable == null)
			return;
		if (this.tables != null)
			if (this.tables.contains(oldTable))
				this.tables.remove(oldTable);
	}

	public void removeAllTables() {
		if (tables != null)
			tables.clear();
	}
	
	public QueryTable findTable(String alias){
		if(alias.equalsIgnoreCase(masterTable.getAlias())){
			return masterTable;
		}else{
			Iterator<QueryTable> iter = getIteratorTable();
			while(iter.hasNext()){
				QueryTable table = iter.next();
				if(alias.equalsIgnoreCase(table.getAlias())){
					return table;
				}
			}
		}
		return null;
	}
	
	public List<QueryTable> findTables(String tableName){
		List<QueryTable> list = new ArrayList<QueryTable>();
		if(tableName.equalsIgnoreCase(masterTable.getTableName())){
			list.add(masterTable);
		}
		Iterator<QueryTable> iter = getIteratorTable();
		while(iter.hasNext()){
			QueryTable table = iter.next();
			if(tableName.equalsIgnoreCase(table.getTableName())){
				list.add(table);
			}
		}
		return list;
	}
	
	
	public Join findJoin(String alias, String referenceAlias){
		Iterator<Join> iter = getIteratorJoin();
		while(iter.hasNext()){
			Join join = iter.next();
			if(alias.equalsIgnoreCase(join.getTable().getAlias()) 
					&& referenceAlias.equalsIgnoreCase(join.getReferenceTable().getAlias())){
				return join;
			}
		}
		return null;
	}
	
	public List<Join> findJoins(String tableName, String referenceTableName){
		List<Join> list = new ArrayList<Join>();
		Iterator<Join> iter = getIteratorJoin();
		while(iter.hasNext()){
			Join join = iter.next();
			if(tableName.equalsIgnoreCase(join.getTable().getTableName()) 
					&& referenceTableName.equalsIgnoreCase(join.getReferenceTable().getTableName())){
				list.add(join);
			}
		}
		return list;
	}
	
	
	public JoinContext takeJoinContext(){
		if(joinContext==null){
			analaysisJoinContext();
		}
		return joinContext;
	}
	
	public void analaysisJoinContext(){
		joinContext = new JoinContext(masterTable);
		if(joins==null || joins.isEmpty())
			return;
		analaysisJoinContext(joinContext);
		
	}
	
	
	private void analaysisJoinContext(JoinContext context){
		Iterator<Join> joinIter = getIteratorJoin();
		QueryTable table = context.getTable();
		while(joinIter.hasNext()){
			Join join = joinIter.next();
			if(table==join.getTable()){
				JoinContext _context = new JoinContext(join.getReferenceTable());
				context.addJoin(join, _context);
				analaysisJoinContext(_context);
			}
		}
			
		
	}
	
	public void addSQL(StringBuilder buf){
		FromSQLBuilder builder = (FromSQLBuilder)DBDriver.getRegisteredDBDriver().findSQLBuilder(this.getClass().getName());
		buf.append(builder.toSQLString(this));
	}
	
}