package test.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;

import test.entity.UserInfo;
import test.service.HelloService;

@Controller
public class HelloController {

	@Autowired
	private HelloService helloService;

	@RequestMapping("/hello")
	public ModelAndView hello(String msg) {
		ModelAndView model = new ModelAndView("hello");
		String hello = helloService.sayHello(msg);
		model.addObject("test",hello);
		System.out.println(model);
		return model;
	}
	/**
	 * 由传入参数插入数据到Info表，然后查询表中记录条数在页面上显示 
	 */
	@RequestMapping("/info")
	public ModelAndView info(UserInfo info){
		ModelAndView model = new ModelAndView("info");
		helloService.insert(info);
		List<UserInfo> list = helloService.select();
		model.addObject("list", list);
		return model;
	}
	@RequestMapping("/index")
	public ModelAndView getIndex(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	//跳转到登录页面
	@RequestMapping("/login")
	public ModelAndView login() throws Exception {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	/** 
	 * 验证用户名和密码 
	 * @param String username,String password
	 * @return 
	 */  
	@RequestMapping(value="/checkLogin",method=RequestMethod.POST)  
	@ResponseBody  
	public String checkLogin(String username,String password) {  
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);  
			Subject currentUser = SecurityUtils.getSubject();  
			if (!currentUser.isAuthenticated()){
				//使用shiro来验证  
				token.setRememberMe(true);  
				currentUser.login(token);//验证角色和权限  
			} 
		}catch(Exception ex){
//			throw new BusinessException(LuoErrorCode.LOGIN_VERIFY_FAILURE);
		}
		result.put("success", true);
		return JSONUtils.toJSONString(result);  
	}  
	  /** 
     * 退出登录
     */  
    @RequestMapping(value="/logout",method=RequestMethod.POST)    
    @ResponseBody    
    public String logout() {   
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        Subject currentUser = SecurityUtils.getSubject();       
        currentUser.logout();    
        return JSONUtils.toJSONString(result);
    }  
    @RequestMapping("/newPageNotAdd")
    public ModelAndView newPageNotAdd() throws Exception {
        ModelAndView mav = new ModelAndView("newPageNotAdd");
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("newPage")){
           //有权限
        	System.out.println("有");
        }else{
           // 无权限
        	System.out.println("没有");
        }
        return mav;
    }

}
