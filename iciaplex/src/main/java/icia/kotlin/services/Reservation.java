package icia.kotlin.services;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Movie;
import icia.kotlin.mapper.MapperIF;
import icia.kotlin.mapper.ReservationIF;

@Service
public class Reservation {
	
		@Autowired
		private ReservationIF reserve;
		@Autowired
		private PlatformTransactionManager tran;
		
		ModelAndView mav = new ModelAndView();
		public ModelAndView entrance(Movie movie) {
						
			if(movie.getMvCode() == null) {
				mav = MovieListCtl();
			}
						
			return mav;
			
		}

		private ModelAndView MovieListCtl() {
			ModelAndView mav = new ModelAndView();
			ArrayList<Movie> movieList = null;
			
			/* AccessTime*/
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss E요일");
			mav.addObject("Access",sdf.format(date));
			
			System.out.println(reserve.getMovieList().size());
			//movieList=this.getMovieList();
			
			mav.setViewName("home");
			
			
			
			return mav;
		}

//		private ArrayList<Movie> getMovieList() {
//			
//			return reserve.getMovieList();
//		}


}
