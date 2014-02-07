package com.single.db.query;


/**
 * 表跟表之间的关联信息.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public class Join {
	private QueryTable table;
	private QueryTable referenceTable;	//关联的表
	private JoinWay joinWay;	//关联方式
	private JoinOn on;	//关联条件
	
	
	public Join(QueryTable table, QueryTable referenceTable, JoinWay joinWay){
		this.table = table;
		this.referenceTable = referenceTable;
		this.joinWay = joinWay;
		this.on = new JoinOn();
	}
	
	
	public QueryTable getTable() {
		return table;
	}
	
	public void setTable(QueryTable table) {
		this.table = table;
	}
	
	public QueryTable getReferenceTable() {
		return referenceTable;
	}
	
	public void setReferenceTable(QueryTable referenceTable) {
		this.referenceTable = referenceTable;
	}
	
	public JoinWay getJoinWay() {
		return joinWay;
	}
	
	public void setJoinWay(JoinWay joinWay) {
		this.joinWay = joinWay;
	}
	
	public JoinOn getJoinOn(){
		return on;
	}
}