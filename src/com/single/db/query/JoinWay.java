package com.single.db.query;

/**
 * 关联类型
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public enum JoinWay {
	 /**
     * SQL Left join
     */
    LEFT,   //   =-1,
    
    /**
     * SQL Inner join
     */
    INNER,  //   = 0,
    
    /**
     * SQL Right join
     */
    RIGHT;  //   = 1
	
}
