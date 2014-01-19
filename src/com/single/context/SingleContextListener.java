package com.single.context;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SingleContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		URL propertiesURL = getClass().getResource("/single.properties");
		if(propertiesURL!=null){
			try {
				WebContext.loadProperties(propertiesURL.openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		WebApplicationContext webAppCtx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		ServletContext context = event.getServletContext();
		WebContext.setApplicationContext(webAppCtx);
		WebContext.setServletContext(context);
	}
	
	
	
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}


}
