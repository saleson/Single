package com.spring.db.test;

import static org.junit.Assert.*;

import org.apache.empire.db.oracle.DBDatabaseDriverOracle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/applicationContext-*.xml"})
public class TestEmpireDBQuery {

	@Test
	public void test() {
		DBDatabaseDriverOracle driver = new DBDatabaseDriverOracle();
		
		fail("Not yet implemented");
	}

}
