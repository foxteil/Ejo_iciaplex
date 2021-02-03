package icia.kotlin.plex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.beans.Movie;
import icia.kotlin.services.Authentication;
import icia.kotlin.services.Reservation;


@Controller
public class HomeController {
	@Autowired
	private Reservation reserve;
	@Autowired
	private Authentication auth;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute Movie movie) {
		
		ModelAndView mav = null;
		movie.setSCode("H");	
		mav = reserve.entrance(movie);
		return mav;
	}
	
	@RequestMapping(value="/LogInform", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView logInForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");		
		
		return mav;
	}

	@RequestMapping(value="/Step2", method= { RequestMethod.POST})
	public ModelAndView Step2(@ModelAttribute Movie mv) {
		ModelAndView mav =null;
		mv.setSCode("S2");
		
		
		return mav=reserve.entrance(mv);
	}
	@RequestMapping(value="/Step3", method= { RequestMethod.POST})
	public ModelAndView Step3(@ModelAttribute Movie mv) throws UnsupportedEncodingException {
		ModelAndView mav =null;

		 mav = reserve.entrance(mv);
		 System.out.println(mav.getModel().get("screen"));
		 URLEncoder.encode(mav.getModel().get("screen").toString(), "UTF-8");
		 return mav;
	}
	
	
}
