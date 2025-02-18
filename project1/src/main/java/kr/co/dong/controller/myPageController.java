package kr.co.dong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.dong.project.usercontroller.UserController;

@Controller
public class myPageController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/myEdit", method = RequestMethod.GET)
	public String myEdit() {
		
		return "myEdit";
	}
	
	@RequestMapping(value="/orderList", method = RequestMethod.GET)
	public String orderList() {
		
		return "orderList";
	}
	
	@RequestMapping(value="/orderDetail", method = RequestMethod.GET)
	public String orderDetail() {
		
		return "orderDetail";
	}
	
	@RequestMapping(value="/CustomerService", method = RequestMethod.GET)
	public String CustomerService() {
		
		return "CustomerService";
	}
	
	@RequestMapping(value="/RecentlyView", method = RequestMethod.GET)
	public String RecentlyView() {
		
		return "RecentlyView";
	}
	
}
