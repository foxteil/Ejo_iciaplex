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
			String movieList = new String();			
			
			
			movieList=makeHtml(reserve.getMovieList());
			
			mav.addObject("movieL",movieList);
			mav.setViewName("home");
			
			System.out.println(movieList);
					
			return mav;
		}

		private String makeHtml(ArrayList<Movie> movieList) {
			StringBuffer sb = new StringBuffer();
			System.out.println("makeHtml진입");
			 int i = 0;
			 
			
			 sb.append("<div class=\"slideshow-container\">");
		 
			 for(i=0;i<movieList.size();i++) {
		    	 if(i%3==0) {
		     sb.append("<div class=\"mySlides fade\" name=\"mySlides\">");
		     sb.append("<div class=\"numbertext\">"+i+"</div>");
		     sb.append("<img src=resources/img/"+movieList.get(i).getMvImage()+" style=\"width:30%\" onClick=\"movieClick("+movieList.get(i).getMvCode()+")\" >");
		     sb.append("</div>");
		    	 }
		    	 if(i%3==1) {
				     sb.append("<div class=\"mySlides fade\" name=\"mySlides\">");
				     sb.append("<div class=\"numbertext\">"+i+"</div>");
				     sb.append("<img src=resources/img/"+movieList.get(i).getMvImage()+" style=\"width:30%\" onClick=\"movieClick("+movieList.get(i).getMvCode()+")\" >");
				     sb.append("</div>");
				    	 }
		    	 if(i%3==2) {
				     sb.append("<div class=\"mySlides fade\" name=\"mySlides\">");
				     sb.append("<div class=\"numbertext\">"+i+"</div>");
				     sb.append("<img src=resources/img/"+movieList.get(i).getMvImage()+" style=\"width:30%\" onClick=\"movieClick("+movieList.get(i).getMvCode()+")\" >");
				     sb.append("</div>");
				    	 }
		    	 
		     }
		     sb.append("</div>");
		     
		     sb.append("<div style=\"text-align:auto\">");
		     for(i=0;i<movieList.size();i++) {
			    	 if(i%3==0) {
		      sb.append("<span class=\"dot\"></span> ");
		      }
			       	 if(i%3==1) {
					      sb.append("<span class=\"dot\"></span> ");
					      }
			       	 if(i%3==2) {
					      sb.append("<span class=\"dot\"></span> ");
					      }
			    	 
			    	 
		     }
		     sb.append(" </div>");
		   	 sb.append("</div>");	
		   	 
		  
		   	 sb.append("</div>");
			return sb.toString();
		}



//		private ArrayList<Movie> getMovieList() {
//			
//			return reserve.getMovieList();
//		}


}
