package com.single.app.component.metadate.model.assembler;

import java.util.Set;

import com.single.app.component.metadate.MetadataConstant;
import com.single.app.component.metadate.ViewModule;
import com.single.app.component.metadate.mapping.ViewModuleFactory;

public class ViewModelAssembler extends AbstractModelAssembler <ViewModule> {

	private static ViewModelAssembler assembler = new ViewModelAssembler();
	
	public static ViewModelAssembler getInstance(){
		return assembler;
	}
	
	@Override
	public ViewModule buildUp(String viewName) {
		ViewModule viewModel = null;
		Set<ViewModule> set = mapping.getViewModuleGroup(viewName);
		if(set==null || set.size()==0){
			logger.warn("没有找到任何跟视图名['" + viewName + "']有关的视图模型(ViewModule)!");
		}else{
			viewModel = mapping.getViewModule(viewName);
			if(viewModel!=null && "2".equals("1")){
				viewModel.isLatest(false);
			}else{
				//viewModel = new ViewModule(viewName, viewModel.getTitle(), viewModel.getPageCode());
				viewModel = ViewModuleFactory.newViewModule();
				for(ViewModule _viewModel : set){
					viewModel = _viewModel;
				}
			}
			//view = set.iterator().next();
			//structure_viewmodule_/test/test.jfl
			//structure_/test/test.jfl
			mapping.putViewModule(viewName, viewModel, new String[]{MetadataConstant.VIEWCOMPLETEGROUPNAME});
		}
		return viewModel;
	}

}
