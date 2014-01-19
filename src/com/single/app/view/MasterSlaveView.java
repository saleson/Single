package com.single.app.view;



public class MasterSlaveView extends BaseView{

	protected MasterTable masterTable;
	protected ListTable slaveTable;
	
	
	public MasterSlaveView(String path, String pageCode) {
		super(path, pageCode);
	}

	public MasterSlaveView(String path, String pageCode, String title){
		super(path, pageCode, title);
	}

	@Override
	public ViewTable[] getViewTables() {
		return new ViewTable[]{masterTable, slaveTable};
	}

	
	public void setMasterTable(MasterTable table){
		this.masterTable = table;
	}

	public MasterTable getMasterTable() {
		return masterTable;
	}
	
	public ListTable getSlaveTable() {
		return slaveTable;
	}

	public void setSlaveTable(ListTable slaveTable) {
		this.slaveTable = slaveTable;
	}

}
