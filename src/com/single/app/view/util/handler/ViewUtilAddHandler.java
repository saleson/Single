package com.single.app.view.util.handler;

import com.single.app.view.View;
import com.single.app.view.util.ViewUtil;

public interface ViewUtilAddHandler<U extends ViewUtil<? extends View>> {

	public void putInitialData(U viewUtil);
}
