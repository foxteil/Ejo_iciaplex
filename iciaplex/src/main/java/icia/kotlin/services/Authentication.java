package icia.kotlin.services;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.plex.MapperIF;
import lombok.Setter;

@Service
public class Authentication {


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


	@Autowired
	private MapperIF mapper;

	
	private ModelAndView loginCtl(Member m) {
		ModelAndView mav;
		
		mav = new ModelAndView();
		
		System.out.println("로그인 진입");
		if(this.isMember(m)) {
			System.out.println("아이디 확인");
			if(this.isAccess(m)) {
				System.out.println("로그인 성공");
			    mav.addObject("member", this.getMemberInfo(m));
			}
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
}
