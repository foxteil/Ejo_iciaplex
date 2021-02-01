package icia.kotlin.mapper;

import icia.kotlin.beans.Member;
import icia.kotlin.beans.Movie;

public interface MapperIF {
	public String getDate();	
	public int isMember(Member m);
	public int isAccess(Member m);
	public Member isMInfo(Member m);
	public int insCustomer(Member member);
	public int insMovie(Movie mv);
}
