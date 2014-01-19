package com.spring.db.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/applicationContext-*.xml"})
public class TestProxool {

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void test() {
			System.out.println(dataSource.toString());
			try {
				Connection conn = dataSource.getConnection();
				ResultSet rs1 =conn.createStatement().executeQuery("select * from kc_sfdj where rownum<20");
				while(rs1.next()){
					System.out.println("conn1:" + rs1.getString("sfdjdh"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		System.out.println("百喳");
		//fail("Not yet implemented");
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	

}
