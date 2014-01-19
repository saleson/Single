package com.single.app.view.util.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.single.app.view.View;
import com.single.app.view.util.ViewUtil;

/**
 * 生成单据的页面.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public interface ViewUtilGenerateHandler <U extends ViewUtil<? extends View>>{

	public void generate(U viewUtil, Object data);
	
	public void generate(U viewUtil, HttpServletRequest request);
	
	@SuppressWarnings("rawtypes")
	public void generate(ViewUtil viewUtil, Map data);
	
}
