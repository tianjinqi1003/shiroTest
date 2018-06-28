package test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import test.entity.UserInfo;

public interface UserMapper {
	List<UserInfo> selectAll(); //查询接口，非注解方式

	@Insert({"insert into info values(#{name}, #{age})"})
	int insert(UserInfo info); //插入接口，注解方式

}
