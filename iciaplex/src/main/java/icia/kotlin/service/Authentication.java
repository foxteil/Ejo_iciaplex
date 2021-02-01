package icia.kotlin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.beans.Movie;
import icia.kotlin.plex.MapperIF;


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
			mav = this.loginCtl(m);
			break;
		}
		
		return mav;
		
	}


	private ModelAndView loginCtl(Member m) {
		ModelAndView mav = null;
		
		TransactionStatus status = tran.getTransaction(new DefaultTransactionDefinition());
		
		mav = new ModelAndView(); 
		try {
		if(this.ismember(m)) {
			if(this.isAccess(m)) {
				mav.addObject("member", this.getMemberInfo(m));
				/*transaction 처리를 위한 메서드1 : st insert*/
				m.setMId("CMA1");
				m.setMPwd("1234");
				m.setMName("췍췍2");
				m.setMPhone("01022342200");
				this.insCustomer(m);
				
				/*transaction 처리를 위한 메서드2 : mv insert*/
				Movie movie= new Movie();
				movie.setMvCode("30051239");
				movie.setMvName("ㅇㅎ우");
				movie.setMvGrade("C");
				movie.setMvStatus("P");
				movie.setMvImage("09062704L.jsp");
				movie.setMvComments("테스트이옵니다 ");
				this.insMovie(movie);
				tran.commit(status);
			}
			
		}
		
	}catch(Exception e) {
		e.printStackTrace();
		tran.rollback(status);
			
	}
	
		mav.setViewName("loginForm");
	
		return mav;
		
	}
	private boolean convertToBoolean(int value) {
		return value==1? true: false;
		
	}
	/* member 여부 확인*/
	private boolean ismember(Member member) {
		return convertToBoolean(mapper.isMember(member));
		
	}
	
	/* 액세스 기능 여부 : 패스워드 일치 여부 */
	private boolean isAccess(Member member) {
		return convertToBoolean(mapper.isAccess(member));
	}
	
	/* 회원정보 가져오기*/
	private Member getMemberInfo(Member member) {
		return mapper.getMemberInfo(member);
		
	}
	
	/*SPRING FRAMWORK 에서의 TRANSACTION
	 * 1. @Transactional을이용한 Transaction
	 * 2. AOP이용한 Transaction 설계기반
	 * 3. Programmatic Transaction 
	 * */
	
	/*transaction 처리를 위한 메서드1 : st insert*/
	private int insCustomer(Member member) {
		return mapper.insCustomer(member);
	}
	
	/*transaction 처리를 위한 메서드2 : mv insert*/
	private int insMovie(Movie movie) {
		return mapper.insMovie(movie);
	}


}
