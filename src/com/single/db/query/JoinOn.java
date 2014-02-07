package com.single.db.query;

public class JoinOn extends Where{

	@Override
	protected void appendConcatString(StringBuilder c) {
		c.append(" ON ");
	}
}
