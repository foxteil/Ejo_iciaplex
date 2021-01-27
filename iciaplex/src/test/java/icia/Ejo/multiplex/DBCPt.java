package icia.Ejo.multiplex;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DBCPt {
	@Setter(onMethod_= {@Autowired})
	private DataSource dbcp;
	
	@Setter(onMethod_= {@Autowired})
	private SqlSessionFactory sqlS;
	
	@Setter(onMethod_= {@Autowired})
	private mapperA mapper;
	
	@Test	
	public void getConnectionTest() {
		Connection connect;
		try {
			SqlSession session = sqlS.openSession();
			connect = dbcp.getConnection();
			log.info(session);
			log.info(connect);
			log.info(mapper.getDate());
			log.info(mapper.getDate2());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
