package icia.kotlin.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;



import icia.kotlin.beans.Member;
import icia.kotlin.mapper.MapperIF;


@Service
public class Authentication {
	ModelAndView mav = null;
	
	@Autowired
	private MapperIF mapper;
	@Autowired
	private PlatformTransactionManager tran; //--> 가장 최신버전
	//데이터 타입이 완벽하게 일치하지 않기 때문에 root-context에서 썼던(transaction정보) 이름과 똑같은 이름으로 사용하여야 한다.
	
	
	public Authentication(){
		
	}
	
	public ModelAndView entrance(Member m) {
				
		switch (m.getSCode()) {
		case "A":
			mav=LogInCtl(m);
			break;

		default:
			break;
		}
		return mav;
	}
	

	
	private ModelAndView LogInCtl(Member m) {
			mav =new ModelAndView();
															//DefaultTransactionDefinition : 정의 방식
			TransactionStatus status = tran.getTransaction(new DefaultTransactionDefinition());
		//TransactionStatus = Transaction의 상태값을 리턴받는 녀석 --> 커밋과 롤백을 처리
		
		try {
		if(this.isMember(m)) {
			if(this.isAccess(m)) {
				System.out.println("로그인 까지 들어옴");
				mav.addObject("mInfId", this.getMember(m).getMId());
				mav.addObject("mInfNm", this.getMember(m).getMName());
				mav.addObject("mInfPw", this.getMember(m).getMPwd());
				mav.addObject("mInfPh", this.getMember(m).getMPhone());
				mav.addObject("member", this.getMember(m));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			tran.rollback(status);
			System.out.println("롤백 성공");
		}
		mav.setViewName("loginForm");
		return mav;
	}


	//엑세스 가능여부
	private boolean isAccess(Member m) {
		
		return convert(mapper.isAccess(m));
	}
	//Member여부 확인
	private boolean isMember(Member m) {
		
		return convert(mapper.isMember(m));
	}
	
	private Member getMember(Member m) {
		
		return (mapper.isMInfo(m));
	}
	
	private boolean convert(int i) {
		
		
		return (i !=0 )? true:false;
	}
		
	/* Spring Framework에서의 Transaction
	 * 1. @Transactional을 이용한 Transaction :: private에서는 사용불가
	 * 2. AOP를 이용한 Transaction :: AOP는 transaction을 위한 방식은 아님
	 * 3. Programmatic Transaction :: 원하는 위치에서 원할때 트랜잭션 가능  - 명시적 트랜잭션 (권장,실무에서많이 쓰임)
	 */
	 
	
}
