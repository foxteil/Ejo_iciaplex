package icia.kotlin.spring;

import org.apache.ibatis.annotations.Select;

public interface MapperIF {
	@Select("SELECT SYSDATE FROM DUAL")
	public String getDate();
	
	public String getDate2();
}
