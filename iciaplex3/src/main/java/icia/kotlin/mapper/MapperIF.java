package icia.kotlin.mapper;

import java.util.ArrayList;

import icia.kotlin.beans.Member;
import icia.kotlin.beans.Movie;
import icia.kotlin.beans.Seat;

public interface MapperIF {
	public String getDate();	
	public int isMember(Member m);
	public int isAccess(Member m);
	public Member isMInfo(Member m);
	public int insCustomer(Member member);
	public int insMovie(Movie mv);

}
