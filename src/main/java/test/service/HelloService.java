package test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.dao.UserMapper;
import test.entity.UserInfo;

@Service
public class HelloService {

	public String sayHello(String msg){
		return "Hello, "+ msg +"!";
	}
	@Autowired
	private UserMapper userMapper;
	
	 public void insert(UserInfo info){
		 userMapper.insert(info);
	    }

	    public List<UserInfo> select(){
	        return userMapper.selectAll();
	    }
}
