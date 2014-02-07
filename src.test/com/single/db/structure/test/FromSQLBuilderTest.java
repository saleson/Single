package com.single.db.structure.test;

import com.single.db.query.From;
import com.single.db.query.Join;
import com.single.db.query.JoinWay;
import com.single.db.query.QueryItem;
import com.single.db.query.QueryOpersign;
import com.single.db.query.QueryTable;
import com.single.db.query.TableColumn;
import com.single.db.query.exceptions.DBDriverFailRegisteredException;
import com.single.db.structure.SingleDBStructure;

public class FromSQLBuilderTest {

	public static void main(String[] args) throws DBDriverFailRegisteredException {
		new SingleDBStructure();
		
		
		QueryTable masterTable = new QueryTable("emp", "e");
		From from = new From(masterTable);
		
		QueryTable joinTable1 = new QueryTable("bm", "b");
		Join join1 = new Join(masterTable, joinTable1, JoinWay.LEFT);
		join1.getJoinOn().addQuerys(new QueryItem(new TableColumn(masterTable, "deptid"), QueryOpersign.EQUAL, new TableColumn(joinTable1, "deptid")));
		from.addJoin(join1);
		
		QueryTable joinTable2 = new QueryTable("sk", "s");
		Join join2 = new Join(joinTable1, joinTable2, JoinWay.LEFT);
		join2.getJoinOn().addQuerys(new QueryItem(new TableColumn(joinTable1, "dsdcolumn"), QueryOpersign.EQUAL,  new TableColumn(joinTable2, "dsdcolumn")));
		join2.getJoinOn().addQuerys(new QueryItem(new TableColumn(joinTable1, "dsdcolumn2"), QueryOpersign.EQUAL,  new TableColumn(joinTable2, "dsdcolumn2")));
		join2.getJoinOn().addQuerys(new QueryItem(new TableColumn(masterTable, "dsdcolumn3"), QueryOpersign.EQUAL,  new TableColumn(joinTable2, "dsdcolumn3")));
		from.addJoin(join2);
		
		QueryTable joinTable3 = new QueryTable("skdjfks", "k");
		Join join3 = new Join(joinTable2, joinTable3, JoinWay.LEFT);
		join3.getJoinOn().addQuerys(new QueryItem(new TableColumn(joinTable2, "kskcolumn"), QueryOpersign.EQUAL,  new TableColumn(joinTable3, "kskcolumn")));
		from.addJoin(join3);
		
		QueryTable joinTable4 = new QueryTable("Takdj", "k");
		Join join4 = new Join(joinTable1, joinTable4, JoinWay.LEFT);
		join4.getJoinOn().addQuerys(new QueryItem(new TableColumn(joinTable1, "column"), QueryOpersign.EQUAL,  new TableColumn(joinTable4, "column")));
		from.addJoin(join4);
		
		
		
		StringBuilder buf = new StringBuilder();
		from.addSQL(buf);
		System.out.println(buf.toString());
	}
}
