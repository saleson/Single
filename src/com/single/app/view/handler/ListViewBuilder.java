package com.single.app.view.handler;

import com.single.app.component.metadate.MetadataConstant;
import com.single.app.component.metadate.MetadataModule;
import com.single.app.component.metadate.ViewModule;
import com.single.app.view.ListTable;
import com.single.app.view.ListView;
import com.single.commons.util.StringUtils;

public class ListViewBuilder extends BaseViewBuilder<ListView>{

	@Override
	public ListView build(ViewModule viewModule) {
		if(viewModule==null){
			return null;
		}
		ListView view = new ListView(viewModule.getPath(), viewModule.getPageCode(), viewModule.getTitle());
		ViewBuildHandler.putAttributeToView(view, viewModule);
		String metadataName = view.getAttribute(MetadataConstant.VIEW_LIST_METADATA);
		if(StringUtils.isEmpty(metadataName)){
			logger.error("视图 " + view.getPath() + " 没有设置列表的字段模型.");
		}
		MetadataModule metadata = findMetadataModule(metadataName);
		if(metadata==null){
			logger.error("视图 " + view.getPath() + " 没有找到列表的字段模型['" + metadataName + "'].");
		}
		ListTable table = new ListTable(view, metadata);
		view.setListTable(table);
		return view;
	}

}
