package icia.Ejo.multiplex;

import org.apache.ibatis.annotations.Select;

public interface mapperA {

	@Select("SELECT SYSDATE FROM DUAL")
	public String getDate();
	
	public String getDate2();
}
