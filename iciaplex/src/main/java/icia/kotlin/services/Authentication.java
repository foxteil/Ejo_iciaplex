package icia.kotlin.services;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.DBCP;
import icia.kotlin.plex.MapperIF;
import icia.kotlin.plex.Member;
import icia.kotlin.plex.MemberLS;
import lombok.Setter;

@Service
public class Authentication {
	ModelAndView mav = null;
	
	@Autowired
	private MapperIF mapper;

	
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
			MemberLS ml = new MemberLS();
		
		if(this.isMember(m)) {
			if(this.isAccess(m)) {
				System.out.println("진입 성공");
				
								
				mav.addObject("mId",m.getMId());
				mav.addObject("mPwd", m.getMPwd());
				mav.addObject("mInfId", mapper.isMInfo(m).getMId());
				mav.addObject("mInfNm", mapper.isMInfo(m).getMName());
				mav.addObject("mInfPw", mapper.isMInfo(m).getMPwd());
				mav.addObject("mInfPh", mapper.isMInfo(m).getMPhone());
//				${mInfId}<br />
//				${mInfNm}<br />
//				${mInfPw}<br />
//				${mInfPh}<br />
			}
		}

		mav.setViewName("loginForm");
						
		return mav;
	}



	private boolean isAccess(Member m) {
		
		return convert(mapper.isAccess(m));
	}

	private boolean isMember(Member m) {
		
		return convert(mapper.isMember(m));
	}
	
	

	
	
	private boolean convert(int i) {
		
		
		return (i !=0 )? true:false;
	}
	
}
