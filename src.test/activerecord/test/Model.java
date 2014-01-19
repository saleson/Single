package activerecord.test;


@SuppressWarnings("rawtypes")
public abstract class Model<M extends Model> extends com.jfinal.plugin.activerecord.Model<M>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String getStr(String attr) {
		Object value = get(attr);
		return value instanceof String ? (String) value : value.toString();
	}

}
