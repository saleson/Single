package com.single.db.query;

/***********************************************************************
 * Module:  Group.java
 * Author:  Administrator
 * Purpose: Defines the Class Group
 ***********************************************************************/

import java.util.*;

/** @pdOid 629c3210-093b-4dde-9e30-d0cd91394555 */
public class Group {
   /** @pdRoleInfo migr=no name=SelectColumn assc=association3 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Aggregation */
   public java.util.Collection selectColumn;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection getSelectColumn() {
      if (selectColumn == null)
         selectColumn = new java.util.HashSet();
      return selectColumn;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorSelectColumn() {
      if (selectColumn == null)
         selectColumn = new java.util.HashSet();
      return selectColumn.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newSelectColumn */
   public void setSelectColumn(java.util.Collection newSelectColumn) {
      removeAllSelectColumn();
      for (java.util.Iterator iter = newSelectColumn.iterator(); iter.hasNext();)
         addSelectColumn((SelectColumn)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newSelectColumn */
   public void addSelectColumn(SelectColumn newSelectColumn) {
      if (newSelectColumn == null)
         return;
      if (this.selectColumn == null)
         this.selectColumn = new java.util.HashSet();
      if (!this.selectColumn.contains(newSelectColumn))
         this.selectColumn.add(newSelectColumn);
   }
   
   /** @pdGenerated default remove
     * @param oldSelectColumn */
   public void removeSelectColumn(SelectColumn oldSelectColumn) {
      if (oldSelectColumn == null)
         return;
      if (this.selectColumn != null)
         if (this.selectColumn.contains(oldSelectColumn))
            this.selectColumn.remove(oldSelectColumn);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllSelectColumn() {
      if (selectColumn != null)
         selectColumn.clear();
   }

}