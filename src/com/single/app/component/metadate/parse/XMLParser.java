package com.single.app.component.metadate.parse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.CDataBookmark;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import com.single.app.component.metadate.ButtonModule;
import com.single.app.component.metadate.CssInclude;
import com.single.app.component.metadate.CssModule;
import com.single.app.component.metadate.DisplayField;
import com.single.app.component.metadate.EditField;
import com.single.app.component.metadate.ImageTextButton;
import com.single.app.component.metadate.MetadataModule;
import com.single.app.component.metadate.ModelFramework;
import com.single.app.component.metadate.PathInclude;
import com.single.app.component.metadate.ScriptInclude;
import com.single.app.component.metadate.ScriptModule;
import com.single.app.component.metadate.ViewModule;
import com.single.app.component.metadate.WhereField;
import com.single.commons.util.StringUtils;
import com.single.xmlbeans.configuration.ButtonMappingType;
import com.single.xmlbeans.configuration.EidtFieldType;
import com.single.xmlbeans.configuration.FieldType;
import com.single.xmlbeans.configuration.ImageTextButtonType;
import com.single.xmlbeans.configuration.MetadataMappingType;
import com.single.xmlbeans.configuration.MetadataMappingType.Metadata;
import com.single.xmlbeans.configuration.ScriptMappingType;
import com.single.xmlbeans.configuration.ScriptType;
import com.single.xmlbeans.configuration.SetAttributeType;
import com.single.xmlbeans.configuration.SingleConfigDocument;
import com.single.xmlbeans.configuration.StyleMappingType;
import com.single.xmlbeans.configuration.StyleScriptIncludeType;
import com.single.xmlbeans.configuration.StyleType;
import com.single.xmlbeans.configuration.ViewMappingType;
import com.single.xmlbeans.configuration.ViewType;
import com.single.xmlbeans.configuration.WhereFieldType;

