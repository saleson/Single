package com.single.db.query;


/**
 * 表跟表之间的关联信息.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public class Join {
	private Table table;
	private Table joinTable;	//关联的表
	private JoinWay joinWay;	//关联方式
	private QueryCondition query;	//关联条件
	
	public Join(){}
	
	public Join(Table table, Table joinTable, JoinWay joinWay){
		this.table = table;
		this.joinTable = joinTable;
		this.joinWay = joinWay;
		this.query = new QueryCondition();
	}
	
	
	public Table getTable() {
		return table;
	}
	
	public void setTable(Table table) {
		this.table = table;
	}
	
	public Table getJoinTable() {
		return joinTable;
	}
	
	public void setJoinTable(Table joinTable) {
		this.joinTable = joinTable;
	}
	
	public JoinWay getJoinWay() {
		return joinWay;
	}
	
	public void setJoinWay(JoinWay joinWay) {
		this.joinWay = joinWay;
	}
	
	public QueryCondition getQuery() {
		return query;
	}
	
}