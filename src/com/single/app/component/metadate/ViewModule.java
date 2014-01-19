package com.single.app.component.metadate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import com.single.commons.util.ArrayUtils;
import com.single.commons.util.StringUtils;


/**
 * 视图模型,可以通过其path属性查找,也可以通过path进入页面.
 * 页面中的的权限也是通过pagecode属性控制的.
 * 且视图模型中包含有属性集合,可以控制页面的显示内容,排版等.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public class ViewModule extends AbstractModelFrameWork{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Map<String, String> attributes;
	protected String path;
	protected String title;
	protected String pageCode;
	protected String header;
	protected PathInclude[] pathIncludes;
	protected String footer;
	protected CssInclude[] styleIncludes;
	protected ScriptInclude[] scriptIncludes;
	protected CssModule[] viewPrivateStyles;
	protected ScriptModule[] viewPrivateScripts;
	protected ButtonModule[] viewPrivateButtons;
	protected ViewStructure viewStructure;
	
	public ViewModule(String groupName, String path, String pageCode){
		super(groupName);
		attributes = new HashMap<String, String>();
		this.path = path;
		this.pageCode = pageCode;
	}
	
	public Map<String, String> getAttributes() {
		return MapUtils.unmodifiableMap(attributes);
	}
	
	public Map<String, String> getAllowsMmodifiableAttributes() {
		return attributes;
	}
	
	public void putAttribute(String attributeName, String attributeValue){
		attributes.put(attributeName, attributeValue);
	}
	
	public void removeAttribute(String attributeName){
		attributes.remove(attributeName);
	}
	
	public boolean containAttribute(String attributeName){
		return attributes.containsKey(attributeName);
	}
	
	public String getAttribute(String attributeName){
		return attributes.get(attributeName);
	}

	public String getName(){
		return getPath();
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	
	public ViewStructure getViewStructure() {
		return viewStructure;
	}

	public void setViewStructure(ViewStructure viewStructure) {
		this.viewStructure = viewStructure;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public IncludeModule[] getPathIncludes() {
		return pathIncludes;
	}

	public void setPathIncludes(PathInclude[] pathIncludes) {
		this.pathIncludes = pathIncludes;
	}
	
	public void removePathInclude(PathInclude... includes){
		removeArrayProperties(this.pathIncludes, includes);
	}
	
	public void addPathInclude(PathInclude... includes){
		addArrayProperties(this.pathIncludes, includes);
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public CssInclude[] getStyleIncludes() {
		return styleIncludes;
	}

	public void setStyleIncludes(CssInclude[] styleIncludes) {
		this.styleIncludes = styleIncludes;
	}
	
	public void removeStyleIncludes(CssInclude... styleIncludes){
		removeArrayProperties(this.styleIncludes, styleIncludes);
	}
	
	public void addStyleIncludes(CssInclude... styleIncludes){
		addArrayProperties(this.styleIncludes, styleIncludes);
	}

	public ScriptInclude[] getScriptIncludes() {
		return scriptIncludes;
	}

	public void setScriptIncludes(ScriptInclude[] scriptIncludes) {
		this.scriptIncludes = scriptIncludes;
	}

	public void removeScriptIncludes(ScriptInclude... scriptIncludes){
		removeArrayProperties(this.scriptIncludes, scriptIncludes);
	}
	
	public void addScriptIncludes(ScriptInclude... scriptIncludes){
		addArrayProperties(this.scriptIncludes,scriptIncludes);
	}
	
	public CssModule[] getViewPrivateStyles() {
		return viewPrivateStyles;
	}
	
	public void setViewPrivateStyles(CssModule[] viewPrivateStyles) {
		this.viewPrivateStyles = viewPrivateStyles;
	}

	public void removeViewPrivateStyles(CssModule... viewPrivateStyles){
		removeArrayProperties(this.viewPrivateStyles, viewPrivateStyles);
	}
	
	public void addViewPrivateStyles(CssModule... viewPrivateStyles){
		addArrayProperties(this.viewPrivateStyles, viewPrivateStyles);
	}

	public ScriptModule[] getViewPrivateScripts() {
		return viewPrivateScripts;
	}
	
	public void setViewPrivateScripts(ScriptModule[] viewPrivateScripts) {
		this.viewPrivateScripts = viewPrivateScripts;
	}

	public void removeViewPrivateScripts(ScriptModule... viewPrivateScripts){
		removeArrayProperties(this.viewPrivateScripts, viewPrivateScripts);
	}
	
	public void addViewPrivateScripts(ScriptModule... viewPrivateScripts){
		addArrayProperties(this.viewPrivateScripts, viewPrivateScripts);
	}
	
	public ButtonModule[] getViewPrivateButtons() {
		return viewPrivateButtons;
	}
	
	public void setViewPrivateButtons(ButtonModule[] viewPrivateButtons) {
		this.viewPrivateButtons = viewPrivateButtons;
	}

	public void removeViewPrivateButtons(ButtonModule... viewPrivateButtons){
		removeArrayProperties(this.viewPrivateButtons, viewPrivateButtons);
	}
	
	public void addViewPrivateButtons(ButtonModule... viewPrivateButtons){
		addArrayProperties(this.viewPrivateButtons, viewPrivateButtons);
	}
	
	/**
	 * 新增数组,如果array1(克隆过的)中有不包含array2的对象,则新增进去.
	 * @param array1
	 * @param array2
	 */
	private <T> void addArrayProperties(T[] array1, T[] array2){
		array1 = addArray(array1, array2);
	}
	
	/**
	 * 删除数组,如果array1中包含array2的对象,则删除.
	 * @param array1
	 * @param array2
	 */
	private <T> void removeArrayProperties(T[] array1, T[] array2){
		array1 = ArrayUtils.removeAll(array1, array2);
	}
	
	/**
	 * 新增数组,如果array1(克隆过的)中有不包含array2的对象,则新增进去.
	 * 返回一个被新增过的数组,array1不改变.
	 * @param array1
	 * @param array2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T[] addArray(T[] array1, T[] array2){
		List<T> list = new ArrayList<T>();
		for(T a : array2){
			if(a==null || StringUtils.isEmpty(a.toString()) || ArrayUtils.contains(array1, a)){
				continue;
			}
			list.add(a);
		}
		return ArrayUtils.addAll(array1, (T[]) list.toArray());
	}
	
	
	@Override
	public ViewModule clone() {
		ViewModule view = null;
		try {
			view = (ViewModule) super.clone();
			if(this.pathIncludes!=null)
				view.pathIncludes = this.pathIncludes.clone();
			if(this.styleIncludes!=null)
				view.styleIncludes = this.styleIncludes.clone();
			if(this.scriptIncludes!=null)
				view.scriptIncludes = this.scriptIncludes.clone();
			if(this.viewPrivateStyles!=null)
				view.viewPrivateStyles = this.viewPrivateStyles.clone();
			if(this.viewPrivateScripts!=null)
				view.viewPrivateScripts = this.viewPrivateScripts.clone();
			if(this.viewPrivateButtons!=null)
				view.viewPrivateButtons = this.viewPrivateButtons.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return view;
	}

	@Override
	public boolean needAssembly() {
		return true;
	}
	
	public void modify(ViewModule another){
		
	}

}
