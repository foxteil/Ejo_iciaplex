package icia.kotlin.services;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.plex.MapperIF;
import icia.kotlin.beans.Movie;
import lombok.Setter;

@Service
public class Authentication {

	@Autowired
	private MapperIF mapper;
	@Autowired
	private PlatformTransactionManager tran;
	
	public Authentication() {}
	
	public ModelAndView entrance(Member m) {
		ModelAndView mav = null;
		 
		switch(m.getServiceCode()) {
		case "A" : 
			mav =  this.loginCtl(m);
			break;
		}
		
		return mav;
	}
	
	private ModelAndView loginCtl(Member m) {
		ModelAndView mav;
		
		TransactionStatus status = tran.getTransaction(new DefaultTransactionDefinition());
		//commit, rollback 처리를 위한 상태값 = 트랜젝션 연결
		
		mav = new ModelAndView();
		try {
		if(this.isMember(m)) {
			if(this.isAccess(m)) {
			    mav.addObject("member", this.getMemberInfo(m));
				/*transaction 처리를 위한 메서드 1 : st insert*/
			    m.setMId("tran3");
			    m.setMPwd("1234");
			    m.setMName("트랜");
			    m.setMPhone("01087952");
			    
			    this.insCustomer(m);
				/*transaction 처리를 위한 메서드 2 : mv insert*/
			    Movie movie = new Movie();
			    movie.setMvCode("19020103");
			    movie.setMvName("난리난리난리나");
			    movie.setMvGrade("C");
			    movie.setMvStatus("A");
			    movie.setMvImage("19020103L.jpg");
			    movie.setMvComments("세기의 대결이 시작된다");
			    this.insMovie(movie);
		
			    	tran.commit(status);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			 System.out.println("롤백");
			tran.rollback(status);
		}
		
		mav.setViewName("loginForm");
		return mav;
	}

	

	private Member getMemberInfo(Member member) {
		return mapper.getMemberInfo(member);
		
	}

	private boolean convertToBoolean(int value) {
		return value==1? true:false;
	}
	
	/*Member 여부 확인 */
	private boolean isMember(Member member) {
		return convertToBoolean(mapper.isMember(member));
	}
	
	/*Access 가능 여부 : 패스워드 일치 여부 */
	private boolean isAccess(Member member) {
		return convertToBoolean(mapper.isAccess(member));
	}
	
	/*Spring Framework 에서의 Transaction 
	 * 1.transactional 을 이용한 transaction - private 환경에서는 불가, public 환경에서만 구동
	 * 2.AOP 이용한 Transaction
	 * 3.Programmatic Transaction
	 * */
	
	/*transaction 처리를 위한 메서드 1 : st insert*/
    private int insCustomer(Member member) {
    	return mapper.insCustomer(member);
    }
	/*transaction 처리를 위한 메서드 2 : mv insert*/
   
    private int insMovie(Movie movie) {
    	return mapper.insMovie(movie);
		
	}
}
