package com.test;

import com.single.app.component.metadate.ViewStructure;

public class Test {

	public static void main(String[] args) {
		ViewStructure[] vss = ViewStructure.values();
		for(ViewStructure vs : vss){
			System.out.println(vs.name());
		}
		ViewStructure vs = ViewStructure.valueOf("MasterSlave");
		System.out.println(vs.name());
		vs = ViewStructure.valueOf("List");
		System.out.println(vs.name());
	}
}
