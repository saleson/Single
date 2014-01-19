package com.single.app.view.handler;

import com.single.app.component.metadate.ViewModule;
import com.single.app.view.View;

/**
 * 视图构建器
 * @author Saleson. 
 * Computer by Administrator.
 *
 * @param <V>
 */
public interface  ViewBuilder<V extends View> {

	public V build(String path);
	
	public V build(ViewModule viewModule);
	
	
}
