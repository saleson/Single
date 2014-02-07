package com.single.db.query;


public class QueryItem extends DBExpr implements QueryTJ{

	private SelectColumn column;
	private QueryOpersign opersign;
	private Object value;
	private String condition;
	
	private boolean or;
	
	public QueryItem(SelectColumn column,  Object value){
		this(column, QueryOpersign.EQUAL, value);
	}
	
	
	public QueryItem(SelectColumn column, QueryOpersign opersign, Object value){
		this(column, opersign, value, false);
	}
	
	
	public QueryItem(SelectColumn column, QueryOpersign opersign, Object value, boolean isOr){
		this.column = column;
		this.opersign = opersign;
		this.value = value;
		this.or = isOr;
	}
	
	
	public SelectColumn getColumn() {
		return column;
	}



	public void setColumn(SelectColumn column) {
		this.column = column;
	}



	public QueryOpersign getOpersign() {
		return opersign;
	}



	public void setOpersign(QueryOpersign opersign) {
		this.opersign = opersign;
	}



	public Object getValue() {
		return value;
	}



	public void setValue(Object value) {
		this.value = value;
	}



	public boolean isOr() {
		return or;
	}



	public void setOr(boolean or) {
		this.or = or;
	}


	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}
	

	public void addSQL(StringBuilder buf){
		buf.append(column.toSQLString());
		String arraySep = "+";
        switch (opersign){ // other than default:
            case BETWEEN:
            case NOTBETWEEN:
                arraySep = " AND ";
                break;
            case IN:
            case NOTIN:
                arraySep = ", ";
                break;
            default:
                // Nothing to do
                break;
        }
        
		String valsql = value instanceof SelectColumn ? ((SelectColumn) value).toSQLString() : getObjectValue(column.getDataType(), value, arraySep);;
		switch (opersign) {
			case EQUAL:
	            buf.append("=");
	            break;
	        case NOTEQUAL:
	            buf.append("<>");
	            break;
	        case LESSTHAN:
	            buf.append("<");
	            break;
	        case MOREOREQUAL:
	            buf.append(">=");
	            break;
	        case GREATERTHAN:
	            buf.append(">");
	            break;
	        case LESSOREQUAL:
	            buf.append("<=");
	            break;
	        case LIKE:
	            buf.append(" LIKE ");
	            valsql = "%" + valsql + "%";
	            break;
	        case NOTLIKE:
	            buf.append(" NOT LIKE ");
	            valsql = "%" + valsql + "%";
	            break;
	        case NULL:
	            buf.append(" IS NULL");
	            valsql = null;
	            break;
	        case NOTNULL:
	            buf.append(" IS NOT NULL");
	            valsql = null;
	            break;
	        case BETWEEN:
	            buf.append(" BETWEEN ");
	            break;
	        case NOTBETWEEN:
	            buf.append(" NOT BETWEEN ");
	            break;
	        case IN:
	            buf.append(" IN (");
	            buf.append(valsql);
	            buf.append(")");
	            valsql = null;
	            break;
	        case NOTIN:
	            buf.append(" NOT IN (");
	            buf.append(valsql);
	            buf.append(")");
	            valsql = null;
	            break;
	        default:
	            // NONE
	            buf.append(" ");
		}
		
		if (valsql != null)
            buf.append(valsql);
		
	}
	
}
