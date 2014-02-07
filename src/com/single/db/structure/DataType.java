package com.single.db.structure;

/**
 * DataType is an enumeration of data types that are supported
 * with the empire-db component.
 * 
 */
public enum DataType
{
    /**
     * Unknown, used internally only for sql phrases
     */
    UNKNOWN, //      = 0;
    
    /**
     * Integer value (16, 32 or 64 bit)
     */
    INTEGER, //      = 1;
    
    /**
     * A numeric sequence generated value
     */
    AUTOINC, //      = 2;
    
    /**
     * Small text (represents varchar)
     */
    TEXT, //         = 3;
    
    /**
     * Date only value (without time)
     */
    DATE, //         = 4;
    
    /**
     * Date value including time. Also knows a timestamp
     */
    DATETIME, //     = 5;
    
    /**
     * Fixed length character value.
     */
    CHAR, //         = 6;
    
    /**
     * floating point value
     */
    FLOAT, //        = 7;
    
    /**
     * Decimal numeric value (size indicates scale and precision)
     */
    DECIMAL, //      = 8;
    
    /**
     * Boolean field (emulated if not supported by DBMS as number or char)
     */
    BOOL, //         = 9;
    
    /**
     * Long text > 2K
     */
    CLOB, //         = 10;
    
    /**
     * Binary data
     */
    BLOB, //         = 11;
    
    /**
     * Unique Identifier (non-numeric, treated like text) 
     */
    UNIQUEID; //     = 12;
    
    /**
     * Returns true if the data type is a text based data type (char, text or clob)
     * @return true if the data type is a character based data type
     */
    public boolean isText()
    {
        return (this==DataType.TEXT || this==DataType.CHAR || this==DataType.CLOB);
    }

    /**
     * Returns true if the data type is a numeric data type (integer, decimal, float)
     * @return true if the data type is a numeric data type
     */
    public boolean isNumeric()
    {
        return (this==DataType.INTEGER || this==DataType.DECIMAL || this==DataType.FLOAT || this==DataType.AUTOINC);
    }

    /**
     * Returns true if the data type is a date based data type (date or datetime)
     * @return true if the data type is a date based data type
     */
    public boolean isDate()
    {
        return (this==DataType.DATE || this==DataType.DATETIME);
    }

    /**
     * Returns true if the data type is a boolean type
     * @return true if the data type is a boolean type
     */
    public boolean isBoolean()
    {
        return (this==DataType.BOOL);
    }
}