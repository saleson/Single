package com.single.jfianl.plugin.proxool;

import javax.sql.DataSource;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import com.jfinal.plugin.IPlugin;
import com.jfinal.plugin.activerecord.IDataSourceProvider;

public class ProxoolPlugin implements IPlugin, IDataSourceProvider{

	private ProxoolDataSource dataSource;
	
	@Override
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(ProxoolDataSource dataSource){
		this.dataSource = dataSource;
	}

	@Override
	public boolean start() {
		return true;
	}

	@Override
	public boolean stop() {
		if (dataSource != null)
			dataSource = null;
		return true;
	}

}
