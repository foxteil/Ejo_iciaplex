package icia.kotlin.plex;

import org.apache.ibatis.annotations.Select;

public interface MapperIF {
	
	public String getDate2();

	
	public int isMember(Member m);
	
	public int isAccess(Member m);


	public Member isMInfo(Member m);

}
