package com.single.app.component.metadate.model.assembler;

import org.apache.log4j.Logger;

import com.single.app.component.metadate.mapping.StructureMapping;

public abstract class AbstractModelAssembler<T> implements ModelAssembler<T> {

	protected static Logger logger = Logger.getLogger(AbstractModelAssembler.class.getName());
	
	protected StructureMapping mapping = StructureMapping.getInstance();
	
}
