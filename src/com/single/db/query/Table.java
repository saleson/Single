package com.single.db.query;


import java.util.*;

public class Table {
   private String tableName;
   private String alias;
   
   public java.util.Collection<Join> join;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Join> getJoin() {
      if (join == null)
         join = new java.util.HashSet<Join>();
      return join;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator<Join> getIteratorJoin() {
      if (join == null)
         join = new java.util.HashSet<Join>();
      return join.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newJoin */
   public void setJoin(java.util.Collection<Join> newJoin) {
      removeAllJoin();
      for (java.util.Iterator<Join> iter = newJoin.iterator(); iter.hasNext();)
         addJoin((Join)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newJoin */
   public void addJoin(Join newJoin) {
      if (newJoin == null)
         return;
      if (this.join == null)
         this.join = new java.util.HashSet<Join>();
      if (!this.join.contains(newJoin))
         this.join.add(newJoin);
   }
   
   /** @pdGenerated default remove
     * @param oldJoin */
   public void removeJoin(Join oldJoin) {
      if (oldJoin == null)
         return;
      if (this.join != null)
         if (this.join.contains(oldJoin))
            this.join.remove(oldJoin);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllJoin() {
      if (join != null)
         join.clear();
   }

}