package com.single.app.view.util.handler;

import com.single.app.view.View;
import com.single.app.view.util.ViewUtil;

public interface ViewUtilSaveHandler <U extends ViewUtil<? extends View>> {

	public void save(U viewUtil);
}
