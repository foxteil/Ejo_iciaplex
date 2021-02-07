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
		@Autowired
		private Gson gson;
		
		public ModelAndView entrance(Movie mv) {
						
			System.out.println(mv);
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
			case "S3a":
				//mav=LogInCtl(m);
				
				mav=Step3a(mv);
				break;
			case "S4":
				//mav=LogInCtl(m);
				
				mav=Step4(mv);
				break;
			
			default :
				
				break;
			}

			return mav;
			
		}
		private ModelAndView Step3a(Movie mv) {
			String screen = new String();
			System.out.println(mv);
			
			mav.addObject("Access",this.getCurrentDate('f'));
			mav.addObject("screen",screen);
			
			System.out.println("in Step3"+screen);
			
			mav.setViewName("step4");
			
			return mav;
		}
		private ModelAndView Step4(Movie mv) {
			String step = null ;
			
			String date = getCurrentDate('f');
						
			mav.addObject("SeatInfo",gson.toJson(reserve.getSeat(mv)));
			mav.addObject("Access",date);
			
			step="step4";
			
			mav.setViewName(step);
			
			return mav;
		}
		
		private ModelAndView Step3(Movie mv) {
			String screen = new String();
			String add = new String();
			add = mv.getMvDate();
			add += "%";
			mv.setMvDate(add);
			System.out.println(mv);
		
				String jsonData = gson.toJson(reserve.getScreen(mv));
				System.out.println(jsonData);
				mav.addObject("screen",jsonData);
			
			
			mav.setViewName("screen");
			
		
			return mav;
		}

		private ModelAndView Step2(Movie mv) {

			if (mv.getICode().equals("b")) {
				String poster = new String();
				poster = makePoster(reserve.getMovieList2(mv), this.nextDate());
				// String date = makeDate(this.nextDate());
				
				mav.addObject("poster", poster);
				mav.setViewName("date");

			} else {

				/* Start Date */
				mav.addObject("Access", this.getCurrentDate('d'));

				/* Movie Info & Convert to Json */
				String jsonData = gson.toJson(reserve.getMovieList2(mv));
				System.out.println(jsonData);
				mav.addObject("movieData", jsonData);

				/* view */
				mav.setViewName("step2");

			}

			return mav;
		}
		 private ArrayList<String> nextDate() {
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
			sb.append("<section>");
			sb.append(makeDate(al));
			sb.append("</section>");
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
			mav.addObject("Access",sdf.format(date));
			
			Gson gson = new Gson(); 
			String jsonData =gson.toJson(reserve.getMovieList());
			mav.addObject("jsonData",jsonData);
			
			//System.out.println(jsonData);
					
			movieList=makeHtml(reserve.getMovieList());
			mav.addObject("movieL",movieList);
			mav.setViewName("home");
			
			return mav;
		}

		private String makeHtml(ArrayList<Movie> movieList) {
			StringBuffer sb = new StringBuffer();
			System.out.println("makeHtml진입");
			 int i = 0;
			 		 
			 sb.append("<div class=\"slideshow-container\">");
	          
	           for(i=0;i<movieList.size();i++) {
	              if(i%3==0) {sb.append("<div class=\"mySlides fade\" name=\"mySlides\">");}
	           sb.append("<div class=\"poster\">");
	           sb.append("<div class=\"comment\">"+"영화제목 :"+ movieList.get(i).getMvName()+"<br/>"
	                                      +"코멘트 :"+movieList.get(i).getMvComments()+"<br/>"
	                                      +"코멘트 :"+movieList.get(i).getMvComments()+"<br/>"
	                                      +"상영중 :"+movieList.get(i).getMvStatus()+ "<br/>"
	                                      +"등급 : "+movieList.get(i).getMvGrade()+ "</div>");
	           sb.append("<img src=resources/img/"+movieList.get(i).getMvImage()+"  onClick=\"movieClick("+movieList.get(i).getMvCode()+")\" >");
	           sb.append("</div>");
	           
	           if(i%3 == 2) { sb.append("</div>");}
	            }
	         if(i%3 !=2) {sb.append("</div>"); }
	         
	           sb.append("</div></div>");
	           sb.append("<div class=\"dots\">");
	           for(i=0;i<movieList.size();i++) {
	              //if(i%3==0) {
	              sb.append("<span class=\"dot\" onclick=\"currentSlide("+i+")\"></span> ");
	              //}
	           }
	           sb.append("</div>");
	           sb.append("<a class=\"prev\" onclick=\"plusSlides(-1)\">&#10094;</a>");
	           sb.append("<a class=\"next\" onclick=\"plusSlides(1)\">&#10095;</a>");
	           
	          sb.append("</div>");
		   	 

		   	 
			return sb.toString();
		}


		private String getCurrentDate(char dateType) {
			Date date = new Date();
			
			SimpleDateFormat sdf = (dateType == 'f')? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일"):
				(dateType=='d')? new SimpleDateFormat("yyyy-MM-dd"):
						(dateType=='t')? new SimpleDateFormat("HH:mm E요일"): null;
						
		return sdf.format(date);
		}
		

//		private ArrayList<Movie> getMovieList() {
//			
//			return reserve.getMovieList();
//		}


}
