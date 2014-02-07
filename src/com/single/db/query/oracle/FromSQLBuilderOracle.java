package com.single.db.query.oracle;

import java.util.Iterator;
import java.util.Map.Entry;

import com.single.db.query.From;
import com.single.db.query.Join;
import com.single.db.query.JoinWay;
import com.single.db.query.QueryTable;
import com.single.db.query.parse.FromSQLBuilder;
import com.single.db.query.parse.JoinContext;

public class FromSQLBuilderOracle implements FromSQLBuilder {

	@Override
	public String toSQLString(From from) {
		StringBuilder buf = new StringBuilder();
		buf.append(" FROM ").append(from.getMasterTable().getTableName()).append(" ")
			.append(from.getMasterTable().getAlias());
		JoinContext context = from.takeJoinContext();
		addJoinSQL(context, buf);
		return buf.toString();
	}

	
	private void addJoinSQL(JoinContext context, StringBuilder buf){
		Iterator<Entry<Join, JoinContext>> entryIter = context.getIteratorEntrys();
		while(entryIter.hasNext()){
			Entry<Join, JoinContext> entry = entryIter.next();
			Join join = entry.getKey();
			QueryTable referenceTable = join.getReferenceTable();
			JoinWay type = join.getJoinWay();
			switch(type)
            {
                case LEFT:  buf.append(" LEFT JOIN ");break;
                case INNER: buf.append(" INNER JOIN ");break;
                case RIGHT: buf.append(" RIGHT JOIN ");break;
                default:    buf.append(" JOIN "); // should not come here!
            }
			buf.append(referenceTable.getTableName()).append(" ").append(referenceTable.getAlias());
			Iterator<Entry<Join, JoinContext>> contextEntryIter = entry.getValue().getIteratorEntrys();
			if(contextEntryIter.hasNext()){
				addJoinSQL(entry.getValue(), buf);
			}
			join.getJoinOn().addSQL(buf);
		}
		
	}
	
	

}
