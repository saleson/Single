package com.single.db.query.parse;

import com.single.db.query.From;

public interface FromSQLBuilder extends SQLBuilder{

	public String toSQLString(From from);
}
