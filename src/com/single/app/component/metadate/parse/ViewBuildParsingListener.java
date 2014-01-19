package com.single.app.component.metadate.parse;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.single.app.component.metadate.MetadataConstant;
import com.single.app.component.metadate.ModelFramework;
import com.single.app.component.metadate.ViewModule;
import com.single.app.component.metadate.ViewStructure;
import com.single.app.component.metadate.mapping.StructureMapping;
import com.single.app.view.handler.ViewBuildHandler;

/**
 * 监测到有ViewModule被改动就重新更新相应的View对象.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public class ViewBuildParsingListener extends AbstractParsingListener{

	private Logger logger;
	private Set<String> viewNameSet = new TreeSet<String>();
	@Override
	public void beforeParse(ParsingProcessContext context) {
		logger = context.getLogger();
		if(logger==null)
			logger = Logger.getLogger(ViewBuildParsingListener.class);
	}

	@Override
	public void invoke(List<ModelFramework> mfList) {
		for(ModelFramework mf : mfList){
			if(mf instanceof ViewModule){
				viewNameSet.add(mf.getName());
			}
		}
	}

	@Override
	public void afterParsed() {
		Iterator<String> iter = viewNameSet.iterator();
		StructureMapping mapping = StructureMapping.getInstance();
		while(iter.hasNext()){
			ViewModule viewModule = mapping.getViewModule(iter.next());
			if(viewModule!=null){
				try{
					viewModule.setViewStructure(ViewStructure.valueOf(viewModule.getAttribute(MetadataConstant.VIEW_STRUCTURE)));
				}catch (Exception e) {
					logger.error("视图[" + viewModule.getPath() + "]没有找到" + MetadataConstant.VIEW_STRUCTURE + "属性,不能确定该视图的结构!", e);
					return;
				}
				ViewBuildHandler.buildView(viewModule);
			}
		}
	}

}
