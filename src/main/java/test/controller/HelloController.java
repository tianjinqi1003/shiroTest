package test.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
