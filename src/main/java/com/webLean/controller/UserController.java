package com.webLean.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webLean.domain.userT;
import com.webLean.service.IUserService;
  
  
@Controller  
@RequestMapping("/center")  
public class UserController{  
    @Autowired  
    private IUserService userService; 
     
    @RequestMapping("/index.html")  
    public ModelAndView toIndex(){  
    	ModelAndView modelAndView = new ModelAndView("center/index");
        userT user = this.userService.getUserById(1);  
       modelAndView.addObject("user", user);
       // setAttr("user", user);
        return modelAndView;
    }
    
    
    @RequestMapping("/index2.html")  
    public ModelAndView toIndex2(){  
    	ModelAndView modelAndView = new ModelAndView("center/index2");
        userT user = this.userService.getUserById(1);  
      modelAndView.addObject("user", user);
        //setAttr("user", user);
        return modelAndView;
    }
}