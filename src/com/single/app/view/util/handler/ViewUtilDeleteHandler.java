package com.single.app.view.util.handler;

import com.single.app.view.View;
import com.single.app.view.util.AssociatedNotDeleteException;
import com.single.app.view.util.ViewUtil;

public interface ViewUtilDeleteHandler<U extends ViewUtil<? extends View>> {

	public void delete(U viewUtil) throws AssociatedNotDeleteException;
	
}
