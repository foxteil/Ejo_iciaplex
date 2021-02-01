package icia.kotlin.plex;

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
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.beans.Movie;
import icia.kotlin.services.Authentication;
import icia.kotlin.services.Reservation;


@Controller
public class HomeController {
	@Autowired
	private Reservation reserve;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private Authentication auth;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute Movie movie) {
		
		ModelAndView mav = null;
			
		mav = reserve.entrance(movie);
		return mav;
	}
	
	@RequestMapping(value="/LogInform", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView logInForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");		
		
		return mav;
	}

	@RequestMapping(value="/LogIn", method= { RequestMethod.POST})
	public ModelAndView logIn(@ModelAttribute Member m) {
		ModelAndView mav =null;
		
		return mav=auth.entrance(m);
	}
	
	
	
}
