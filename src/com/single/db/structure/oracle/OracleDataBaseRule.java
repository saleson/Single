package com.single.db.structure.oracle;

import java.sql.Types;
import org.apache.commons.lang3.ArrayUtils;
import com.single.db.structure.DataBaseRule;

public class OracleDataBaseRule implements DataBaseRule {

	public static final int[] columnTypes = {101, 100, Types.BLOB, Types.CLOB, Types.CHAR, Types.TIMESTAMP, Types.LONGVARCHAR, 1111, Types.DECIMAL, Types.VARCHAR};
	public static final String[] columnTypeNames = {"BINARY_DOUBLE", "BINARY_FLOAT", "BLOB", "CLOB", "CHAR", "DATE", "LONG", "NCLOB", "NUMBER", "VARCHAR2"};
	private static OracleDataBaseRule rule = new OracleDataBaseRule();
	
	public static OracleDataBaseRule getInstance(){
		return rule;
	}
	
	
	private OracleDataBaseRule(){
		
	}
	
	@Override
	public int getMaxUserNameLength() {
		return 30;
	}

	@Override
	public int getMaxTableNameLength() {
		return 30;
	}

	@Override
	public int getMaxColumnNameLength() {
		return 30;
	}

	@Override
	public int getKeyNameLength() {
		return 30;
	}

	@Override
	public int getIndexNameLength() {
		return 30;
	}

	@Override
	public int[] getColumnTypes() {
		return columnTypes;
	}

	@Override
	public String[] getColumnTypeStrings() {
		return columnTypeNames;
	}

	@Override
	public int getColumnType(String typeString) {
		int index = ArrayUtils.indexOf(columnTypeNames, typeString);
		return index !=-1 ? columnTypes[index] : 0;
	}

	@Override
	public String getColumnTypeString(int type) {
		int index = ArrayUtils.indexOf(columnTypes, type);
		return index !=-1 ? columnTypeNames[index] : null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class[] getColumnTypes2JavaTypes() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getColumnType2JavaType(String typeString) {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getColumnType2JavaType(int typeString) {
		// TODO Auto-generated method stub
		return null;
	}

}
