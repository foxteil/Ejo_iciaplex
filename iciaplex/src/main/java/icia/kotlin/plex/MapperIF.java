package icia.kotlin.plex;

import icia.kotlin.beans.Member;

public interface MapperIF {

	
	public int isMember(Member member);
	public int isAccess(Member member);
	public Member getMemberInfo(Member member);



}
