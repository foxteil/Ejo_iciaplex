package icia.kotlin.spring;

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
public class DBCPtester {
	@Setter(onMethod_ = {@Autowired})
	private DataSource data;
	
	@Setter(onMethod_ = {@Autowired})
	private SqlSessionFactory sqlSession;
	
	@Setter(onMethod_ = {@Autowired})
	private MapperIF mapper;
	
	@Test
	public void connectTest() {
		Connection connect;
		try {
			SqlSession session = sqlSession.openSession();
			connect = data.getConnection();
			log.info(session);
			log.info(connect);
			log.info(mapper.getDate());
			log.info(mapper.getDate2());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
				
	}

}
