package com.single.app.view.util.handler;

import com.single.app.view.View;
import com.single.app.view.util.ViewUtil;

public interface ViewUtilFactory<V extends ViewUtil<? extends View>> {

	public V getViewUtil(View view);
	
	public V getViewUtil(String path);
}
