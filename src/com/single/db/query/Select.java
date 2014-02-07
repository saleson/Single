package com.single.db.query;

import java.util.*;

public class Select extends QueryTable {
	public Collection<SelectColumn> selectColumn;
	private From from;
	private Where where;
	private Group group;
	private Having having;
	private Order order;


	/** @pdGenerated default getter */
	public Collection<SelectColumn> getSelectColumn() {
		if (selectColumn == null)
			selectColumn = new HashSet<SelectColumn>();
		return selectColumn;
	}

	/** @pdGenerated default iterator getter */
	public Iterator<SelectColumn> getIteratorSelectColumn() {
		if (selectColumn == null)
			selectColumn = new HashSet<SelectColumn>();
		return selectColumn.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newSelectColumn
	 */
	public void setSelectColumn(Collection<SelectColumn> newSelectColumn) {
		removeAllSelectColumn();
		for (Iterator<SelectColumn> iter = newSelectColumn.iterator(); iter.hasNext();)
			addSelectColumn((SelectColumn) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newSelectColumn
	 */
	public void addSelectColumn(SelectColumn newSelectColumn) {
		if (newSelectColumn == null)
			return;
		if (this.selectColumn == null)
			this.selectColumn = new HashSet<SelectColumn>();
		if (!this.selectColumn.contains(newSelectColumn))
			this.selectColumn.add(newSelectColumn);
	}

	/**
	 * @pdGenerated default remove
	 * @param oldSelectColumn
	 */
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