package com.single.db.query;

import java.util.Collection;

import org.apache.log4j.Logger;

import com.single.db.structure.DataType;

public class DBExpr {
	
	protected Logger logger = Logger.getLogger(DBExpr.class);

	
	protected DataType getDataType(){
		return null;
	}
	
	protected String getObjectValue(DataType dataType, Object value, String arraySep){
        // it's an Object
		if (value instanceof Collection<?>){
        	value = ((Collection<?>)value).toArray();
        }
        // Check whether it is an array
        if (value!=null && value.getClass().isArray()){
            StringBuilder buf = new StringBuilder();
            // An Array of Objects
            Object[] array = (Object[]) value;
            for (int i = 0; i < array.length; i++)
            { // Array Separator
                if (i > 0 && arraySep != null)
                    buf.append(arraySep);
                // Append Value
                buf.append(getObjectValue(dataType, array[i], arraySep));
            }
            return buf.toString();
        } else{   // Scalar Value from DB
        	DBDriver driver = null;
            try{
            	driver = DBDriver.getRegisteredDBDriver();
            }catch (Exception e) {
				logger.warn("取程序数据库驱动异常-->" + e.getMessage());
			}
            
            return driver==null ? String.valueOf(value) : driver.getValueString(value, dataType);
        }
    }
}
