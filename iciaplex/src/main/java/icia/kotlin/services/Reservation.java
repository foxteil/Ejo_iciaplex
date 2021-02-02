package icia.kotlin.services;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.omg.PortableInterceptor.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.zaxxer.hikari.util.ConcurrentBag;

import icia.kotlin.beans.Count;
import icia.kotlin.beans.Movie;
import icia.kotlin.mapper.MapperIF;
import icia.kotlin.mapper.ReservationIF;

@Service
public class Reservation {
	ModelAndView mav = null;
		
		@Autowired
		private MapperIF map;
		@Autowired
		private ReservationIF reserve;
		@Autowired
		private PlatformTransactionManager tran;
		
		
		public ModelAndView entrance(Movie mv) {
						
			
			switch (mv.getSCode()) {
			case "H":
				//mav=LogInCtl(m);
				
				mav = MovieListCtl();
				break;
			
			case "S2":
				//mav=LogInCtl(m);
				
				mav=Step2(mv);
				break;
				
			case "S3":
				//mav=LogInCtl(m);
				
				mav=Step3(mv);
				break;
			
			default :
				
				break;
			}

			return mav;
			
		}
		private ModelAndView Step3(Movie mv) {
			System.out.println(mv);
			System.out.println(mv.getMvCode());
			
			System.out.println(mv.getMvDate());
			return null;
		}
		private ModelAndView Step2(Movie mv) {
			System.out.println(mv.getMvCode());
			String poster = new String();
			
			poster=makePoster(reserve.getMovieList2(mv), this.nextDate());
			
			String date = makeDate(this.nextDate());		
		
		
			mav.addObject("poster",poster);
			
			mav.setViewName("date");
			return mav;
		}
		 private ArrayList<String> nextDate() throws ParseException {
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd:E요일");
			  ArrayList<String> al= new ArrayList<>();
			  Calendar c = Calendar.getInstance ( );
			  
			  al.add(0,sdf.format(c.getTime()));
			  for (int i=1;i<7;i++) {
			  c.add(Calendar.DATE, 1);
			  al.add(i, sdf.format(c.getTime()));
			  }
			   return al;
			 }

		

		private String makeDate(ArrayList<String> al) {
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<7;i++) {
			sb.append("<input type=\"button\" value=\""+al.get(i)+"\"  class=\"test\" onClick=\"DateClick(this)\">");
			
			}
			
			return sb.toString();
		}
		private String makePoster(ArrayList<Movie> movieList2, ArrayList<String> al) {
			StringBuffer sb = new StringBuffer();
			
			sb.append("<div> ");
			sb.append("<div style=\"float: left\" >");
			sb.append("<img src=resources/img/"+movieList2.get(0).getMvImage()+" style=\"width:30%\">");
			sb.append("</div>");
			sb.append("<div style=\"float: left \" >");
			sb.append(makeDate(al));
			sb.append("</div>");
			sb.append("<br>");
			sb.append("<button>"+movieList2.get(0).getMvGrade()+"</button>");
			sb.append("<button>"+movieList2.get(0).getMvName()+"</button>");;
			sb.append("</n>");
			sb.append("</div>");
			sb.append("<input type=\"hidden\" name=\"mvcode\" value=\""+movieList2.get(0).getMvCode()+"\">");			
			return sb.toString();
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
		     }
		     sb.append(" </div>");
		   	 sb.append("</div>");	
		   	 sb.append("</div>");
		   	 
		   	 ///////////////////////////////////////////////////////////////
		   	 
		   	
		 	 for(i=1;i<movieList.size();i++) {
		    	 if(i%3==1) {
		     sb.append("<div class=\"mySlides2 fade\" >");
		     
		     sb.append("<img src=resources/img/"+movieList.get(i).getMvImage()+" style=\"width:30%\" onClick=\"movieClick("+movieList.get(i).getMvCode()+")\" >");
		     sb.append("</div>");
		    	 }
		     }
		     sb.append("</div>");
		     sb.append("<div style=\"text-align:auto\">");
		     for(i=1;i<movieList.size();i++) {
			    	 if(i%3==1) {
	         sb.append("<span class=\"dot2\"></span> ");
			    	 }
		     }
		     sb.append(" </div>");
		   	 sb.append("</div>");	
		   	 
		   	 //////////////////////////////////////////////////////////////////
		   	 
		   	
		 	 for(i=2;i<movieList.size();i++) {
		    	 if(i%3==2) {
		     sb.append("<div class=\"mySlides3 fade\" >");
		    
		     sb.append("<img src=resources/img/"+movieList.get(i).getMvImage()+" style=\"width:30%\" onClick=\"movieClick("+movieList.get(i).getMvCode()+")\" >");
		     sb.append("</div>");
		    	 }
		     }
		     sb.append("</div>");
		     sb.append("<div style=\"text-align:auto\">");
		     for(i=2;i<movieList.size();i++) {
			    	 if(i%3==2) {
	         sb.append("<span class=\"dot3\"></span> ");
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
