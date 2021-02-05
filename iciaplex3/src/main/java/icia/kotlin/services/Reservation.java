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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.zaxxer.hikari.util.ConcurrentBag;

import icia.kotlin.beans.Count;
import icia.kotlin.beans.Movie;
import icia.kotlin.beans.Seat;
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
		@Autowired
		private Gson gson;
		
		public ModelAndView entrance(Movie mv) {
						
			
			switch (mv.getSCode()) {
			case "H":
				mav = MovieListCtl();
				break;
			
			case "S2":
				mav=Step2(mv);
				break;
				
			case "S3":
				mav=Step3(mv);
				break;
			
			case "S4":
				mav=Step4(mv);
				break;
				
			
			default :
				
				break;
			}

			return mav;
			
		}
		 private ModelAndView Step4(Movie mv) {
			 ModelAndView mav = new ModelAndView();
			 System.out.println(mv);
			
			 mav.addObject("Access", this.getCurrentDate('f'));
			 mav.addObject("SeatInfo", gson.toJson(this.getSeat(mv)));
			 mav.setViewName("step4");
			return mav;
		}
		 
		 private ArrayList<Seat> getSeat(Movie mv){
			 System.out.println(reserve.getSeat(mv).size());
			 return reserve.getSeat(mv);
		 }
		 
		private ModelAndView Step3(Movie mv) {
	         String screen = new String();
	         String add = new String();
	       
	         add = mv.getMvDate();
	         add += "%";
	         mv.setMvDate(add);
	         
	         
//	               Json 방법
	          screen = gson.toJson(reserve.getScreen(mv));
	          System.out.println(screen);
	          
	      
	          
//	               우리가 원래 사용했던 방법           
	         //screen = makeScreen(reserve.getScreen(mv));
//	         System.out.println(reserve.getScreen(mv).get(0).getMvName());
//	         System.out.println(reserve.getScreen(mv).get(0).getMvGrade());
//	         System.out.println(reserve.getScreen(mv).get(0).getMvSCREEN());
//	         System.out.println(reserve.getScreen(mv).get(0).getDATIME());
//	         
	         mav.addObject("screen",screen);
	         
	         mav.setViewName("screen");
	      
	         return mav;
	      }
		 
		 //select seat
		 
		
		 
		private String makeScreen(ArrayList<Movie> sc) {
			StringBuffer sb = new StringBuffer();
			for(int i =0;i<sc.size();i++) {
				sb.append("<input type= \"text \" readOnly value=\" MVCODE:"+sc.get(i).getMvCode() +" \" > ");
				sb.append("<input type= \"text \" readOnly value=\" MVNAME:"+sc.get(i).getMvName() +" \" > ");
				sb.append("<input type= \"text \" readOnly value=\" MVGRADE:"+sc.get(i).getMvGrade() +" \" > ");
				sb.append("<input type= \"text \" readOnly value=\" MVSCREEN:"+sc.get(i).getMvSCREEN() +" \" > ");
				sb.append("<input type= \"text \" readOnly value=\" DATIME:"+sc.get(i).getDATIME()+" \" > ");
			}
			
			
			return sb.toString();
		}
		private ModelAndView Step2(Movie mv) {
	
			
			if(mv.getICode().equals("b")) {
				String poster = new String();
				poster=makePoster(reserve.getMovieList2(mv), this.nextDate());
				mav.addObject("poster",poster);
				
				mav.setViewName("date");
			}else {
				
			
			 /*Start Date */
            mav.addObject("Access",this.getCurrentDate('d'));
            
            /*Movie Info & Convert to Json*/
            String jsonData = gson.toJson(reserve.getMovieList2(mv));
            mav.addObject("movieData",jsonData);

            /*view*/
            mav.setViewName("step2");
			}
	
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
			
			sb.append("<div>");
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
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일");
			mav.addObject("Access", this.getCurrentDate('f'));
			
			movieList=makeHtml(reserve.getMovieList());
			
			Gson gson = new Gson(); 
	         String jsonData =gson.toJson(reserve.getMovieList());
	         
	         
	         mav.addObject("jsonData",jsonData);
			mav.addObject("movieL",movieList);
			mav.setViewName("home");
		
					
			return mav;
		}

		

		
		
		private String makeHtml(ArrayList<Movie> movieList) {
			 StringBuffer sb = new StringBuffer();
	          int i = 0;
	                 
	          sb.append("<div class=\"slideshow-container\">");
	          
	           for(i=0;i<movieList.size();i++) {
	          sb.append("<div class=\"mySlides fade\" name=\"mySlides\">");
	           sb.append("<img src=resources/img/"+movieList.get(i).getMvImage()+" style=\"width:300px\" onClick=\"movieClick("+movieList.get(i).getMvCode()+")\" >");
	           sb.append("</div>");
	          }
	           sb.append("</div>");
	           sb.append("</div>");   
	             sb.append("</div>");
		 
		   	 
//		   	 ///////////////////////////////////////////////////////////////
//		   	 
//		   	
//		 	 for(i=1;i<movieList.size();i++) {
//		    	 if(i%3==1) {
//		     sb.append("<div class=\"mySlides2 fade\" >");
//		     
//		     sb.append("<img src=resources/img/"+movieList.get(i).getMvImage()+" style=\"width:30%\" onClick=\"movieClick("+movieList.get(i).getMvCode()+")\" >");
//		     sb.append("</div>");
//		     
//		    	 }
//		     }
//		     sb.append("</div>");
//		     sb.append("<div style=\"text-align:auto\">");
//		     for(i=1;i<movieList.size();i++) {
//			    	 if(i%3==1) {
//	         sb.append("<span class=\"dot2\"></span> ");
//			    	 }
//		     }
//		     sb.append(" </div>");
//		   	 sb.append("</div>");	
//		  		
//		   	 
//		   	 //////////////////////////////////////////////////////////////////
//		   	 
//		   	
//		 	 for(i=2;i<movieList.size();i++) {
//		    	 if(i%3==2) {
//		     sb.append("<div class=\"mySlides3 fade\" >");
//		    
//		     sb.append("<img src=resources/img/"+movieList.get(i).getMvImage()+" style=\"width:30%\" onClick=\"movieClick("+movieList.get(i).getMvCode()+")\" >");
//		     sb.append("</div>");
//		    	 }
//		     }
//		     sb.append("</div>");
//		     sb.append("<div style=\"text-align:auto\">");
//		     for(i=2;i<movieList.size();i++) {
//			    	 if(i%3==2) {
//	         sb.append("<span class=\"dot3\"></span> ");
//			    	 }
//		     }
//		     sb.append(" </div>");
//		   	 sb.append("</div>");
//		   	 sb.append("</div>");
//		   	 
//		   	 
	   	 
			return sb.toString();
		}



		private ArrayList<Movie> getMovieList() {
			return reserve.getMovieList();
		}

//		current DataTime
		private String getCurrentDate(char dateType) {
			Date date = new Date();
			
			SimpleDateFormat sdf = (dateType=='f')? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일"):
				(dateType=='d')? new SimpleDateFormat("yyyy-MM-dd"):
					(dateType=='t')? new SimpleDateFormat("HH:mm:E요일") : null;
					
					
					return sdf.format(date);
		}
		
}
