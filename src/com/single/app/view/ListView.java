package com.single.app.view;



public class ListView extends BaseView {

	protected ListTable table;

	public ListView(String path, String pageCode) {
		super(path, pageCode);
	}
	
	public ListView(String path, String pageCode, String title){
		super(path, pageCode, title);
	}

	@Override
	public ViewTable[] getViewTables() {
		return new ViewTable[]{table};
	}

	
	public void setListTable(ListTable table){
		this.table = table;
	}

	public ListTable getListTable(){
		return table;
	}
}
