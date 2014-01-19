package com.single.app.view.handler;

import com.single.app.component.metadate.MetadataConstant;
import com.single.app.component.metadate.MetadataModule;
import com.single.app.component.metadate.ViewModule;
import com.single.app.view.ListTable;
import com.single.app.view.MasterSlaveView;
import com.single.app.view.MasterTable;
import com.single.commons.util.StringUtils;

public class MasterSlaveViewBuilder extends BaseViewBuilder<MasterSlaveView>{

	@Override
	public MasterSlaveView build(ViewModule viewModule) {
		if(viewModule==null){
			return null;
		}
		MasterSlaveView view = new MasterSlaveView(viewModule.getPath(), viewModule.getPageCode(), viewModule.getTitle());
		ViewBuildHandler.putAttributeToView(view, viewModule);
		String masterName = view.getAttribute(MetadataConstant.VIEW_MASTER_METADATA);
		checkMetadataName(masterName, "视图 " + view.getPath() + " 没有设置主表的字段模型.");
		String slaveName = view.getAttribute(MetadataConstant.VIEW_SLAVE_METADATA);
		checkMetadataName(slaveName, "视图 " + view.getPath() + " 没有设置从表的字段模型.");
		MetadataModule masterMetadata = findMetadataModule(masterName);
		checkMetadataModule(masterMetadata, "视图 " + view.getPath() + " 没有找到主表的字段模型['" + masterName + "'].");
		MetadataModule slaveMetadata = findMetadataModule(masterName);
		checkMetadataModule(slaveMetadata, "视图 " + view.getPath() + " 没有找到从表的字段模型['" + slaveName + "'].");
		MasterTable masterTable = new MasterTable(view, findMetadataModule(masterName));
		ListTable slaveTable = new ListTable(view, findMetadataModule(slaveName));
		view.setMasterTable(masterTable);
		view.setSlaveTable(slaveTable);
		return view;
	}
	
	
	public void checkMetadataName(String metadataName, String msg){
		if(StringUtils.isEmpty(metadataName)){
			logger.error(msg);
		}
	}
	
	public void checkMetadataModule(MetadataModule metadata, String msg){
		if(metadata==null){
			logger.error(msg);
		}
	}

}
