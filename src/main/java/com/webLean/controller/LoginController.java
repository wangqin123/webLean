package com.webLean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

	
	  @RequestMapping("/login.html")  
	    public ModelAndView toIndex(){  
	    	ModelAndView modelAndView = new ModelAndView("center/login");
	        return modelAndView;
	    }
}
