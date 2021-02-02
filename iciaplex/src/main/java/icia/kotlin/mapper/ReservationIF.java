package icia.kotlin.mapper;

import java.util.ArrayList;

import icia.kotlin.beans.Count;
import icia.kotlin.beans.Movie;


public interface ReservationIF {

	public ArrayList<Movie> getMovieList();
	
	public ArrayList<Movie> getMovieList2(Movie mv);

	public String getDate();

}
