package icia.kotlin.plex;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, ModelAndView mv) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		mv.addObject("welcome","스프링에 온것을 환영하다");
		mv.addObject("serverTime", formattedDate );
		
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping(value = "/LoginForm", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView logInForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public ModelAndView logIn(@ModelAttribute Member m,@RequestParam("memberInfo") String[] member,
			@RequestParam("mPwd") String pwd) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("mId",m.getMId());
		mav.addObject("mPwd",m.getMPwd());
		mav.addObject("memberId",member[0]);
		mav.addObject("memberPw",member[1]);
		
		mav.setViewName("loginForm");
		return mav;
	}
}