/**
 * 
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public class XMLParser extends AbstractModelParser {

	private static Logger logger = Logger.getLogger(XMLParser.class.getName());
	
	
	@Override
	public synchronized List<ModelFramework> parse(File xmlFile) throws XmlException, IOException {
		logger.info("开始解析XML文件 '" + xmlFile.getCanonicalPath() + "'...");
		if(StringUtils.isEmpty(context.getModelGroupName())){
			context.setModelGroupName(xmlFile.getName());
		}
		return parse(SingleConfigDocument.Factory.parse(xmlFile));
	}

	@Override
	public synchronized List<ModelFramework> parse(InputStream in) throws XmlException, IOException {
		return parse(SingleConfigDocument.Factory.parse(in));
	}

	@Override
	public synchronized List<ModelFramework> parse(Reader reader) throws XmlException, IOException {
		return parse(SingleConfigDocument.Factory.parse(reader));
	}
	
	/**
	 * 
	 * @param document
	 * @return
	 */
	public synchronized List<ModelFramework> parse(SingleConfigDocument document){
		logger.info("开始解析SingleConfigDocument对象['"+StringUtils.defaultString(context.getModelGroupName())+"'] ...");
		List<ModelFramework> list = new ArrayList<ModelFramework>();
		SingleConfigDocument.SingleConfig config = document.getSingleConfig();
		com.single.commons.util.ListUtils.addAll(list, parseButtonMapping(config.getButtonMapping()));
		com.single.commons.util.ListUtils.addAll(list, parseStyleMapping(config.getStyleMapping()));
		com.single.commons.util.ListUtils.addAll(list, parseScriptMapping(config.getScriptMapping()));
		com.single.commons.util.ListUtils.addAll(list, parseMetadataMapping(config.getMetadataMapping()));
		com.single.commons.util.ListUtils.addAll(list, parseViewMapping(config.getViewMapping()));
		if(!context.isFactoryGenerated()){
			ParserUtil.invokeParsingListenerInvoke(this, list);
		}
		logger.info("SingleConfigDocument对象['"+StringUtils.defaultString(context.getModelGroupName())+"']解析完成!");
		return list;
	}
	
	/**
	 * 
	 * @param buttonMapping
	 * @return
	 */
	public List<ButtonModule> parseButtonMapping(ButtonMappingType buttonMapping){
		return buttonMapping == null ? null : resolveImageTextButtons(buttonMapping.getImageTextButtonArray());
	}
	
	/**
	 * 
	 * @param buttons
	 * @return
	 */
	private List<ButtonModule> resolveImageTextButtons(ImageTextButtonType[] buttons){
		List<ButtonModule> list = new ArrayList<ButtonModule>();
		for(ImageTextButtonType button : buttons){
			ImageTextButton but = resolveImageTextButton(button);
			list.add(but);
		}
		return list;
	}
	
	/**
	 * 
	 * @param button
	 * @return
	 */
	private ImageTextButton resolveImageTextButton(ImageTextButtonType button){
		ImageTextButton but = new ImageTextButton(context.getModelGroupName(), button.getName());
		but.setText(button.getText());
		but.setTooltip(button.getTooltip());
		but.setOnClick(button.getOnClick());
		but.setWidth(button.getWidth());
		but.setHeight(button.getHeight());
		but.setInitialState(button.getInitialState().toString());
		but.setEnableDetect(button.getEnableDetect());
		but.setIndex(button.getIndex());
		but.setImageSrc(button.getImageSrc());
		but.setDisableImageSrc(button.getDisableImageSrc());
		but.setGroup(button.getGroup());
		return but;
	}
	
	/**
	 * 
	 * @param styleMapping
	 * @return
	 */
	public List<CssModule> parseStyleMapping(StyleMappingType styleMapping){
		return styleMapping == null ? null : resolveStyleTypes(styleMapping.getStyleArray());
	}
	
	/**
	 * 
	 * @param styles
	 * @return
	 */
	private List<CssModule> resolveStyleTypes(StyleType[] styles){
		List<CssModule> list = new ArrayList<CssModule>();
		for(StyleType style : styles){
			CssModule css = resolveStyleType(style);
			list.add(css);
		}
		return list;
	}
	
	/**
	 * 
	 * @param style
	 * @return
	 */
	private CssModule resolveStyleType(StyleType style){
		CssModule css = new CssModule(context.getModelGroupName(), style.getName());
		css.setType(style.getType());
		css.appendAll(getCDataText(style));
		return css;
	}
	
	/**
	 * 
	 * @param scriptMapping
	 * @return
	 */
	public List<ScriptModule> parseScriptMapping(ScriptMappingType scriptMapping){
		return scriptMapping == null ? null : resolveScriptTypes(scriptMapping.getScriptArray());
	}
	
	/**
	 * 
	 * @param scripts
	 * @return
	 */
	private List<ScriptModule> resolveScriptTypes(ScriptType[] scripts){
		List<ScriptModule> list = new ArrayList<ScriptModule>();
		for(ScriptType scriptType : scripts){
			list.add(resolveScriptType(scriptType));
		}
		return list;
	}
	
	/**
	 * 
	 * @param scriptType
	 * @return
	 */
	private ScriptModule resolveScriptType(ScriptType scriptType){
		ScriptModule script = new ScriptModule(context.getModelGroupName(), scriptType.getName());
		script.setType(scriptType.getType());
		script.appendAll(getCDataText(scriptType));
		return script;
	} 
	
	/**
	 * 
	 * @param metadataMapping
	 * @return
	 */
	public List<MetadataModule> parseMetadataMapping(MetadataMappingType metadataMapping){
		List<MetadataModule> list = new ArrayList<MetadataModule>();
		if(metadataMapping!=null){
			Metadata[] metadatas = metadataMapping.getMetadataArray();
			for(Metadata metadata : metadatas){
				list.add(resolveMetadata(metadata));
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param metadata
	 * @return
	 */
	private MetadataModule resolveMetadata(Metadata metadata){
		MetadataModule metadataModule = new MetadataModule(context.getModelGroupName(), metadata.getName());
		FieldType[] fieldTypes = metadata.getFieldArray();
		if(fieldTypes!=null){
			for(FieldType fieldType : fieldTypes){
				DisplayField field = new DisplayField(fieldType.getName(), fieldType.getCaption());
				field.setPrimary(fieldType.getPrimary());
				field.setTipTitle(fieldType.getTipTitle());
				field.setDatatype(fieldType.getDatatype().toString());
				field.setDisplay(fieldType.getDisplay());
				field.setFormat(fieldType.getFormat());
				field.setIndex(fieldType.getIndex());
				ParserUtil.resolveFieldItems(field, fieldType.getItems());
				field.setType(fieldType.getType().toString());
				metadataModule.addDisplayField(field);
			}
		}
		
		EidtFieldType[] editFieldTypes = metadata.getEditFieldArray();
		if(editFieldTypes!=null){
			for(EidtFieldType editFieldType : editFieldTypes){
				EditField field = new EditField(editFieldType.getName(), editFieldType.getCaption());
				field.setEditable(editFieldType.getEditable());
				field.setCodeRule(editFieldType.getCodeRule());
				field.setTipTitle(editFieldType.getTipTitle());
				field.setDatatype(editFieldType.getDatatype().toString());
				field.setDisplay(editFieldType.getDisplay());
				field.setFormat(editFieldType.getFormat());
				field.setIndex(editFieldType.getIndex());
				ParserUtil.resolveFieldItems(field, editFieldType.getItems());
				field.setType(editFieldType.getType().toString());
				metadataModule.addDisplayField(field);
			}
		}
		
		WhereFieldType[] whereFieldTypes = metadata.getWhereFieldArray();
		if(whereFieldTypes!=null){
			for(WhereFieldType whereFieldType : whereFieldTypes){
				WhereField field = new WhereField(whereFieldType.getName(), whereFieldType.getCaption());
				field.setField(whereFieldType.getField());
				field.setOpersign(whereFieldType.getOpersign().toString());
				field.setTipTitle(whereFieldType.getTipTitle());
				field.setDatatype(whereFieldType.getDatatype().toString());
				field.setDisplay(whereFieldType.getDisplay());
				field.setFormat(whereFieldType.getFormat());
				field.setIndex(whereFieldType.getIndex());
				ParserUtil.resolveFieldItems(field, whereFieldType.getItems());
				field.setType(whereFieldType.getType().toString());
				metadataModule.addWhereField(field);
			}
		}
		return metadataModule;
	}
	/**
	 * 
	 * @param viewMapping
	 * @return
	 */
	public List<ViewModule> parseViewMapping(ViewMappingType viewMapping){
		List<ViewModule> list = new ArrayList<ViewModule>();
		if(viewMapping!=null){
			ViewType[] viewTypes = viewMapping.getViewArray();
			for(ViewType viewType : viewTypes){
				list.add(resolveViewType(viewType));
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param viewType
	 * @return
	 */
	private ViewModule resolveViewType(ViewType viewType){
		ViewModule view = new ViewModule(context.getModelGroupName(), viewType.getPath(), viewType.getPagecode()); 
		SetAttributeType[] attributes = viewType.getSetAttributeArray();
		for(SetAttributeType attribute : attributes){
			view.putAttribute(attribute.getKey().toString(), attribute.newCursor().getTextValue());
		}
		view.setTitle(viewType.getTitle());
		view.setPageCode(viewType.getPagecode());
		if(viewType.getHeader()!=null)
			view.setHeader(viewType.getHeader().getPath());
		if(viewType.getFooter()!=null)
			view.setFooter(viewType.getFooter().getPath());
		
		com.single.xmlbeans.configuration.PathInclude[] includes = viewType.getIncludeArray();
		if(includes!=null){
			List<PathInclude> includeList = new ArrayList<PathInclude>();
			for(int i=0;i<includes.length;i++){
				com.single.xmlbeans.configuration.PathInclude include = includes[i];
				PathInclude pathInclude = new PathInclude(include.getPath(), include.getMethod().toString());
				includeList.add(pathInclude);
			}
			view.addPathInclude(includeList.toArray(new PathInclude[includeList.size()]));
		}
		
		StyleScriptIncludeType[] styleIncludes = viewType.getStyleIncludeArray();
		if(styleIncludes!=null){
			List<CssInclude> styleIncludeList = new ArrayList<CssInclude>();
			for(int i=0;i<styleIncludes.length;i++){
				StyleScriptIncludeType include = styleIncludes[i];
				String names = include.getNames();
				if(StringUtils.isNotEmpty(names)){
					CssInclude styleInclude = new CssInclude(names.split(","), include.getMethod().toString());
					styleIncludeList.add(styleInclude);
				}
			}
			view.addStyleIncludes(styleIncludeList.toArray(new CssInclude[styleIncludeList.size()]));
		}
		
		StyleScriptIncludeType[] scriptIncludes = viewType.getScriptIncludeArray();
		if(scriptIncludes!=null){
			List<ScriptInclude> scriptIncludeList = new ArrayList<ScriptInclude>();
			for(int i=0;i<scriptIncludes.length;i++){
				StyleScriptIncludeType include = scriptIncludes[i];
				String names = include.getNames();
				if(StringUtils.isNotEmpty(names)){
					ScriptInclude styleInclude = new ScriptInclude(names.split(","), include.getMethod().toString());
					scriptIncludeList.add(styleInclude);
				}
			}
			view.addScriptIncludes(scriptIncludeList.toArray(new ScriptInclude[scriptIncludeList.size()]));
		}
		
		if(viewType.getToolbar()!=null){
			view.setViewPrivateButtons(com.single.commons.util.ListUtils.toArray(
					resolveImageTextButtons(viewType.getToolbar().getImageTextButtonArray()), ButtonModule.class));
		}
		
		StyleType[] styleTypes = viewType.getStyleArray();
		if(styleTypes!=null){
			view.setViewPrivateStyles(com.single.commons.util.ListUtils.toArray(resolveStyleTypes(styleTypes), CssModule.class));
		}
		
		ScriptType[] scriptTypes = viewType.getScriptArray();
		if(scriptTypes!=null){
			view.setViewPrivateScripts(com.single.commons.util.ListUtils.toArray(resolveScriptTypes(scriptTypes), ScriptModule.class));
		}
		return view;
	}
	
	/**
	 * 
	 * @param xObject
	 * @return
	 */
	public static String getCDataText(XmlObject xObject){
		XmlCursor c = xObject.newCursor();
		c.setTextValue(c.getTextValue());
		c.toFirstContentToken();
		c.setBookmark(CDataBookmark.CDATA_BOOKMARK);
		return c.getTextValue();
	}
	
	/**
	 * 
	 * @param xObject
	 * @return
	 */
	public static String getText(XmlObject xObject){
		XmlCursor c = xObject.newCursor();
		c.setTextValue(c.getTextValue());
		c.toFirstContentToken();
		return c.getTextValue();
	}
	
	
	@Override
	public void setParsingContext(ParsingProcessContext context) {
		super.setParsingContext(context);
		context.setLogger(logger);
	}
}
