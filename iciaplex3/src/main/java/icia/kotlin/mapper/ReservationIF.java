package icia.kotlin.mapper;

import java.util.ArrayList;

import icia.kotlin.beans.Count;
import icia.kotlin.beans.Movie;
import icia.kotlin.beans.Seat;


public interface ReservationIF {

	public ArrayList<Movie> getMovieList();
	
	public ArrayList<Movie> getMovieList2(Movie mv);

	public String getDate();
	
	public ArrayList<Movie> getScreen(Movie mv);
	
	public ArrayList<Seat> getSeat(Movie mv);

}
