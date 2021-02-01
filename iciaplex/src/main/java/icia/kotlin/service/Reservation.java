package icia.kotlin.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Movie;
import icia.kotlin.plex.ReservationIf;

@Service
public class Reservation {
	@Autowired
	private ReservationIf mapper;
	@Autowired
	private PlatformTransactionManager tran;
	
	public ModelAndView entrance(Movie movie) {
		
		ModelAndView mav = null;
		
		if(movie.getMvCode() == null) {
			mav= this.movieListCtl();
			
		}else {
			switch(movie.getMvCode()) {
			case"":
				
				break;
			}
		}
		return mav;
	}

	private ModelAndView movieListCtl() {
		ModelAndView mav = new ModelAndView();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss E요일");
		mav.addObject("Access", sdf.format(date));
		
		System.out.println(this.getMocieList().size());
		
		mav.setViewName("home");
		return mav;
	}
	
	private ArrayList<Movie> getMocieList(){
		return mapper.getMovieList();
	}

}
