package com.single.app.component.metadate.parse;

import java.util.List;


public abstract class AbstractModelParser implements ModelParser {

	protected ParsingProcessContext context;
	protected boolean isParsing = false;
	
	@Override
	public void setParsingContext(ParsingProcessContext context) {
		this.context = context;
		//context.setParser(this);
	}

	@Override
	public ParsingProcessContext getParsingContext() {
		return context;
	}
	
	@Override
	public void isParsing(boolean isParsing) {
		this.isParsing = isParsing;
	}
	
	@Override
	public boolean isParsing() {
		return isParsing;
	}
	
	public List<ParsingListener> getListeners() {
		return context.getListeners();
	}

	public void addtListener(ParsingListener listener){
		context.addtListener(listener);
	}
	
	@Override
	public void finish() {
		if(!context.isFactoryGenerated()){
			ParserUtil.invokeParsingListenerAfterParsed(this);
		}
	}

}
