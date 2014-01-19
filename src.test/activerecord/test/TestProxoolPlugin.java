package activerecord.test;


import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.spring.SpringPlugin;
import com.single.db.TableManager;
import com.single.jfianl.dbmodle.BM;
import com.single.jfianl.plugin.proxool.ProxoolPlugin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/applicationContext-*.xml"})
public class TestProxoolPlugin {

	@Autowired
	private DataSource dataSource;
	
	/**
	*/
	
	@Test
	public void test(){
		ProxoolPlugin proxoolPlugin = new ProxoolPlugin();
		proxoolPlugin.setDataSource((ProxoolDataSource) dataSource);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(proxoolPlugin);
		arp.setDialect(new OracleDialect());
		arp.setContainerFactory(new CaseInsensitiveContainerFactory());
		TableManager.configActiveRecordMapping(arp);
		arp.start();
		List<BM> bmList = BM.dao.find("select * from bm");
		int i=0;
		try {
			for(BM bm:bmList){
				System.out.println(bm.get("deptid"));
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new BM().set("mc", "James").save();
		
		
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
