package com.single.config;

import org.logicalcobwebs.proxool.ProxoolDataSource;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.spring.SpringPlugin;
import com.jfinal.render.ViewType;
import com.single.context.WebContext;
import com.single.db.TableManager;
import com.single.jfianl.controller.LoginController;
import com.single.jfianl.controller.RootController;
import com.single.jfianl.plugin.proxool.ProxoolPlugin;

public class SingleJFinalConfig extends JFinalConfig {


	
	
	@Override
	public void afterJFinalStart() {
		super.afterJFinalStart();
	}

	@Override
	public void beforeJFinalStop() {
		super.beforeJFinalStop();
	}

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setViewType(ViewType.FREE_MARKER); 
	}

	@Override
	public void configHandler(Handlers me) {
	}

	@Override
	public void configInterceptor(Interceptors me) {
	}

	@Override
	public void configPlugin(Plugins me) {
		ProxoolPlugin proxoolPlugin = new ProxoolPlugin();
		me.add(proxoolPlugin);
		proxoolPlugin.setDataSource((ProxoolDataSource) WebContext.getBean("dataSource"));
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(proxoolPlugin);
		me.add(arp);
		arp.setDialect(new OracleDialect());
		arp.setContainerFactory(new CaseInsensitiveContainerFactory());
		TableManager.configActiveRecordMapping(arp);
		
		me.add(new SpringPlugin(WebContext.getApplicationContext()));
	}
	

	@Override
	public void configRoute(Routes me) {
		me.add("/", RootController.class);
		me.add("/login", LoginController.class);
	}

}
