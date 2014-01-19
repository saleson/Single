package com.single.app.view.util.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.single.app.view.View;
import com.single.app.view.util.ViewUtil;

public interface ViewUtilPostHandler<U extends ViewUtil<? extends View>> {

	public void post(U viewUtil, Object Data);
	
	public void post(U viewUtil, HttpServletRequest request);
	
	@SuppressWarnings("rawtypes")
	public void post(ViewUtil viewUtil, Map data);
}
