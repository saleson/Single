package com.single.app.component.metadate.model.assembler;


/**
 * 模型组装器.
 * 将同一种类型同个名称的{@link com.single.app.component.metadate.ModelFramework}接口实现类组装起来.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public interface ModelAssembler<T> {

	public T buildUp(String name);
}
